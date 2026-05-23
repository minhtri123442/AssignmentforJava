package Model;

public enum ReaderType {
    Student(3),
    Lecture(5);

    private final int maxBookLimit;

    ReaderType(int maxBookLimit) {
        this.maxBookLimit = maxBookLimit;
    }

    public int getMaxBooksLimit() {
        return maxBookLimit;
    }


}
