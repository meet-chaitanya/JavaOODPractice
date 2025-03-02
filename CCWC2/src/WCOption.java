public enum WCOption {
    L("-l"), M("-m"), W("-w"), C("-c"), D("default");
    private final String value;
    WCOption(String s) {
        this.value = s;
    }

    public String getValue() {
        return value;
    }

    public static WCOption findByOption(String s) {
        for (WCOption option1: values()) {
           if (option1.getValue().equalsIgnoreCase(s)){
                return option1;
           }
        }
        return D;
    }
}
