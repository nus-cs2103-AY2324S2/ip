package CinnamoRoll;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class TaskList {
    private final ArrayList<Task> tasks;
    private final String path = "src/main/java/Cinnamo.txt";
    private final Parser parser = new Parser();

    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getUser(int index) {
        return this.tasks.get(index);
    }

    /**
     * Writes the tasks stored in the current task list to the file specified by the PATH constant.
     * The tasks are formatted and written according to the list() method's output.
     */

    // Solution below, very specifically only on how to write into the file using filewriter object with
    // write/close methods, and how writeinto method allows other methods to
    // directly write into the file were adapted from two sources: chatgpt code and previous
    // batch's level 8 submission here:
    // 1. https://chat.openai.com/c/c34f9461-e0dc-4946-b2f2-eeec0e39aded
    // 2. https://github.com/david-eom/CS2103T-IP/releases/tag/Level-8
    // 3. Solution was also inspired by chatgpt generated result here:
    // https://chat.openai.com/c/c34f9461-e0dc-4946-b2f2-eeec0e39aded
    public void writeInto() {
        try {
            FileWriter filewriter = new FileWriter(this.path);
            String writeinto = this.storeTask();
            filewriter.write(writeinto);
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
     * @throws CinnamoException If there is an error parsing the task instruction
     */
    public String executeTask(String[] instruction) throws CinnamoException {
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
    public String listTask() {
        String output = "Here are the tasks in your list >.<:\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            output += String.valueOf(i + 1) + "." + this.tasks.get(i).toString();
            if (i < this.tasks.size() - 1) {
                output += "\n";
            }
        }
        return output;
    }

    public String storeTask() {
        String output = "";
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
    public String findTask(String str) {
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
    public String deleteTask(String[] str) throws Exception, CinnamoIndexException {
        try {
            int index = Integer.parseInt(str[1]);
            Task temp = this.tasks.get(index - 1);
            this.tasks.remove(index - 1);
            this.writeInto();
            return String.format("Noted. I've removed the following task:%n   %s%n"
                    + "Now, you have %d tasks in the list", temp.toString(), this.tasks.size());
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
    public String markTask(String[] str) throws Exception, CinnamoIndexException {
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
    public String unmarkTask(String[] str) throws Exception, CinnamoIndexException {
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
    // Solution below on how to convert string into enum value for switch-case was inspired
    // by the following stackoverflow forum:
    // https://stackoverflow.com/questions/10387329/using-string-representations-of-enum-values-in-switch-case
    void respondUser(String str) throws Exception {
        try {
            Parser parser = new Parser();
            String[] arr = parser.parseInput(str);

            switch (User.valueOf(arr[0])) {
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
