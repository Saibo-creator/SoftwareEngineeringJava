// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// You CAN change the bodies of existing methods/constructors
// You CAN add new private methods/constructors
// You CANNOT add interface implementations unless explicitly instructed to do so
// You CANNOT add new public, package-private or protected methods/constructors
// You CANNOT edit the names, parameters, checked exceptions or return types of existing methods/constructors
// You CANNOT delete existing methods/constructors
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
package homework03;

import homework03.util.Database;



import java.rmi.server.ExportException;
import java.util.*;
import java.util.function.Consumer;

/**
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * You CAN change the bodies of existing methods/constructors
 * You CAN add new private methods/constructors
 * You CANNOT add interface implementations unless explicitly instructed to do so
 * You CANNOT add new public, package-private or protected methods/constructors
 * You CANNOT edit the names, parameters, checked exceptions or return types of existing methods/constructors
 * You CANNOT delete existing methods/constructors
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
public class DatabaseImpl implements Database {

    private final HashMap<String, Set<String>> records= new HashMap<>();

    @Override
    public void put(String sciper, String day, Consumer<String> onSuccess, Consumer<Exception> onError) {
        if (sciper==null||day==null||onSuccess==null||onError==null) throw new IllegalArgumentException();
        try {
            records.putIfAbsent(sciper,new HashSet<>());
            records.get(sciper).add(day);
        } catch (Exception e){
            onError.accept(e);
        }
        onSuccess.accept(day);
    }

    @Override
    public void get(String sciper, Consumer<Set<String>> onSuccess, Consumer<Exception> onError) {
        if (sciper==null||onSuccess==null||onError==null) throw  new IllegalArgumentException();
        Set<String> days;
        try {
            days = records.getOrDefault(sciper, new HashSet<>());
        } catch (Exception e){
            onError.accept(e);
            return;
        }
        onSuccess.accept(days);
    }

    @Override
    public void getBookingsCount(String day, Consumer<Integer> onSuccess, Consumer<Exception> onError) {
        if (day==null||onSuccess==null||onError==null) throw  new IllegalArgumentException();
        int count = 0;
        try {
            Set<String> keySet = records.keySet();
            for (String key: keySet) {
                Set<String> values = records.getOrDefault(key,new HashSet<>());
                if (values.contains(day)) count++;
            }

        } catch (Exception e){
            onError.accept(e);
            return ;
        }

        onSuccess.accept(count);
    }
}
