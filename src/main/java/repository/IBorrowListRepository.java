package repository;

import model.BorrowList;
import model.Borrower;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBorrowListRepository extends PagingAndSortingRepository<BorrowList, Long> {
    BorrowList findByBorrowCode(Long borrowCode);
}
