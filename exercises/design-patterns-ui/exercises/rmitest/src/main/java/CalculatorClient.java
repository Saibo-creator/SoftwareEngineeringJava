

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


//File name: CalculatorClient.java
public class CalculatorClient {
    public static void main(String[] args) {
        try {
            Calculator calculator = (Calculator) Naming.lookup("rmi://localhost:1099/CalculatorServer");
            System.out.println(calculator.add(1, 1));
            System.out.println(calculator.sub(1, 1));
            System.out.println(calculator.mul(1, 1));
            System.out.println(calculator.div(1, 1));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
