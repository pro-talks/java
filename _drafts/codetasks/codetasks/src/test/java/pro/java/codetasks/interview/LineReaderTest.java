package pro.java.codetasks.interview;

import org.junit.Test;

import java.io.File;

/**
 * @author Serzh Nosov created on 21.01.2020.
 */
public class LineReaderTest {

    @Test
    public void readText() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("text.txt").getFile());

        try (LineReader reader = new LineReader(file)) {
            System.out.println(reader.read(5));
            System.out.println(reader.read(4));
            System.out.println(reader.read(100));
            System.out.println(reader.readLine());
            System.out.println(reader.readLine());
            System.out.println(reader.read(7));
        }

        /**
         * We passed upon the stair
         * he spoke in was and when
         * although I wasn't there
         * he said I was his friend
         * which came as a surprise
         * I spoke into his eyes
         * I thought you died alone
         * a long long time ago
         */
    }
}
