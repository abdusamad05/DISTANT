package com.folivora.distant2.domain;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Пожалуйста заполните это поле")
    private String text;
    @NotBlank(message = "Пожалуйста заполните это поле")
    @Length(max = 255, message = "название не должно превышать 255 символов" )
    private String lessonname;
    private String lessoncategory;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")

    private User author;

    private String filename;




    public Lesson() {
    }

    public Lesson(String text, String lessonname, String lessoncategory, User user) {
        this.author = user;
        this.text = text;
        this.lessonname = lessonname;
        this.lessoncategory = lessoncategory;
    }


    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLessonname() {
        return lessonname;
    }

    public void setLessonname(String lessonname) {
        this.lessonname = lessonname;
    }

    public String getLessoncategory() {
        return lessoncategory;
    }

    public void setLessoncategory(String lessoncategory) {
        this.lessoncategory = lessoncategory;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }





}
