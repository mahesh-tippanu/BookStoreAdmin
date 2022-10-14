package com.example.bookstore_backend.controller;

import com.example.bookstore_backend.dto.BookDTO;
import com.example.bookstore_backend.dto.ResponseDTO;
import com.example.bookstore_backend.model.Book;
import com.example.bookstore_backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;


    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getAllBookList() {
        ResponseDTO responseDto = new ResponseDTO("List Of Books", bookService.getAllList());
        return new ResponseEntity<ResponseDTO>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = {"/get/{bookId}"})
    public ResponseEntity<ResponseDTO> getBookData(@PathVariable int bookId) {
        Book book= bookService.getBookById(bookId);
        ResponseDTO responseDTO = new ResponseDTO("Success Call for Person Id!!!", book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

//    @RequestMapping("/{id}")
//       public ResponseEntity<ResponseDTO> getBookById(@PathVariable int id) {
//        ResponseDTO responseDto = new ResponseDTO("Book:", bookService.getById(id));
//        return new ResponseEntity<ResponseDTO>(responseDto, HttpStatus.OK);
//    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addBook(@RequestBody BookDTO bookDto) {
        Book book = new Book(bookDto);
        ResponseDTO responseDto = new ResponseDTO("Book Added", bookService.addBook(book));
        return new ResponseEntity<ResponseDTO>(responseDto, HttpStatus.OK);
    }
    @PutMapping(value = {"/edit/{bookId}"})
    public ResponseEntity<ResponseDTO> editBookData(@PathVariable int bookId,
                                                           @RequestBody BookDTO bookDTO) {
        Book book = bookService.editAddressBookData(bookId, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Data UPDATED Successfully!!!", book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @DeleteMapping(value = {"/remove/{bookId}"})
    public ResponseEntity<ResponseDTO> removeAddressBookData(@PathVariable int bookId) {
        bookService.deleteBookData(bookId);
        ResponseDTO responseDTO = new ResponseDTO("Data DELETED Successfully!!!",
                "ID number: " + bookId + " --> DELETED!!!");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}