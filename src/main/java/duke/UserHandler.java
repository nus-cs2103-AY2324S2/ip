package duke;


import java.io.IOException;
import java.time.LocalDate;

/**
 * This class connects the chat logic with the GUI
 */

public class UserHandler {

    /**
     * This method processes the user commands
     * @param input User command
     * @param taskList The class that handles task actions
     * @param storage The class that handles file updating
     * @return The messaage the user sees
     * @throws DukeException
     */
    public static String chat(String input, TaskList taskList, Storage storage) throws DukeException {

        assert input != null : "Empty message";


        if (input.matches("bye")) {
            return "That's it, it's ova";


        } else if (input.toLowerCase().matches("list")) {

            return taskList.listTasks();


        } else if (input.toLowerCase().matches("\\bmark\\b.*")) {

            String r = taskList.markTask(input);

            try {
                storage.write("data/duke.txt", taskList.getList());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            return r;


        } else if (input.toLowerCase().matches("\\bunmark\\b.*")) {


            String r = taskList.unmarkTask(input);
            try {
                storage.write("data/duke.txt", taskList.getList());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            return r;


        } else if (input.toLowerCase().matches("\\bdeadline\\b.*")) {

            if (input.length() <= 9) {
                throw new DukeException("Empty Description");
            }

            String r = taskList.addTask(input);
            InputHandler handler = new InputHandler();
            String[] data = input.split("/");

            LocalDate deadlineDate = handler.formatDeadline(data);

            String task = data[0].substring(9);
            Deadline d = new Deadline(task, deadlineDate);

            try {
                storage.addData("data/duke.txt", d.toString() + System.lineSeparator());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            return r;


        } else if (input.toLowerCase().matches("\\bevent\\b.*")) {

            if (input.length() <= 6) {
                throw new DukeException("Empty Description");
            }
            String r = taskList.addTask(input);
            String[] data = input.split("/");
            String task = data[0].substring(6);

            Event e = new Event(task, data[1].substring(5), data[2].substring(3));

            try {
                storage.addData("data/duke.txt", e.toString() + System.lineSeparator());
            } catch (IOException err) {
                System.out.println(err.getMessage());
            }

            return r;


        } else if (input.toLowerCase().matches("\\btodo\\b.*")) {
            if (input.length() <= 5) {
                throw new DukeException("Empty Description");
            }
            String r = taskList.addTask(input);

            try {
                storage.addData("data/duke.txt", new Todo(input.substring(5)).toString() + System.lineSeparator());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            return r;


        } else if (input.toLowerCase().matches("\\bdelete\\b.*")) {

            String r = taskList.deleteTask(input);
            try {
                storage.write("data/duke.txt", taskList.getList());
            } catch (IOException err) {
                System.out.println(err.getMessage());
            }

            return r;
        } else if (input.toLowerCase().matches("\\bfind\\b.*")) {

            return taskList.findTask(input);
        } else if(input.toLowerCase().matches("\\bhelp\\b.*")) {

        return taskList.help();

        } else if (input.trim().isEmpty()) {

            return "";
        }
        return "Unable to process or understand command.";
    }

}

