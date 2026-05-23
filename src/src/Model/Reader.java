package Model;
public class Reader {

    private String readerId;
    private String fullName;
    private String email;
    private ReaderType type;

    public Reader(String readerId, String fullName, String email, ReaderType type) {
        this.readerId = readerId;
        this.fullName = fullName;
        this.email = email;
        this.type = type;
    }

    // Lấy giới hạn số sách được mượn dựa vào loại thẻ
    public int getMaxBorrowLimit() {
        return type.getMaxBooksLimit();
    }

    // Getters và Setters
    public String getReaderId() { return readerId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public ReaderType getType() { return type; }
}
