package views;

public class RestaurantViewConnector {

    String location = "";
    Process viewProcess;



    private static final class SingletonHolder {
        public static final RestaurantViewConnector INSTANCE = new RestaurantViewConnector();
    }

    public static final RestaurantViewConnector getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
