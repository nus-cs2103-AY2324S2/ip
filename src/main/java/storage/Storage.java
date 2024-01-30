package storage;

import model.StoredTaskList;
import model.TaskList;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Storage {
    private static final String FILE_PATH = "tasks.txt";

    /**
     * Stores the string representation of the taskList in a designated file.
     */
    public static void store(StoredTaskList taskList) {
        try (FileWriter out = new FileWriter(FILE_PATH)){
            out.write(taskList.parsableString());
            out.close();
        } catch (IOException err) {
            // do nothing
            System.out.println(err.toString());
        }
    }

    /**
     * Retrieves a previously stored data file and returns its representation as a TaskList object.
     *
     * @return the stored TaskList object
     */
    public static StoredTaskList get() {
        StoredTaskList output = new StoredTaskList();
        File f = new File(FILE_PATH);
        try {
            // only creates file if it doesn't exist yet
            f.createNewFile();
        } catch (IOException err) {
            System.out.println(err.toString());
        }

        try (BufferedReader in = new BufferedReader(new FileReader(f))) {
            Stream<String> allLines = in.lines();
            Stream<ArrayList<String>> splitParams = allLines.map((line) ->
                    new ArrayList<>(Arrays.asList(line.split("\\|"))));
            ArrayList<ArrayList<String>> data = splitParams.collect(Collectors.toCollection(ArrayList::new));
            System.out.println(data);
        } catch (IOException err) {
            System.out.println(err.toString());
        }

        return output;
    }
}
