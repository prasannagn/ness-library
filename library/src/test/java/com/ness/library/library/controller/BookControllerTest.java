package com.ness.library.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ness.library.api.model.Book;
import com.ness.library.library.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@ContextConfiguration(classes = {TestConfig.class})
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService service;

    private ObjectMapper objectMapper = new ObjectMapper();

    private JacksonTester<Book> jsonTester;

    @Before
    public void setup() {
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void createTest()
            throws Exception {

        Book book = new Book();
        book.setTitle("Book Title");
        given(service.save(book)).willReturn(book);

        final String bookJson = jsonTester.write(book).getJson();

        mvc.perform(post("/books")
                .content(bookJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Book Title")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/books/0{?memberId}")));

    }

    @Test
    public void updateTest()
            throws Exception {

        Book book = new Book();
        book.setId(1);
        book.setTitle("Book Title");
        given(service.update(1, book)).willReturn(book);

        final String bookJson = jsonTester.write(book).getJson();

        mvc.perform(put("/books/1")
                .content(bookJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Book Title")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/books/1{?memberId}")));

    }

    @Test
    public void patchTest()
            throws Exception {

        Book book = new Book();
        book.setTitle("Book Title");
        given(service.patch(1, book)).willReturn(book);

        final String bookJson = jsonTester.write(book).getJson();

        mvc.perform(patch("/books/1")
                .content(bookJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Book Title")));
    }

    @Test
    public void deleteTest()
            throws Exception {
        mvc.perform(delete("/books/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void findOneTest()
            throws Exception {

        Book book = new Book();


        given(service.findOne(1)).willReturn(book);

        mvc.perform(get("/books/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/books/0{?memberId}")));
    }


}
