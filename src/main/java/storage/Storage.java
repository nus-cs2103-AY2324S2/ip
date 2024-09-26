package storage;
import parser.Util;
import tasklist.Deadline;
import tasklist.Event;
import tasklist.Task;
import tasklist.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage Class to perform file actions
 */
public class Storage {
    private  String filepath= "";
    private File f;
    private boolean doesExist;

    /**
     * Searches and adds the hard disk for the file
     * Creates the file or parent directory
     * if it does not exist
     *
     * @param filepath the filepath
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        this.f = new File(filepath);
        this.doesExist = f.exists();
        File parentDirectory = f.getParentFile();
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs(); // Creates parent directories if they don't exist
        }
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch(java.io.IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Scans the file for the Tasks
     * Adds the tasks as their respective subtasks to an array list
     * @return ArrayList<Task> that contains the tasks in the file
     * @throws FileNotFoundException if file not found
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<>();
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String data = s.nextLine();
            char isDoneChar = data.charAt(2);
            boolean isDone = isDoneChar == 1;
            switch(data.charAt(0)) {
                case 'T':
                    list.add(new Todo(data.substring(4), isDone));
                    break;
                case 'D':
                    int dateIndex = Util.findNthDividerIndex(data, '|', 3);
                    list.add(new Deadline(data.substring(4, dateIndex), isDone, data.substring(dateIndex + 1)));
                        break;
                case 'E':
                    int dateStartIndex = Util.findNthDividerIndex(data, '|', 3);
                    int dateEndIndex = Util.findNthDividerIndex(data, '|',4);
                    list.add(new Event(data.substring(4, dateStartIndex), isDone,
                            data.substring(dateStartIndex + 1, dateEndIndex), data.substring(dateEndIndex + 1)));
                    break;
                default:
                    break;

            }

        }
        return list;
    }

    /**
     * Writes the whole task list to the file
     * @param list task list
     */
    public void saveFile (ArrayList<Task> list) {
        String toSave = "";
        for (int i = 0; i < list.size(); i ++) {
            toSave += list.get(i).saveStorage() + "\n";
        }
        try{
            FileWriter fw = new FileWriter(filepath);
            fw.write(toSave);
            fw.close();
        } catch(java.io.IOException e ) {
            e.printStackTrace();
        }

    }

}
