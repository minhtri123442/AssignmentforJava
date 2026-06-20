package Model;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class BorrowSlip implements Returnable {

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
        this.actualReturnDate = null; // null nghia la chua tra
    }


    @Override
    public void confirmReturn(String date) {
        try {
            this.actualReturnDate = LocalDate.parse(date);
            System.out.println("Đã xác nhận trả sách thành công vào ngày: " + date);
        } catch (DateTimeParseException e) {
            System.out.println("Lỗi: Định dạng ngày trả không hợp lệ (Bắt buộc: YYYY-MM-DD)");
        }
    }

    @Override
    public String getReturnDate() {
        // Nếu đã trả thì trả về chuỗi ngày, ngược lại trả về chuỗi trống hoặc báo chưa trả
        return (this.actualReturnDate != null) ? this.actualReturnDate.toString() : "";
    }

    @Override
    public boolean isReturned() {
        return this.actualReturnDate != null;
    }


    // tinh so ngay tre han
    public long getOverdueDays(LocalDate currentDate) {
        LocalDate checkDate = (actualReturnDate != null) ? actualReturnDate : currentDate;
        if (checkDate.isAfter(dueDate)) {
            return ChronoUnit.DAYS.between(dueDate, checkDate);
        }
        return 0;
    }

    // tinh tien phat
    public long caculateFine(LocalDate currentDate) {
        return getOverdueDays(currentDate) * 5000;
    }

    // danh dau da tra sach
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