import java.util.Calendar;

public class Cache<K,V> implements CacheInter<K,V>{

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
                // bubbleSort(cache);
                System.out.println(cache.get(i).getArr(key)[0]);
                hits++;
                return cache.get(i).getVal(key);
            }
        }
        misses++;
        return null;
    }

    @Override
    public void store(K key,V value) {
        Object[] arr={null,null,null,null};
        if(cache.length()<capacity){
            cache.add(new HashMap<>());
            cache.get(cache.length()-1).put(key,value,arr);
        }else{
            cache.delete(cache.get(cache.length()));
            cache.add(new HashMap<>());
            cache.get(cache.length()-1).put(key,value,arr);
        }
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
    //     Object temp;
    //     for (int i = 2; i < unsorted.length(); i++) {
    //         for (int j =unsorted.length(); j >i; j--){
    //             if((int)unsorted.get(j-1).getTable().getArray()[0]>(int)unsorted.get(j-1).getTable().getArray()[0]){
    //                 temp=unsorted.get(j-1).getTable().getArray()[0];
    //                 unsorted.get(j-1).getTable().getArray()[0]=unsorted.get(j-1).getTable().getArray()[0];
    //                 unsorted.get(j-1).getTable().getArray()[0]=temp;
    //             }else if((int)unsorted.get(j-1).getTable().getArray()[0]==(int)unsorted.get(j-1).getTable().getArray()[0]){
    //                 if((int)unsorted.get(j-1).getTable().getArray()[1]>(int)unsorted.get(j-1).getTable().getArray()[1]){
    //                     temp=unsorted.get(j-1).getTable().getArray()[1];
    //                     unsorted.get(j-1).getTable().getArray()[1]=unsorted.get(j-1).getTable().getArray()[1];
    //                     unsorted.get(j-1).getTable().getArray()[1]=temp;
    //                 }else if((int)unsorted.get(j-1).getTable().getArray()[1]==(int)unsorted.get(j-1).getTable().getArray()[1]){
    //                     if((int)unsorted.get(j-1).getTable().getArray()[2]>(int)unsorted.get(j-1).getTable().getArray()[2]){
    //                         temp=unsorted.get(j-1).getTable().getArray()[2];
    //                         unsorted.get(j-1).getTable().getArray()[2]=unsorted.get(j-1).getTable().getArray()[2];
    //                         unsorted.get(j-1).getTable().getArray()[2]=temp;
    //                     }else if((int)unsorted.get(j-1).getTable().getArray()[2]==(int)unsorted.get(j-1).getTable().getArray()[2]){
    //                         if((int)unsorted.get(j-1).getTable().getArray()[3]>(int)unsorted.get(j-1).getTable().getArray()[3]){
    //                             temp=unsorted.get(j-1).getTable().getArray()[3];
    //                             unsorted.get(j-1).getTable().getArray()[3]=unsorted.get(j-1).getTable().getArray()[3];
    //                             unsorted.get(j-1).getTable().getArray()[3]=temp;
    //                         }
    //                     }
    //                 }
    //             }
    //         }
    //     }
    //     return unsorted;
    // }
    
}
