package Model;

import java.time.LocalDate;

public class Librarian {

    // quy trinh muon sach
    public boolean borrowBook(Library library, String readerId, String bookId, String slipId, LocalDate borrowDate, LocalDate dueDate) {
        Reader reader = library.findReaderById(readerId);
        Book book = library.findBookById(bookId);

        if (reader == null || book == null) {
            System.out.println("Không tìm thấy Độc giả hoặc Sách.");
            return false;
        }

        // kiem tra so luong sach trong kho
        if (!book.isAvailable()) {
            System.out.println("Sách '" + book.getTitle() + "' đã hết trong kho.");
            return false;
        }

        // kiem tra gioi han muon theo loai the
        long currentlyBorrowed = library.countBorrowedBooksByReader(readerId);
        if (currentlyBorrowed >= reader.getMaxBorrowLimit()) {
            System.out.println("Từ chối: Độc giả " + reader.getFullName() + " đã đạt giới hạn mượn (" + reader.getMaxBorrowLimit() + " cuốn).");
            return false;
        }

        // tao phieu muon va tru so luong kho
        BorrowSlip newSlip = new BorrowSlip(slipId, reader, book, borrowDate, dueDate);
        library.addBorrowSlip(newSlip);
        book.decreaseQuantity();

        System.out.println("Thành công: Đã tạo phiếu mượn cho sách '" + book.getTitle() + "'.");
        return true;
    }

    // quy trinh tra sach
    public void returnBook(Library library, String slipId, LocalDate returnDate) {
        for (BorrowSlip slip : library.getSlips()) {
            if (slip.getSlipId().equals(slipId) && slip.getActualReturnDate() == null) {
                // danh dau tra sach
                slip.markAsReturned(returnDate);

                // tra sach lai vao kho
                slip.getBook().increaseQuantity();

                System.out.println("Thành công: Đã trả sách '" + slip.getBook().getTitle() + "'.");

                // tinh tien phat neu co
                long fine = slip.caculateFine(returnDate);
                if (fine > 0) {
                    System.out.println("CẢNH BÁO: Trễ hạn " + slip.getOverdueDays(returnDate) + " ngày. Số tiền phạt: " + fine + " VND.");
                }
                return;
            }
        }
        System.out.println("Lỗi: Không tìm thấy phiếu mượn hợp lệ hoặc sách đã được trả trước đó.");
    }
}
