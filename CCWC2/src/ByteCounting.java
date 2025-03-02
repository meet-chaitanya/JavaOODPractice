import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ByteCounting implements CountBehavior {
    @Override
    public Result performCount(InputStream is) {
        Result result = new Result();
        int count = 0;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            int c;
            while((c = br.read()) != -1) {
                byte[] bytes = Character.toString((char)c).getBytes(StandardCharsets.UTF_8);
                count += bytes.length;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (count > 0) result.setByteCount(count);
        return result;
    }
}
