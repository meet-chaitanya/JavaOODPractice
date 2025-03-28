import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class LineCounting implements CountBehavior {
    @Override
    public Result performCount(InputStream is) {
        Result result = new Result();
        int count = 0;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            while ((br.readLine()) != null) {
                count++;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        if (count > 0) result.setLineCount(count);
        return result;
    }
}
