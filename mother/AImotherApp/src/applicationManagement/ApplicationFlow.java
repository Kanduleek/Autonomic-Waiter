package applicationManagement;

import controllers.RestaurantController;

public class ApplicationFlow {

    // restaurant controller
    RestaurantController controller = new RestaurantController();

    // two threads, first controls waiter, second generate customers in time (while(true) loops in the threads bodies)
    Thread waiter;
    Thread customers;

    public void simulate() {
        // this method runs the threads
    }
}
