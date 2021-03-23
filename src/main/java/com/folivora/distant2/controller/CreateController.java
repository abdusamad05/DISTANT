package com.folivora.distant2.controller;

import com.folivora.distant2.domain.Category;
import com.folivora.distant2.domain.Lesson;
import com.folivora.distant2.domain.User;
import com.folivora.distant2.repos.CategoryRepo;
import com.folivora.distant2.repos.LessonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@PreAuthorize("hasAuthority('TEACHER')")
public class CreateController {

        @Autowired
        private LessonRepo lessonRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    //Место хранения загруженных файлов
    @Value("${upload.path}")
    private String uploadPath;

    //Вывод списка статей
        @GetMapping("/main")
        public String main(@RequestParam (required = false, defaultValue = "") String filter, Model model) {
            Iterable<Lesson> lessons = lessonRepo.findAll();
            Iterable<Category> categories = categoryRepo.findAll();

            if (filter !=null && !filter.isEmpty()) {
                lessons = lessonRepo.findByLessonnameLike("%" + filter + "%");
            } else {
                lessons = lessonRepo.findAll();

            }

            model.addAttribute("lessons", lessons);
            model.addAttribute("filter", filter);
            model.addAttribute("categories", categories);

            return "main";
        }
    //Создание статей
    @PostMapping("/main")
        public String add(

                @AuthenticationPrincipal User user,
                @Valid Lesson lesson,
                BindingResult bindingResult,
                Model model,
                @RequestParam("file") MultipartFile file)
    throws IOException {
            lesson.setAuthor(user);


            if (bindingResult.hasErrors()){
                Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

                model.mergeAttributes(errorsMap);
                model.addAttribute("lesson", lesson);

            }else {
                if (file != null && !file.getOriginalFilename().isEmpty()) {

                    File uploadDir = new File(uploadPath);

                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }
                    String uuidFile = UUID.randomUUID().toString();
                    String resultFilename = uuidFile + "." + file.getOriginalFilename();

                    file.transferTo(new File(uploadPath + "/" + resultFilename));

                    lesson.setFilename(resultFilename);

                }

                lessonRepo.save(lesson);

            }

        Iterable<Lesson> lessons = lessonRepo.findAll();

        model.addAttribute("lessons", lessons);

        return "redirect:/";
    }

}
