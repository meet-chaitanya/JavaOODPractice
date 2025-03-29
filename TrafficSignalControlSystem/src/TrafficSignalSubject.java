public interface TrafficSignalSubject {
    void attach(SensorObserver observer);
    void detach(SensorObserver observer);
    void notifyObservers();
}
