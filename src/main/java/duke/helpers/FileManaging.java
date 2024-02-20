package duke.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * FileManaging class
 */
public class FileManaging {
    /**
     * Adds hard disk memory into local storage.
     *
     * @param filePath path to retrieve the memory.
     * @param storage the storage to store items from memory.
     * @throws FileNotFoundException if file is not found.
     * @throws DukeException if there is storage format wrong.
     */
    public static void readFileContent(String filePath, TaskList storage) throws FileNotFoundException, DukeException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        storage.removeAll();

        while (s.hasNext()) {
            // Create a duke.task.Task object and store into local array.
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
                throw new DukeException("duke.helpers.Storage Format Issue");
            }
        }
    }

    /**
     * Stores storage memory into hard disk.
     *
     * @param filePath path to store the memory.
     * @param filePath2 path to store the secondary memory.
     * @param storage the storage that will be stored in memory.
     * @throws IOException
     */
    public static void writeToFile(String filePath, String filePath2, TaskList storage) throws IOException {
        FileWriter fw2 = new FileWriter(filePath2);
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        StringBuilder output = new StringBuilder("");
        while (s.hasNext()) {
            String line = s.nextLine();
            output.append(line);
            output.append("\n");
        }
        fw2.write(output.toString());
        fw2.close();


        FileWriter fw = new FileWriter(filePath);
        fw.write(storage.storageListing());
        fw.close();
    }
}
