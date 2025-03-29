public class Sensor implements SensorObserver{
    private String sensorId;
    private boolean vehicleDetected;

    public Sensor(String sensorId) {
        this.sensorId = sensorId;
        this.vehicleDetected = false;
    }

    @Override
    public void update(String sensorId, boolean vehicleDetected) {
        this.vehicleDetected = vehicleDetected;
        System.out.println("Sensor: " + sensorId + " update: " + (vehicleDetected ? "vehicle detected" : "No vehicle detected"));
    }

    public String getSensorId() {
        return sensorId;
    }

    public boolean isVehicleDetected() {
        return vehicleDetected;
    }
}
