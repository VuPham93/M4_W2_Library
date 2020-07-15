package service.formatter;

import model.Borrower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import service.borrower.BorrowerService;

import java.text.ParseException;
import java.util.Locale;

public class BorrowerFormatter implements Formatter<Borrower> {
    private BorrowerService borrowerService;

    @Autowired
    public BorrowerFormatter(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @Override
    public Borrower parse(String text, Locale locale) throws ParseException {
        return borrowerService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Borrower object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}
