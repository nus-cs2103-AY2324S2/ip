package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;


public class StorageTest {
    private static String INDENT = "    ";
    private static final String FILE_NAME = "./src/test/java/TestOutput.txt";
    private static final String TEST_INPUT = "todo true Read\ndeadline false 1990-12-03 Return Book\nevent false 1999-05-03 Eat book";
    private static final String EXPECTED_OUTPUT = INDENT + "1.[T] Read is complete!\n" + INDENT + "2.[D] Return Book by Dec 3 1990\n" + INDENT + "3.[E] Eat book by May 3 1999\n"; 

    @Test
    public void testLoad() throws FileNotFoundException, IOException {
        File file = new File(FILE_NAME);
        
        if (file.exists() == false) {
            try {
                file.createNewFile();
            } catch (IOException e) { 
                System.out.println("Failed to create test text file");
            }
        }
        FileWriter writer = new FileWriter(FILE_NAME);
        writer.write(TEST_INPUT);
        writer.close();
        
        Storage storage = new Storage(FILE_NAME);
        TaskList testList = storage.load();
        String output = testList.showList();

        assertEquals(EXPECTED_OUTPUT, output);
        }
}
