public class Main {
    public static void main(String[] args){

        Cache<String,String> cache=new Cache<>(100);

        cache.store("trospe","123456789");
        cache.store("trospe1","123456789");

        cache.store("trospe2","123456789");
        cache.store("trospe3","123456789");

        System.out.println(cache.lookUp("trospe2"));
    }
}
