import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task){
        tasks.add(task);
    }
    /*
    This method prints a given task at position i
     */
    public String printTask(int i){
        Task task = tasks.get(i);
        return task.toString();
    }
    public int taskNumber() {
        return tasks.size();
    }
    public void markCompleteTask(int i){
        Task task = tasks.get(i);
        task.markDone();
    }
    public void unmarkCompleteTask(int i){
        Task task = tasks.get(i);
        task.unmarkDone();
    }
    public void deleteTask(int i){
        tasks.remove(i);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tasks.size();i++){
            sb.append(i + 1)
                    .append(". ")
                    .append(tasks.get(i).toString())
                    .append("\n");
        }
        return sb.toString();
    }


    protected void writeToFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tasks.size(); i++) {
            sb.append(tasks.get(i).toFileString()).append("\n");
        }

        String textToAdd = sb.toString();
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}
