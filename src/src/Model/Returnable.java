package Model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public interface Returnable {

    // Abstract Methods
    void confirmReturn(String date);
    String getReturnDate();
    boolean isReturned();

    default boolean isLate(String dueDate) {
        if (dueDate == null || dueDate.isEmpty()) {
            return false;
        }
        try {
            LocalDate due = LocalDate.parse(dueDate); // Định dạng mặc định: YYYY-MM-DD
            LocalDate today = LocalDate.now();

            return today.isAfter(due);
        } catch (DateTimeParseException e) {
            System.out.println("Lỗi định dạng ngày (Yêu cầu: YYYY-MM-DD): " + e.getMessage());
            return false;
        }
    }
}
