package ghbot.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

/**
 * StorageTest Class.
 */
public class StorageTest {
    @Test
    public void testReadFile() throws IOException {
        File file = new File("data/testHistory.txt");
        Scanner sc = new Scanner(file);
        assertEquals("T | 0 | sleep", sc.nextLine());
    }
}
