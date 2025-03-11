public class InputSourceFactory {

    public static InputSource createInputSource(String filePath) {
        if (filePath == null || filePath.equals("-")) {
            return new StdinInputSource();
        } else {
            return new FileInputSource(filePath);
        }
    }
}
