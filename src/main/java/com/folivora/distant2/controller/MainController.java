package com.folivora.distant2.controller;


import com.folivora.distant2.domain.Lesson;
import com.folivora.distant2.repos.LessonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private LessonRepo lessonRepo;
    //Вывод списка статей
    @GetMapping("/")
    public String main(@RequestParam (required = false, defaultValue = "") String filter, Model model) {
        Iterable<Lesson> lessons = lessonRepo.findAll();
        if (filter !=null && !filter.isEmpty()) {
            lessons = lessonRepo.findByLessonnameLike("%" + filter + "%");
        } else {
            lessons = lessonRepo.findAll();
        }
        model.addAttribute("lessons", lessons);
        model.addAttribute("filter", filter);

        return "home";
    }
    //Вывод статьи
    @GetMapping("/lesson/{lessons}")
    public String lesson(@PathVariable Lesson lessons, Model model){

        model.addAttribute("lessons", lessons);

        return "lesson";
    }
}
