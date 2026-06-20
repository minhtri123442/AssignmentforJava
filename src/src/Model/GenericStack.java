package Model;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

// 1. Xây dựng class GenericStack<T> mô phỏng cấu trúc dữ liệu ngăn xếp (LIFO)
public class GenericStack<T> {
    // Field: danh sách nội bộ
    private List<T> elements;

    // Constructor khởi tạo ngăn xếp
    public GenericStack() {
        this.elements = new ArrayList<>();
    }

    // Thêm item vào đỉnh ngăn xếp
    public void push(T item) {
        elements.add(item);
    }

    // Lấy và xóa phần tử ở đỉnh. Ném EmptyStackException nếu rỗng
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.remove(elements.size() - 1);
    }

    // Xem phần tử đỉnh mà không xóa. Ném EmptyStackException nếu rỗng
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.get(elements.size() - 1);
    }

    // Trả về true nếu ngăn xếp rỗng
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    // Trả về số phần tử hiện có
    public int size() {
        return elements.size();
    }


}
