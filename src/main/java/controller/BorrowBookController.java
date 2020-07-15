package controller;

import model.Book;
import model.BorrowList;
import model.Borrower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.book.IBookService;
import service.borrowList.IBorrowListService;
import service.borrower.IBorrowerService;
import service.exception.OutOfBookException;
import service.exception.WrongBorrowCodeException;

@Controller
public class BorrowBookController {
    @Autowired
    private IBorrowerService borrowerService;

    @Autowired
    private IBookService bookService;

    @Autowired
    private IBorrowListService borrowListService;

    @ModelAttribute("book")
    public Iterable<Book> allBooks() {
        return bookService.findAll();
    }

    @ModelAttribute("borrower")
    public Iterable<Borrower> allBorrower() {
        return borrowerService.findAll();
    }

    @PostMapping("/borrow-book")
    public ModelAndView borrowBook(@ModelAttribute BorrowList borrowList) throws OutOfBookException {
        borrowListService.save(borrowList);
        return new ModelAndView("redirect:/book");
    }

    @PostMapping("/return-book")
    public ModelAndView returnBook(@RequestParam Long borrowCode) throws WrongBorrowCodeException {
        borrowListService.deleteByBorrowCode(borrowCode);
        return new ModelAndView("redirect:/book");
    }

    @ExceptionHandler(OutOfBookException.class)
    public ModelAndView showOutOfBookPage() {
        return new ModelAndView("error/out-of-book");
    }

    @ExceptionHandler(WrongBorrowCodeException.class)
    public ModelAndView showWrongBorrowCodePage() {
        return new ModelAndView("error/wrong-borrow-code");
    }
}
