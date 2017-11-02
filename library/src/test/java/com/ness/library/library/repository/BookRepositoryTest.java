package com.ness.library.library.repository;

import com.ness.library.library.model.BookModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void findOneTest() {
        BookModel book = new BookModel();
        BookModel persistedBook = entityManager.persist(book);
        entityManager.flush();
        BookModel found = bookRepository.findOne((Integer) entityManager.getId(persistedBook));
        assertThat(found).isEqualTo(persistedBook);
    }

    @Test
    public void findAllTest() {
        BookModel book = new BookModel();
        BookModel persistedBook = entityManager.persist(book);
        entityManager.flush();
        List<BookModel> found = bookRepository.findAll();
        assertThat(found.get(0)).isEqualTo(persistedBook);
    }

    @Test
    public void saveTest() {
        BookModel book = new BookModel();
        BookModel persistedBook = bookRepository.save(book);
        Integer id = (Integer) entityManager.getId(persistedBook);
        BookModel found = entityManager.find(BookModel.class, id);
        assertThat(found).isEqualTo(persistedBook);
    }

    @Test
    public void deleteTest() {
        BookModel book = new BookModel();
        Integer id = (Integer) entityManager.persistAndGetId(book);
        entityManager.flush();
        bookRepository.delete(id);
        BookModel found = entityManager.find(BookModel.class, id);
        assertThat(found).isNull();
    }

}
