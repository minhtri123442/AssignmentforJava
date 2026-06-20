package Model;

public class Pair<K, V> {
    private K key;
    private V value;

    // Constructor không tham số
    public Pair() {
    }

    // Constructor đầy đủ tham số
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // Getter và Setter cho key
    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    // Getter và Setter cho value
    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    // Phương thức toString() để in thông tin Pair
    @Override
    public String toString() {
        return "Pair{" + "key=" + key + ", value=" + value + '}';
    }

    // Generic static method swap: trả về Pair đảo ngược key/value
    public static <K, V> Pair<V, K> swap(Pair<K, V> pair) {
        if (pair == null) {
            return null;
        }
        return new Pair<>(pair.getValue(), pair.getKey());
    }

    // Generic static method comparePairs: so sánh hai Pair theo value (V extends Comparable<V>)
    // Trả về true nếu p1.value >= p2.value, ngược lại trả về false
    public static <K, V extends Comparable<V>> boolean comparePairs(Pair<K, V> p1, Pair<K, V> p2) {
        if (p1 == null || p2 == null) {
            throw new IllegalArgumentException("Các cặp Pair so sánh không được để null");
        }
        // compareTo trả về > 0 nếu lớn hơn, = 0 nếu bằng nhau, < 0 nếu nhỏ hơn
        return p1.getValue().compareTo(p2.getValue()) >= 0;
    }


}
