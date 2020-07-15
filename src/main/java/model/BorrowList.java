package model;

import javax.persistence.*;

@Entity
@Table(name = "borrowlist")
public class BorrowList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private Borrower borrower;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private Long borrowCode;

    public BorrowList() {
    }

    public BorrowList(Long id, Borrower borrower, Book book, Long borrowCode) {
        this.id = id;
        this.borrower = borrower;
        this.book = book;
        this.borrowCode = borrowCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getBorrowCode() {
        return borrowCode;
    }

    public void setBorrowCode(Long borrowCode) {
        this.borrowCode = borrowCode;
    }
}
