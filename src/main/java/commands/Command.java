package commands;

import storages.Storage;
import tasks.taskType.Deadline;
import tasks.taskType.Event;
import tasks.taskType.Task;
import tasks.TaskList;
import tasks.taskType.ToDo;
import ui.Ui;

/**
 * Represents the different valid commands a user enters
 */
public class Command {
    private String command;

    public Command(String command) {
        this.command = command;
    }

    public String[] getKeywords(String fullCommand) {
        return fullCommand.split(" ");
    }

    public String getTaskSymbol(String command) {
        String res = "";
        switch (command) {
        case "todo":
            res = "T";
            break;
        case "event":
            res = "E";
            break;
        case "deadline":
            res = "D";
            break;
        default:
            break;
        }
        return res;
    }

    /**
     * Returns a boolean of whether the date passed
     * by the user is in the list of current tasks.
     * @param word
     * @return
     */
    public boolean findWordPresent(Task currTask, String word) {
        return currTask.getTask().contains(word);
    }

    public TaskList getKeywordMatchList(TaskList tasks, String keyWord) {
        TaskList matchList = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.getTask(i);
            if (findWordPresent(currTask, keyWord)) {
                matchList.addTask(currTask);
            }
        }
        return matchList;
    }
    public String findCommand(TaskList matchList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchList.size(); i++) {
            sb.append(matchList.getTask(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a String with the details of the tasks and
     * the tasks being marked with an 'X' as done.
     *
     * @param tasks The full command as typed into the CLI by the user.
     * @param fullCommand The full command as typed into the CLI by the user.
     */
    public String mark(TaskList tasks, String fullCommand) {
        String[] command = fullCommand.split(" ");
        try {
            Task currTask = tasks.getTask(Integer.parseInt(command[1]) - 1);
            currTask.setDone();
            return "Nice! I've marked this task as done: \n"
                    + "    " + currTask;
        } catch (IndexOutOfBoundsException err) {
            String single = tasks.size() <= 1 ? "task" : "tasks";
            int num = tasks.size();
            return "You only have " + num + " "
                    + single
                    + " currently. Type \"list\" to view all your current "
                    + single;
        } catch (NumberFormatException err) {
            return "Please enter a valid input";
        }
    }

    /**
     * Returns a String with the details of the tasks and
     * the tasks being unmarked and the 'X' removed.
     *
     * @param tasks The full command as typed into the CLI by the user.
     * @param fullCommand The full command as typed into the CLI by the user.
     */
    public String unmark(TaskList tasks, String fullCommand) {
        String[] command = fullCommand.split(" ");
        try {
            Task currTask = tasks.getTask(Integer.parseInt(command[1]) - 1);
            currTask.setUndone();
            return "Nice! I've marked this task as not done yet: \n"
                    + "    " + currTask;
        } catch (IndexOutOfBoundsException err) {
            String single = tasks.size() <= 1 ? "task" : "tasks";
            int num = tasks.size();
            return "You only have " + num + " "
                    + single
                    + " currently. Type \"list\" to view all your current "
                    + single;
        } catch (NumberFormatException err) {
            return "Please enter a valid input";
        }
    }

    public String delete(TaskList tasks, String fullCommand) {
        String[] command = fullCommand.split(" ");
        String single = tasks.size() <= 1 ? "task" : "tasks";
        try {
            Task currTask = tasks.getTask(Integer.parseInt(command[1]) - 1);
            tasks.deleteTask(Integer.parseInt(command[1]) - 1);
            return "Noted. I've removed this task:\n"
                    + "    " + currTask + "\n"
                    + "Now you have " + tasks.size() + " " + single + " in the task list.";
        } catch (IndexOutOfBoundsException err) {
            return "You only have " + tasks.size() + " " + single + " currently. Type \"tasks\" to view all your current " + single;
        } catch (NumberFormatException err) {
            return "Please enter a valid input";
        }
    }

    public String displayTask(Task currTask, TaskList tasks) {
        String singular = tasks.size() == 1 ? "task" : "tasks";
        int num = tasks.size();
        return "Got it. I've added this task: \n"
                + "    " + currTask
                + "\nNow you have " + num + " " + singular + " in the list.";
    }

    public String currTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list: \n");
        for (int i = 1; i  <= tasks.size(); i++) {
            sb.append(i + ". ");
            sb.append(tasks.getTask(i - 1) + "\n");
        }
        return sb.toString();
    }

    public String execute(String fullCommand, TaskList tasks, Ui ui, Storage store) {
        String[] command = this.getKeywords(fullCommand);
        String symbol = this.getTaskSymbol(command[0]);
        String output = "";
        if (command.length <= 1 && command[0].equals("help")) {
            output = ui.output(ui.listOfCommands());
        } else {
            switch (command[0]) {
            case "list":
                output = ui.output(currTaskList(tasks));
                break;
            case "todo":
                ToDo newTdTask = new ToDo(fullCommand, symbol, false);
                tasks.addTask(newTdTask);
                output = ui.output(displayTask(newTdTask, tasks));
                break;
            case "event":
                Event newEventTask = new Event(fullCommand, symbol, false);
                tasks.addTask(newEventTask);
                output = ui.output(displayTask(newEventTask, tasks));
                break;
            case "deadline":
                Deadline newDlTask = new Deadline(fullCommand, symbol, false);
                tasks.addTask(newDlTask);
                output = ui.output(displayTask(newDlTask, tasks));
                break;
            case "mark":
                output = ui.output(mark(tasks, fullCommand));
                break;
            case "unmark":
                output = ui.output(unmark(tasks, fullCommand));
                break;
            case "delete":
                output = ui.output(delete(tasks, fullCommand));
                break;
            case "find":
                int keywordIndex = fullCommand.indexOf(command[1]);
                String keyWord = fullCommand.substring(keywordIndex);
                TaskList matchList = getKeywordMatchList(tasks, keyWord);
                output = ui.output(findCommand(matchList));
                break;
            case "help":
                ui.output(ui.listOfCommands());
            default:
                output = ui.output("Sorry Old Man Fredricksen don't recognise this input! Type \"help\" if you need a guide on input format!");
                break;
            }
        }
        return output;
    }
}
