import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import exceptions.*;
import tasks.*;

public class TaskList {
    public ArrayList<Task> taskList;
    private Storage storage;
    TaskList(Storage storage) {
        taskList = new ArrayList<>();
        this.storage = storage;
    }

    public void readFile(File newFile) throws FileNotFoundException{
        storage.readFile(newFile, this.taskList);
    }
    public void addToList (Task t) throws IOException{
        taskList.add(t);
        storage.refreshFile(taskList);
    }

    public void removeFromList (int index) throws IOException{
        taskList.remove(index);
        storage.refreshFile(taskList);
    }

    public void printList() {
        int counter = 1;
        for (Task t : taskList) {
            System.out.printf("%d. %s%n", counter, t.toString());
            counter++;
        }
    }

    public void markList(int index) throws IncompatibleMarkException, IOException{
        if (taskList.get(index).getCompletionStatus()) {
            taskList.get(index).toggleCompletion();
            storage.refreshFile(taskList);
            return;
        }
        throw new IncompatibleMarkException();
    }

    public void unmarkList(int index) throws IncompatibleMarkException, IOException{
        if (!taskList.get(index).getCompletionStatus()) {
            taskList.get(index).toggleCompletion();
            storage.refreshFile(taskList);
            return;
        }
        throw new IncompatibleMarkException();
    }
    public int size() {
        return taskList.size();
    }

    public Task get(int i) {
        return taskList.get(i);
    }
}
