package com.baeldung.jpa.primarykey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "article") 
public class Article { 

    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_gen") 
    @SequenceGenerator(name="article_gen", sequenceName="article_seq") 
    private Long id; 
    
    @Column(name = "title") 
    private String title; 
    
    @Column(name = "content") 
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}