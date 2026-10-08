package com.bookapplication.bookapplication.Service;

import com.bookapplication.bookapplication.Entity.Book;
import com.bookapplication.bookapplication.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    public Book addBook(Book book){
     return bookRepository.save(book);
    }

    public Book getByName(String name) {
        return bookRepository.findBookByTitle(name);
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(int id) {
         bookRepository.deleteById(id);
    }
}
