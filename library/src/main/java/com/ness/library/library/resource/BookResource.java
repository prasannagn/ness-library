package com.ness.library.library.resource;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.ness.library.api.model.Book;
import com.ness.library.api.model.Status;
import com.ness.library.library.controller.BookController;
import com.ness.library.library.controller.MemberController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class BookResource extends ResourceSupport {
    @JsonUnwrapped
    private Book book;

    public BookResource(Book book) {
        this.book = book;
        add(linkTo(methodOn(BookController.class).findOne(book.getId(), null)).withSelfRel());
        add(linkTo(methodOn(BookController.class).findOne(book.getId(), null)).slash("tags").withRel("tags"));
    }

    public BookResource(Book book, Integer memberId) {
        this.book = book;
        add(linkTo(methodOn(BookController.class).findOne(book.getId(), null)).withSelfRel());
        add(linkTo(methodOn(BookController.class).findOne(book.getId(), null)).slash("tags").withRel("tags"));
        if (null != memberId && book.getStatus() == Status.AVAILABLE) {
            add(linkTo(methodOn(MemberController.class).takeBook(memberId, book.getId())).withRel("take"));
        }
    }

    public Book getBook() {
        return book;
    }
}
