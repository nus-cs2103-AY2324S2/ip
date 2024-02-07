package fireraya.main;

import fireraya.exception.FirerayaException;
import fireraya.task.Deadline;
import fireraya.task.Event;
import fireraya.task.Task;
import fireraya.task.Todo;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveToFile(ArrayList<Task> tasks) throws FirerayaException {
        try {
            File file = new File(filePath);

            BufferedWriter saveText = new BufferedWriter(new FileWriter(file));
            for (Task t : tasks) {
                saveText.append(t.saveFormat());
                saveText.append("\n");
            }
            saveText.close();
        } catch (IOException e) {
            throw new FirerayaException("An IOException occurred. " + e);
        }
    }

    public ArrayList<Task> load() throws FirerayaException {
        ArrayList<Task> loaded = new ArrayList<>();

        //Tests if file exists. If not, load new file
        try {
            File curr = new File(filePath);
            if (!curr.exists()) {
                return loaded;
            }

            Scanner s = new Scanner(curr);
            while (s.hasNext()) {
                String input = s.nextLine();
                String[] all = input.split("\\|");
                String keyword = all[0];
                int arrLen = all.length;

                Task nextTask = null;

                if (keyword.equals("T")) {
                    nextTask = new Todo(all[2]);
                }

                if (keyword.equals("D")) {
                    nextTask = new Deadline(all[2], all[3]);
                }

                if (keyword.equals("E")) {
                    nextTask = new Event(all[2], all[3], all[4]);
                }

                if (all[1].equals("1")) {
                    assert nextTask != null;
                    nextTask.markAsDone();
                }

                loaded.add(nextTask);

                System.out.println(Arrays.toString(all));

            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return loaded;
    }
}



