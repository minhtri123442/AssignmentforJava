package Model;

public class Fine implements Fineable {
    private String fineId;
    private String readerId;
    private double totalFine;
    private boolean paid;

    public Fine(String fineId, String readerId) {
        this.fineId   = fineId;
        this.readerId = readerId;
        this.paid = false;
        this.totalFine = 0.0;
    }

    @Override
    public void addFine(double amount) {
        if (Fineable.isValidFineAmount(amount)) {
            totalFine += amount;
        }
    }

    @Override
    public double getTotalFine() { return totalFine; }

    @Override
    public boolean hasPaidFine() { return paid; }

    @Override
    public void payFine() {
        System.out.printf("Fine paid: %,.0f VND%n", totalFine);
        paid = true;
        totalFine = 0;
    }
}