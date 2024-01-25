import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.*;

public class Shodan {
    private static List<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        greet();
        handleInput();
        exit();
    }

    public static void greet() {
        String logo = "   _____ __  ______  ____  ___    _   __  \n"
                    + "  / ___// / / / __ \\/ __ \\/   |  / | / /\n"
                    + "  \\__ \\/ /_/ / / / / / / / /| | /  |/ / \n"
                    + " ___/ / __  / /_/ / /_/ / ___ |/ /|  /    \n"
                    + "/____/_/ /_/\\____/_____/_/  |_/_/ |_/    \n";
        System.out.println(logo);
        System.out.println("Greetings, human.");
        System.out.println("What can I do for you?");
    }
    public static void handleInput() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            try {
                System.out.print("> ");
                String line = sc.nextLine().stripLeading();
                if (line.isEmpty()) continue;
                List<String> tokens = new LinkedList<String>(Arrays.asList(line.split(" ")));
                String input = tokens.get(0).toLowerCase();
                switch(input) {
                    case "bye":
                        sc.close();
                        return;
                    case "list":
                        list();
                        break;
                    case "mark":
                        mark(tokens, true);
                        break;
                    case "unmark":
                        mark(tokens, false);
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        addTask(tokens);
                        break;
                    case "delete":
                        delete(tokens);
                        break;
                    default:
                        System.out.println("Command not recognised.");
                        break;
                }
            } catch (ShodanException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void list() throws ShodanException {
        if (taskList.isEmpty()) {
            throw new ShodanException("You currently have no tasks.");
        }
        System.out.println("Here is your list of tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(i + 1 + ". ");
            System.out.println(taskList.get(i));
        }
    }
    public static void mark(List<String> tokens, boolean done) throws ShodanException {
        if (tokens.size() == 1) {
            throw new ShodanException("No arguments provided. Please specify the task number, for example: \n\tmark 1");
        } else if (tokens.size() != 2) {
            throw new ShodanException("Too many arguments. Please specify the task number, for example: \n\t mark 1");
        }
        try {
            int taskNum = Integer.parseInt(tokens.get(1));
            if (taskNum < 1 || taskNum > taskList.size()) {
                throw new ShodanException("Couldn't find task with that number. Use the list command to view all current tasks.");
            }
            Task selectedTask = taskList.get(taskNum - 1);
            if (done) {
                selectedTask.done();
                System.out.println("Task set to done:\n\t" + selectedTask);
            } else {
                selectedTask.undone();
                System.out.println("Task has been set as not done yet:\n\t" + selectedTask);
            }
        } catch (NumberFormatException e) {
            throw new ShodanException("Input argument not recognised, please enter the task number.");
        }
    }
    public static void delete(List<String> tokens) throws ShodanException {
        if (tokens.size() == 1) {
            throw new ShodanException("No arguments provided. Please specify the task number, for example: \n\tdelete 1");
        } else if (tokens.size() != 2) {
            throw new ShodanException("Too many arguments. Please specify the task number, for example: \n\t delete 1");
        }
        try {
            int taskNum = Integer.parseInt(tokens.get(1));
            if (taskNum < 1 || taskNum > taskList.size()) {
                throw new ShodanException("Couldn't find task with that number. Use the list command to view all current tasks.");
            }
            Task selectedTask = taskList.remove(taskNum - 1);
            System.out.println("The following task has been removed:\n\t" + selectedTask);
            System.out.printf("There are now %d tasks remaining in the list.\n", taskList.size());
        } catch (NumberFormatException e) {
            throw new ShodanException("Input argument not recognised, please enter the task number.");
        }
    }

    public static void addTask(List<String> tokens) throws ShodanException {
        Task newTask = null;
        StringJoiner taskName = new StringJoiner(" ");
        StringJoiner startDate = new StringJoiner(" ");
        StringJoiner endDate = new StringJoiner(" ");
        int state = 0;
        int sections = 1;
        switch(tokens.remove(0).toLowerCase()) {
            case "todo":
                newTask = new Todo(String.join(" ", tokens));
                break;
            case "deadline":
                for (String token : tokens) {
                    if (token.equals("/by")) {
                        state = 1;
                        sections++;
                        continue;
                    }
                    switch (state) {
                        case 0:
                            taskName.add(token);
                            break;
                        case 1:
                            endDate.add(token);
                            break;
                    }
                }
                if (sections < 2) {
                    throw new ShodanException("Adding a deadline requires a end date specified with /by. For example:\n\tdeadline return books /by 2pm");
                }
                if (endDate.toString().isBlank()) {
                    throw new ShodanException("The /by field cannot be empty. Please specify a end date/time.");
                }
                newTask = new Deadline(taskName.toString(), endDate.toString());
                break;
            case "event":
                for (String token : tokens) {
                    if (token.equals("/to")) {
                        state = 1;
                        sections++;
                        continue;
                    } else if (token.equals("/from")) {
                        state = 2;
                        sections++;
                        continue;
                    }
                    switch (state) {
                        case 0:
                            taskName.add(token);
                            break;
                        case 1:
                            endDate.add(token);
                            break;
                        case 2:
                            startDate.add(token);
                            break;
                    }
                }
                if (sections < 3) {
                    throw new ShodanException("Adding an event requires both a start and end date/time using /from and /to. For example:\n\tevent attend birthday party /from 5pm /to 6pm");
                }
                if (startDate.toString().isBlank()) {
                    throw new ShodanException("The /from field cannot be empty. Please specify a end date/time.");
                }
                if (endDate.toString().isBlank()) {
                    throw new ShodanException("The /to field cannot be empty. Please specify a end date/time.");
                }
                newTask = new Event(taskName.toString(), startDate.toString(), endDate.toString());
                break;
        }
        if (newTask.getName().isBlank()) {
            throw new ShodanException("You need to specify a name for your task.");
        }
        taskList.add(newTask);
        System.out.println("Task has been added:\n\t" + newTask);
        System.out.printf("You have %d tasks now.\n", taskList.size());
    }
    public static void exit() {
        System.out.println("Goodbye.");
    }
}
