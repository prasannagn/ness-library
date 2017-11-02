package com.ness.library.library.service;

import com.ness.library.api.model.Book;
import com.ness.library.library.model.BookModel;
import com.ness.library.library.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BookServiceTest {

    @TestConfiguration
    static class BookServiceTestContextConfiguration {

        @Bean
        public BookService bookService() {
            return new BookService();
        }
    }

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository repository;

    @Before
    public void setUp() {
        BookModel book = new BookModel();
        book.setId(1);
        book.setTitle("Title");
        when(repository.findOne(1)).thenReturn(book);

        book = new BookModel();
        book.setId(1);
        book.setTitle("Test book");
        when(repository.save(eq(book))).thenReturn(book);


        book = new BookModel();
        book.setId(1);
        book.setTitle("Title");
        book.setDescription("Test description");
        when(repository.save(eq(book))).thenReturn(book);
    }

    @Test
    public void findOneTest() {
        Book book = bookService.findOne(1);
        assertThat(book.getId()).isEqualTo(1);
    }

    @Test
    public void saveTest() {
        Book b = new Book();
        b.setId(1);
        b.setTitle("Test book");
        Book savedBook = bookService.save(b);
        assertThat(savedBook.getId()).isEqualTo(1);
    }

    @Test
    public void updateTest() {
        Book b = new Book();
        b.setTitle("Test book");
        Book updatedBook = bookService.update(1, b);
        assertThat(updatedBook.getId()).isEqualTo(1);
        assertThat(updatedBook.getTitle()).isEqualTo("Test book");
    }

    @Test
    public void patchTest() {
        Book b = new Book();
        b.setDescription("Test description");
        Book updatedBook = bookService.patch(1, b);
        assertThat(updatedBook.getId()).isEqualTo(1);
        assertThat(updatedBook.getTitle()).isEqualTo("Title");
        assertThat(updatedBook.getDescription()).isEqualTo("Test description");
    }

}
