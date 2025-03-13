public class RESPTest {
    public static void main(String[] args) {
        RESPMessage simpleString = new SimpleString("hello world");
        System.out.println(simpleString.serialize());

        RESPMessage error = new Error("error message");
        System.out.println(error.serialize());

        RESPMessage integerReply = new IntegerReply(43);
        System.out.println(integerReply.serialize());

        RESPMessage bulkString = new BulkString("ping");
        System.out.println(bulkString.serialize());

        RESPMessage array = new Array(new RESPMessage[]{
                new BulkString("echo"),
                new BulkString("hello world")
        });
        System.out.println(array.serialize());

        try {
            String respString = "+OK\r\n";
            RESPMessage deserialized = RESPParser.parse(respString);
            System.out.println(deserialized.serialize());

            respString = "*2\r\n$4\r\necho\r\n$11\r\nhello world\r\n";
            deserialized = RESPParser.parse(respString);
            System.out.println(deserialized.serialize());

            respString = "$-1\r\n";
            deserialized = RESPParser.parse(respString);
            System.out.println(deserialized.serialize());

            try {
                String invalidResp = "*2\r\n$4\r\necho\r\n";
                RESPMessage invalid = RESPParser.parse(invalidResp);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid RESP message: " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to parse RESP message: " + e.getMessage());
        }
    }
}