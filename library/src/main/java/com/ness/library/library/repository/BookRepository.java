package com.ness.library.library.repository;

import com.ness.library.library.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModel, Integer> {
}
