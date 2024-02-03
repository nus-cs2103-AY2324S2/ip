package botbot.task;

import botbot.exception.BotBotException;
import botbot.exception.CommandException;
import botbot.exception.DescriptionException;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list = new ArrayList<>();

    public void addTask(String taskString) throws BotBotException {
        String[] temp = taskString.split(" ", 2);
        String taskType = temp[0];
        String taskInfo = temp.length > 1 ? temp[1] : "";
        if (taskInfo.isEmpty()) {
            throw new DescriptionException();
        }
        switch (taskType) {
            case "todo":
                addTask(new ToDo(taskInfo));
                break;
            case "deadline":
                String[] temp2 = taskInfo.split(" /", 2);
                addTask(new Deadline(temp2[0], temp2[1].substring(3)));
                break;
            case "event":
                String[] temp3 = taskInfo.split(" /", 3);
                addTask(new Event(temp3[0], temp3[1].substring(5), temp3[2].substring(3)));
                break;
            default:
                throw new CommandException();
        }
    }
    private Task getTask(int i) {
        return this.list.get(i);
    }
    public String getFileRep(int i) { return getTask(i).fileRep(); }
    private void addTask(Task task) throws BotBotException {
        System.out.printf("I have added the following task:\n%s\n", task.getRep());
        this.list.add(task);
        System.out.printf("You now have %d task(s) in your list!%n", list.size());
    }
    public void deleteTask(int i) {
        System.out.printf("I have removed the following task:\n%s\n", getTask(i - 1).getRep());
        this.list.remove(i - 1);
        System.out.printf("You now have %d task(s) in your list!%n", list.size());
    }
    public void printList() {
        for (int i = 1; i < this.list.size() + 1; i++) {
            System.out.println(i + ". " + getTask(i - 1).getRep());
        }
    }
    public void mark(int i) {
        Task task = getTask(i - 1);
        task.mark();
        System.out.println(task.getRep());
    } // Handle index exception here maybe
    public void unmark(int i) {
        Task task = getTask(i - 1);
        task.unmark();
        System.out.println(task.getRep());
    }
    public void addTaskInit(Task t) {
        this.list.add(t);
    }
    public int size() {
        return this.list.size();
    }
    public void printFind(String s) {
        int count = 1;
        for (Task task : list) {
            if (task.getName().contains(s)) {
                System.out.println(count + ". " +task.getRep());
                count++;
            }
        }
    }
}
