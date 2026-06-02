package Model;

public class LibraryCardAccount extends DigitalAccount{
    public LibraryCardAccount(String id, String name) { super(id, name); }
    @Override public boolean authenticate(String pass) { return "pass123".equals(pass); }
    @Override public int getDownloadLimit() { return 3; }

}
