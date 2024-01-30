import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {
    }

    public void markTask(int index) {
        Task currentTask = taskList.get(index);
        System.out.println("\tWe have completed this task!");
        currentTask.mark();
        System.out.println("\t" + currentTask.getTaskType() + " " + currentTask.getStatus() + " " + currentTask.getTask());
    }

    public void deleteTask(int index) {
        Task currentTask = taskList.get(index);
        System.out.println("\tTask has been deleted!");
        System.out.println("\t" + currentTask.getTaskType() + " " + currentTask.getStatus() + " " + currentTask.getTask());
        taskList.remove(index);
    }

    public void unmarkTask(int index) {
        Task currentTask = taskList.get(index);
        System.out.println("\tOops, task unmarked!");
        currentTask.unmark();
        System.out.println("\t" + currentTask.getTaskType() + " " + currentTask.getStatus() + " " + currentTask.getTask());
    }

    public void listTask() {
        System.out.println("\tThese are the tasks we currently have: ");

        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            System.out.println("\t" + (i+1) + ". " + currentTask.toString());
        }

        System.out.println("\tWe have " + (taskList.size()) + " tasks.");
    }

    public void addTask(String first, String second)  {
        Task newTask;

        if (first.equals("todo")) {
            newTask = new ToDo(second, "T");
            this.taskList.add(newTask);
            System.out.println("\t" + newTask.announcement());
            System.out.println("\t\t" + newTask.toString());
        } else if (first.equals("deadline")) {
            String[] secondaryInputSplit = second.split("/");
            newTask = new Deadline(secondaryInputSplit[0], "D", secondaryInputSplit[1]);
            this.taskList.add(newTask);
            System.out.println("\t" + newTask.announcement());
            System.out.println("\t\t" + newTask.toString());
        } else if (first.equals("event")) {
            String[] secondaryInputSplit = second.split("/");
            newTask = new Event(secondaryInputSplit[0], "E", secondaryInputSplit[1],
                    secondaryInputSplit[2]);
            this.taskList.add(newTask);
            System.out.println("\t" + newTask.announcement());
            System.out.println("\t\t" + newTask.toString());
        } else {
            System.out.println("\tInvalid Task");
        }
    }

    public int size() {
        return taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }
}
