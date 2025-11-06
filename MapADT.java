import java.io.PrintStream;

public interface MapADT<K, V> {
   // Methods that may change the map
   public boolean insert(K key, V value);
   public boolean remove(K key);
   
   // Methods that do not change the map
   public V get(K key);
   public boolean contains(K key);   
   public boolean isEmpty();
   public int getLength();
   public void print(PrintStream out);
}