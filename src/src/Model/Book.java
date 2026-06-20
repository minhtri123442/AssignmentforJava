package Model;

public class Book implements Borrowable, Comparable<Book>  {
    private String id;
    private String title;
    private String author;
    private int publishYear;
    private int quantity;
    private boolean referenceOnly = false;
    private String currentBorrowerId; // null neu chua ai muon
    private String borrowDate;



    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.currentBorrowerId = null;
    }


    public void decreaseQuantity()
    {
        if(quantity >0)
        {
            quantity--;
        }
    }

    @Override
    public String toString() {
        return "[Mã Sách: " + id + "] " + title + " - Tác giả: " + author + " - SL: " + quantity;
    }

    @Override
    public void borrowBy(String readerId, String date) {
        if (!isAvailable()) {
            System.out.println("Book '" + title + "' is not available.");
            return;
        }
        this.currentBorrowerId = readerId;
        this.borrowDate        = date;
        System.out.println("Book '" + title + "' borrowed by " + readerId);
    }

    @Override
    public void returnBook(String date) {
        System.out.println("Book '" + title + "' returned on " + date);
        this.currentBorrowerId = null;
        this.borrowDate        = null;
    }

    @Override
    public boolean isAvailable() { return currentBorrowerId == null; }

    @Override
    public String getBorrowerId() { return currentBorrowerId; }


    public void increaseQuantity()
    {
        quantity++;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublishYear() { return publishYear; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public boolean isReferenceOnly() { return referenceOnly; }
    public void setReferenceOnly(boolean referenceOnly) { this.referenceOnly = referenceOnly; }

    public void decreaseStock() {
        if (this.quantity > 0) {
            this.quantity--;
        }
    }
    public int getQuantity() { return quantity; }


    @Override
    public int compareTo(Book other) {
        if (other == null) return 1;
        return this.id.compareTo(other.getId()); // So sánh sắp xếp theo mã sách
    }
}
