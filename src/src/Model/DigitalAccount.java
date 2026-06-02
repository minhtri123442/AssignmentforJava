package Model;

public abstract class DigitalAccount {

    protected  String accountId;
    protected String displayName;

    public  DigitalAccount(String accountId, String displayName) {
        this.accountId = accountId;
        this.displayName = displayName;
    }
    public abstract boolean authenticate(String password);
    public abstract int getDownloadLimit();

    public boolean requestDownload(int downloadsToday) {
        int limit  = getDownloadLimit();
        if( limit == -1 || downloadsToday < limit)
        {
            System.out.println(displayName + " tải xuống thành công: " + (downloadsToday + 1) + "/" + (limit == -1 ? "∞" : limit));
            return true;
        }
        System.out.println(displayName + " đã đạt giới hạn tải xuống hôm nay!");
        return false;
    }

}
