public class HashMap<K,V,A> {

    private final int size=100;

    private Entry<K,V,A> table[];

    class Entry<K,V,A>{
        private K key;
        private V value;
        private A[] array;
        private Entry<K,V,A> next;

        public Entry(K key, V value){
            this.key=key;
            this.value=value;
            this.array=null;
        }

        public K getKey(){
            return this.key;
        }

        public V getValue(){
            return this.value;
        }

        public A[] getArray(){
            return this.array;
        }

        public void setValue(V value){
            this.value=value;
        }

        public void setArray(A[] array){
            this.array=array;
        }
    }

    public HashMap(){

        this.table=new Entry[size];

    }

    public Entry<K,V,A> getTable(){
        return this.table[0];
    }

    public void put(K key,V value, A[] array){
        int hash=key.hashCode() % size;
        Entry<K,V,A> entry=table[hash];

        if (entry==null){
            table[hash]= new Entry<K,V,A>(key, value);
        }else{
            while(entry.next!=null){
                if (entry.getKey()==key){
                    entry.setValue(value);
                    entry.setArray(array);
                    return;
                }
                entry=entry.next;
            }
            if (entry.getKey()==key){
                entry.setValue(value);
                entry.setArray(array);
                return;
            }
            entry.next= new Entry<K,V,A>(key,value);
        }
    }

    public V getVal(Object key){
        int hash= key.hashCode() % size;
        Entry<K,V,A> entry =table[hash];

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

    public A[] getArr(Object key){
        int hash= key.hashCode() % size;
        Entry<K,V,A> entry =table[hash];

        if (entry==null){
            return null;
        }

        while(entry!=null){
            if (entry.getKey()==key){
                return entry.getArray();
            }
            entry=entry.next;
        }

        return null;
    }

    public Entry<K,V,A> remove(K key){
        int hash= key.hashCode() % size;
        Entry<K,V,A> entry =table[hash];

        if (entry==null){
            return null;
        }
        if (entry.getKey()==key){
            table[hash]=entry.next;
            entry.next=null;
            return entry;
        }

        Entry<K,V,A> prev=entry;
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
