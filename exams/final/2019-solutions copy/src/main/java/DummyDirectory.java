import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyDirectory implements  Directory{
    Map<String, List<Person>> map;


    public DummyDirectory() {
        map= new HashMap<>();
        Person jack=new Person("Jack","student",22,"Lausanne");
        Person joe=new Person("joe","student",24,"Lausanne");
        Person thomas=new Person("Thomas","student",24,"Ecublens");
        map.put("jack", Arrays.asList(jack,joe));
        map.put("thomas", Arrays.asList(thomas));
    }

    @Override
    public List<Person> search(String name) {
        return map.get(name);
    }

    public void addRecord(String name, List<Person> personList){
        map.put(name,personList);
    }

    public void deleteRecord(String name){
        map.remove(name);
    }
}
