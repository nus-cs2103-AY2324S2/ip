package duke;
import exceptions.DukeException;
import exceptions.TaskNotExistException;
import model.Deadline;
import model.Event;
import model.Task;
import model.Todo;
import service.Parser;
import service.Storage;
import service.TaskList;
import ui.UI;

public class Duke {

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response = processCommand(input, taskList, true);
        
        return response;
    }


    public enum Command {
        Todo, Deadline, Event, Delete, Mark, Unmark, List, Bye, Unknown, Find;

        public static Command fromString(String maybeCommand) {
            try {
                return Command.valueOf(maybeCommand);
            } catch (Exception e) {
                return Unknown;
            }
        }
    }

    /**
     * Performs the appropriate action based on the user's input of the task.
     * From adding of tasks which can be todos, deadlines and events, deleting and marking and unmarking them.
     *
     * @param userInput user's commands
     * @param todos the list of tasks that are currently present.
     * @param isVerbose helps to ensure whether we are preloading (includes the need to save the entries)
     * @throws RuntimeException
     */
    public static String processCommand(String userInput, TaskList todos, Boolean isVerbose) throws RuntimeException {
        System.out.println("USERINPUT: " + userInput);
        String maybeCommand;
        String arr[];
        String reply = "";
        try {
            arr = userInput.split(" ", 2); // String in Array format. Useful: https://www.geeksforgeeks.org/split-string-java-examples/
            assert((arr.length) > 1);
            maybeCommand = arr[0];
        } catch (Exception e) {
            maybeCommand = null;
            return "null";
        }

        Command command = Command.fromString(maybeCommand);

        switch (command) {
        case Find:
            //add tasks with that keyword to a new arrayList, print items in that array list.
            if (isVerbose) {
                TaskList matchingTasks = new TaskList();
                String query = arr[1];
                for (int i = 0; i < todos.size(); i++) {
                    Task currTask = todos.get(i);
                    if (currTask.toString().contains(query)) {
                        matchingTasks.add(currTask);
                    }
                }

                ui.showLine();
                if (matchingTasks.size() > 0) {
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < matchingTasks.size(); i++) {
                        System.out.println((i + 1) + ". " + matchingTasks.get(i).toString());
                        reply += ((i + 1) + ". " + matchingTasks.get(i).toString());
                    }
                } else {
                    System.out.println(" Sorry no tasks found matching that word :<");
                    reply += (" Sorry no tasks found matching that word :<");
                }
                ui.showLine();
            }
            break;

        case Bye:
            reply += ui.formalities("farewell");
            break;
        case List:
            // list tasks
            if (isVerbose) {
                ui.showLine();
                System.out.println(" Here are the tasks in your list:");
                reply += " Here are the tasks in your list:";
            }
            for (int i = 0; i < todos.size(); i++) {
                Task currTask = todos.get(i);
                if (isVerbose) {
                    System.out.println((i + 1) + ". " + currTask.toString());
                    reply += (i + 1) + ". " + currTask.toString();
                }
            }
            if (isVerbose) {
                ui.showLine();
            }
            break;
        case Mark:
            // mark task as done
            Integer index = Integer.valueOf(arr[1]) - 1;
            Task currTask = todos.get(index);
            currTask.markAsDone();
            if (isVerbose) {
                System.out.print(" Nice! I've marked this task as done:\n");
                reply +=" Nice! I've marked this task as done:\n";
                System.out.println(" " + currTask.toString());
                reply += " " + currTask.toString();
                ui.showLine();
                storage.updateRecords(taskList);
            }
            break;
        case Unmark:
            // mark task as undone
            index = Integer.valueOf(arr[1]) - 1;
            if (isVerbose) {
                ui.showLine();
                System.out.print(" OK, I've marked this task as not done yet:\n");
                reply +=" OK, I've marked this task as not done yet:\n";
            }

            currTask = todos.get(index);
            currTask.markAsUndone();

            if (isVerbose) {
                System.out.println(" " + currTask.toString());
                ui.showLine();
                storage.updateRecords(taskList);
            }
            break;
        case Todo:
            if (arr.length == 1) {
                throw new DukeException(" Nuh uh! The description of a todo cannot be empty.");
            }
            if (isVerbose) {
                ui.showLine();
                System.out.println("Got it. Added this task:");
            }
            Todo todo = new Todo(arr[1]);
            todos.add(todo);
            if (isVerbose) {
                System.out.println(todo.toString());
                System.out.println("Now you have " + todos.size() + " tasks in the list.");
                ui.showLine();
                storage.updateRecords(taskList);
            }
            break;
        case Deadline:
            if (arr.length == 1) {
                throw new DukeException(" Nuh uh! The description of a deadline cannot be empty.\nMake sure to add a deadline after the description with /by too!");
            }
            if (isVerbose) {
                ui.showLine();
                System.out.println("Got it. Added this task:");
            }
            String arguments[] = arr[1].split(" /by ");
            String description = arguments[0];
            String by = arguments[1];
            Deadline deadline = new Deadline(description, by);
            todos.add(deadline);
            if (isVerbose) {
                System.out.println(deadline.toString());
                System.out.println("Now you have " + todos.size() + " tasks in the list.");
                ui.showLine();
                storage.updateRecords(taskList);
            }
            break;
        case Event:
            if (arr.length == 4) {
                throw new DukeException(" Nuh uh! The description of an event cannot be empty.\nMake sure to add a from and to date after the description with /from and /to too!");
            }
            if (isVerbose) {
                ui.showLine();
                System.out.println("Got it. Added this task:");
            }
            // extraction of parameters
            String getDesc[] = arr[1].split(" /from ");
            String desc = getDesc[0];
            String getDates[] = getDesc[1].split(" /to ");
            String from = getDates[0];
            String to = getDates[1];

            //creating of event
            Event event = new Event(desc, from, to);
            todos.add(event);

            if (isVerbose) {
                System.out.println(event.toString());
                System.out.println("Now you have " + todos.size() + " tasks in the list.");
                ui.showLine();
                storage.updateRecords(taskList);
            }
            break;
        case Delete:
            if (arr.length == 1) {
                throw new DukeException(" Nuh uh! Which task to delete? \nMake sure to add the task number!");
            }
            if (isVerbose) {
                ui.showLine();
            }
            Integer i = Integer.valueOf(arr[1]);
            Task task;
            try {
                task = todos.get(i - 1);
                todos.remove(i - 1);
            } catch (Exception e) {
                throw new TaskNotExistException(Integer.toString(i));
            }
            if (isVerbose) {
                System.out.println("Okay! I've fed this task to Woodstock, bye bye!:");
                System.out.println(task.toString());
                System.out.println("Now you have " + todos.size() + " tasks in the list.");
                ui.showLine();
                storage.updateRecords(taskList);
            }
            break;
        default:
            ui.showLine();
            System.out.println("Uh ah I don't understand ya ");
            ui.showLine();
            break;
        }
        return reply;
    }

    private static TaskList taskList;
    private static UI ui;
    private static Storage storage;

    /**
     * Constructor of Duke
     * @param filePath file storage location to save and retrieve list of tasks
     */
    public Duke(String filePath) {
        ui = new UI();
        taskList = new TaskList();
        storage = new Storage(filePath);
    }

    public Duke() {
       new Duke("./src/main/java/data/duke.txt");
    }

    /**
     * With a Duke object, .run() is the main entry point of the program, running all its processes.
     *
     */
    public void run(String input) {
        //do something
        ui.formalities("greet");

        //Load existing information
        try {
            storage.loadInfo(taskList);
            ui.showLine();
        } catch (Exception e) {
            e.getMessage();
            taskList = null;
        }

        while (true) {
            //Parsing user input.
            String command = new Parser().parse();

            //Process user command
            try {
                processCommand(command, taskList, true);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                ui.showLine();
            }
        }
    }

    /**
     * With a Duke object, .run() is the main entry point of the program, running all its processes.
     *
     */
    public void run() {
        //do something
        ui.formalities("greet");

        //Load existing information
        try {
            storage.loadInfo(taskList);
            ui.showLine();
        } catch (Exception e) {
            e.getMessage();
            taskList = null;
        }
        while (true) {
            //Parsing user input.
           String command = new Parser().parse();
           assert(command != null);
            //Process user command
            try {
                processCommand(command, taskList, true);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./src/main/java/data/duke.txt").run();
    }
}
