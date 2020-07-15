package service.borrowList;

import model.BorrowList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import service.exception.OutOfBookException;
import service.exception.WrongBorrowCodeException;

public interface IBorrowListService {
    Page<BorrowList> findAll(Pageable pageable);

    Iterable<BorrowList> findAll();

    BorrowList findById(Long id);

    void save(BorrowList borrowList) throws OutOfBookException;

    BorrowList findByBorrowCode(Long borrowCode);

    void deleteByBorrowCode(Long borrowCode) throws WrongBorrowCodeException;
}
