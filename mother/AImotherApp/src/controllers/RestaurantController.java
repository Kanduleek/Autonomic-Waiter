package controllers;

import controllers.people.CustomerController;
import controllers.people.WaiterController;
import controllers.rooms.HallController;
import controllers.rooms.KitchenController;
import views.RestaurantViewConnector;

public class RestaurantController {

    // controllers
    CustomerController customerController = new CustomerController();
    WaiterController waiterController = new WaiterController();

    HallController hallController = new HallController();
    KitchenController kitchenController = new KitchenController();

    // graphics app in python (view)
    RestaurantViewConnector view = RestaurantViewConnector.getInstance();



}
