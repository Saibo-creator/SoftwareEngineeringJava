

import java.rmi.Remote;
import java.rmi.RemoteException;

//File name: Calculator.java
public interface Calculator extends Remote {
    int add(int a, int b) throws RemoteException;
    int sub(int a, int b) throws RemoteException;
    int mul(int a, int b) throws RemoteException;
    int div(int a, int b) throws RemoteException;
}