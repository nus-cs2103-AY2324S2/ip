import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import command.Command;
import common.DukeException;
import common.Parser;
import common.Storage;
import common.Ui;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.loadData());

    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Ui.showLine();
                Command cmd = new Parser(userInput, tasks).parse();
                cmd.execute();
                isExit = cmd.isExit();

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid task number. :(");
    
            } catch (NumberFormatException e) {
                System.out.println("Input is not an integer. :(");
    
            } catch (NoSuchElementException e) {
                System.out.println("Missing task number. :(");

            } catch (DukeException e) {
                System.out.println(e.getMessage());

            } finally {
                Ui.showLine();
            }
        }
        storage.saveDataAndExit(tasks);
        Ui.showLine();
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

        /* 
        // loop only exits if input is "bye"
        while (!userInput.toLowerCase().equals("bye")) {
            System.out.println("________________________________________\n");

            // Level-2: if the input is "list"
            if (userInput.toLowerCase().equals("list") || userInput.toLowerCase().equals("mark 1") || userInput.toLowerCase().equals("unmark 1")) {
                new Parser(userInput, tasks).parseAndExecute();

            } else {
                // using String splitting as main parsing tool
                // format <cmd> <task name> /by | /from <deadline> /to <endtime>
                StringTokenizer st = new StringTokenizer(userInput);
                String cmd = st.nextToken();

                    // Level-4: ToDo, Deadline, Event
                if (cmd.equals("todo") || cmd.equals("event") || cmd.equals("deadline")) {
                    try {
                        addTask(tasks, cmd, st);

                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }

                    // Level-6: Delete
                } else if (cmd.equals("delete")) {
                    try {
                        int indexOfTask = Integer.parseInt(st.nextToken());
                        delete(tasks, cmd, indexOfTask);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Invalid task number. :(");

                    } catch (NumberFormatException e) {
                        System.out.println("Input is not an integer. :(");

                    } catch (NoSuchElementException e) {
                        System.out.println("Missing task number. :(");
                    }

                    // Level-5: Throw exception for other inputs
                } else {
                    try {
                        throw new DukeException("OOPS!! Pls try again. :)");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            System.out.println("________________________________________\n");
            userInput = ui.readCommand();
        }
        */

    // Level-3: mark & unmark
    public static void changeStatus(TaskList taskList, String cmd,
            int indexOfTask) throws IndexOutOfBoundsException,
            NumberFormatException, NoSuchElementException {

        Task t = taskList.get(indexOfTask);
        if (cmd.equals("mark")) {
            t.markAsDone();
            System.out.println("Nice! I've marked this task as done:");

        } else if (cmd.equals("unmark")) {
            t.unmark();
            System.out.println("OK, I've marked this task as not done yet:");
        }

        System.out.println(" " + t.toString());
    }

    // Level-4: ToDo, Deadline, Event
    public static void addTask(TaskList taskList, String cmd,
            StringTokenizer st) throws DukeException {

        if (cmd.equals("todo")) {
            String taskName = "";
            while (st.hasMoreTokens()) {
                taskName += st.nextToken() + " ";
            }
            if (taskName.equals("")) {
                throw new DukeException("Missing task name. :(");
            } else {
                ToDo td = new ToDo(taskName.strip());
                taskList.add(td);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + td.toString());
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            }

        } else if (cmd.equals("deadline")) {
            try {
                String taskName = "";
                String deadline = "";

                while (st.hasMoreTokens()) {
                    String temp = st.nextToken();
                    if (temp.equals("/by")) {
                        deadline = st.nextToken();
                        break;
                    } else {
                        taskName += temp + " ";
                    }
                }

                if (taskName.equals("") || deadline.equals("")) {
                    throw new DukeException("Missing field(s) / incorrect input(s). :(\nCheck if you have used the keyword \"/by\"");
                } else {
                    Deadline d = new Deadline(taskName.strip(), deadline);
                    taskList.add(d);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + d.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }

            } catch (NoSuchElementException e) {
                System.out.println("Missing deadline. :(");
            }

        } else if (cmd.equals("event")) {
            try {
                String taskName = "";
                String startTime = "";
                String endTime = "";

                while (st.hasMoreTokens()) {
                    String temp = st.nextToken();
                    if (temp.equals("/from")) {
                        startTime = st.nextToken();
                        continue;
                    } else if (temp.equals("/to")) {
                        endTime = st.nextToken();
                        break;
                    } else {
                        taskName += temp + " ";
                    }
                }

                if (taskName.equals("") || startTime.equals("") || endTime.equals("")) {
                    throw new DukeException("Missing field(s) / incorrect input(s). :(\nCheck if you have used the keyword \"/from\" and \"/to\"");
                } else {
                    Event e = new Event(taskName.strip(), startTime, endTime);
                    taskList.add(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + e.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }
                
            } catch (NoSuchElementException e) {
                System.out.println("Missing field(s) / incorrect input(s). :(\nCheck if you have used the keyword \"/from\" and \"/to\"");
            }
        }

    }

    // Level-6: Delete
    public static void delete(TaskList taskList, String cmd,
            int indexOfTask) throws IndexOutOfBoundsException,
            NumberFormatException, NoSuchElementException {

        Task t = taskList.remove(indexOfTask);

        System.out.println("Noted, I've removed this task:");
        System.out.println(" " + t.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
