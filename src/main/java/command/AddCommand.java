package command;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import common.DukeException;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.ToDo;

/**
 * {@inheritDocs}
 * Adds a task into a tasklist.
 */
public class AddCommand extends Command {
    private String command;
    private TaskList taskList;
    private StringTokenizer st;

    /**
     * Creates an instance of AddCommand.
     */
    public AddCommand(String command, TaskList taskList, StringTokenizer st) {
        this.command = command;
        this.taskList = taskList;
        this.st = st;
    }

    /**
     * {@inheritDocs}
     * Adds a task into a tasklist.
     *
     * @throws DukeException If the command cannot be executed.
     */
    @Override
    public String execute() throws DukeException {
        try {
            String taskName = "";

            switch (command) {
            case "todo":
                while (st.hasMoreTokens()) {
                    taskName += st.nextToken() + " ";
                }

                if (taskName.equals("")) {
                    throw new DukeException("Missing task name. :(");

                } else {
                    ToDo td = new ToDo(taskName.strip());
                    taskList.add(td);
                    assert taskList.contains(td) : taskName + " is not added.";

                    String s = "Got it. I've added this task:\n"
                            + "  " + td.toString()
                            + "\nNow you have " + taskList.size() + " tasks in the list.";

                    System.out.println(s);
                    return s;                
                }

            case "deadline":
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
                    throw new DukeException("Missing field(s) / incorrect input(s). :("
                            + "\nCheck if you have used the keyword \"/by\"");

                } else {
                    Deadline d = new Deadline(taskName.strip(), deadline);
                    taskList.add(d);
                    assert taskList.contains(d) : taskName + " is not added.";

                    String s = "Got it. I've added this task:\n"
                            + "  " + d.toString()
                            + "\nNow you have " + taskList.size() + " tasks in the list.";

                    System.out.println(s);
                    return s;    
                }

            case "event":
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
                    throw new DukeException("Missing field(s) / incorrect input(s). :(\n"
                            + "Check if you have used the keyword \"/from\" and \"/to\"");

                } else {
                    Event e = new Event(taskName.strip(), startTime, endTime);
                    taskList.add(e);
                    assert taskList.contains(e) : taskName + " is not added.";


                    String s = "Got it. I've added this task:\n"
                            + "  " + e.toString()
                            + "\nNow you have " + taskList.size() + " tasks in the list.";

                    System.out.println(s);
                    return s; 
                }

            default:
                return "Missing field(s) / incorrect input(s). :(";
            }

        } catch (NoSuchElementException e) {
            System.out.println("Missing field(s) / incorrect input(s). :(");
            return "Missing field(s) / incorrect input(s). :(";
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return True if program will exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
