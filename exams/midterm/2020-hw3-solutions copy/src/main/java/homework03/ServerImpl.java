// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// You CAN change the bodies of existing methods/constructors
// You CAN add new private methods/constructors
// You CANNOT add interface implementations unless explicitly instructed to do so
// You CANNOT add new public, package-private or protected methods/constructors
// You CANNOT edit the names, parameters, checked exceptions or return types of existing methods/constructors
// You CANNOT delete existing methods/constructors
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
package homework03;

import homework03.util.AlreadyBookedException;
import homework03.util.Database;
import homework03.util.DayFullException;
import homework03.util.Server;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
public class ServerImpl implements Server {
    private static final int BOOKINGS_PER_DAY_THRESHOLD = 5;
    private final Database db;
    private final Map<String,Integer> bookingsPerDay;

    public ServerImpl(Database database) {
        if (database==null) throw new IllegalArgumentException();
        db = database;
        bookingsPerDay = new HashMap<>();

    }

    @Override
    public void book(String sciper, String day, Consumer<String> onSuccess, Consumer<Exception> onError) {
        if (sciper==null||day==null||onSuccess==null||onError==null) throw new IllegalArgumentException();


        db.get(sciper, new Consumer<Set<String>>() {
            @Override
            public void accept(Set<String> bookedDays) {
                if (bookedDays.contains(day)) {
                    onError.accept( new AlreadyBookedException(""));
                    return;
                }
                int numBookings = bookingsPerDay.getOrDefault(day,0);
                if (numBookings>BOOKINGS_PER_DAY_THRESHOLD) {
                    onError.accept( new DayFullException(""));
                    return;
                }
                db.put(sciper,day,onSuccess,onError);
                bookingsPerDay.put(day, bookingsPerDay.getOrDefault(day,0)+1);

            }
        },onError);
    }

    @Override
    public void getBookings(String sciper, Consumer<Set<String>> onSuccess, Consumer<Exception> onError) {
        if (sciper==null||onSuccess==null||onError==null) throw new IllegalArgumentException();
        db.get(sciper,onSuccess,onError);
    }
}

