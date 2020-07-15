package service.formatter;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import service.book.BookService;

import java.text.ParseException;
import java.util.Locale;

public class BookFormatter implements Formatter<Book> {
    private BookService bookService;

    @Autowired
    public BookFormatter(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Book parse(String text, Locale locale) throws ParseException {
        return bookService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Book object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}
