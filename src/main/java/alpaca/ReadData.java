package alpaca;

import alpaca.tasks.Deadline;
import alpaca.tasks.Event;
import alpaca.tasks.Task;
import alpaca.tasks.ToDo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the storage and retrieval of data.
 **/
public abstract class ReadData {
    private static final String dataPath = "data/tasks.txt";

    protected static void read(ArrayList<Task> list) {
        File f = new File(dataPath);
        if (!f.isFile()) {
            return;
        }
        assert f.isFile(): "File should exist here";
        try {
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] processedData = data.split("\\s\\|\\s");
                if (processedData.length == 3) {
                    switch (processedData[0]) {
                        case "D":
                            list.add(new Deadline(processedData[1].equals("1"), processedData[2]));
                            break;
                        case "E":
                            list.add(new Event(processedData[1].equals("1"), processedData[2]));
                            break;
                        case "T":
                            list.add(new ToDo(processedData[1].equals("1"), processedData[2]));
                            break;
                        default:
                            break;
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void write(ArrayList<Task> list) {
        if (list.isEmpty()) {
            return;
        }
        createFile();
        File f = new File(dataPath);
        assert f.exists(): "File supposed to exist after createFile";
        try {
            new FileOutputStream(dataPath).close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataPath, true));
            writer.write("");
            for (Task task : list) {
                writer.append(task.toStringAlt() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFile() {
        File f = new File("data");
        if (!f.exists() || !f.isDirectory()) {
            f.mkdir();
        }
        f = new File(dataPath);
        if (!f.isFile()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
    }
}
