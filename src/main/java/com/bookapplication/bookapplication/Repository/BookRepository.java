package com.bookapplication.bookapplication.Repository;

import com.bookapplication.bookapplication.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
    public Book findBookByTitle(String title);
}
