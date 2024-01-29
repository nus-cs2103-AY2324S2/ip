import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FileManaging {
    /**
     * Adds hard disk memory into local storage.
     *
     * @param filePath
     * @param storage
     * @throws FileNotFoundException if file is not found.
     * @throws DukeException if there is storage format wrong.
     */
    public static void readFileContent(String filePath, TaskList storage) throws FileNotFoundException, DukeException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            // Create a Task object and store into local array.
            String line = s.nextLine();
            String[] arr = line.split(" , ");

            if (arr.length == 3) {
                Task curr = Task.of(arr[0], Integer.parseInt(arr[1]) == 1 , arr[2]);
                storage.add(curr);
            } else if (arr.length == 5) {
                String by = arr[3] + " " + arr[4];
                Task curr = Task.of(arr[0], Integer.parseInt(arr[1]) == 1, arr[2], by);
                storage.add(curr);
            } else if (arr.length == 7) {
                String from = arr[3] + " " + arr[4];
                String to = arr[5] + " " + arr[6];
                Task curr = Task.of(arr[0], Integer.parseInt(arr[1]) == 1, arr[2], from, to);
                storage.add(curr);
            } else {
                throw new DukeException("Storage Format Issue");
            }
        }
    }

    /**
     * Stores storage memory into hard disk.
     *
     * @param filePath
     * @param storage
     * @throws IOException
     */
    public static void writeToFile(String filePath, TaskList storage) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        fw.write(storage.storageListing());
        fw.close();
    }
}
