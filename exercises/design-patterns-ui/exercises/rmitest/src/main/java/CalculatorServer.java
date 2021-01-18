import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


//File name: CalculatorServer.java
public class CalculatorServer {
    public static void main(String[] args) {
        try {
            Naming.bind("rmi://localhost:1099/CalculatorServer", new CalculatorImpl());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}