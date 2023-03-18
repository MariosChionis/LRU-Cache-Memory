public class Cache<K,V> implements CacheInter<K,V>{

    private final int capacity;
    private final HashMap<K, V> map;
    private Node head;
    private Node tail;
    private int hits;
    private int misses;
    private int lookupsum;

    public Cache(int capacity) {
        this.capacity = capacity;
        this.map= new HashMap<>(capacity);
    }

    public V get(K key) {
        if (map.containsKey(key)) {
            Node node = new Node(key,map.get(key));
            removeNode(node);
            addNodeToHead(node);
            return node.value;
        } else {
            return null;
        }
    }

    private void addNodeToHead(Node node) {
        node.next = head;
        node.prev = null;
        if (head != null) {
            head.prev = node;
        }
        head = node;
        if (tail == null) {
            tail = head;
        }
    }

    private void removeNode(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }

    private class Node {
        K key;
        V value;
        Node prev;
        Node next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public V lookUp(K key) {
        this.lookupsum++;
        if(map.containsKey(key)){
            this.hits++;
            return map.get(key);
        }else{
            this.misses++;
            return null;
        }
    }

    @Override
    public void store(K key, V value) {
        if (map.containsKey(key)) {
            Node node = new Node(key,map.get(key));
            node.value = value;
            removeNode(node);
            addNodeToHead(node);
        } else {
            if (map.size() >= capacity) {
                map.remove(tail.key);
                removeNode(tail);
            }
            Node node = new Node(key, value);
            map.put(key,node.value);
            addNodeToHead(node);
        }
    }

    @Override
    public double getHitRatio() {
        return getHits()/getNumberOfLookUps();
    }

    @Override
    public long getHits() {
        return hits;
    }

    @Override
    public long getMisses() {
        return misses;
    }

    @Override
    public long getNumberOfLookUps() {
        return lookupsum;
    }
}