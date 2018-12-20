package applicationManagement;

public class SimulationStarter {

    private static final ApplicationFlow simulation = new ApplicationFlow();

    public static void start() {
        simulation.simulate();
    }
}
