package service.borrowList;

import model.Book;
import model.BorrowList;
import model.Borrower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import repository.IBorrowListRepository;
import service.book.IBookService;
import service.borrower.IBorrowerService;
import service.exception.OutOfBookException;
import service.exception.WrongBorrowCodeException;

public class BorrowListService implements IBorrowListService {
    @Autowired
    private IBorrowListRepository borrowListRepository;

    @Autowired
    private IBookService bookService;

    @Autowired
    private IBorrowerService borrowerService;

    @Override
    public Page<BorrowList> findAll(Pageable pageable) {
        return borrowListRepository.findAll(pageable);
    }

    @Override
    public Iterable<BorrowList> findAll() {
        return borrowListRepository.findAll();
    }

    @Override
    public BorrowList findById(Long id) {
        return borrowListRepository.findOne(id);
    }

    @Override
    public void save(BorrowList borrowList) throws OutOfBookException {
        Book book = borrowList.getBook();
        if (book.getQuantity() == 0) {
            throw new OutOfBookException();
        } else {
            book.setQuantity(book.getQuantity() - 1);
            bookService.save(book);

            Borrower borrower = borrowList.getBorrower();
            borrowerService.save(borrower);

            borrowList.setBorrowCode(book.getId() * 100 + borrower.getId() + 10000);

            borrowListRepository.save(borrowList);
        }
    }

    @Override
    public BorrowList findByBorrowCode(Long borrowCode) {
        return borrowListRepository.findByBorrowCode(borrowCode);
    }

    @Override
    public void deleteByBorrowCode(Long borrowCode) throws WrongBorrowCodeException {
        BorrowList borrowList = borrowListRepository.findByBorrowCode(borrowCode);
        if (borrowList == null) {
            throw new WrongBorrowCodeException();
        } else {
            Book book = borrowList.getBook();
            Borrower borrower = borrowList.getBorrower();

            borrowListRepository.delete(borrowList.getId());
            book.setQuantity(book.getQuantity() + 1);
            bookService.save(book);
            borrowerService.remove(borrower.getId());
        }
    }
}
