import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Sensor northSensor = new Sensor("North-Sensor");
        Sensor southSensor = new Sensor("South-Sensor");
        Sensor eastSensor = new Sensor("East-Sensor");

        TrafficSignal northSignal = new TrafficSignal("North");
        TrafficSignal southSignal = new TrafficSignal("South");
        TrafficSignal eastSignal = new TrafficSignal("East");

        TrafficSignalController controller = new TrafficSignalController(Arrays.asList(northSignal, southSignal, eastSignal));


        controller.attach(northSensor);
        controller.attach(southSensor);
        controller.attach(eastSensor);


        System.out.println("Simulating normal signal operation...");
        controller.manageSignals();

        System.out.println("Simulating emergency vehicle detection...");
        controller.detectEmergency();
    }
}