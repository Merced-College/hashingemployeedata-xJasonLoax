// Jason Loa
// 11/13/25
// code from book

public class ChainingHashTableItem<K, V> {
   public K key;
   public V value;
   public ChainingHashTableItem<K, V> next;

   public ChainingHashTableItem(K itemKey, V itemValue) {
      key = itemKey;
      value = itemValue;
      next = null;
   }
}