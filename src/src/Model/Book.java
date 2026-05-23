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

    public void increaseQuantity()
    {
        quantity++;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublishYear() { return publishYear; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
