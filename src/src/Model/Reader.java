package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public abstract class Reader implements Notifiable {

    private String readerId;
    private String fullName;
    private String email;
    protected int currentBorrowCount = 0;
    private List<String> notifications = new ArrayList<>();
    public Reader(String readerId, String fullName, String email)
    {
        this.readerId = readerId;
        this.fullName = fullName;
        this.email = email;
    }

    @Override
    public void sendNotification(String message) {
        notifications.add(message);
        System.out.println("[" + fullName + "] " + message);
    }

    @Override
    public List<String> getNotificationHistory() {
        // Trả về list bọc unmodifiable để bảo mật dữ liệu không cho sửa từ bên ngoài
        return Collections.unmodifiableList(notifications);
    }

    public abstract int getMaxBookBorrow();
    public abstract String getInfo();
    public abstract double calculateLateFee(int daysLate);

    public final BorrowResult processBorrow(Book book) {
        // Bước 1: Kiểm tra giới hạn số lượng mượn (Cố định chung)
        if (!checkBorrowQuota()) {
            return new BorrowResult(false, "Da dat gioi han muon: " + getMaxBookBorrow() + " cuon");
        }

        // Bước 2: Kiểm tra điều kiện đặc thù của từng loại độc giả (Abstract)
        if (!checkSpecialCondition(book)) {
            return new BorrowResult(false, getSpecialConditionMessage());
        }

        // Bước 3: Trừ tồn kho sách (Cố định chung)
        if (book.getQuantity() <= 0) {
            return new BorrowResult(false, "Sach da het hang trong kho!");
        }
        book.decreaseStock();
        currentBorrowCount++;

        // Bước 4: Hook method - hành động mở rộng sau khi mượn thành công
        onBorrowSuccess(book);

        return new BorrowResult(true, "Muon thanh cong: " + book.getTitle());
    }

    // Bước 1: Cố định dùng chung nội bộ
    private boolean checkBorrowQuota() {
        return currentBorrowCount < getMaxBookBorrow();
    }

    // Bước 2: Ép các lớp con tự định nghĩa điều kiện đặc thù
    protected abstract boolean checkSpecialCondition(Book book);
    protected abstract String getSpecialConditionMessage();

    // Bước 4: Hook method (Có thể override hoặc không)
    protected void onBorrowSuccess(Book book) {
        System.out.println(getFullName() + " muon thanh cong: " + book.getTitle());
    }


    //ghi de
    @Override
    public String toString() {
        return "[Mã Độc Gỉa:  "+ readerId+"] Họ tên: "+ fullName + " - Email:" + email;
    }
    // Getters và Setters
    public String getReaderId() { return readerId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }



    public void setReaderId(String readerId) {this.readerId = readerId; }
    public void setFullName(String fullName) {this.fullName = fullName; }
    public void setEmail(String email) {this.email = email;}
}
