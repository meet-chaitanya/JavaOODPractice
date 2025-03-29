public class TrafficSignal {
    private SignalState state;
    private String road;

    public TrafficSignal(String road) {
        this.road = road;
        this.state = new RedSignal();
    }

    public void setState(SignalState state) {
        this.state = state;
    }

    public void changeSignal() {
        state.handleSignal(this);
    }

    public String getRoad() {
        return road;
    }
}
