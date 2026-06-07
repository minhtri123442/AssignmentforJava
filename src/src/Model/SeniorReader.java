package Model;

public class SeniorReader extends CardHolder {
    private String seniorCardNumber;

    public SeniorReader(String readerId, String fullName, String email, String seniorCardNumber) {
        super(readerId, fullName, email);
        this.seniorCardNumber = seniorCardNumber;
    }

    public String getSeniorCardNumber() {
        return seniorCardNumber;
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return 0.0;
    }

    @Override
    public int getMaxBookBorrow() {
        return Integer.MAX_VALUE;
    }

    @Override
    public String getInfo() {
        return "Ma the: "+ seniorCardNumber + " [NCT - MIEN PHAT]";
    }


    /*
    * không thể gọi được getInfo() nếu như hàm đó là 1 hàm trừu tượng, không có thân hàm=> không thể gọi super được.
    * */

    @Override
    protected boolean checkSpecialCondition(Book book) { return true; }

    @Override
    protected String getSpecialConditionMessage() { return ""; }

    @Override
    protected void onBorrowSuccess(Book book) {
        super.onBorrowSuccess(book);
        System.out.println("  ->Nguoi cao tuoi — khong thu phi phat");
    }
}