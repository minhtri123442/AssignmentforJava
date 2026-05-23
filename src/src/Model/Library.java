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

}
