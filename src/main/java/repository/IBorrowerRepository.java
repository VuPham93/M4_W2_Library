package repository;

import model.Borrower;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBorrowerRepository extends PagingAndSortingRepository<Borrower, Long> {
}
