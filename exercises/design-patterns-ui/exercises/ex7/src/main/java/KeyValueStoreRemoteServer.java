import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class KeyValueStoreRemoteServer {
    public static void main(String[] args)  {
        KeyValueStoreRemoteImpl keyValueStoreRemoteImpl;
        try {
            keyValueStoreRemoteImpl = new KeyValueStoreRemoteImpl();
            Naming.bind("rmi://localhost:1099/KeyValueStoreRemoteServer", keyValueStoreRemoteImpl);
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
