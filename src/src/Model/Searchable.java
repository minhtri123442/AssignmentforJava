package Model;

import java.util.List;

public interface Searchable {

    // Abstract Methods
    List<Book> searchByTitle(String kw);
    List<Book> searchByAuthor(String kw);

    static String normalizeKeyword(String str) {
        if (str == null) return "";
        return str.trim().toLowerCase().replaceAll("\\s+", " ");
    }
}
