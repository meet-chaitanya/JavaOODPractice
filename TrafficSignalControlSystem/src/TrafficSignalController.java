import java.util.ArrayList;
import java.util.List;

public class TrafficSignalController implements TrafficSignalSubject{
    private List<TrafficSignal> signalList;
    private List<SensorObserver> observers;
    private boolean emergencyVehicleDetected;

    public TrafficSignalController(List<TrafficSignal> signalList) {
        this.signalList = signalList;
        this.observers = new ArrayList<>();
        this.emergencyVehicleDetected = false;
    }

    @Override
    public void attach(SensorObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(SensorObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (SensorObserver observer: observers) {
            observer.update("Emergency", emergencyVehicleDetected);
        }
    }

    public void detectEmergency() {
        emergencyVehicleDetected = true;
        System.out.println("Emergency vehicle detected! All signals switched to GREEN for emergency vehicle.");
        notifyObservers();
    }

    public void clearEmergency() {
        emergencyVehicleDetected = false;
        System.out.println("Emergency vehicle cleared. Normal Signal operation resumed.");
        notifyObservers();
    }

    public void manageSignals() {
        for (TrafficSignal signal: signalList) {
            signal.changeSignal();
        }
    }
}
