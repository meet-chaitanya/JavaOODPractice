public class GreenSignal implements SignalState{
    @Override
    public void handleSignal(TrafficSignal trafficSignal) {
        System.out.println(trafficSignal.getRoad() + ": Traffic signal is GREEN");
        trafficSignal.setState(new YellowSignal());
    }
}
