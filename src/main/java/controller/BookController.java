package controller;

import model.Book;
import model.BorrowList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import service.book.IBookService;
import service.borrowList.IBorrowListService;

@Controller
public class BookController {
    @Autowired
    private IBookService bookService;

    @Autowired
    private IBorrowListService borrowListService;

    @ModelAttribute("borrow")
    public Iterable<BorrowList> allBorrow() {
        return borrowListService.findAll();
    }

    @GetMapping("/book")
    public ModelAndView listBooks(@PageableDefault(size = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("book/list");
        Page<Book> booksList = bookService.findAll(pageable);
        modelAndView.addObject("bookList", booksList);
        return modelAndView;
    }

    @GetMapping("/book/{id}")
    public ModelAndView showBookInfo(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("book/detail");
        Book book = bookService.findById(id);
        BorrowList borrowList = new BorrowList();
        borrowList.setBook(book);
        modelAndView.addObject("borrowList", borrowList);
        return modelAndView;
    }
}
