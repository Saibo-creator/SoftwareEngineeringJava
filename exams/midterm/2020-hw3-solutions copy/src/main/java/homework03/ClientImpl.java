// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// You CAN change the bodies of existing methods/constructors
// You CAN add new private methods/constructors
// You CANNOT add interface implementations unless explicitly instructed to do so
// You CANNOT add new public, package-private or protected methods/constructors
// You CANNOT edit the names, parameters, checked exceptions or return types of existing methods/constructors
// You CANNOT delete existing methods/constructors
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
package homework03;

import homework03.util.Client;
import homework03.util.Server;

import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Pattern;

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
public class ClientImpl implements Client {
    private final String sciper;
    private final Server server;
    private static final String SCIPER_REGEX = "[0-9]{6}";
    /**
     * The constructor checks that the given SCIPER has the right format, i.e. 6 digits from 0 to 9.
     */
    public ClientImpl(String sciper, Server server) {
        if (sciper==null) throw new IllegalArgumentException("SCIPER must not be null");
        if (server==null) throw new IllegalArgumentException("server must not be null");
        if (!Pattern.matches(SCIPER_REGEX, sciper)) throw  new IllegalArgumentException("SCIPER doesn't have the right format");
        this.sciper = sciper;
        this.server = server;
    }

    @Override
    public void book(String day, Consumer<String> onSuccess, Consumer<Exception> onError) {
        if (day==null) throw new IllegalArgumentException("day must not be null");
        if (onSuccess==null) throw new IllegalArgumentException("onSuccess must not be null");
        if (onError==null) throw new IllegalArgumentException("onError must not be null");

        server.book(sciper,day,onSuccess,onError);
    }

    @Override
    public void getBookings(Consumer<Set<String>> onSuccess, Consumer<Exception> onError) {
        if (onSuccess==null) throw new IllegalArgumentException("onSuccess must not be null");
        if (onError==null) throw new IllegalArgumentException("onError must not be null");

        server.getBookings(sciper,onSuccess,onError);
    }
}
