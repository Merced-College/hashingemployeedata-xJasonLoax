import java.io.PrintStream;

public class ChainingHashTable<K, V> implements MapADT<K, V> {
   private ChainingHashTableItem<K, V>[] table;

   public ChainingHashTable() {
      // Initialize with an initial capacity of 11
      this(11);
   }

   public ChainingHashTable(int initialCapacity) {
      table = new ChainingHashTableItem[initialCapacity];
   }
   
   // Returns a non-negative hash code for the specified key.
   private int hash(K key) {
      long keyHash = key.hashCode();
      
      // Java's hashCode() method may return a negative number
      if (keyHash < 0) {
         keyHash += 2147483648L;
      }
      
      return (int)keyHash;
   }

   // Inserts the specified key/value pair. If the key already exists, the 
   // corresponding value is updated. If inserted or updated, true is returned. 
   // If not inserted, then false is returned.
   @Override
   public boolean insert(K key, V value) {
      // Hash the key to get the bucket index
      int bucketIndex = hash(key) % table.length;
      
      // Traverse the linked list, searching for the key. If the key exists in 
      // an item, the value is replaced. Otherwise a new item is appended.
      ChainingHashTableItem currentItem = table[bucketIndex];
      ChainingHashTableItem previousItem = null;
      while (currentItem != null) {
         if (key.equals(currentItem.key)) {
            currentItem.value = value;
            return true;
         }
         
         previousItem = currentItem;
         currentItem = currentItem.next;
      }
      
      // Append to the linked list
      if (table[bucketIndex] == null) {
         table[bucketIndex] = new ChainingHashTableItem(key, value);
      }
      else {
         previousItem.next = new ChainingHashTableItem(key, value);
      }

      return true;
   }

   // Searches for the specified key. If found, the key/value pair is removed 
   // from the hash table and true is returned. If not found, false is returned.
   @Override   
   public boolean remove(K key) {
      // Hash the key to get the bucket index
      int bucketIndex = hash(key) % table.length;
      
      // Search the bucket's linked list for the key
      ChainingHashTableItem currentItem = table[bucketIndex];
      ChainingHashTableItem previousItem = null;
      while (currentItem != null) {
         if (key.equals(currentItem.key)) {
            if (previousItem == null) {
               // Remove linked list's first item
               table[bucketIndex] = currentItem.next;
            }
            else {
               previousItem.next = currentItem.next;
            }
            return true;
         }
         
         previousItem = currentItem;
         currentItem = currentItem.next;
      }
      
      return false; // Key not found
   }
   
   // Searches for the key, returning the corresponding value if found, null if 
   // not found.
   @Override
   public V get(K key) {
      // Hash the key to get the bucket index
      int bucketIndex = hash(key) % table.length;
      
      // Search the bucket's linked list for the key
      ChainingHashTableItem<K, V> item = table[bucketIndex];
      while (item != null) {
         if (key.equals(item.key)) {
            return item.value;
         }
         item = item.next;
      }
      
      return null; // Key not found
   }
   
   @Override
   public boolean contains(K key) {
      return get(key) != null;
   }
   
   @Override 
   public int getLength() {
      int length = 0;
      
      // Loop through buckets
      for (var bucket : table) {
         ChainingHashTableItem<K,V> item = bucket;
         
         // Loop through the bucket's linked list
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
         }
         else {
            ChainingHashTableItem item = table[i];
            while (item != null) {
               out.print(String.format("%s, %s --> ", item.key.toString(), 
                  item.value.toString()));
               item = item.next;
            }
            out.println();
         }
      }
   }
   
   // Returns a string containing contents of each bucket
   @Override
   public String toString() {
      StringBuilder result = new StringBuilder();
      
      for (int i = 0; i < table.length; i++) {
         result.append(i + ": ");
         
         if (table[i] == null) {
            result.append("(empty)\n");
         }
         else {
            ChainingHashTableItem item = table[i];
            
            // Append first item and move to next
            result.append(item.key);
            result.append(": ");
            result.append(item.value);
            item = item.next;
            
            while (item != null) {
               result.append(" --> ");
               result.append(item.key);
               result.append(": ");
               result.append(item.value);
               item = item.next;
            }
            
            result.append("\n");
         }
      }
      
      return result.toString();
   }
}