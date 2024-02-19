package baron.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FileUtilsTest {
    public static final String BASE_DIR = System.getProperty("user.dir") + "/src/test/java/baron.Database";

    public FileUtilsTest() {

    }

    @Test
    public void readFile_fileExists_linesReturned() {
        Path filePath = Path.of(BASE_DIR + "/todo.txt");
        List<String> actual = FileUtils.read(filePath);
        List<String> expected = Arrays.asList("0 | 1 | I'm a todo actually, you know?", "1 | 1 | "
                + "Yeah what he said, I'm one too!");
        assertEquals(actual, expected);
    }

}
