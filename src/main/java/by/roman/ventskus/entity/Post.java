package by.roman.ventskus.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roman Ventskus on 20.12.2015.
 */
@javax.persistence.Entity
@Table(name = "post")
public class Post extends BaseEntity {

    @Column(name = "author")
    private String author;

    @Column(name = "text")
    private String text;

    @Column(name = "creation_date")
    private Date date;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
