import java.io.*;
import java.util.*;

enum Command {
    TODO("todo", "todo <task_name>"),
    DEADLINE("deadline", "deadline <task_name> /by <due_date>"),
    EVENT("event","event <task_name> /from <start_date> /to <end_date"),
    LIST("list", "list"),
    MARK ("mark", "mark <task_number>"),
    UNMARK("unmark", "unmark <task_number>"),
    DELETE("delete", "delete <task_number>"),
    TERMINATE("bye", "bye");

    public final String commandName;
    public final String commandSignature;

    private Command(String commandName, String commandSignature) {
        this.commandName = commandName;
        this.commandSignature = commandSignature;
    }
}
public class Duke {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("================================ \n" +
                "Hello I'm Axolotl! \n" +
                "What can I do for you? \n" +
                "================================ \n");

        String input = sc.nextLine();
        ArrayList<Task> list = new ArrayList<>();

        while (!input.equals(Command.TERMINATE.commandName)) {
            int cmdSplit = input.indexOf(" ");
            String command = input;
            String task = null;
            try {
                if (cmdSplit != -1) {
                    command = input.substring(0, cmdSplit);
                    task = input.substring(input.indexOf(" ") + 1);
                } else {
                    if (command.equals(Command.MARK.commandName) | command.equals(Command.UNMARK.commandName) | command.equals(Command.DELETE.commandName)) {
                        System.out.println("-------------------------------- \n" +
                                "Oops, I'm not sure which task you are referring to! Please indicate a task number (e.g. " + command + " 1) \n" +
                                "-------------------------------- \n");
                        continue;
                    } else if (command.equals(Command.TODO.commandName)) {
                        System.out.println("-------------------------------- \n" +
                                "Oops, wrong format! Please indicate task details (e.g. todo CS2103 Lab 1) \n" +
                                "-------------------------------- \n");
                        continue;
                    }
                }

                if (command.equals(Command.LIST.commandName)) {
                    printTaskList(list);
                } else if (command.equals(Command.MARK.commandName) | command.equals(Command.UNMARK.commandName) | command.equals(Command.DELETE.commandName)) {
                    int taskNo = Integer.parseInt(task) - 1;

                    if (taskNo >= list.size()) {
                        System.out.println("-------------------------------- \n" +
                                "Oops, task " + task + " does not exist. Please try again! \n" +
                                "Here is your list for reference: \n");
                        printTaskList(list);
                        System.out.println("-------------------------------- \n");
                    } else {
                        Task t = list.get(taskNo);
                        if (command.equals(Command.MARK.commandName)) {
                            t.done();
                            System.out.println("-------------------------------- \n" +
                                    "Nice! I've marked task " + task + " as done: \n" +
                                    t.toString() + "\n" +
                                    "-------------------------------- \n");
                        }
                        else if (command.equals(Command.UNMARK.commandName)) {
                            t.undone();
                            System.out.println("-------------------------------- \n" +
                                    "Sure, I've marked task " + task + " as not done yet: \n" +
                                    t.toString() + "\n" +
                                    "-------------------------------- \n");
                        }
                        else {
                            list.remove(taskNo);
                            System.out.println("-------------------------------- \n" +
                                    "Okay, I will delete this task: \n" +
                                    t.toString() + "\n" +
                                    "You now have " + list.size() + " in the list. \n" +
                                    "-------------------------------- \n");
                        }
                    }
                } else if (command.equals(Command.TODO.commandName) | command.equals(Command.DEADLINE.commandName) | command.equals(Command.EVENT.commandName)) {
                    Task t = null;
                    boolean success = true;
                    if (command.equals(Command.TODO.commandName)) {
                        t = new ToDo(task);
                    }
                    else if (command.equals(Command.DEADLINE.commandName)) {
                        if (task == null || !task.contains(" /by ")) {
                            success = false;
                            System.out.println("-------------------------------- \n" +
                                    "Oops, wrong format! Please follow this format for deadline task entries (e.g. deadline submit report /by 11/10/2019 5pm ) \n" +
                                    "-------------------------------- \n");
                        } else {
                            String[] deadline = task.split(" /by ");
                            t = new Deadline(deadline[0], deadline[1]);
                        }
                    }
                    else {
                        if (task == null || !(task.contains(" /from ") && task.contains(" /to "))) {
                            success = false;
                            System.out.println("-------------------------------- \n" +
                                    "Oops, wrong format! Please follow this format for event task entries (e.g. event team project meeting /from June 9th 2pm /to 4pm ) \n" +
                                    "-------------------------------- \n");
                        } else {
                            String event = task.substring(0, task.indexOf(" /from "));
                            String from = task.substring(task.indexOf("/from ") + 6, task.indexOf(" /to "));
                            String to = task.substring((task.indexOf("/to ") + 4));
                            t = new Event(event, from, to);
                        }
                    }
                    if (success) {
                        list.add(t);
                        System.out.println("-------------------------------- \n" +
                                "Sure, I've added this task: \n" +
                                t.toString() + "\n" +
                                "Now you have " + list.size() + " task(s) in the list. \n" +
                                "-------------------------------- \n");
                    }
                } else {
                    System.out.println( "-------------------------------- \n" +
                                        "Oops, I'm not sure what you meant by that! Commands available:");
                    for (Command c: EnumSet.allOf(Command.class)) {
                        System.out.println(c.commandSignature);
                    }

                    System.out.println("-------------------------------- \n");
                }
            }
            finally {
                input = sc.nextLine();
            }
        }

        System.out.println("================================ \n" +
                "Bye. Hope to see you again soon! \n" +
                "================================ \n");
    }

    public static void printTaskList(ArrayList<Task> list) {
        System.out.println( "-------------------------------- \n" +
                "Here are the tasks in your list:");
        if (list.isEmpty()) {
            System.out.println("----You have no tasks yet.----");
        }
        else {
            for (int i = 0; i < list.size(); i++) {
                Task iTask = list.get(i);
                System.out.println((i + 1) + ". " + iTask.toString());
            }
        }
        System.out.println( "-------------------------------- \n");
    }
}
