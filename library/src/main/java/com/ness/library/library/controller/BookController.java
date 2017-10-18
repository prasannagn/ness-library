package com.ness.library.library.controller;

import com.ness.library.api.model.Book;
import com.ness.library.library.resource.BookResource;
import com.ness.library.library.resource.PageResource;
import com.ness.library.library.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class BookController {
    @Resource
    private BookService bookService;

    //Create
    @RequestMapping(value = "/books", method = POST, produces = "application/hal+json")
    public ResponseEntity<BookResource> create(@RequestBody Book book) {
        return ResponseEntity.ok(new BookResource(bookService.save(book)));
    }

    //Update
    @RequestMapping(value = "/books/{id}", method = PUT, produces = "application/hal+json")
    public ResponseEntity<BookResource> update(@PathVariable("id") int bookId, @RequestBody Book book) {
        return ResponseEntity.ok(new BookResource(bookService.update(bookId, book)));
    }

    //Partial Update
    @RequestMapping(value = "/books/{id}", method = PATCH, produces = "application/hal+json")
    public ResponseEntity<BookResource> patch(@PathVariable("id") int bookId, @RequestBody Book book) {
        return ResponseEntity.ok(new BookResource(bookService.patch(bookId, book)));
    }

    //Read
    @RequestMapping(value = "/books", produces = "application/hal+json", method = GET)
    public ResponseEntity<PageResource<BookResource>> list(@PageableDefault(size = 1, page = 0) Pageable pageable, @RequestParam(required = false) Integer memberId) {
        Page<Book> books = bookService.findAll(pageable);
        Page<BookResource> booksResource = books.map(book -> new BookResource(book, memberId));
        return ResponseEntity.ok(new PageResource<BookResource>(booksResource, "page", "size"));
    }

    @RequestMapping(value = "/books/{id}", produces = "application/hal+json", method = GET)
    public ResponseEntity<BookResource> findOne(@PathVariable("id") int bookId, @RequestParam(required = false) Integer memberId) {
        return ResponseEntity.ok(new BookResource(bookService.findOne(bookId), memberId));
    }

    //Delete
    @RequestMapping(value = "/books/{id}", method = DELETE, produces = "application/hal+json")
    public HttpEntity<String> delete(@PathVariable("id") int bookId) {
        bookService.delete(bookId);
        return new HttpEntity<String>("Deleted");
    }
}
