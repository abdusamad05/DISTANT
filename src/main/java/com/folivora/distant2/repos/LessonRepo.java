package com.folivora.distant2.repos;

import com.folivora.distant2.domain.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LessonRepo extends CrudRepository<Lesson, Long> {

    List<Lesson> findByLessonnameLike(String lessonname);

}
