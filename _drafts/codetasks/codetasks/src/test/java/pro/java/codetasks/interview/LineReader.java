package pro.java.codetasks.interview;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Serzh Nosov created on 21.01.2020.
 */
public class LineReader implements AutoCloseable {

    private FileReader fileReader;
    private BufferedReader br;
    private String current;

    public LineReader(File file) throws FileNotFoundException {
        this.fileReader = new FileReader(file);
        this.br = new BufferedReader(fileReader);
    }

    public String readLine() throws IOException {
        int i = (current == null) ? 0 : current.length();
        return read(new StringBuilder(), i);
    }

    public String read(int i) throws IOException {
        return read(new StringBuilder(), i);
    }

    public String read(StringBuilder sb, int i) throws IOException {
        updateCurrentIfNeeded();

        if (i < current.length()) {
            String str = current.substring(0, i);
            this.current = current.substring(i);
            sb.append(str);
            return sb.toString();
        } else {
            int newI = i - current.length();
            sb.append(this.current)
                    .append(System.getProperty("line.separator"));

            this.current = null;
            return read(sb, newI);
        }
    }

    private void updateCurrentIfNeeded() throws IOException {
        if (current == null || current.isEmpty()) {
            String line;
            if ((line = br.readLine()) != null) {
                current = reverseWords(line);
            }
        }
    }

    private String reverseWords(String str) {
        String[] arrayWords = str.trim().split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = arrayWords.length - 1; i >= 0; i--) {
            result.append(arrayWords[i]).append(" ");
        }
        return result.toString();
    }

    @Override
    public void close() throws Exception {
        this.fileReader.close();
    }
}
