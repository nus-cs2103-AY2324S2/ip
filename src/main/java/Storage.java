import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Returns a string of message to be parsed.
    public ArrayList<String> load() throws TinyException {
        try {
            //Creating the folder if it does not exists
            if (!new File("../../../data").exists()) {
                new File("../../../data").mkdirs();    
            }
            

            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            ArrayList<String> dataFromFile = new ArrayList<>();
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                dataFromFile.add(data);
            }
            sc.close();
            return dataFromFile;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            throw new TinyException("");
        }
    }

    public void save(ArrayList<String> tasksToSave) {
        try {
            new FileWriter(filePath).close();

            FileWriter myWriter = new FileWriter(filePath);
            for (int i = 0; i < tasksToSave.size(); i++) {
                myWriter.write(tasksToSave.get(i) + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred when saving.");
        }
    }
}
