import java.io.FileInputStream;
import java.io.InputStream;

public class UnixWCSimulator {
    public static void main(String[] args)  {
        try {
            String option = args.length >= 1 ? args[0] : "";
            String fileName = "";
            if (args.length > 0) {
                fileName = args[args.length - 1];
            }
            CountingBehaviorFactory factory = new CountingBehaviorFactory();
            CountBehavior countBehavior = factory.createCountingBehavior(option);
            InputStream is = null;
            if (System.in.available() > 0) {
                is = System.in;
            } else if (!fileName.isEmpty()) {
                is = new FileInputStream(fileName);
            }
            UnixWCTool wcTool = new UnixWCTool(countBehavior, is);
            wcTool.performCount();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
