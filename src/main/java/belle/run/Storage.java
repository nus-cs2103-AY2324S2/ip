package belle.run;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import belle.others.BelleException;
import belle.tasks.DeadlineTask;
import belle.tasks.EventTask;
import belle.tasks.Task;
import belle.tasks.TodoTask;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    enum Type {
        T,
        D,
        E
    }
    /**
     * Constructs Storage.
     *
     * @param filePath File containing storage data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns list that was saved previously.
     *
     * @return ArrayList that was saved previously.
     * @throws BelleException  If an invalid item in HardDisk.
     */
    public ArrayList<Task> loadList() throws BelleException {
        ArrayList<Task> currList = new ArrayList<>();
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                return currList;
            }
            Scanner listScanner = new Scanner(f);

            //higher-level function that adds saved data to list
            addToList(currList, listScanner);
            return currList;
        } catch (IOException e) {
            throw new BelleException("Sorry!! I'm having trouble loading the list.");
        }
    }

    /**
     * Adds tasks from storage to list used by Belle.
     *
     * @param currList ArrayList being used in
     *                 the current program.
     * @param listScanner Scanner to scan data from f.
     * @throws BelleException If tries to add a task
     *      that is not an Event, Deadline or todotask.
     */
    public void addToList(ArrayList<Task> currList, Scanner listScanner) throws BelleException {
        while (listScanner.hasNext()) {
            String currLine = listScanner.nextLine();
            String[] currTaskList = currLine.split(" , ");

            if (currTaskList[0].equals(Type.T.name())) {
                currList.add(new TodoTask(currTaskList[2], Boolean.valueOf(currTaskList[1])));
            } else if (currTaskList[0].equals(Type.D.name())) {
                currList.add(new DeadlineTask(currTaskList[2], Boolean.valueOf(currTaskList[1]),
                        currTaskList[3]));
            } else if (currTaskList[0].equals(Type.E.name())) {
                currList.add(new EventTask(currTaskList[2], Boolean.valueOf(currTaskList[1]),
                        currTaskList[3], currTaskList[4]));
            } else {
                throw new BelleException("Not valid item in HardDisk");
            }
        }
    }

    /**
     * Saves list everytime it is updated.
     *
     * @param currList List to save to database.
     * @throws BelleException  If an invalid item
     *         that is being saved.
     */
    public void save(ArrayList<Task> currList) throws BelleException {
        File f = new File(this.filePath);
        try {
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(this.filePath);

            //high-level step that writes new list to harddisk
            writeToList(currList, fw);
            fw.close();
        } catch (IOException e) {
            throw new BelleException("Sorry!! I'm having trouble saving the list.");
        }

    }


    /**
     * Writes to file everytime the list is edited.
     *
     * @param currList List used to track tasks in
     *                 current program.
     * @param fw FileWriter used to write to file
     *           used to store all task data.
     * @throws BelleException If tries to add a task
     *     that is not an Event, Deadline or todotask.
     * @throws IOException If filewritwe has an error
     *     when trying to write to the file.
     */
    public void writeToList(ArrayList<Task> currList, FileWriter fw) throws IOException, BelleException {
        String currItem;
        String div = " , ";
        for (Task i : currList) {
            if (i.getType().equals(Type.T.name())) {
                currItem = i.getType() + div + i.getDone() + div + i.getName() + System.lineSeparator();
            } else if (i.getType().equals(Type.D.name())) {
                DeadlineTask d = (DeadlineTask) i;
                currItem = d.getType() + div + d.getDone() + div + d.getName() + div
                        + d.getDeadline() + System.lineSeparator();
            } else if (i.getType().equals(Type.E.name())) {
                EventTask e = (EventTask) i;
                currItem = e.getType() + div + e.getDone() + div + e.getName()
                        + div + e.getStart() + div + e.getEnd() + System.lineSeparator();
            } else {
                throw new BelleException("There is an invalid Item when trying to save list");
            }
            fw.write(currItem);
        }
    }
}
