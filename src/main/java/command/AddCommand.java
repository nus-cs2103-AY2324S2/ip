package command;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import common.DukeException;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.ToDo;

public class AddCommand extends Command {
    private String command;
    private TaskList taskList;
    private StringTokenizer st;

    public AddCommand(String command, TaskList taskList, StringTokenizer st) {
        this.command = command;
        this.taskList = taskList;
        this.st = st;
    }

    @Override
    public void execute() throws DukeException {
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
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + td.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }
                break;

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
                        throw new DukeException("Missing field(s) / incorrect input(s). :(\nCheck if you have used the keyword \"/by\"");
                    } else {
                        Deadline d = new Deadline(taskName.strip(), deadline);
                        taskList.add(d);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(" " + d.toString());
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    }
                    break;

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
                    throw new DukeException("Missing field(s) / incorrect input(s). :(\n" + 
                            "Check if you have used the keyword \"/from\" and \"/to\"");
                } else {
                    Event e = new Event(taskName.strip(), startTime, endTime);
                    taskList.add(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + e.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Missing field(s) / incorrect input(s). :(");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }

}
