package lilybot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class for loading and saving files.
 */
public class Storage {

    /** File to be loaded or saved */
    private File file;

    /**
     * Constructs a Storage object.
     * Creates a new file if the file required is not found.
     *
     * @param filePath The path of file duke.txt.
     * @throws IOException For input error.
     */
    public Storage(String filePath) throws IOException {
        this.file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public File getFile() {
        return file;
    }

    public static String getFilePathToLilyBotTxt() {
        String filePath = new File("").getAbsolutePath();
        filePath += "/lilybot.txt";
        return filePath;
    }

    /**
     * Loads the file from the txt file.
     *
     * @return An arraylist of tasks stored in the txt file.
     * @throws FileNotFoundException For cases when file is not found.
     */
    public ArrayList<Task> loadFile() throws FileNotFoundException {
        ArrayList<Task> ls = new ArrayList<>();
        ArrayList<String> existingList = new ArrayList<>();

        //Load the current content of the file
        Scanner s = new Scanner(getFile());
        while (s.hasNext()) {
            String st = s.nextLine();
            existingList.add(st);
        }

        //Turn content in the file into a list
        for (int i = 0; i < existingList.size(); i++) {
            String tk = existingList.get(i);
            String[] arr = tk.split("<>", 4);

            switch (arr[0]) {
            case "T":
                try {
                    if (Integer.valueOf(arr[1]) == 0) {
                        //The task is not finished
                        Task t = new ToDo(arr[2]);
                        ls.add(t);
                    } else if (Integer.valueOf(arr[1]) == 1) {
                        //Task Finished
                        Task t = new ToDo(arr[2]);
                        t.mark();
                        ls.add(t);
                    } else {
                        Ui.botUnknownFormat(i);
                    }
                } catch (Exception e) {
                    Ui.botUnknownFormat(i);
                }
                break;
            case "D":
                try {
                    if (Integer.valueOf(arr[1]) == 0) {
                        //The ddl is not finished
                        Task t = new Deadline(arr[2], arr[3]);
                        ls.add(t);
                    } else if (Integer.valueOf(arr[1]) == 1) {
                        //DDL Finished
                        Task t = new Deadline(arr[2], arr[3]);
                        t.mark();
                        ls.add(t);
                    } else {
                        Ui.botUnknownFormat(i);
                        System.out.println("brk pt 1");
                    }
                } catch (Exception e) {
                    Ui.botUnknownFormat(i);
                }
                break;
            case "E":
                try {
                    if (Integer.valueOf(arr[1]) == 0) {
                        //The event is not finished
                        String[] temp = arr[3].split("to", 2);
                        String toTime = temp[1].trim();
                        String timeOfTo = ("to " + toTime).trim();
                        Task t = new Event(arr[2], temp[0], timeOfTo);
                        ls.add(t);
                    } else if (Integer.valueOf(arr[1]) == 1) {
                        //Event Finished
                        String[] temp = arr[3].split(" to ", 2);
                        String toTime = temp[1].trim();
                        String timeOfTo = ("to " + toTime).trim();
                        Task t = new Event(arr[2], temp[0], timeOfTo);
                        t.mark();
                        ls.add(t);
                    } else {
                        Ui.botUnknownFormat(i);
                    }
                } catch (Exception e) {
                    Ui.botUnknownFormat(i);
                }
                break;
            default:
                //Format unknown
                Ui.botUnknownFormat(i);
                break;
            }
        }

        return ls;
    }


    /**
     * Writes to file in duke.txt and save it.
     *
     * @param file The file used.
     * @param ls   The taskList to be saved.
     * @throws IOException For input error.
     */
    public static void saveFile(File file, TaskList ls) throws IOException {
        FileWriter fw = new FileWriter(file);
        String separator = "<>";
        ArrayList<String> arr = new ArrayList<>();

        if (ls.getSize() == 0) {
            fw.write("");
        } else {
            for (int i = 0; i < ls.getSize(); i++) {
                Task task = ls.get(i);
                if (task instanceof ToDo) {
                    String s;
                    if (!task.getStatus()) {
                        s = "T" + separator + "0" + separator + task.getDescription();
                    } else {
                        s = "T" + separator + "1" + separator + task.getDescription();
                    }
                    arr.add(s);
                } else if (task instanceof Deadline) {
                    String s;
                    if (!task.getStatus()) {
                        s = "D" + separator + "0" + separator + task.getDescription() + separator + ((Deadline) task).getByDate();
                    } else {
                        s = "D" + separator + "1" + separator + task.getDescription() + separator + ((Deadline) task).getByDate();
                    }
                    arr.add(s);
                } else if (task instanceof Event) {
                    String s;
                    if (!task.getStatus()) {
                        s = "E" + separator + "0" + separator + task.getDescription() + separator + ((Event) task).getFromTo();
                    } else {
                        s = "E" + separator + "1" + separator + task.getDescription() + separator + ((Event) task).getFromTo();
                    }
                    arr.add(s);
                } else {
                    System.out.println("The task you enter is of a type undefined here.");
                }
            }

            for (String str : arr) {
                fw.write(str + System.lineSeparator());
            }
        }
        fw.close();
    }
}
