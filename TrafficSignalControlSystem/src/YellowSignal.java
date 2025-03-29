public class YellowSignal implements SignalState{
    @Override
    public void handleSignal(TrafficSignal trafficSignal) {
        System.out.println(trafficSignal.getRoad() + ": Traffic signal is YELLOW");
        trafficSignal.setState(new RedSignal());
    }
}
