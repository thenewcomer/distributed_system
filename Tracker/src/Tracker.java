
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import org.json.JSONObject;

public class Tracker {
    private Tracker() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            TrackerInterface stub = (TrackerInterface) registry.lookup("Hey");
            String response = stub.sayHello();
            String jsonString = stub.returnParameters();
            System.out.println("response: " + response);
            JSONObject jsonObj = new JSONObject(jsonString);
            System.out.println(jsonObj.toString());
            System.out.println(jsonObj.getInt("N"));

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

}
