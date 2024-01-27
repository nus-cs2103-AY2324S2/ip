package duke;

public class Parser {
    public static boolean parse(String command, TaskList items, Ui ui) throws DukeException {
        if (command.equals("bye")) {
            ui.bye();
            return false;
        } else if (command.equals("list")) {
            ui.tasks(items);
        } else if (command.startsWith("mark")) {
            try {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                items.get(index).markDone();

                ui.markTask();
                ui.println(items.get(index).toString());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid task ID to mark");
            }

        } else if (command.startsWith("unmark")) {
            try {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                items.get(index).unmarkDone();

                ui.unmarkTask();
                ui.println(items.get(index).toString());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid task ID to unmark");
            }
        }

        else if (command.startsWith("todo")) {
            if (command.length() <= 5) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }

            ui.addTask();

            Todo todo = new Todo(command.substring(5));

            items.add(todo);

            ui.println(todo.toString());
            ui.countTasks(items.size());

        } else if (command.startsWith("deadline")) {
            if (command.length() <= 9) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }

            try{
                String[] splittedCommand = command.split(" /by ");
                String task = splittedCommand[0].substring(9);
                String by = splittedCommand[1];

                Deadline deadline = new Deadline(task, Utils.convertStringToDateTime(by));
                items.add(deadline);

                ui.addTask();
                ui.println(deadline.toString());
                ui.countTasks(items.size());;
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid deadline date. Use /by");
            }

        } else if (command.startsWith("event")) {
            if (command.length() <= 6) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }

            try {
                String[] splittedCommand = command.split(" /from ");
                String task = splittedCommand[0].substring(6);

                String[] splittedTime = splittedCommand[1].split(" /to ");
                String from = splittedTime[0];
                String to = splittedTime[1];

                Event event = new Event(task, Utils.convertStringToDateTime(from), Utils.convertStringToDateTime(to));
                items.add(event);

                ui.addTask();
                ui.println(event.toString());
                ui.countTasks(items.size());;
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid event date. Use /from and /to");
            }

        } else if (command.startsWith("delete")) {
            if (command.length() <= 7) {
                throw new DukeException("OOPS!!! duke.Task ID cannot be empty.");
            }

            try {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;

                Task task = items.remove(index);
                ui.deleteTask();
                ui.println(task.toString());
                ui.countTasks(items.size());;
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid task ID to delete");
            }
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }
}
