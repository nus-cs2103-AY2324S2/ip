package Commands;

import Tasks.TaskList;
import Tasks.Task;
import Ui.Ui;
import Storages.Storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Command {
    private String command;

    public Command(String command) {
        this.command = command;
    }

    public String[] keyWords(String fullCommand) {
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
        }
        return res;
    }

    public DateTimeFormatter[] formatDateTime() {
        return new DateTimeFormatter[]{
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("d-M-yyyy")
        };
    }

    public void fromCommand(String fullCommand, int fromStartIndex, String symbol, TaskList tasks) {
        int toStartIndex = fullCommand.indexOf("/to");
        String to = fullCommand.substring(toStartIndex + 4);
        String from = fullCommand.substring(fromStartIndex + 6, toStartIndex - 1);
        LocalDateTime duefrom = null;
        LocalDateTime dueto = null;
        DateTimeFormatter[] formats = this.formatDateTime();

        for (DateTimeFormatter format : formats) {
            try {
                duefrom = LocalDateTime.parse(from, format);
                dueto = LocalDateTime.parse(to, format);
                String task = fullCommand.substring(0, fromStartIndex - 1);
                String dueEvent = task + "(from: " + duefrom.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a")) + " to: " + dueto.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a")) + ")";
                Task newTask = new Task(dueEvent, symbol, false, duefrom, dueto);
                tasks.addTask(newTask);
                break;
            } catch (DateTimeParseException var16) {
                System.out.println("Please pass date in dd/MM/yyyy HHmm format");
            }
        }

    }

    public void byCommand(String fullCommand, int byStartIndex, String symbol, TaskList tasks) {
        String timecommand = fullCommand.substring(byStartIndex + 4);
        LocalDateTime time = null;
        DateTimeFormatter[] formats = this.formatDateTime();
        for (DateTimeFormatter format : formats) {
            try {
                time = LocalDateTime.parse(timecommand, format);
                String task = fullCommand.substring(9, byStartIndex - 1);
                String deadline = task + "(by: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a")) + ")";
                Task newTask = new Task(deadline, symbol, false, time);
                tasks.addTask(newTask);
                break;
            } catch (DateTimeParseException var13) {
                System.out.println("Please pass date in dd/MM/yyyy HHmm format");
            }
        }

    }

    public String mark(TaskList tasks, String fullCommand) {
        String[] command = fullCommand.split(" ");
        try {
            Task currTask = tasks.getTask(Integer.parseInt(command[1]) - 1);
            System.out.println("Nice! I've marked this task as done:");
            currTask.setDone();
            return "    " + currTask.printTask(currTask.getType(), currTask.getDone(), currTask.getTask());
        } catch (IndexOutOfBoundsException err) {
            String single = tasks.size() <= 1 ? "task" : "tasks";
            int num = tasks.size();
            return "You only have " + num + " " + single + " currently. Type \"list\" to view all your current " + single;
        }
    }

    public String unmark(TaskList tasks, String fullCommand) {
        String[] command = fullCommand.split(" ");
        try {
            Task currTask = tasks.getTask(Integer.parseInt(command[1]) - 1);
            System.out.println("OK, I've marked this task as not done yet:");
            currTask.setUndone();
            return "    " + currTask.printTask(currTask.getType(), currTask.getDone(), currTask.getTask());
        } catch (IndexOutOfBoundsException var6) {
            String single = tasks.size() <= 1 ? "task" : "tasks";
            int num = tasks.size();
            return "You only have " + num + " " + single + " currently. Type \"list\" to view all your current " + single;
        }
    }

    public void delete(TaskList tasks, String fullCommand) {
        String[] command = fullCommand.split(" ");
        String single = tasks.size() <= 1 ? "task" : "tasks";
        try {
            Task currTask = tasks.getTask(Integer.parseInt(command[1]) - 1);
            System.out.println("Noted. I've removed this task:");
            tasks.deleteTask(Integer.parseInt(command[1]) - 1);
            System.out.println("    " + currTask.printTask(currTask.getType(), currTask.getDone(), currTask.getTask()));
        } catch (IndexOutOfBoundsException var6) {
            System.out.println("You only have " + tasks.size() + " " + single + " currently. Type \"tasks\" to view all your current " + single);
        }
        System.out.println("Now you have " + tasks.size() + " " + single + " in the tasks.");
    }

    public boolean bye() {
        return false;
    }

    public void execute(String fullCommand, TaskList tasks, Ui ui, Storage store) {
        String[] command = this.keyWords(fullCommand);
        if (command.length <= 1) {
            ui.listOfCommands();
        } else {
            switch (command[0]) {
                case "list":
                    ui.output(tasks.getList().toString());
                    break;
                case "todo":
                case "event":
                case "deadline":
                    ui.showLine();
                    System.out.println("Got it. I've added this task: ");
                    String symbol = this.getTaskSymbol(command[0]);
                    int byStartIndex = fullCommand.indexOf("/by");
                    int fromStartIndex = fullCommand.indexOf("/from");
                    if (byStartIndex == -1 && fromStartIndex == -1) {
                        String des = fullCommand.substring(command[0].length() + 1);
                        Task newTask = new Task(des, symbol, false);
                        tasks.addTask(newTask);
                    } else if (fromStartIndex == -1) {
                        byCommand(fullCommand, byStartIndex, symbol, tasks);
                    } else {
                        fromCommand(fullCommand, fromStartIndex, symbol, tasks);
                    }

                    Task latestTask = tasks.getTask(tasks.size() - 1);
                    String task = latestTask.printTask(latestTask.getType(), latestTask.getDone(), latestTask.getTask());
                    System.out.println("    " + task);
                    String singular = tasks.size() == 1 ? "task" : "tasks";
                    int num = tasks.size();
                    System.out.println("Now you have " + num + " " + singular + " in the list.");
                    ui.showLine();
                    break;
                case "mark":
                    ui.output(mark(tasks, fullCommand));
                    break;
                case "unmark":
                    ui.output(unmark(tasks, fullCommand));
                    break;
                case "delete":
                    ui.showLine();
                    delete(tasks, fullCommand);
                    ui.showLine();
                default:
                    ui.listOfCommands();
            }

        }
    }
}
