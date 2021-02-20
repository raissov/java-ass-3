package kz.raissov.springProject.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Booking {
    private long bookingId;
    private long isbn;
    private String emailId;
    private String bookingStatus;
    private Date date;

    public Booking(long bookingId, long isbn, String emailId, String bookingStatus, Date date) {
        this.bookingId = bookingId;
        this.isbn = isbn;
        this.emailId = emailId;
        this.bookingStatus = bookingStatus;
        this.date = date;
    }

    public Booking() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getBookingId(){
        return bookingId;
    }
    public void setBookingId(long bookingId){
        this.bookingId = bookingId;
    }
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "books",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "isbn")
    )
    private Set<Book> bookSet = new HashSet<>();
    @Column(name = "isbn", nullable = false)
    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }
    @Column(name = "email_id")
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    @Column(name = "bookingStatus", nullable = false)
    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
