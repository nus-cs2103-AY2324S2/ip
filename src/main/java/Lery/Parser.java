package Lery;

import Lery.task.*;

public class Parser {
    private Storage storage;

    public Parser(Storage s) {
        this.storage = s;

    }




    public String parseCommand(String command) throws DukeException {
        TaskList taskList = this.storage.getTaskList();
        if (command.equalsIgnoreCase("list")) {
            return taskList.printList();
        } else if (command.startsWith("mark")) {
            try {
                Integer id = Integer.parseInt(command.substring(5));
                return taskList.getTask(id - 1).markAsDone();
            } catch (NumberFormatException e) {
                throw new DukeException("Erm... Please enter a valid task number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Erm... Please enter a task number.");
            }

        } else if (command.startsWith("unmark")) {
            try {
                Integer id = Integer.parseInt(command.substring(7));
                return taskList.getTask(id - 1).markAsDone();
            } catch (NumberFormatException e) {
                throw new DukeException("Erm... Please enter a valid task number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Erm... Please enter a task number.");
            }
        } else if (command.startsWith("delete")) {
            try {
                Integer id = Integer.parseInt(command.substring(7));
                return this.parseDeleteTaskCommand(taskList.getTask(id - 1));
            } catch (NumberFormatException e) {
                throw new DukeException("Erm... Please enter a valid task number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Erm... Please enter a task number.");
            }
        } else {
            return this.parseAddTaskCommand(command);

        }
    }

    public String parseAddTaskCommand(String command) throws DukeException {
        String msg = "Got it. I've added this task:\n";
        Task newTask = new Task(command);
        if (command.startsWith("todo")) {
            try {
                newTask = new Todo(command.substring(5));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException("Erm... Please provide event name.");
            }
        } else if (command.startsWith("deadline")){
            try {
                String[] taskDesc = command.substring(9).split("/by ");
                this.storage.checkDateFormat(taskDesc[1]);
                newTask = new Deadline(taskDesc[0], taskDesc[1]);


            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException("Erm... Please provide event details." + e);
            }
        } else if (command.startsWith("event")) {
            try {
                newTask = new Event(command.substring(6));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException("Erm... Please provide event details.");
            }
        } else {
            throw new DukeException("Erm... Please provide a valid command.");
        }
        this.storage.saveTasks(newTask);

        msg = msg+"["+ newTask.getType()  +"]"+ "["+newTask.getStatusIcon() +"]" + " " +
                newTask.getDescription() + newTask.getExtraInfo() +"\nNow you have " +
                Integer.toString(this.storage.getSize()) +" tasks in the list.";
        return msg;


    }

    public String parseDeleteTaskCommand(Task task) {
        this.storage.delete(task);
        String msg = "Noted. I've removed this task:\n" + task.getType() + "[" + task.getStatusIcon() + "]" +
                " " + task.getDescription() + task.getExtraInfo() + "\nNow you have " +
                Integer.toString(this.storage.getSize()) + " tasks in the list.\n";
        return msg;
    }
}
