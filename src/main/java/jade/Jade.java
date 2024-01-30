package jade;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Jade {
    public String line = "\t——————————————————————————————————————————\n";
    public String logo = "\t  ____   ___    ____     ______\n"
            + "\t  |  |  / _ \\  |  ___ \\ / |____/\n"
            + "\t  |  | | | | | | |  | | | |____\n"
            + "\t  |  | | |_| | | |  | | | |____|\n"
            + "\t|\\|  | | ___ | | |__| | | |____\n"
            + "\t \\___| |_| |_| |_____/  \\_|____\\\n";
    private boolean exitProg = false;
    private List<Task> userList;

    Jade() {
       this.userList = new ArrayList<>();
    }

    public static void main(String[] args) {
        Jade myJade = new Jade();
        Scanner scanner = new Scanner(System.in);
        myJade.launch(scanner);
    }

    private void launch(Scanner scanner) {
        System.out.printf("%s%s\tHello, I'm Jade\n\twhat can I do for you?\n%s", logo, line, line);
        while(!this.exitProg) {
            String command = scanner.nextLine();
            echo(command);
        }
    }

    public void echo(String inputCommand) {
        try {
            String[] command = inputCommand.split(" ");
            Command commandHeader = Command.valueOf(command[0]);
            if (commandHeader.equals(Command.todo)) {
                if (command.length == 1) {
                    throw new JadeException("Sorry, your task to do cannot be empty.");
                }
                 addTodo(command);
            } else if (commandHeader.equals(Command.deadline)) {
                addDeadline(command);
            } else if (commandHeader.equals(Command.event)) {
                addEvent(command);
            } else if (commandHeader.equals(Command.list)) {
                printList();
            } else if (commandHeader.equals(Command.mark)) {
                if (command.length == 1 || Integer.parseInt(command[1]) > userList.size()) {
                    throw new JadeException("Please input a valid number to mark Done.");
                }
                markDone(command[1]);
            } else if (commandHeader.equals(Command.unmark)) {
                if (command.length == 1 || Integer.parseInt(command[1]) > userList.size()) {
                    throw new JadeException("Please input a valid number to unmark Done.");
                }
                unmarkDone(command[1]);
            } else if (commandHeader.equals(Command.delete)) {
                if (command.length == 1 || Integer.parseInt(command[1]) > userList.size()) {
                    throw new JadeException("Please input a valid number to delete the task.");
                }
                deleteTask(command[1]);
            } else if (commandHeader.equals(Command.bye)) {
                goodbye();
            } else {
                throw new JadeException("Sorry, I don't have this command currently.");
            }
        } catch (JadeException je) {
            System.out.printf("%s\t%s\n%s", line, je.getMessage(), line);
        } catch (IllegalArgumentException iae) {
            System.out.printf("%s\tInput is invalid, please retry. \n%s", line, line);
        }
    }

    public void addTodo(String[] command) {
        String todoDescription = String.join(" ", Arrays.copyOfRange(command, 1, command.length));
        Task todoT = new Todo(todoDescription);
        userList.add(todoT);
        saveChange();
        System.out.printf("%s\tGot it. I've added this task:\n\t %s\n\tNow you have %d task(s) in the list.\n%s", line, todoT, userList.size(), line);
    }

    public void addDeadline(String[] command) {
        String deadlineDescription = String.join(" ", Arrays.copyOfRange(command, 1, Arrays.asList(command).indexOf("/by")));
        String deadlineDate = String.join(" ", Arrays.copyOfRange(command, Arrays.asList(command).indexOf("/by") + 1, command.length));
        Task deadlineT = new Deadline(deadlineDescription, deadlineDate);
        userList.add(deadlineT);
        saveChange();
        System.out.printf("%s\tGot it. I've added this task:\n\t %s\n\tNow you have %d task(s) in the list.\n%s", line, deadlineT, userList.size(), line);
    }

    public void addEvent(String[] command) {
        String eventDescription = String.join(" ", Arrays.copyOfRange(command, 1, Arrays.asList(command).indexOf("/from")));
        String startDate = String.join(" ", Arrays.copyOfRange(command, Arrays.asList(command).indexOf("/from") + 1, Arrays.asList(command).indexOf("/to")));
        String endDate = String.join(" ", Arrays.copyOfRange(command, Arrays.asList(command).indexOf("/to") + 1, command.length));
        Task eventT = new Event(eventDescription, startDate, endDate);
        userList.add(eventT);
        saveChange();
        System.out.printf("%s\tGot it. I've added this task:\n\t %s\n\tNow you have %d task(s) in the list.\n%s", line, eventT, userList.size(), line);
    }

    public void printList() {
        System.out.println(line + "\tHere are the task(s) in your list:");
        for (int i = 1; i <= userList.size(); i++) {
            System.out.printf("\t%d. %s\n", i, userList.get(i-1));
        }
        System.out.print(line);
    }

    public void markDone(String inputIndex) {
        int indexMark = Integer.parseInt(inputIndex);
        userList.get(indexMark-1).mark();
        saveChange();
        System.out.printf("%s\tNice, I've marked this task as done:\n\t  %s\n%s", line, userList.get(indexMark-1), line);
    }

    public void unmarkDone(String inputIndex) {
        int indexUnmark = Integer.parseInt(inputIndex);
        userList.get(indexUnmark-1).unMark();
        saveChange();
        System.out.printf("%s\tOK, I've marked this task as not done yet:\n\t  %s\n%s", line, userList.get(indexUnmark-1), line);
    }

    public void deleteTask(String inputIndex) {
        int indexUnmark = Integer.parseInt(inputIndex);
        Task deletedTask = userList.get(indexUnmark-1);
        userList.remove(indexUnmark-1);
        saveChange();
        System.out.printf("%s\tOK, I've deleted this task:\n\t  %s\n\tNow you have %d task(s) in the list.\n%s", line, deletedTask, userList.size(), line);
    }

    public void goodbye() {
        System.out.printf("%s\tBye. Hope to see you again soon.\n%s",line,line);
        exitProg = true;
    }

    public String listFormatter() {
        StringBuilder sb = new StringBuilder();
        for (Task task : userList) {
            sb.append(task.taskFormatter());
        }
        return sb.toString();
    }

    private void saveChange() {
        try {
            Path dataDir = java.nio.file.Paths.get(System.getProperty("user.dir"), "data");
            File jadeDir = new File(dataDir.toString());
            if (!jadeDir.exists()) {
                jadeDir.mkdir();
            }
            Path dataFilePath = java.nio.file.Paths.get(System.getProperty("user.dir"), "data", "jadeList.txt");
            File jadeList = new File(dataFilePath.toString());
            if (!jadeList.exists()) {
                jadeList.createNewFile();
            }
            FileWriter jadeListWriter = new FileWriter(jadeList);
            jadeListWriter.write(listFormatter());
            jadeListWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
