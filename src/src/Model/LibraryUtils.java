package Model;
import java.util.List;

public class LibraryUtils {

    public static <T extends Comparable<T>> T findMin(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        T min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(min) < 0) {
                min = array[i];
            }
        }
        return min;
    }

    public static void printAll(List<? extends Object> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

    public static <T> void copyList(List<? super T> dest, List<? extends T> src) {
        if (src == null || dest == null) {
            return;
        }
        for (T item : src) {
            dest.add(item);
        }
    }
}
