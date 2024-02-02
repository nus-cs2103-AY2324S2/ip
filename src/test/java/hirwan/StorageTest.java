package hirwan;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StorageTest {
    @Test
    public void newFileReadTest() {
        String FILE_PATH = "C:\\Users\\eugen\\Documents\\National University of Singapore\\Y2S2\\CS2103T\\IP\\src\\main\\java\\data\\hirwan.txt";
        File file = new File(FILE_PATH);
        assertEquals(true, file.exists());
    }

    @Test
    public void writeTask_StringList_() {
        String FILE_PATH = "C:\\Users\\eugen\\Documents\\National University of Singapore\\Y2S2\\CS2103T\\IP\\src\\main\\java\\data\\hirwan.txt";
        List<String> listToWrite = new ArrayList<>();
        listToWrite.add("1");
        listToWrite.add("2");
        listToWrite.add("3");
        Storage.writeTask(listToWrite);

        List<String> listToRead = new ArrayList<>();
        try {
            File filePath = new File(FILE_PATH);
            Scanner scan = new Scanner(filePath);
            while (scan.hasNext()) {
                listToRead.add(scan.nextLine());
            }
            assertEquals("1\n2\n3", listToRead);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
