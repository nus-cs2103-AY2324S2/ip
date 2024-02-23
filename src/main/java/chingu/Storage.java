package chingu;

import chingu.task.Event;
import chingu.task.ToDo;
import chingu.task.Deadline;
import chingu.task.Task;
import chingu.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with loading tasks from saved file and
 * saving tasks in the file after the job is done.
 */
public class Storage {

    public File store;

    /**
     * The constructor that creates an instance of Storage class.
     *
     * @param filePath where the file is located and the file where the tasks will be saved
     */
    public Storage(String filePath) {
        this.store = new File(String.valueOf(filePath));
    }

    /**
     * Loads the tasks saved in the file into the tasklist once the program start running.
     *
     * @return Arraylist<Task>
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> task = new ArrayList<Task>();
        if (this.store.exists()) {
            Scanner reader = new Scanner(store);
            sortTaskType(reader, task);
        }
        store.delete();
        return task;
    }

    /**
     * Sort out the type of task to assign to next functions (such as settleTodo, settleEvent and
     * settleDeadline) to load the specific type of task.
     *
     * @param reader that reads each line (each task)
     * @param tasklist - the list of tasks
     */
    public void sortTaskType(Scanner reader, ArrayList<Task> tasklist) {
        while (reader.hasNextLine()) {
            if (reader.hasNext("T")) {
                settleTodo(reader, tasklist);
            } else if (reader.hasNext("D")) {
                settleDeadline(reader, tasklist);
            } else {
                settleEvent(reader, tasklist);
            }
        }
    }

    /**
     * settle Todo type of task to upload into the tasklist
     *
     * @param reader that reads each line (each task)
     * @param tasklist - list of tasks
     */
    public void settleTodo(Scanner reader, ArrayList<Task> tasklist) {
        String data = reader.nextLine();
        String[] divided = data.split("\\|", 2);
        boolean isDone;
        data = divided[1];
        divided = data.split("\\|", 2);
        String status = divided[0].trim();
        if (status.equals("1")) {
            isDone = true;
        } else {
            isDone = false;
        }
        data = divided[1];
        divided = data.split("/priority", 2);
        String description = divided[0].trim();
        String priority = divided[1].trim();
        Task new_task = new ToDo(description, priority);
        if (isDone) {
            new_task.markAsDone();
        }
        tasklist.add(new_task);
    }

    /**
     * settle Event type of task to upload into the tasklist
     *
     * @param reader that reads each line (each task)
     * @param tasklist - list of tasks
     */
    public void settleEvent(Scanner reader, ArrayList<Task> tasklist) {
        String data = reader.nextLine();
        String[] divided = data.split("\\|", 2);
        boolean isDone;
        data = divided[1];
        divided = data.split("\\|", 2);
        String status = divided[0].trim();
        if (status.equals("1")) {
            isDone = true;
        } else {
            isDone = false;
        }
        data = divided[1];
        divided = data.split("\\|", 2);
        String D = divided[0].trim();
        data = divided[1];
        divided = data.split("\\|", 2);
        String from = divided[0].trim();
        String toPriority = divided[1].trim();
        divided = toPriority.split("/priority", 2);
        String to = divided[0].trim();
        String priority = divided[1].trim();
        Task new_task = new Event(D, from, to, priority);
        if (isDone) {
            new_task.markAsDone();
        }
        tasklist.add(new_task);
    }

    /**
     * settle Deadline type of task to upload into the tasklist
     *
     * @param reader that reads each line (each task)
     * @param tasklist - list of tasks
     */
    public void settleDeadline(Scanner reader, ArrayList<Task> tasklist) {
        String data = reader.nextLine();
        String[] divided = data.split("\\|", 2);
        boolean isDone;
        data = divided[1];
        divided = data.split("\\|", 2);
        String status = divided[0].trim();
        if (status.equals("1")) {
            isDone = true;
        } else {
            isDone = false;
        }
        data = divided[1];
        divided = data.split("\\|", 2);
        String D = divided[0];
        String byPriority = divided[1].trim();
        divided = byPriority.split("/priority", 2);
        String by = divided[0].trim();
        String priority = divided[1].trim();
        Task new_task = new Deadline(D, by, priority);
        if (isDone) {
            new_task.markAsDone();
        }
        tasklist.add(new_task);
    }

    /**
     * Saves the tasks in Tasklist in the file before the program ends.
     *
     * @param tasks
     */
    public void save(TaskList tasks) {
        try {
            File dir = new File("store");

            if (!dir.exists()) {
                dir.mkdirs();
            }

            if (!store.exists()) {
                store.createNewFile();
            }

            FileWriter data = new FileWriter(store);
            String to_record = "";
            for (int i = 0; i < tasks.getSizeNumber(); i++) {
                Task temp = tasks.getTask(i);
                String taskDetailsToAdd = sortTask(temp, "");
                to_record += taskDetailsToAdd;
            }
            data.write(to_record);
            data.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sorts out the type of task before saving to the file
     *
     * @param taskToSave task to be sorted before being saved in the file
     * @param taskDetailsToAdd collection of details to add to the saved file
     * @return
     */
    public String sortTask(Task taskToSave, String taskDetailsToAdd) {
        if (taskToSave instanceof ToDo) {
            return saveToDo(taskToSave, taskDetailsToAdd);
        } else if (taskToSave instanceof Event) {
            return saveEvent(taskToSave, taskDetailsToAdd);
        } else {
            return saveDeadline(taskToSave, taskDetailsToAdd);
        }
    }

    /**
     * Return the final taskDetailsToAdd containing that ToDo tasks into the file
     *
     * @param toDoToSave Todo that will be saved in the file
     * @param taskDetailsToAdd collection of details to add to the saved file
     * @return the final taskDetailsToAdd
     */
    public String saveToDo(Task toDoToSave, String taskDetailsToAdd) {
        String isDone = "0";
        if (toDoToSave.isDone) {
            isDone = "1";
        }
        taskDetailsToAdd += "T" +" | " + isDone
                + " | " + toDoToSave.describeTask()
                + "/priority " + toDoToSave.getPriority() + "\n";
        return taskDetailsToAdd;
    }

    /**
     * Return the final taskDetailsToAdd containing that ToDo tasks into the file
     *
     * @param eventToSave Todo that will be saved in the file
     * @param taskDetailsToAdd collection of details to add to the saved file
     * @return the final taskDetailsToAdd
     */
    public String saveEvent(Task eventToSave, String taskDetailsToAdd) {
        Event t = (Event) eventToSave;
        String isDone = "0";
        if (eventToSave.isDone) {
            isDone = "1";
        }
        taskDetailsToAdd += "E" + " | " + isDone
                + " | " + eventToSave.describeTask() + "|"
                + t.from.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"))
                + "|" + t.to.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"))
                + "/priority " + t.getPriority()
                + "\n";
        return taskDetailsToAdd;
    }

    /**
     * Return the final taskDetailsToAdd containing that ToDo tasks into the file
     *
     * @param deadlineToSave Todo that will be saved in the file
     * @param taskDetailsToAdd collection of details to add to the saved file
     * @return the final taskDetailsToAdd
     */
    public String saveDeadline(Task deadlineToSave, String taskDetailsToAdd) {
        Deadline t = (Deadline) deadlineToSave;
        String isDone = "0";
        if (deadlineToSave.isDone) {
            isDone = "1";
        }
        taskDetailsToAdd += "D" + " | " + isDone
                + " | " + deadlineToSave.describeTask() + "|"
                + t.by.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                + "/priority " + t.getPriority()
                + "\n";
        return taskDetailsToAdd;
    }


}
