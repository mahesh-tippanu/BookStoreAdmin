package com.example.bookstore_backend.model;

import com.example.bookstore_backend.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @Column(name = "bookId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long bookId;
    public String bookName;
    public int price;
    public String authorName;
    public int bookQuantity;
    public String profilePic;

    public String bookdescription;


    public void updateData(BookDTO bookDTO) {
        this.bookName = bookDTO.bookName;
        this.price = bookDTO.price;
        this.authorName = bookDTO.authorName;
        this.bookQuantity = bookDTO.bookQuantity;
        this.profilePic = bookDTO.profilePic;

    }



    public Book(BookDTO bookDTO) {
        this.bookName = bookDTO.bookName;
        this.price = bookDTO.price;
        this.authorName = bookDTO.authorName;
        this.bookQuantity = bookDTO.bookQuantity;
        this.profilePic = bookDTO.profilePic;
    }

}
