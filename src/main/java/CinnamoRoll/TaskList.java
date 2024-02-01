package CinnamoRoll;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class TaskList {
    private final ArrayList<Task> tasks;
    private final String PATH;
    private final Parser parser = new Parser();

    private enum Users {
        MARK,
        UNMARK,
        LIST,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }

    TaskList(String path) {
        this.tasks = new ArrayList<Task>();
        this.PATH = path;
    }

    TaskList() {
        this.tasks = new ArrayList<Task>();
        this.PATH = "src/main/test.txt";
    }

    TaskList(ArrayList<Task> tasks, String path) {
        this.tasks = tasks;
        this.PATH = path;
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Writes the tasks stored in the current task list to the file specified by the PATH constant.
     * The tasks are formatted and written according to the list() method's output.
     *
     * @throws IOException If there is an error writing to the file.
     */
    public void writeInto() throws IOException {
        try {
            FileWriter filewriter = new FileWriter(this.PATH);
            filewriter.write(this.list());
            filewriter.close();
        } catch (IOException ex) {
            System.out.println("No input provided!");
        }
    }

    /**
     * Executes a task instruction, adds the task to the task list, and writes the updated list to the file.
     *
     * @param instruction An array representing the task instruction.
     * @return A string message indicating the success of adding the task.
     * @throws IOException If there is an error writing to the file.
     * @throws CinnamoException If there is an error parsing the task instruction or a specific type of task-related exception occurs.
     */
    private String execute(String[] instruction) throws IOException, CinnamoException {
        try {
            Task task = this.parser.parse_tasks(instruction);
            this.tasks.add(task);
            this.writeInto();
            return task.added(this.tasks.size());
        } catch (ArrayIndexOutOfBoundsException exception) {
            if (instruction[0].equals("TODO")) {
                throw new CinnamoTodoException();
            } else if (instruction[0].equals("DEADLINE")) {
                throw new CinnamoDeadlineException();
            } else {
                throw new CinnamoEventException();
            }
        }
    }

    /**
     * Generates a formatted string listing all tasks in the current task list.
     *
     * @return A string representation of the tasks in the format "1. [Task1] \n 2. [Task2] \n ..."
     */
    private String list() {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            output += String.valueOf(i + 1) + "." + this.tasks.get(i).toString();
            if (i < this.tasks.size() - 1) {
                output += "\n";
            }
        }
        return output;
    }

    /**
     * Deletes a task from the task list based on the provided index and updates the file accordingly.
     *
     * @param str An array containing the command and task index for deletion.
     * @return A formatted string indicating the success of the deletion and the updated task list size.
     * @throws CinnamoIndexException If the provided index is out of bounds.
     * @throws Exception If there is an error parsing the index or writing to the file.
     */
    private String delete(String[] str) throws Exception, CinnamoIndexException {
        try {
            int index = Integer.parseInt(str[1]);
            Task temp = this.tasks.get(index - 1);
            this.tasks.remove(index - 1);
            this.writeInto();
            return String.format("Noted. I've removed the following task:%n   %s%n" +
                    "Now, you have %d tasks in the list", temp.toString(), this.tasks.size());
        } catch (IndexOutOfBoundsException exception) {
            throw new CinnamoIndexException();
        }
    }

    /**
     * Marks a task as done in the task list based on the provided index and updates the file accordingly.
     *
     * @param str An array containing the command and task index for marking the task as done.
     * @return A formatted string indicating the success of marking the task as done.
     * @throws CinnamoIndexException If the provided index is out of bounds.
     * @throws Exception If there is an error parsing the index or writing to the file.
     */
    String mark(String[] str) throws Exception, CinnamoIndexException {
        try {
            int index = Integer.parseInt(str[1]) - 1;
            this.tasks.get(index).marked();
            this.writeInto();
            return String.format("Nice! I've marked this task as done:%n   %s",
                    this.tasks.get(index).toString());

        } catch (IndexOutOfBoundsException exception) {
            throw new CinnamoIndexException();
        }
    }

    /**
     * Marks a task as not done in the task list based on the provided index and updates the file accordingly.
     *
     * @param str An array containing the command and task index for marking the task as not done.
     * @return A formatted string indicating the success of marking the task as not done.
     * @throws CinnamoIndexException If the provided index is out of bounds.
     * @throws Exception If there is an error parsing the index or writing to the file.
     */
    String unmark(String[] str) throws Exception, CinnamoIndexException {
        try {
            int index = Integer.parseInt(str[1]) - 1;
            this.tasks.get(index).unmarked();
            this.writeInto();
            return String.format("Ok! I've marked this task as not done yet:%n      %s",
                    this.tasks.get(index).toString());

        } catch (IndexOutOfBoundsException exception) {
            throw new CinnamoIndexException();
        }
    }

    /**
     * Processes and responds to the user's input by delegating tasks to corresponding methods.
     *
     * @param str The user's input to be processed.
     * @throws CinnamoException If an error related to task execution or command parsing occurs.
     * @throws Exception If there is an error during the response process.
     */
    void respond(String str) throws Exception, CinnamoException {
        try {
            Parser parser = new Parser();
            String[] arr = parser.parse(str);
            switch (TaskList.Users.valueOf(arr[0])) {
                case MARK:
                    System.out.println(this.mark(arr));
                    break;

                case UNMARK:
                    System.out.println(this.unmark(arr));
                    break;

                case LIST:
                    System.out.println(this.list());
                    break;

                case DELETE:
                    System.out.println(this.delete(arr));
                    break;

                case TODO:
                    System.out.println(this.execute(arr));
                    break;

                case DEADLINE:
                    System.out.println(this.execute(arr));
                    break;

                case EVENT:
                    System.out.println(this.execute(arr));
                    break;

                default:
                    throw new CinnamoException();
            }
        } catch (CinnamoException cin) {
            System.out.println(cin.toString());
        } catch (IllegalArgumentException e) {
            CinnamoException cin = new CinnamoException();
            System.out.println(cin.toString());
        }
    }
}
