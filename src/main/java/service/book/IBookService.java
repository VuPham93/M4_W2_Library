package service.book;

import model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService {
    Page<Book> findAll(Pageable pageable);

    Iterable<Book> findAll();

    Book findById(Long id);

    void save(Book book);

    void remove(Long id);
}
