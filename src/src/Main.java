import Model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void processAllBorrowable(List<Borrowable> items) {
        System.out.println("=== BORROWABLE ITEM STATUS ===");
        for (Borrowable item : items) {
            String s = item.isAvailable()
                    ? "Available"
                    : "Borrowed by " + item.getBorrowerId();
            System.out.println("  -> " + s);
        }
    }

    // Phương thức đa hình gửi thông báo đến các đối tượng implement Notifiable
    public static void notifyAll(List<Notifiable> users, String message) {
        System.out.println("=== SENDING NOTIFICATIONS ===");
        for (Notifiable user : users) {
            user.sendNotification(message);
        }
    }
    public static void main(String[] args) {
        Borrowable book1 = new Book("B001", "Clean Code", "Robert Martin");
        Borrowable book2 = new Book("B002", "Design Patterns", "GoF");

        book1.borrowBy("R001", "2024-09-01");
        System.out.println("Available: " + book2.isAvailable()); // true

        // Dung static method cua interface
        System.out.println(Borrowable.isValidBorrowDuration(10)); // true
        System.out.println(Borrowable.isValidBorrowDuration(20)); // false

        // Dung default method
        System.out.println(book1.calculateFine(3)); // 15000.0

        book1.returnBook("2024-09-15");


        List<Borrowable> items = new ArrayList<>();
        Book b1 = new Book("B003", "Refactoring", "Martin Fowler");
        Book b2 = new Book("B004", "Effective Java", "Joshua Bloch");
        b1.borrowBy("R002", "2026-06-20");

        items.add(b1);
        items.add(b2);
        processAllBorrowable(items); // Gọi phương thức đa hình
        System.out.println();


        List<Notifiable> readers = new ArrayList<>();
        readers.add(new Student("R001", "Ngô Minh Thuận", "Thuan@hcmute.edu.vn"));
        readers.add(new Student("R002", "Nguyễn Thành Dương", "Duong@hcmute.edu.vn"));

        notifyAll(readers, "Thu vien se dong cua vao ngay le.");

        System.out.println("\n--- TEST METHOD NOTIFIABLE ---");
        readers.get(0).sendOverdueNotification();

    }
}