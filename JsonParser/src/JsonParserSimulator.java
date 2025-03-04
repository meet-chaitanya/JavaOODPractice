public class JsonParserSimulator {
    public static void main(String[] args) {
        String jsonString = "{\n" +
                "  \"orderId\": \"AWJSHSHSU\",\n" +
                "  \"customer\": {\n" +
                "    \"name\": \"JaneDoe\",\n" +
                "    \"address\": {\n" +
                "      \"street\": \"ABCMainSt\",\n" +
                "      \"city\": \"NewYork\",\n" +
                "      \"state\": \"NY\",\n" +
                "      \"zipCode\": 10001\n" +
                "    },\n" +
                "    \"isMember\": true\n" +
                "  },\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"productId\": \"PWUWU\",\n" +
                "      \"name\": \"WirelessMouse\",\n" +
                "      \"quantity\": 2,\n" +
                "      \"price\": 25.5\n" +
                "    },\n" +
                "    {\n" +
                "      \"productId\": \"PUSJS\",\n" +
                "      \"name\": \"Keyboard\",\n" +
                "      \"quantity\": 1,\n" +
                "      \"price\": 45\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"Processing\",\n" +
                "  \"isPaid\": false,\n" +
                "  \"totalAmount\": 96\n" +
                "}";
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(jsonString);
    }
}