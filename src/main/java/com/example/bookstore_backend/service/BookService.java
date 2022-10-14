package com.example.bookstore_backend.service;

import com.example.bookstore_backend.dto.BookDTO;
import com.example.bookstore_backend.dto.ResponseDTO;
import com.example.bookstore_backend.exception.BookException;
import com.example.bookstore_backend.model.Book;
import com.example.bookstore_backend.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class BookService {

    private static final String BOOK_DETAILS_ADDED = "Book added";

    @Autowired
    private BookRepo bookRepo;


//    public Book getById(int id) {
//        if (bookRepo.getById(id) != null) {
//            return bookRepo.getById(id);
//        } else {
//            return null;
//
//        }
//    }

    public List<Book> getAllList() {
        return bookRepo.findAll();
    }
    public Book getBookById(long bookId) {
        return bookRepo.findById(bookId)
                .orElseThrow(() -> new BookException("Book  Id not Found!!!"));
    }
    public Book addBook(Book bookEntity) {
        bookRepo.save(bookEntity);
        return bookEntity;
    }

    public Book editAddressBookData(long bookId, BookDTO bookDTO) {
        Book book = this.getBookById(bookId);
        book.updateData(bookDTO);
        return bookRepo.save(book);
    }
    public void deleteBookData(long bookId) {
        Book book = this.getBookById(bookId);
        bookRepo.delete(book);
    }

}