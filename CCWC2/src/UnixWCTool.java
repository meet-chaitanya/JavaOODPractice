import java.io.InputStream;

public class UnixWCTool {
    CountBehavior countBehavior;
    InputStream inputStream;

    UnixWCTool(CountBehavior cb, InputStream is) {
        this.countBehavior = cb;
        this.inputStream = is;
    }

    public void performCount() {
        Result result = countBehavior.performCount(inputStream);
        System.out.println(result);
    }
}