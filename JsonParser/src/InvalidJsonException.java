public class InvalidJsonException extends RuntimeException {
    InvalidJsonException(String errorMessage) {
        System.out.println("Invalid JSON String parsed: "+ errorMessage);
    }
}
