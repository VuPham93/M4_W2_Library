package service.borrower;

import model.Borrower;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBorrowerService {
    Page<Borrower> findAll(Pageable pageable);

    Iterable<Borrower> findAll();

    Borrower findById(Long id);

    void save(Borrower borrower);

    void remove(Long id);
}
