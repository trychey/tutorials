package com.baeldung.httpinterface;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

interface BooksService {

    @GetExchange("/books")
    List<Book> getBooks();

    @GetExchange("/books/{title}")
    Book getBook(@PathVariable String title);

    @PostExchange("/books")
    ResponseEntity<Book> saveBook(@RequestBody Book book);

}
