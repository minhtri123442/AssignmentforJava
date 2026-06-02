package Model;
public abstract class Reader {

    private String readerId;
    private String fullName;
    private String email;

    public Reader(String readerId, String fullName, String email)
    {
        this.readerId = readerId;
        this.fullName = fullName;
        this.email = email;
    }

    public abstract int getMaxBookBorrow();
    public abstract String getInfo();
    public abstract double calculateLateFee(int daysLate);
    //ghi de
    @Override
    public String toString() {
        return "[Mã Độc Gỉa:  "+ readerId+"] Họ tên: "+ fullName + " - Email:" + email;
    }
    // Getters và Setters
    public String getReaderId() { return readerId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }



    public void setReaderId(String readerId) {this.readerId = readerId; }
    public void setFullName(String fullName) {this.fullName = fullName; }
    public void setEmail(String email) {this.email = email;}
}
