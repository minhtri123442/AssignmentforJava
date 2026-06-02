package Model;

public class LecturerAccount extends DigitalAccount{
    public LecturerAccount(String id, String name) { super(id, name); }
    @Override public boolean authenticate(String pass) { return "pass123".equals(pass); }
    @Override public int getDownloadLimit() { return -1; }
}
