package Model;

public class StudentReader extends Reader {

    public StudentReader(String readerId, String fullName, String email) {
        super(readerId, fullName, email);
    }

    @Override
    public int getMaxBookBorrow() { return 5; }

    @Override
    public String getInfo() { return toString(); }

    @Override
    public double calculateLateFee(int daysLate) { return daysLate * 5000.0; }

    @Override
    protected boolean checkSpecialCondition(Book book) { return true; }

    @Override
    protected String getSpecialConditionMessage() { return "Không có điều kiện đặc biệt."; }
}