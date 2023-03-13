import java.util.Calendar;

public class Cache<K,V> implements CacheInter{

    private V value;
    private K key;
    private int capacity;
    private SinglyLinkedList<HashMap<K,V,Object>> cache;
    private int hits;
    private int misses;
    private int lookupsum;


    public Cache(int capacity){

        this.capacity=capacity;

        this.cache=new SinglyLinkedList<HashMap<K,V,Object>>();

        this.hits=0;
        this.misses=0;
        this.lookupsum=0;

    }

    @Override
    public V lookUp(Object key) {
        lookupsum++;
        for (int i = 0; i < cache.length(); i++) {
            if(cache.get(i).getTable().getKey()==key){
                createAccessTime((cache.get(i).getArr(key)));
                hits++;
                return cache.get(i).getVal(key);
            }
        }
        misses++;
        return null;
    }

    @Override
    public void store(Object key, Object value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'store'");
    }

    @Override
    public double getHitRatio() {
        return hits/lookupsum;
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

    public Object[] createAccessTime(Object[] timearray){

        Calendar date=Calendar.getInstance();

        timearray[0]=date.get(Calendar.HOUR);
        timearray[1]=date.get(Calendar.MINUTE);
        timearray[2]=date.get(Calendar.SECOND);
        timearray[3]=date.get(Calendar.MILLISECOND);

        return timearray;
    }

    // public SinglyLinkedList<HashMap<K,V,Object>> bubbleSort(SinglyLinkedList<HashMap<K,V,Object>> unsorted){

    // }
    
}
