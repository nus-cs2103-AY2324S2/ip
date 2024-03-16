package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * Represents the storage which stores the lists inside the disk.
 */
public class Storage {
    private static String fileListPath = "./data/duke.txt";
    private ArrayList<String> listStates = new ArrayList<>();

    private void addList(String str) {
        this.listStates.add(str);
    }

    private void removeList(int index) {
        this.listStates.remove(index);
    }

    private void setList(int index, String newRecord) {
        this.listStates.set(index, newRecord);
    }

    /**
     * Access the stored lists and parse it into usable ArrayList of Task.
     * Each line of the stored file is read and parsed using a Parser.
     * The listStates is changed with each task read to capture the file state.
     *
     * @return the converted Task ArrayList from the stored data.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> temp = new ArrayList<>();
        this.initializeListFile();
        File f = new File(fileListPath);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String str = s.nextLine();
                this.addList(str);
                Parser.initializeTask(str, temp);
            }
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
        }
        return temp;
    }

    /**
     * Rewrites the entire text file used as the disk storage of the lists.
     * The rewriting is based on the listStates field, which reflects the latest changes in the list.
     */
    private void writeList() {
        this.initializeListFile();
        try {
            FileWriter fw = new FileWriter(fileListPath);
            for (int i = 0; i < listStates.size(); i++) {
                fw.write(listStates.get(i) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("File Saving failed..." + e);
        }
    }

    /**
     * Removes the task at the given index in the list from the stored data.
     * @param index the given index of the task to be removed.
     */
    public void removeListStateRecord(int index) {
        this.removeList(index);
        this.writeList();
    }

    /**
     * Add task into the stored data using the given type and data.
     *
     * @param type the type of the task to be added.
     *             Can be todo, deadline, or event.
     * @param data the data of the task to be added in the form of String array,
     *             with varying size depending on the type.
     *             For todos, the data only has description.
     *             For deadlines, the data has description and the deadline.
     *             For events, the data has description, start time and end time.
     */
    public void addListStateRecord(String type, String[] data) {
        String newRecord = "";
        switch (type) {
        case "todo":
            String[] recordT = {"T", "0", data[0]};
            newRecord = String.join(" | ", recordT);
            break;
        case "deadline":
            String[] recordD = {"D", "0", data[0], data[1]};
            newRecord = String.join(" | ", recordD);
            break;
        case "event":
            String[] record = {"E", "0", data[0], data[1], data[2]};
            newRecord = String.join(" | ", record);
            break;
        default:
            break;
        }
        this.addList(newRecord);
        this.writeList();
    }

    /**
     * Modify the task at the given index in the stored data,
     * by changing its mark/unmark state depending on the value of isMarking given
     *
     * @param isMarking the value to indicate whether to mark or unmark the taks.
     *                  true to mark, and false to unmark.
     * @param index the index of the task to modify.
     */
    public void modifyStateRecord(boolean isMarking, int index) {
        String[] record = this.listStates.get(index).split(" \\| ");
        record[1] = isMarking ? "1" : "0";
        String newRecord = String.join(" | ", record);
        this.setList(index, newRecord);
        this.writeList();
    }

    /**
     * Initializes the directory and file of the stored list.
     * If the directory does not exist, it is created.
     * If the file does not exist, it is created.
     */
    public void initializeListFile() {
        try {
            File dir = new File("./data/");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(fileListPath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("FILE Creation failed" + e);
        }
    }
}
