package service;

import snoopy.Snoopy;
import exceptions.DukeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    static final String FILE_PATH = "./data/snoopy.txt";
    public Storage() {

    }

    private static boolean isCorrupt(File file) throws FileNotFoundException {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String task[] = scanner.nextLine().split(" \\| ");
                String type = task[0];
                String done = task[1];
                String desc = task[2];
                String time1 = null;
                String time2 = null;
                switch (type) {
                case "T":
                    break;
                case "D":
                    time1 = task[3];
                    break;
                case "E":
                    time1 = task[3];
                    time2 = task[4];
                    break;
                }
            }
            scanner.close();
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    private static void parseTodoFile(File file, TaskList taskList) throws Exception {
        Scanner scanner = new Scanner(file);
        Integer taskCounter = 1;
        while (scanner.hasNextLine()) {
            String task[] = scanner.nextLine().split(" \\| ");
            String type = task[0];
            String done = task[1];
            String desc = task[2];
            String time1 = null;
            String time2 = null;
            switch (type) {
            case "D":
                time1 = task[3];
                Snoopy.processCommand("Deadline " + desc + " /by " + time1, taskList, false);
                break;
            case "E":
                time1 = task[3];
                time2 = task[4];
                Snoopy.processCommand("Event " + desc + " /from " + time1 + " /to " + time2, taskList, false);
                break;
            case "T":
                Snoopy.processCommand("Todo " + desc, taskList, false);
                break;
            }

            if (done.equals("1")) { // done
                Snoopy.processCommand("Mark " + taskCounter.toString(), taskList, false);
            }
            //parse each line
            taskCounter++;
        }
        scanner.close();

    }

    private static boolean createFile(File file) throws Exception {
        file.getParentFile().mkdirs();
        file.createNewFile();
        boolean fileIsCreated = file.createNewFile(); //May throw IOException
        if (fileIsCreated) {
            return true;
        } else {
            throw new DukeException("Unable to create new file");
        }
    }

    private void openStoredFile(TaskList taskList) throws Exception {

        //See if file already exists then parse it
        File file = new File(FILE_PATH);

        try {
            if (!file.exists()) { //Create if don't exist
                createFile(file);
            } else if (isCorrupt(file)) { //file does exist
                //file corrupt
                createFile(file);
            } else {
                //parse current info and return.
                parseTodoFile(file, taskList);
            }
        } catch (Exception e) {
            throw new DukeException("open file failed");
        }
    }

    /**
     * Preload the current already-stored list of tasks from data.txt.
     * @param taskList tasklist object where the tasks would be added to.
     * @throws Exception
     */
    public void loadInfo(TaskList taskList) throws Exception {
        openStoredFile(taskList);
    }

    /**
     * Update the database (snoopy.txt) on the most recent version of the tasklist.
     * @param taskList most updated version of the tasklist.
     * @throws RuntimeException
     */
    public void updateRecords(TaskList taskList) throws RuntimeException {
        assert(FILE_PATH != null);
        assert(taskList != null);

        try {
            File file = new File(FILE_PATH);

            if (taskList.size() == 0) {
                return;
            }
            FileWriter writer = new FileWriter(FILE_PATH);
            writer.write(taskList.get(0).fileSavingString());
            for (int i = 1; i < taskList.size(); i++) {
                writer.write("\n" + taskList.get(i).fileSavingString());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
