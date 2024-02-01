import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ListFileManager {

    public ListFileManager () {
        try {
            //Solution below inspired by https://www.geeksforgeeks.org/file-createnewfile-method-in-java-with-examples/
            File listFile = new File("../../data/tasklist.txt");
            System.out.println("Blank TEst");

            if (listFile.createNewFile()) {
                System.out.println("File created");
                BufferedWriter writer = new BufferedWriter(new FileWriter(listFile, true));
                writer.append("Writing\n");
                writer.close();

            } else {
                System.out.println("File already exists");

            }

        } catch (IOException e) {
            System.out.println("File is empty");


        }
    }

}
