public class HashMap<K,V> {

    private final int size=100;

    private Entry<K,V> table[];

    private class Entry<K,V>{
        private K key;
        private V value;
        private Entry<K,V> next;

        public Entry(K key, V value){
            this.key=key;
            this.value=value;
        }

        public K getKey(){
            return this.key;
        }

        public V getValue(){
            return this.value;
        }

        public void setValue(V value){
            this.value=value;
        }
    }

    public HashMap(){
        table=new Entry[size];
    }

    public void put(K key,V value){
        int hash=key.hashCode() % size;
        Entry<K,V> entry=table[hash];

        if (entry==null){
            table[hash]= new Entry<K,V>(key, value);
        }else{
            while(entry.next!=null){
                if (entry.getKey()==key){
                    entry.setValue(value);
                    return;
                }
                entry=entry.next;
            }
            if (entry.getKey()==key){
                entry.setValue(value);
                return;
            }
            entry.next= new Entry<K,V>(key,value);
        }
    }

    public V get(K key){
        int hash= key.hashCode() % size;
        Entry<K,V> entry =table[hash];

        if (entry==null){
            return null;
        }

        while(entry!=null){
            if (entry.getKey()==key){
                return entry.getValue();
            }
            entry=entry.next;
        }

        return null;
    }

    public Entry<K,V> remove(K key){
        int hash= key.hashCode() % size;
        Entry<K,V> entry =table[hash];

        if (entry==null){
            return null;
        }
        if (entry.getKey()==key){
            table[hash]=entry.next;
            entry.next=null;
            return entry;
        }

        Entry <K,V> prev=entry;
        entry=entry.next;

        while(entry!=null){
            if (entry.getKey()==key){
                prev.next=entry.next;
                entry.next=null;
                return entry;
            }
        }
        return null;
    }
}
