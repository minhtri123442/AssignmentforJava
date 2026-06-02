package Model;

public class Student extends Reader {

    public Student(String readerId, String fullName, String email)
    {
        super(readerId,fullName, email);
    }

    @Override
    public int getMaxBookBorrow() {
        return 3;
    }


    @Override
    public String toString() {
        return super.toString() + " | Loại: Sinh viên | Tối đa: " + getMaxBookBorrow() + " cuốn";
    }
    @Override
    public double calculateLateFee(int daysLate) { return daysLate * 2000; }

    @Override
    public String getInfo() {
        return "Sinh viên: " + getReaderId() + " - " + getFullName()
                + " - Email: " + getEmail() + " - Han muon: " + getMaxBookBorrow() + " cuon";
    }
}
