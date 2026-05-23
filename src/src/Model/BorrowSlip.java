package Model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class BorrowSlip {

    private String slipId;
    private Reader reader;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate actualReturnDate;

    public BorrowSlip(String slipId, Reader reader, Book book, LocalDate borrowDate, LocalDate dueDate) {
        this.slipId = slipId;
        this.reader = reader;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.actualReturnDate = null; //null nghia la chua tra
    }

    //tinh so ngay tre han
    public long getOverdueDays(LocalDate currentDate)
    {
        LocalDate checkDate = (actualReturnDate !=null) ? actualReturnDate : currentDate;
        if(checkDate.isAfter(dueDate))
        {
            return ChronoUnit.DAYS.between(dueDate, checkDate);
        }
        return 0;
    }

    //tinh tien phat
    public long caculateFine(LocalDate currentDate)
    {
        return getOverdueDays(currentDate) * 5000;
    }

    //danh dau da tra sach
    public void markAsReturned(LocalDate returnDate) {
        this.actualReturnDate = returnDate;
    }

    public String getSlipId() { return slipId; }
    public Reader getReader() { return reader; }
    public Book getBook() { return book; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getActualReturnDate() { return actualReturnDate; }

}
