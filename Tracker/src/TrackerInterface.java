import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TrackerInterface extends Remote {
    String sayHello() throws RemoteException;
    String returnParameters() throws RemoteException;

    /**
     * registe a new player
     * @return registe success or unccess
     * @throws RemoteException
     */
    String registeNewPlayer() throws RemoteException;

    /**
     * This function update Tracker's player list
     * @return success or unsccess
     * @throws RemoteException
     */
    String updatePlayerList(String PlayerList) throws RemoteException;

    /**
     * This function return the PlayerList stored in Tracker
     * @return Tracker's player list
     * @throws RemoteException
     */
    String getPlayerList() throws RemoteException;
}
