public class HashMap<K, V> {

    private Entry<K, V>[] buckets;
    private int size;

    public HashMap(int capacity) {
        buckets = new Entry[capacity];
        size = 0;
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> entry = buckets[bucketIndex];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
            entry = entry.getNext();
        }
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.setNext(buckets[bucketIndex]);
        buckets[bucketIndex] = newEntry;
        size++;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> entry = buckets[bucketIndex];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
            entry = entry.getNext();
        }
        return null;
    }

    public boolean containsKey(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> entry = buckets[bucketIndex];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return true;
            }
            entry = entry.getNext();
        }
        return false;
    }

    public void remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> entry = buckets[bucketIndex];
        Entry<K, V> prevEntry = null;
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                if (prevEntry == null) {
                    buckets[bucketIndex] = entry.getNext();
                } else {
                    prevEntry.setNext(entry.getNext());
                }
                size--;
                return;
            }
            prevEntry = entry;
            entry = entry.getNext();
        }
    }

    public int size() {
        return size;
    }

    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }
    }
}
