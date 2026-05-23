import Model.*;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Librarian librarian = new Librarian();

        // Khởi tạo dữ liệu ban đầu
        Book b1 = new Book("B01", "Lập trình Java cơ bản", "Minh Thuan", 2021, 2);
        Book b2 = new Book("B02", "Cấu trúc dữ liệu", "Van Hung", 2020, 10);
        Book b3 = new Book("B03", "C# ASP.NET Core", "Thai Bao", 2022, 3);
        Book b4 = new Book("B04", "Clean Architecture", "Robert C. Martin", 2017, 3);

        library.addBook(b1); library.addBook(b2); library.addBook(b3); library.addBook(b4);

        //Reader r1 = new Reader("R01", "Minh Dang", "minhdang@gmail.com", ReaderType.Student); // Max: 3
        //Reader r2 = new Reader("R02", "Gia Lam", "Gialam@gmail.com", ReaderType.Lecture); // Max: 5

        Reader r1 = new Student("R01", "Minh Dang", "minhdang@gmail.com");
        Reader r2 = new Lecturer("R02", "Gia Lam", "Gialam@gmail.com");

        library.addReader(r1); library.addReader(r2);
        System.out.println("Hoàn tất khởi tạo dữ liệu Sách và Độc giả!\n");

        library.printBooks();
        System.out.println();
        library.printReaders();
        System.out.println("\n======================================================\n");



        System.out.println("---  Tìm kiếm sách ---");
        String keyword = "Clean";
        System.out.println("Từ khóa tìm kiếm nhập vào: '" + keyword + "'");
        List<Book> searchResults = library.searchBook(keyword);
        if (searchResults.isEmpty()) {
            System.out.println("Không tìm thấy cuốn sách nào khớp với từ khóa.");
        } else {
            for (Book b : searchResults) {
                System.out.println("=> Kết quả tìm thấy: [Mã: " + b.getId() + "] " + b.getTitle() + " - Tác giả: " + b.getAuthor());
            }
        }
        System.out.println();



        System.out.println("--- Quy trình mượn sách ---");
        LocalDate today = LocalDate.of(2026, 5, 21);
        LocalDate dueDate1 = today.plusDays(7); // Hẹn trả sau 7 ngày (Hạn trả: 2026-05-28)

        System.out.println("Độc giả R01 (Sinh viên - Giới hạn: 3) tiến hành mượn 3 cuốn sách:");
        librarian.borrowBook(library, "R01", "B01", "S01", today, dueDate1);
        librarian.borrowBook(library, "R01", "B03", "S02", today, dueDate1);
        librarian.borrowBook(library, "R01", "B04", "S03", today, dueDate1);

        System.out.println("\nKiểm tra ràng buộc thẻ: R01 cố tình mượn thêm cuốn thứ 1:");
        librarian.borrowBook(library, "R01", "B01", "S04", today, dueDate1);

        System.out.println("\nKiểm tra trừ kho: Độc giả R02 (Giảng viên) mượn cuốn B02 (Kho ban đầu có 1):");
        librarian.borrowBook(library, "R02", "B02", "SLIP_05", today, dueDate1);

        System.out.println("\nKiểm tra ràng buộc kho: Độc giả mới R03 thử mượn tiếp cuốn B01 khi kho đã hết (bằng 0):");
        Reader r3 = new Student("R03", "Hoang Minh", "HoangMinh@gmail.com");
        library.addReader(r3);
        librarian.borrowBook(library, "R03", "B01", "S06", today, dueDate1);
        System.out.println();


        System.out.println("--- Danh sách phiếu mượn quá hạn ---");
        LocalDate futureDate = today.plusDays(10);
        System.out.println("Ngày hệ thống giả định nhập vào: " + futureDate + " (Ngày hẹn trả gốc là: " + dueDate1 + ")");

        List<BorrowSlip> overdue = library.getOverdueSlips(futureDate);
        System.out.println("Danh sách các phiếu mượn đang bị quá hạn tại thư viện:");
        if (overdue.isEmpty()) {
            System.out.println("Tuyệt vời! Không có phiếu mượn nào quá hạn.");
        } else {
            for (BorrowSlip slip : overdue) {
                System.out.println("=> [Mã phiếu: " + slip.getSlipId() + "] Độc giả: " + slip.getReader().getFullName()
                        + " | Sách mượn: " + slip.getBook().getTitle()
                        + " | Hẹn trả ngày: " + slip.getDueDate());
            }
        }
        System.out.println();



        System.out.println("--- ĐÁP ÁN YÊU CẦU 2: QUY TRÌNH TRẢ SÁCH & TÍNH TIỀN PHẠT ---");

        System.out.println("Độc giả R01 thực hiện trả đúng hạn phiếu SLIP_01 (Trả vào ngày thứ 5 sau khi mượn):");
        librarian.returnBook(library, "SLIP_01", today.plusDays(5));
        System.out.println("Kiểm tra cập nhật lại kho sách B01 (Ban đầu: 2 -> Mượn: 1 -> Trả: hoàn lại): " + library.findBookById("B01").getQuantity());

        System.out.println("\nĐộc giả R02 thực hiện trả trễ hạn phiếu SLIP_05:");
        System.out.println("(Ngày hẹn trả: " + dueDate1 + " | Ngày thực tế mang sách đi trả: " + futureDate + ")");
        librarian.returnBook(library, "SLIP_05", futureDate);
        System.out.println("=== KẾT THÚC CHƯƠNG TRÌNH RE-TEST ===");
    }
}