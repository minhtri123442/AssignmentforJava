package Model;

public class GuestReader extends Reader {

    public GuestReader(String readerId, String fullName, String email) {
        super(readerId, fullName, email);
    }

    @Override
    public int getMaxBookBorrow() {
        return 0;
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return 0.0;
    }

    @Override
    public String getInfo() {
        return super.toString() + " [Khách vãng lai - Chỉ đọc tại chỗ]";
    }
}