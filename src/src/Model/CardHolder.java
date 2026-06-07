package Model;

public abstract class CardHolder extends Reader {
    private String cardExpiryDate;

    public CardHolder(String readerId, String fullName, String email) {
        super(readerId, fullName, email);
    }

    public void renewCard(int months) {
        System.out.println("Gia han the " + months + " thang cho độc giả: " + getFullName());
    }

    public String getCardExpiryDate() { return cardExpiryDate; }
    public void setCardExpiryDate(String cardExpiryDate) { this.cardExpiryDate = cardExpiryDate; }
}