package Model;

public class GuestAccount extends DigitalAccount{
    public GuestAccount(String name) { super("GUEST", name); }
    @Override public boolean authenticate(String cred) { return true; } // Không cần mật khẩu
    @Override public int getDownloadLimit() { return 1; }
}
