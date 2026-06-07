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
}
