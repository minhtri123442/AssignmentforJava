import Model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- KIỂM THỬ VỚI STACK STRING (LỊCH SỬ HÀNH ĐỘNG) ---");

        // • Tạo GenericStack<String> lưu lịch sử hành động
        GenericStack<String> actionStack = new GenericStack<>();
        actionStack.push("Muon sach B001");
        actionStack.push("Tra sach B002");
        actionStack.push("Cap nhat doc gia R001");

        // • In ra đỉnh ngăn xếp bằng peek()
        System.out.println("Phan tu o dinh (peek): " + actionStack.peek());

        // • Pop hai lần và in giá trị lấy ra
        System.out.println("Pop lan 1: " + actionStack.pop());
        System.out.println("Pop lan 2: " + actionStack.pop());

        // Kiểm tra lại đỉnh sau khi pop
        System.out.println("Phan tu o dinh sau khi pop: " + actionStack.peek());

        GenericStack<Integer> errorStack = new GenericStack<>();
        errorStack.push(100);
        errorStack.push(200);
        errorStack.push(404);

        System.out.println("Cac ma loi trong stack (tu dinh den day):");
        while (!errorStack.isEmpty()) {
            System.out.println(errorStack.pop());
        }


        Pair<String, Integer> book1 = new Pair<>("B001", 15);
        Pair<String, Integer> book2 = new Pair<>("B002", 8);

        System.out.println("Sách 1: " + book1);
        System.out.println("Sách 2: " + book2);

        boolean isBook1GreaterOrEqual = Pair.comparePairs(book1, book2);
        if (isBook1GreaterOrEqual) {
            System.out.println("=> Sách có số lượt mượn cao hơn (hoặc bằng): " + book1);
        } else {
            System.out.println("=> Sách có số lượt mượn cao hơn: " + book2);
        }

        Pair<String, String> readerInfo = new Pair<>("Nguyen Van A", "CARD12345");
        System.out.println("Trước khi swap (Tên -> Mã thẻ): " + readerInfo);

        Pair<String, String> swappedReaderInfo = Pair.swap(readerInfo);
        System.out.println("Sau khi swap (Mã thẻ -> Tên): " + swappedReaderInfo);




        //Generics
        Book b1 = new Book("B003", "Cấu trúc dữ liệu", "Thầy Tùng");
        Book b2 = new Book("B001", "Lập trình Java Generic", "Thầy Thuận");
        Book b3 = new Book("B002", "Thiết kế hướng đối tượng", "Thầy Đăng");

        b1.setQuantity(10);
        b2.setQuantity(5);
        b3.setQuantity(20);

        Book[] bookArray = { b1, b2, b3 };

        // Test findMin()
        Book minBook = LibraryUtils.findMin(bookArray);
        System.out.println("-> Cuốn sách có mã ID nhỏ nhất (findMin): " + minBook);

        Reader r1 = new Reader("R001", "Nguyễn Hoàng Thiên Quang", "quang@email.com") {
            @Override public int getMaxBookBorrow() { return 5; }
            @Override public String getInfo() { return toString(); }
            @Override public double calculateLateFee(int daysLate) { return daysLate * 5000.0; }
            @Override protected boolean checkSpecialCondition(Book book) { return true; }
            @Override protected String getSpecialConditionMessage() { return ""; }
        };

        Reader r2 = new Reader("R002", "Trần Nguyễn Như Ý", "nhuy@email.com") {
            @Override public int getMaxBookBorrow() { return 3; }
            @Override public String getInfo() { return toString(); }
            @Override public double calculateLateFee(int daysLate) { return daysLate * 7000.0; }
            @Override protected boolean checkSpecialCondition(Book book) { return true; }
            @Override protected String getSpecialConditionMessage() { return ""; }
        };

        List<Reader> readerList = new ArrayList<>();
        readerList.add(r1);
        readerList.add(r2);

        // Test printAll() với Unbounded Wildcard
        System.out.println("\n--- Danh sách độc giả hiện tại (printAll): ---");
        LibraryUtils.printAll(readerList);

        List<Book> srcBookList = new ArrayList<>();
        srcBookList.add(b1);
        srcBookList.add(b2);

        List<Object> destLibraryItems = new ArrayList<>();
        destLibraryItems.add("=== LOG HỆ THỐNG: DANH SÁCH SÁCH ĐƯỢC SAO CHÉP ===");

        LibraryUtils.copyList(destLibraryItems, srcBookList);

        System.out.println("\n--- Kết quả danh sách đích sau khi copyList: ---");
        LibraryUtils.printAll(destLibraryItems);
    }
}