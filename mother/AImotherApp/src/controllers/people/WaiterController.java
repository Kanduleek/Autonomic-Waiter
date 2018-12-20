package controllers.people;

import entities.Waiter;
import views.RestaurantViewConnector;

public class WaiterController {

    // model
    private Waiter model;
    // view
    private RestaurantViewConnector view;

    public WaiterController() {
        model = new Waiter();
        view = RestaurantViewConnector.getInstance();
    }


    public void move(int startX, int startY, int endX, int endY) {

    }
}
