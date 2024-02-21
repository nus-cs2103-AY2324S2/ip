package botbot.task;

import botbot.exception.BotBotException;
import botbot.exception.CommandException;
import botbot.exception.DescriptionException;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list = new ArrayList<>();

    public String addTask(String taskString) throws BotBotException {
        String[] temp = taskString.split(" ", 2);
        String taskType = temp[0];
        String taskInfo = temp.length > 1 ? temp[1] : "";
        if (taskInfo.isEmpty()) {
            throw new DescriptionException();
        }
        switch (taskType) {
            case "todo":
                return addTask(new ToDo(taskInfo));
            case "deadline":
                String[] temp2 = taskInfo.split(" /", 2);
                return addTask(new Deadline(temp2[0], temp2[1].substring(3)));
            case "event":
                String[] temp3 = taskInfo.split(" /", 3);
                return addTask(new Event(temp3[0], temp3[1].substring(5), temp3[2].substring(3)));
            default:
                throw new CommandException();
        }
    }
    private Task getTask(int i) {
        return this.list.get(i);
    }
    public String getFileRep(int i) { return getTask(i).fileRep(); }
    private String addTask(Task task) throws BotBotException {
        StringBuilder output = new StringBuilder();
        output.append(String.format("I have added the following task:\n%s\n", task.getRep()));
        this.list.add(task);
        output.append(String.format("You now have %d task(s) in your list!%n", list.size()));
        return output.toString();
    }
    public String deleteTask(int i) {
        StringBuilder output = new StringBuilder();
        output.append(String.format("I have removed the following task:\n%s\n", output.append(getTask(i - 1).getRep())));
        this.list.remove(i - 1);
        output.append(String.format("You now have %d task(s) in your list!", list.size()));
        return output.toString();
    }
    public String printList() {
        StringBuilder output = new StringBuilder();
        for (int i = 1; i < this.list.size() + 1; i++) {
            output.append(i).append(". ").append(getTask(i - 1).getRep()).append("\n");
        }
        return output.toString();
    }

    public String mark(int i) {
        Task task = getTask(i - 1);
        task.mark();
        assert task.done;
        return task.getRep();
    } // Handle index exception here maybe

    public String unmark(int i) {
        Task task = getTask(i - 1);
        task.unmark();
        assert !task.done;
        return task.getRep();
    }
    public void addTaskInit(Task t) {
        this.list.add(t);
    }
    public int size() {
        return this.list.size();
    }
    public String printFind(String s) {
        int count = 1;
        StringBuilder output = new StringBuilder();
        for (Task task : list) {
            if (task.getName().contains(s)) {
                output.append(count);
                output.append(". ");
                output.append(task.getRep());
                count++;
            }
        }
        return output.toString();
    }
}
