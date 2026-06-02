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
        //Reader r3 = new Student("R03", "Hoang Minh", "HoangMinh@gmail.com");
        Reader sv = new Student("R001", "Le Van C", "c@student.edu");
        Reader gv = new Lecturer("R002", "Pham Thi D", "d@uni.edu");
        library.addReader(sv);
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


        System.out.println("=== Assignment 05 ===");

        //Bài 1: khi thêm từ khóa abstract vào khai báo class Reader, không thể khai báo theo dạng:
        /*
        * Reader 01 = new Reader();
        * vì abstract được tạo ra để ngăn chạn các dev khởi tạo nhầm đối tượng Reader không xác định loại.
        *
        * Trong thực tế, không hề có độc giả chung chung, khi có người vào thư viện, họ buộc phải nằm trong 2 loại
        * là học viên(Student) và giảng viên(lecture) đẻ hệ thống có thể xác định loại(thời hạn mượn sách, hạn mức mượn,...)
        * */




        System.out.println("------------------bai 5------------");
        Library lib = new Library();
        lib.addBook(b1); lib.addBook(b2);
        lib.addReader(sv); lib.addReader(gv);
        lib.printBooks();
        lib.showAllReaders(); // Gọi getInfo() — Student vs Lecturer
        lib.showLateFees(7); // Gọi calculateLateFee() — khác nhau mỗi loại
        System.out.println("\nHan muon:");
        Reader[] readers = { sv, gv };
        for (Reader r : readers) {
            System.out.println(r.getFullName() + ": " + r.getMaxBookBorrow() + "cuon");
        }
        System.out.println("------------------bai (nang cao)------------");

        DigitalAccount sv1 = new StudentAccount("S01", "Nguyen Van A");
        DigitalAccount guest = new GuestAccount("Khach vang lai");

        sv1.requestDownload(0); // thành công
        sv1.requestDownload(2); // thành công (tổng 3)
        sv1.requestDownload(3); // đạt giới hạn

        guest.requestDownload(0); // thành công
        guest.requestDownload(1); // đạt giới hạn



        //lập luận:
        /*Nhiệm vụ 1:
        *Câu hỏi 1.1: Nên tạo một lớp cha trung gian DigitalAccount không? Hay để cả 4 lớp phẳng?
        * Trả lời: Nên tạo 1 lớp cha DigitalAccount vì nó dễ bảo trì, không cần phải sửa
        * từng cái if/else khi mỗi lần cần chỉnh sửa code/ thêm chức năng mới,...
        *
        * Câu hỏi 1.2: Lớp nào nên là abstract class, lớp nào là concrete class?
        * Trả lời: Lớp DigitalAccount nên là abstract class vì nó lớp là lớp chung, các lớp còn lại là concrete class
        * vì nó có quy tắc cụ thể
        *
        *
        * Câu hỏi 1.3: Phương thức authenticate() và getDownloadLimit() nên là abstract hay có cài đặt mặc định?
        * Trả lời: nó nên là abstract vì mỗi loại tài khaorn đều có các phương thức khác nhau(email,pass,opt, quét mã,không xác thực)
        *
        * Câu hỏi 1.4: Tại sao GuestAccount và LibraryCard KHÔNG NÊN kế thừa từ Reader?
        *
        * Trả lơời:  vì Reader đang có những thuộc tính mà GuestAccount không cần phải có(email, lịch sử mượn/trả sách,...)
        * không nên kế thừa cho thàng LibraryCard vì nó là thẻ vật lí, vì nó chỉ là thẻ nhựa thôi, nó không cần phải lưu
        * lại 1 gmail cho 1 thẻ làm gì, rất vô nghĩa, chỉ cần có id và name, thêm pass nữa là có thể sử dụng, việc gán gmail
        * vào trong thẻ sẽ gây ra sự xuất hiện của dữ liệu rác trong hệ thống.
        *
        *
        * 1. Tại sao DigitalAccount là abstract class?
        * vì nó là hàm dùng chung chung, còn các concrete class khác thì mỗi tài khoản có 1 chức năng riêng, như StudentAccount
        * cần email và mật khẩu để xác thực, còn LecturerAccount không cần mật khẩu mà cần mã OTP
        *
        *
        * 2. Tại sao GuestAccount/LibraryCard KHÔNG kế thừa Reader?
         * => (Gợi ý: quan hệ IS-A vs HAS-A, hoặc khái niệm Composition)
         * sử dụng quan hệ IS-A trong Reader( vd: Student IS-A Reader)
         * đồng thời Reader có tồn tại các thuộc tính phù hợp với Student.
         * hoặc nếu nói về khái niệm Composition, Reader HAS-A DigitalAccount
         * => nghĩa là 1 reader sẽ có 1 tài khoản
         * 1 độc giả(Student) có thể sỡ hữu 1 tài khoản điện tử(StudentAccount)
         * 1 khách vãng lai(Guest) có thể sỡ hữu 1 tài khoản(GuestAccount) để tải sách, họ không phải là 1 reader trong thư viện.
        *
        * 3 - Lợi thế thiết kế hiện tại:
        * Tính mở rộng:  dễ dàng thêm các loại tài khoản mới mà không cần phải thay đổi logic code(tuân thủ Open/Closed principle)
        * của các lớp hiện có.
        * Tính đa hình: phương thức requestDownload() giúp việc xử lí logic tải sách trở nên đồng nhất kể cả là Student hay
        * là Guest để giảm code thừa
        *
         */
    }
}