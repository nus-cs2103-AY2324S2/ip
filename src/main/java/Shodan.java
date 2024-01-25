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
                    default:
                        System.out.println("Command not recognised.");
                        break;
                }
            } catch (Exception e) {
                System.err.println(e);
                System.out.println("Something went wrong. Did you type your command correctly?");
            }
        }
    }
    public static void list() {
        if (taskList.isEmpty()) {
            System.out.println("You currently have no tasks.");
        } else {
            System.out.println("Here is your list of tasks:");
        }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(i + 1 + ". ");
            System.out.println(taskList.get(i));
        }
    }
    public static void mark(List<String> tokens, boolean done) {
        int taskNum = Integer.parseInt(tokens.get(1));
        //TODO: invalid input check
//        if (taskNum < 1 || taskNum > taskList.size()) {
//            System.out.println("Couldn't find task. Try again?");
//            continue;
//        }
        Task selectedTask = taskList.get(taskNum - 1);
        if (done) {
            selectedTask.done();
            System.out.println("Task set to done: " + selectedTask);
        } else {
            selectedTask.undone();
            System.out.println("Task has been set as not done yet: " + selectedTask);
        }
    }

    public static void addTask(List<String> tokens) {
        //TODO: need to handle exceptions when adding tasks
        Task newTask = null;
        StringJoiner taskName = new StringJoiner(" ");
        StringJoiner startDate = new StringJoiner(" ");
        StringJoiner endDate = new StringJoiner(" ");
        int state = 0;

        switch(tokens.remove(0).toLowerCase()) {
            case "todo":
                newTask = new Todo(String.join(" ", tokens));
                break;
            case "deadline":
                for (String token : tokens) {
                    if (token.equals("/by")) {
                        state = 1;
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
                newTask = new Deadline(taskName.toString(), endDate.toString());
                break;
            case "event":
                for (String token : tokens) {
                    if (token.equals("/to")) {
                        state = 1;
                        continue;
                    } else if (token.equals("/from")) {
                        state = 2;
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
                newTask = new Event(taskName.toString(), startDate.toString(), endDate.toString());
                break;
        }
        taskList.add(newTask);
        System.out.println("Task has been added: " + newTask);
        System.out.printf("You have %d tasks now.\n", taskList.size());
    }
    public static void exit() {
        System.out.println("Goodbye.");
    }
}
