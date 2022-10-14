package com.example.bookstore_backend.dto;


import com.example.bookstore_backend.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    public long bookId;
    public String bookName;
    public int price;
    public String authorName;
    @ElementCollection
    public Book book;
    public int bookQuantity;
    public String bookdescription;
    public String profilePic;
}
