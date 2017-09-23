import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TrackerInterface extends Remote {
    String sayHello() throws RemoteException;
    String returnParameters() throws RemoteException;
}
