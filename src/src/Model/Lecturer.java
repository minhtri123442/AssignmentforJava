package Model;

public class Lecturer extends Reader{
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


}
