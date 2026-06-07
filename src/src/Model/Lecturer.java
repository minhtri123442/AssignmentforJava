package Model;

public class Lecturer extends CardHolder{
    public Lecturer(String readerId, String fullName, String email) {
        super(readerId, fullName, email);
    }

    @Override
    public int getMaxBookBorrow() {
        return 5;
    }

    @Override
    public String toString() {
        return super.toString() + " | Loại: Giảng viên | Tối đa: " + getMaxBookBorrow() + " cuốn";
    }

    @Override
    public double calculateLateFee(int daysLate) { return daysLate * 1000; }

    @Override
    public String getInfo() {
        return "giảng viên: " + getReaderId() + " - " + getFullName()
                + " - Email: " + getEmail() + " - Han muon: " + getMaxBookBorrow() + " cuon";
    }

    @Override
    protected boolean checkSpecialCondition(Book book) { return true; }
    @Override
    protected String getSpecialConditionMessage() { return ""; }
}
