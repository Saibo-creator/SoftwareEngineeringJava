package sweng;

import java.util.Collection;

public interface Cache<K, V> {

    boolean contains(K key);

    void put(K key, V value);

    V get(K key);

    void clear();

}
