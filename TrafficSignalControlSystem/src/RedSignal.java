public class RedSignal implements SignalState{
    @Override
    public void handleSignal(TrafficSignal trafficSignal) {
        System.out.println(trafficSignal.getRoad() + ": Traffic Signal is RED");
        trafficSignal.setState(new GreenSignal());
    }
}
