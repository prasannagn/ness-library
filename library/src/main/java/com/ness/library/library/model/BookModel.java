package com.ness.library.library.model;


import com.ness.library.api.model.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    private String description;

    private Status status;

    private String imageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookModel bookModel = (BookModel) o;

        if (id != bookModel.id) return false;
        if (!title.equals(bookModel.title)) return false;
        if (description != null ? !description.equals(bookModel.description) : bookModel.description != null) return false;
        if (status != bookModel.status) return false;
        return imageUrl != null ? imageUrl.equals(bookModel.imageUrl) : bookModel.imageUrl == null;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public static void copy(BookModel source, BookModel target) {
        if (null != source.getImageUrl()) {
            target.setImageUrl(source.getImageUrl());
        }
        if (null != source.getDescription()) {
            target.setDescription(source.getDescription());
        }
        if (null != source.getStatus()) {
            target.setStatus(source.getStatus());
        }

        if (null != source.getTitle()) {
            target.setTitle(source.getTitle());
        }
    }
}