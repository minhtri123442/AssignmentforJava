package Model;

public class Book implements Borrowable  {
    private String id;
    private String title;
    private String author;
    private int publishYear;
    private int quantity;
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

    private boolean referenceOnly = false;

    public boolean isReferenceOnly() { return referenceOnly; }
    public void setReferenceOnly(boolean referenceOnly) { this.referenceOnly = referenceOnly; }

    public void decreaseStock() {
        if (this.quantity > 0) {
            this.quantity--;
        }
    }
    public int getQuantity() { return quantity; }

}
