import Storage.txtFileStorage;
import Task.Task;
import Task.ToDoTask;
import Task.DeadlineTask;
import Task.EventTask;

import java.util.ArrayList;

public class TaskManager {

    private final String TASKSTORAGEFILEPATH = "src/main/java/Storage/data/task.txt";

    private ArrayList<Task> userTasks = new ArrayList<>();

    private txtFileStorage taskStorage = new txtFileStorage(TASKSTORAGEFILEPATH);

    public boolean addToDoTask(String taskName, boolean isCompleted) {
        ToDoTask newTask = new ToDoTask(taskName, isCompleted);
        this.userTasks.add(newTask);
        return true;
    }

    public boolean addDeadlineTask(String taskName, String deadline, boolean isCompleted) {
        DeadlineTask newTask = new DeadlineTask(taskName, deadline, isCompleted);
        this.userTasks.add(newTask);
        return true;
    }

    public boolean addEventTask(String taskName, String startDateTime, String endDateTime, boolean isCompleted) {
        EventTask newTask = new EventTask(taskName, startDateTime, endDateTime, isCompleted);
        this.userTasks.add(newTask);
        return true;
    }

    public Task getTask(int index){
        return this.userTasks.get(index);
    }

    public ArrayList<Task> getUserTasks(){
        return userTasks;
    }

    public Task removeTask(int index) {
        try {
            Task removedTask = this.userTasks.get(index);
            this.userTasks.remove(index);
            return removedTask;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No task with index " + (index + 1) + ". Enter 'list' to view tasks.");
            return null;
        }
    }

    public int getTotalTaskCount(){
        return this.userTasks.size();
    }
    public boolean markTaskCompleted(int index) {
        try {
            this.userTasks.get(index).markCompleted();
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No task with index " + (index + 1) + ". Enter 'list' to view tasks.");
            return false;
        }
    }

    public boolean markTaskIncomplete(int index) {
        try {
            this.userTasks.get(index).markIncomplete();
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No task with index " + (index + 1) + ". Enter 'list' to view tasks.");
            return false;
        }
    }

    private void storeUserTaskToFileStorage() {
        for (Task task : this.userTasks) {
            this.taskStorage.appendToTxtFileStorage(task.getStringStorageRepresentation());
        }
    }

    private void loadUserTaskFromFileStorage(){
        ArrayList<String> readContents = this.taskStorage.readTxtFileStorage();
        for (String readContentString : readContents) {
            String[] readContentWord = readContentString.split("\\|");

            if (readContentWord[0].trim().equals("T")) {
                this.addToDoTask(readContentWord[2].trim(), readContentWord[1].trim().equals("Y"));
            } else if (readContentWord[0].trim().equals("D")) {
                this.addDeadlineTask(readContentWord[2].trim(), readContentWord[3].trim(), readContentWord[1].trim().equals("Y"));
            } else if (readContentWord[0].trim().equals("E")) {
                this.addEventTask(readContentWord[2].trim(), readContentWord[3].trim(), readContentWord[4].trim(), readContentWord[1].trim().equals("Y"));
            }
        }
    }

    public void initialise(){
        if (!this.taskStorage.storageFileExist()) {
            this.taskStorage.createTxtFileStorage();
        }
        this.loadUserTaskFromFileStorage();
    }

    public void termintate(){
        this.taskStorage.clearTxtFileStorage();
        this.storeUserTaskToFileStorage();
    }

}
