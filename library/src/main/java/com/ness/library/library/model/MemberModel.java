package com.ness.library.library.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class MemberModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToMany
    @JoinTable(name = "member_book", joinColumns = @JoinColumn(name = "member_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private List<BookModel> bookModels;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookModel> getBookModels() {
        return bookModels;
    }

    public void setBookModels(List<BookModel> bookModels) {
        this.bookModels = bookModels;
    }
}
