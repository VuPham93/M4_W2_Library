package service.borrower;

import model.Borrower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import repository.IBorrowerRepository;

public class BorrowerService implements IBorrowerService{
    @Autowired
    private IBorrowerRepository borrowerRepository;

    @Override
    public Page<Borrower> findAll(Pageable pageable) {
        return borrowerRepository.findAll(pageable);
    }

    @Override
    public Iterable<Borrower> findAll() {
        return borrowerRepository.findAll();
    }

    @Override
    public Borrower findById(Long id) {
        return borrowerRepository.findOne(id);
    }

    @Override
    public void save(Borrower borrower) {
        borrowerRepository.save(borrower);
    }

    @Override
    public void remove(Long id) {
        borrowerRepository.delete(id);
    }
}
