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
        EVENT,
        FIND
    }

    TaskList() {
        this.tasks = new ArrayList<Task>();
        this.PATH = "src/main/java/test.txt";
    }

    TaskList(ArrayList<Task> tasks, String path) {
        this.tasks = tasks;
        this.PATH = path;
    }

    public Task getUser(int index) {
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
            filewriter.write(this.listTask());
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
    private String executeTask(String[] instruction) throws IOException, CinnamoException {
        try {
            Task task = this.parser.parseTasks(instruction);
            this.tasks.add(task);
            this.writeInto();
            return task.addTask(this.tasks.size());
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
    private String listTask() {
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
     * Searches for tasks containing a specified string and generates a formatted string listing the matching tasks.
     *
     * @param str The string to search for within task descriptions.
     * @return A formatted string listing the tasks that contain the specified string.
     */
    private String findTask(String str) {
        String output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).containString(str)) {
                output += String.valueOf(i + 1) + "." + this.tasks.get(i).toString();
                if (i < this.tasks.size() - 1) {
                    output += "\n";
                }
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
    private String deleteTask(String[] str) throws Exception, CinnamoIndexException {
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
    private String markTask(String[] str) throws Exception, CinnamoIndexException {
        try {
            int index = Integer.parseInt(str[1]) - 1;
            this.tasks.get(index).markTask();
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
    private String unmarkTask(String[] str) throws Exception, CinnamoIndexException {
        try {
            int index = Integer.parseInt(str[1]) - 1;
            this.tasks.get(index).unmarkTask();
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
    void respondUser(String str) throws Exception, CinnamoException {
        try {

            Parser parser = new Parser();
            String[] arr = parser.parseInput(str);

            switch (TaskList.Users.valueOf(arr[0])) {
                case MARK:
                    System.out.println(this.markTask(arr));
                    break;

                case UNMARK:
                    System.out.println(this.unmarkTask(arr));
                    break;

                case LIST:
                    System.out.println(this.listTask());
                    break;

                case DELETE:
                    System.out.println(this.deleteTask(arr));
                    break;

                case TODO:
                    System.out.println(this.executeTask(arr));
                    break;

                case DEADLINE:
                    System.out.println(this.executeTask(arr));
                    break;

                case EVENT:
                    System.out.println(this.executeTask(arr));
                    break;

                case FIND:
                    System.out.println(this.findTask(arr[1]));
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
