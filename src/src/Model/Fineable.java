package Model;

public interface Fineable {
    double FINE_PER_DAY = 5000.0;
    double MAX_FINE     = 200000.0;

    // 3 abstract methods liên quan đến tiền phạt
    void addFine(double amount);
    double getTotalFine();
    boolean hasPaidFine();
    void payFine();

    // default method
    default double calculateTotalFine(int daysOverdue) {
        return Math.min(daysOverdue * FINE_PER_DAY, MAX_FINE);
    }

    // static method
    static boolean isValidFineAmount(double amount) {
        return amount >= 0 && amount <= MAX_FINE;
    }
}