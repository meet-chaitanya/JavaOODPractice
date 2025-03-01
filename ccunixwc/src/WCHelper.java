import java.io.*;
import java.nio.charset.StandardCharsets;

public class WCHelper {
    File file;
    WCHelper(File file ) {
        this.file = file;
    }

    public int getLineCount() {
        int count = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath(), StandardCharsets.UTF_8))) {
            while((br.readLine()) != null) {
                count++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public int getWordCount() {
        int count = 0;
        boolean inWord = false;
        try(BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath(), StandardCharsets.UTF_8))) {
            int c;
            while((c = br.read()) != -1) {
                if (Character.isWhitespace((char)c)) {
                    if (inWord){
                        inWord = false;
                    }
                } else {
                    if (!inWord) {
                        inWord = true;
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public int getCharacterCount() {
        int count = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath(), StandardCharsets.UTF_8))) {
            while((br.read()) != -1) {
                count++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public int getByteCount() {
        int count = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath(), StandardCharsets.UTF_8))) {
            int c;
            while((c = br.read()) != -1) {
                byte[] bytes = Character.toString((char)c).getBytes(StandardCharsets.UTF_8);
                count += bytes.length;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}
