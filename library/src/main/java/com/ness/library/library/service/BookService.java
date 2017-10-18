package com.ness.library.library.service;

import com.ness.library.api.model.Book;
import com.ness.library.library.model.BookModel;
import com.ness.library.library.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;

public class BookService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private BookRepository repository;

    public Book findOne(int bookId) {
        return map(repository.findOne(bookId));
    }

    public Page<Book> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::map);
    }


    public Book save(Book book) {
        return map(repository.save(map(book)));
    }

    public Book update(int bookId, Book source) {
        BookModel target = repository.findOne(bookId);
        BeanUtils.copyProperties(source, target, "id");
        return map(repository.save(target));
    }

    public Book patch(int bookId, Book source) {
        Book target = findOne(bookId);
        Book.copy(source, target);
        return save(target);
    }

    public void delete(int bookId) {
        repository.delete(bookId);
    }

    private Book map(BookModel bookModel) {
        Book book = new Book();
        BeanUtils.copyProperties(bookModel, book);
        return book;
    }

    private BookModel map(Book book) {
        BookModel bookModel = new BookModel();
        BeanUtils.copyProperties(book, bookModel);
        return bookModel;
    }
}
