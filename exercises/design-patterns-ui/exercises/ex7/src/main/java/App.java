import java.rmi.Naming;
import java.rmi.RemoteException;

public class App {
    public static void main(String[] args) {
        KeyValueStoreRemote store;

        try {
            store = (KeyValueStoreRemote) Naming.lookup("rmi://localhost:1099/KeyValueStoreRemoteServer");

            store.put(3, 1993);
            System.out.println(store.get(3));

            store.put(9, 1873);
            System.out.println(store.get(9));

            store.remove(3);
            System.out.println(store.get(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
