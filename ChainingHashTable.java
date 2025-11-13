// Jason Loa

import java.io.PrintStream;

public class ChainingHashTable<K, V> implements MapADT<K, V> {
   private ChainingHashTableItem<K, V>[] table;

   public ChainingHashTable() {
      this(11);
   }

   @SuppressWarnings("unchecked")
   public ChainingHashTable(int initialCapacity) {
      table = new ChainingHashTableItem[initialCapacity];
   }
   
   // Returns a non-negative hash code for the specified key.
   private int hash(K key) {
      long keyHash = key.hashCode();
      if (keyHash < 0) {
         keyHash += 2147483648L;
      }
      return (int) keyHash;
   }

   @Override
   public boolean insert(K key, V value) {
      int bucketIndex = hash(key) % table.length;
      
      ChainingHashTableItem<K, V> currentItem = table[bucketIndex];
      ChainingHashTableItem<K, V> previousItem = null;

      while (currentItem != null) {
         if (key.equals(currentItem.key)) {
            currentItem.value = value;
            return true;
         }
         previousItem = currentItem;
         currentItem = currentItem.next;
      }
      
      if (table[bucketIndex] == null) {
         table[bucketIndex] = new ChainingHashTableItem<>(key, value);
      } else {
         previousItem.next = new ChainingHashTableItem<>(key, value);
      }

      return true;
   }

   @Override   
   public boolean remove(K key) {
      int bucketIndex = hash(key) % table.length;
      
      ChainingHashTableItem<K, V> currentItem = table[bucketIndex];
      ChainingHashTableItem<K, V> previousItem = null;

      while (currentItem != null) {
         if (key.equals(currentItem.key)) {
            if (previousItem == null) {
               table[bucketIndex] = currentItem.next;
            } else {
               previousItem.next = currentItem.next;
            }
            return true;
         }
         previousItem = currentItem;
         currentItem = currentItem.next;
      }
      return false;
   }
   
   @Override
   public V get(K key) {
      int bucketIndex = hash(key) % table.length;
      
      ChainingHashTableItem<K, V> item = table[bucketIndex];
      while (item != null) {
         if (key.equals(item.key)) {
            return item.value;
         }
         item = item.next;
      }
      return null;
   }
   
   @Override
   public boolean contains(K key) {
      return get(key) != null;
   }
   
   @Override 
   public int getLength() {
      int length = 0;
      for (var bucket : table) {
         ChainingHashTableItem<K, V> item = bucket;
         while (item != null) {
            length++;
            item = item.next;
         }
      }
      return length;
   }
   
   @Override
   public boolean isEmpty() {
      return getLength() == 0;
   }   
      
   @Override
   public void print(PrintStream out) {
      for (int i = 0; i < table.length; i++) {
         out.print(i + ": ");
         if (table[i] == null) {
            out.println("(empty)");
         } else {
            ChainingHashTableItem<K, V> item = table[i];
            while (item != null) {
               out.print(String.format("%s => %s --> ", item.key, item.value));
               item = item.next;
            }
            out.println("null");
         }
      }
   }
   
   @Override
   public String toString() {
      StringBuilder result = new StringBuilder();
      for (int i = 0; i < table.length; i++) {
         result.append(i + ": ");
         if (table[i] == null) {
            result.append("(empty)\n");
         } else {
            ChainingHashTableItem<K, V> item = table[i];
            while (item != null) {
               result.append(String.format("[%s => %s]", item.key, item.value));
               if (item.next != null) result.append(" --> ");
               item = item.next;
            }
            result.append("\n");
         }
      }
      return result.toString();
   }

   // ✅ Fixed printTable()
   public void printTable() {
      for (int i = 0; i < table.length; i++) {
         System.out.print("Bucket " + i + ": ");
         if (table[i] == null) {
            System.out.println("(empty)");
         } else {
            ChainingHashTableItem<K, V> item = table[i];
            while (item != null) {
               System.out.print("[" + item.key + " => " + item.value + "] ");
               item = item.next;
            }
            System.out.println();
         }
      }
   }
}