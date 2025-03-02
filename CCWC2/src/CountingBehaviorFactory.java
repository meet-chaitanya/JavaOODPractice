public class CountingBehaviorFactory {

    public CountBehavior createCountingBehavior(String option) {
        CountBehavior countBehavior;
        WCOption option1 = WCOption.findByOption(option);
        switch (option1) {
            case W -> {
                countBehavior = new WordCounting();
            }
            case L -> {
                countBehavior = new LineCounting();
            }
            case M -> {
                countBehavior = new CharacterCounting();
            }
            case C -> {
                countBehavior = new ByteCounting();
            }
            default -> {
                countBehavior = new DefaultCounting();
            }
        }
        return countBehavior;
    }
}
