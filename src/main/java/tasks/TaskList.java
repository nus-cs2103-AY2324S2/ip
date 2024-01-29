package tasks;

import exceptions.DukeException;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTasks() {
        return this.taskList;
    }
    public void yapTasks() {
        if (taskList.size() == 0) {
            System.out.println("Nothin' to yap...");
        }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i+1)+". "+taskList.get(i));
        }
    }

    public void markTaskAsDone(int index) {
        Task task = taskList.get(index - 1);
        task.markDone(false);
    }

    public void unmarkTaskAsDone(int index) {
        Task task = taskList.get(index - 1);
        task.unmarkDone();
    }

    public void addTasktoTaskList(Task task) {
        if (task == null) {
            return;
        }
        taskList.add(task);
    }

    public Task removeTaskfromTaskList(int index) {
        Task task = taskList.remove(index - 1);
        return task;
    }

    public Task initTask(String message, String taskType) {
        Task task;
        try {
            if (taskType.equals("todo")) {
                try {
                    String[] inputs = message.split("todo ");
                    task = new ToDo(inputs[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Whats the task, yapper???");
                }
            } else if (taskType.equals("deadline")) {
                try {
                    message = message.substring("deadline ".length());
                    String[] inputs = message.split("/by");
                    task = new Deadline(inputs[0].trim(), inputs[1].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Deadlines need a deadline, yapper!");
                } catch (StringIndexOutOfBoundsException e ) {
                    throw new DukeException("Whats the task, yapper???");
                }
            } else if (taskType.equals("event")) {
                try {
                    message = message.substring("event ".length());
                    String[] inputs = message.split("/from");
                    String[] innerInputs = inputs[1].split("/to");
                    task = new Event(inputs[0].trim(), innerInputs[0].trim(), innerInputs[1].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("YAPYAP, What time is your from and to?");
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Whats the task, yapper???");
                }
            } else { //should not reach here because of filter in main logic
                task = new Task(message);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return task;
    }

}
