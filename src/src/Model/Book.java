package Model;

public class Book {
    private String id;
    private String title;
    private String author;
    private int publishYear;
    private int quantity;


    public Book(String id, String title, String author, int publishYear, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return quantity > 0;
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
