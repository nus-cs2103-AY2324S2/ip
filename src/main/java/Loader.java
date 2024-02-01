import java.io.*;
import java.util.Scanner;
public class Loader {
    private static final String DATAFILE_PATH = "./data/the-count.txt";
    private static final String DIR_PATH = "./data";

    public Loader(TaskList tasks) {
        load(tasks);
    }

    public void load(TaskList tasks) {

        // Directory creation
        try {
            File directory = new File(DIR_PATH);
            if (directory.mkdirs()) {
                System.out.println("Directory created successfully.");
            } else {
                System.out.println("Directory exists.");
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        // File creation
        try {
            File file = new File(DATAFILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created.");
            } else {
                System.out.println("File already exists.");
            }
            readFile(file, tasks);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void write(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(DATAFILE_PATH);
            fw.write(tasks.getListToWrite());
            fw.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void readFile(File file, TaskList tasks) throws FileNotFoundException {
        // Reading from file
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] parts = line.split("\\|");
            char index = parts[0].charAt(0);
            String taskType = parts[1].trim();
            String isTaskDone = parts[2].trim();
            String info = parts[3].trim();

            switch (taskType) {
                case "T":
                    tasks.add(new ToDo(info));
                    break;
                case "D":
                    String deadlineTime = parts[4].trim();
                    tasks.add(new Deadline(info, deadlineTime));
                    break;
                case "E":
                    String[] time = parts[4].trim().split("-");
                    String fromTime = time[0];
                    String toTime = time[1];
                    tasks.add(new Event(info, fromTime, toTime));
                    break;
                default:
                    break;
            }
            if (isTaskDone.equals("1")) {
                try {
                    tasks.markTask(index - '0', false);
                } catch (TheCountException e) {
                    System.err.println(e);
                }
            }
        }
    }
}
