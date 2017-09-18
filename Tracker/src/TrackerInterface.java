import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TrackerInterface extends Remote {
    String sayHello() throws RemoteException;
    String returnParameters() throws RemoteException;
    String registeNewPlayer() throws RemoteException;

    /**
     * This function
     * @return
     * @throws RemoteException
     */
    String updatePlayerList() throws RemoteException;

    /**
     * This function return the PlayerList stored in Tracker
     */
    String getPlayerList() throws RemoteException;
}
