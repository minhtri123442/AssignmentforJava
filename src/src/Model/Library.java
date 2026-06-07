package Model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Library {

    private List<Book> books = new ArrayList<>();
    private  List<Reader> readers = new ArrayList<>();
    private List<BorrowSlip> borrowSlips = new ArrayList<>();


    public void addBook(Book book) {books.add(book);}
    public void addReader(Reader reader) {readers.add(reader);}
    public void addBorrowSlip(BorrowSlip borrowSlip) {borrowSlips.add(borrowSlip);}



    public void printBooks()
    {
        System.out.println("--------Danh sách sách-------------");
        for(Book book : books) System.out.println(book);
    }

    public void printReaders()
    {
        System.out.println("-------------Danh sách độc giả-------------------------");
        for(Reader reader : readers) System.out.println(reader);
    }

    public Book findBookById(String id)
    {
        for(Book book: books)
        {
            if(book.getId().equals(id)) return book;
        }
        return null;
    }

    public Reader findReaderById(String id)
    {
        for(Reader reader: readers)
        {
            if(reader.getReaderId().equals(id)) return reader;
        }
        return null;
    }

    //dem so sach dang muon cua 1 doc gia
    public long countBorrowedBooksByReader(String readerId) {
        return borrowSlips.stream().filter(s -> s.getReader().getReaderId().equals(readerId)
                && s.getActualReturnDate() == null).count();
    }


    public List<Book> searchBook(String keyword)
    {
        List<Book> result = new ArrayList<>();
        String lowkeyword = keyword.toLowerCase();
        for(Book book: books)
        {
            if(book.getTitle().toLowerCase().contains(lowkeyword)||
                    book.getAuthor().toLowerCase().contains(lowkeyword))
            {
                result.add(book);
            }
        }
        return result;
    }


    public void showLateFees(int daysLate) {
        System.out.println("=== PHI PHAT TRE HAN (" + daysLate + " ngay) ===");
        for (Reader r : readers) {
            // Đa hình: r.calculateLateFee() sẽ tự gọi logic của Student hoặc Lecturer
            System.out.printf("%-25s | Fee: %,.0f VND%n", r.getFullName(), r.calculateLateFee(daysLate));
        }
    }

    public void showAllReaders() {
        System.out.println("=== DANH SACH DOC GIA ===");
        for (Reader r : readers) {
            // Đa hình: r.getInfo() sẽ tự gọi đúng method của từng lớp con
            System.out.println(r.getInfo());
        }
    }


    //danh sach phieu muon qua han
    public List<BorrowSlip> getOverdueSlips(LocalDate currentDate) {
        List<BorrowSlip> overdueSlips = new ArrayList<>();
        for (BorrowSlip slip : borrowSlips) {
            if (slip.getActualReturnDate() == null && currentDate.isAfter(slip.getDueDate())) {
                overdueSlips.add(slip);
            }
        }
        return overdueSlips;
    }

    public List<BorrowSlip> getSlips() { return borrowSlips; }


    // 1. In thông tin tất cả độc giả (Quan sát Dynamic Binding)
    public void printAllReaders() {
        System.out.println("=== Thông tin độc giả ===");
        for (Reader r : readers) {
            System.out.println(r.getInfo());
        }
    }

    public double calculateTotalLateFee(int daysLate) {
        double totalFee = 0.0;
        for (Reader r : readers) {
            totalFee += r.calculateLateFee(daysLate);
        }
        return totalFee;
    }

    public Reader findReaderByName(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        for (Reader r : readers) {
            if (r.getFullName().toLowerCase().contains(lowerKeyword)) {
                return r;
            }
        }
        return null;
    }

    public void printSeniorReaders() {
        System.out.println("=== Danh sách độc giả người cao tuổi ===");
        for (Reader r : readers) {
            if (r instanceof SeniorReader) {
                SeniorReader senior = (SeniorReader) r;
                System.out.println(senior.getInfo() + " | Ma the NCT: " + senior.getSeniorCardNumber());
            }
        }
    }
    public void renewAllCardHolders(List<CardHolder> holders, int months) {
        for (CardHolder h : holders) {
            h.renewCard(months);
        }
    }
    public static void printFeeReport(List<Reader> readers, int daysLate) {
        System.out.println("=== BAO CAO TIEN PHAT (" + daysLate + " NGAY) ===");

        for (Reader r : readers) {
            double fee = r.calculateLateFee(daysLate);

            System.out.printf("%s | Tien phat: %,.0f VND%n", r.getInfo(), fee);
        }
    }

    private LateFeePolicy feePolicy = new StandardFeePolicy();
    public void setFeePolicy(LateFeePolicy policy) {
        this.feePolicy = policy;
        System.out.println("Cap nhat chinh sach phi phat: " + policy.getPolicyName());
    }

    public double calculateTotalFee(int daysLate) {
        double total = 0;
        for (Reader r : readers) {
            double baseFee = r.calculateLateFee(daysLate);

            double adjustedFee = feePolicy.applyPolicy(baseFee);

            System.out.printf("  %-20s | Base: %6.0f | Sau CS: %6.0f VND%n",
                    r.getFullName(), baseFee, adjustedFee);

            total += adjustedFee;
        }
        System.out.printf("==> Tong phi phat (%s): %,.0f VND%n", feePolicy.getPolicyName(), total);
        return total;
    }

    /*
     * * CardHolder IS-A Reader
     * => Kế thừa hợp lý vì: Mọi độc giả có thẻ thành viên (CardHolder) bản chất hoàn toàn là một Độc giả (Reader).
     * Lớp này kế thừa lại toàn bộ thuộc tính cốt lõi (readerId, fullName, email) và các phương thức đa hình
     * (calculateLateFee, getInfo) từ Reader để tái sử dụng code.
     * * * GuestReader IS-A Reader
     * => Kế thừa hợp lý vì: Khách vãng lai vào đọc tại chỗ vẫn là một đối tượng Độc giả (Reader) chịu sự quản lý
     * của thư viện. Lớp này nhận các thuộc tính chung từ Reader nhưng override lại hành vi mượn về (getMaxBookBorrow() = 0)
     * và hành vi phạt trễ (calculateLateFee() = 0) để phản ánh đúng thực tế, đồng thời giúp Library quản lý chung
     * trong một danh sách List Reader đa hình.
     * * * Student, Lecturer, SeniorReader IS-A CardHolder
     * => Kế thừa hợp lý vì: Sinh viên, Giảng viên, hay Người cao tuổi có thẻ đều là các đối tượng sở hữu thẻ thư viện vật lý.
     * Việc kế thừa từ lớp trung gian CardHolder giúp cả 3 lớp con này tự động có được thuộc tính cardExpiryDate
     * và hành vi gia hạn thẻ renewCard(), tránh việc viết lặp đi lặp lại code (DRY - Don't Repeat Yourself).
     *
     *
     * * * Library HAS-A List Book
     * => Lý do dùng Composition thay vì Kế thừa: Thư viện sở hữu, chứa chấp một danh sách các cuốn sách.
     * Thư viện KHÔNG PHẢI là một cuốn sách (Library IS-A Book là sai). Sử dụng Composition giúp Library
     * quản lý vòng đời của bộ sưu tập sách (thêm, xóa, tìm kiếm) một cách độc lập và linh hoạt.
     * * * Library HAS-A List Reader
     * => Lý do dùng Composition thay vì Kế thừa: Thư viện quản lý danh sách các độc giả đến đăng ký.
     * Mối quan hệ ở đây là sở hữu, chứa chấp. Nếu để Library kế thừa Reader thì hoàn toàn bẻ gãy logic ngữ nghĩa thực tế.
     * * * Library HAS-A List BorrowSlip
     * => Lý do dùng Composition thay vì Kế thừa: Phiếu mượn là đối tượng kết nối giữa Độc giả và Sách, được sinh ra
     * và lưu trữ bên trong Thư viện để phục vụ mục đích quản lý quy trình mượn, trả.
     * * * Library HAS-A LateFeePolicy (Strategy Pattern)
     * => Lý do dùng Composition thay vì Kế thừa: Thư viện áp dụng một chính sách tính phí tại một thời điểm.
     * Việc dùng Composition kết hợp Interface giúp Thư viện dễ dàng thay thế, hoán đổi linh hoạt chiến lược tính phí
     * (Standard, Charity, Waived) ngay trong lúc chương trình đang chạy (Runtime) thông qua hàm setter, mà không cần
     * phải tạo ra hàng loạt lớp Library con lồng chéo nhau (Tránh tình trạng nổ tung số lượng lớp - Class Explosion).
     * * * BorrowSlip HAS-A Reader và Book
     * => Lý do dùng Composition thay vì Kế thừa: Một phiếu mượn bắt buộc phải bao gồm thông tin của ai mượn (Reader)
     * và mượn cuốn sách nào (Book). Phiếu mượn không phải là Reader hay Book, nó chỉ "giữ tham chiếu" đến chúng
     * để ghi nhận giao dịch.
     */


    /*
    * Trong lớp Reader (ở bài 7), chúng ta đang lưu trữ trực tiếp thuộc tính trạng thái protected int currentBorrowCount.
    *  Điều này vô tình làm cho lớp Reader phải gánh thêm trách nhiệm quản lý số lượng sách đang cầm giữ, khiến quy trình mượn sách
    * phụ thuộc trực tiếp vào việc tăng, giảm biến đếm này thủ công ngay trong Template Method (currentBorrowCount++).
    * Nó làm giảm tính phân tách trách nhiệm (Single Responsibility Principle - SRP).
    * */
}
