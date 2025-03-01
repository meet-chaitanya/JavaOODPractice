public class Result {
    private int lineCount = -1;
    private int wordCount = -1;
    private int byteCount = -1;
    private int characterCount = -1;

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public int getByteCount() {
        return byteCount;
    }

    public void setByteCount(int byteCount) {
        this.byteCount = byteCount;
    }

    public int getCharacterCount() {
        return characterCount;
    }

    public void setCharacterCount(int characterCount) {
        this.characterCount = characterCount;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        if (lineCount != -1) {
            s.append(lineCount).append(" ");
        }
        if (wordCount != -1) {
            s.append(wordCount).append(" ");
        }
        if (characterCount != -1) {
            s.append(characterCount).append(" ");
        }
        if (byteCount != -1) {
            s.append(byteCount).append(" ");
        }
        return String.valueOf(s);
    }
}
