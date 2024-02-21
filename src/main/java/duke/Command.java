package duke;

import javafx.application.Platform;

/**
 * Represents commands. Eg. Command.DELETE refers to the DELETE command.
 */
public enum Command {
    /**
     * Hello command. Displays welcome message.
     */
    HELLO("hello") {

        @Override
        public String execute(TaskList tasks, String description) throws DukeException {
            Storage.createFile();
            String str = "Hello!\nWhat can I do for you?";
            return str;
        }
    },
    /**
     * Bye command. Displays Bye message.
     */
    BYE("bye") {

        @Override
        public String execute(TaskList tasks, String description) throws DukeException {
            String str = "See you soon!";
            Storage.write(tasks);
            Platform.exit();
            return str;
        }
    },
    /**
     * Mark command. Marks a task as done.
     */
    MARK("mark") {
        @Override
        public String execute(TaskList tasks, String description) throws DukeException {
            String[] arr = description.split(" ", 2);
            if (arr.length <= 1) {
                throw new DukeException("Please use the format: mark <index>");
            }
            try {
                int index = Integer.valueOf(arr[1]);
                Task target = tasks.get(index - 1);
                target.mark();
                Storage.write(tasks);
                String str = String.format("Task have been marked as done.\n%s", target);
                return str;
            } catch (NumberFormatException e) {
                throw new DukeException("Please provide an integer for the index.");
            }


        }
    },
    /**
     * Unmark command. Marks a task as undone.
     */
    UNMARK("unmark") {
        @Override
        public String execute(TaskList tasks, String description) throws DukeException {
            String[] arr = description.split(" ", 2);
            if (arr.length <= 1) {
                throw new DukeException("Please use the format: unmark <index>");
            }
            try {
                int index = Integer.valueOf(arr[1]);
                Task target = tasks.get(index - 1);
                target.unmark();
                Storage.write(tasks);
                String str = String.format("Task have been unmarked.\n%s", target);
                return str;
            } catch (NumberFormatException e) {
                throw new DukeException("Please provide an integer for the index.");
            }
        }

    },
    /**
     * List command. Prints all tasks currently in list.
     */
    LIST("list") {
        @Override
        public String execute(TaskList tasks, String description) {

            return tasks.toString();

        }

    },
    /**
     * Delete command. Delete task from list.
     */
    DELETE("delete") {
        @Override
        public String execute(TaskList tasks, String description) throws DukeException {
            String[] arr = description.split(" ", 2);
            if (arr.length <= 1) {
                throw new DukeException("Please use the format: delete <index>");
            }
            try {
                int index = Integer.valueOf(arr[1]);
                Task target = tasks.get(index - 1);
                tasks.remove(index - 1);
                System.out.println(target);
                int n = tasks.size();
                Storage.write(tasks);
                String str = String.format("Task have been removed.\nYou now have %d tasks.", n);
                return str;
            } catch (NumberFormatException e) {
                throw new DukeException("Please provide an integer for the index.");
            }
        }

    },
    /**
     * Event command. Adds a Event Task to the list.
     */
    EVENT("event") {
        @Override
        public String execute(TaskList tasks, String description) throws DukeException {

            Task task = Parser.parseFromInput(description);
            tasks.add(task);
            int n = tasks.size();
            Storage.clear();
            Storage.write(tasks);
            String str = String.format("added: %s\nYou now have %d tasks.", task, n);
            return str;
        }

    },
    /**
     * Deadline command. Adds a Deadline Task to the list.
     */
    DEADLINE("deadline") {
        @Override
        public String execute(TaskList tasks, String description) throws DukeException {

            Task task = Parser.parseFromInput(description);
            tasks.add(task);
            int n = tasks.size();
            Storage.clear();
            Storage.write(tasks);
            String str = String.format("added: %s\nYou now have %d tasks.", task, n);
            return str;
        }

    },
    /**
     * Todo command. Adds a Todo task to the list.
     */
    TODO("todo") {

        @Override
        public String execute(TaskList tasks, String description) throws DukeException {
            Task task = Parser.parseFromInput(description);
            tasks.add(task);
            int n = tasks.size();
            Storage.clear();
            Storage.write(tasks);

            String str = String.format("added: %s\nYou now have %d tasks.", task, n);
            return str;
        }

    },
    /**
     * Unknown command. Throws an exception if command supplied is not recognized.
     */
    UNKNOWN("unknown") {

        @Override
        public String execute(TaskList tasks, String description) throws DukeException {
            throw new DukeException("I don't know what that means");
        }

    },
    /**
     * Clear command. Clears all tasks in list.
     */
    CLEAR("clear") {
        @Override
        public String execute(TaskList tasks, String description) throws DukeException {
            tasks.clear();
            Storage.clear();
            String str = "List cleared.";
            return str;
        }
    },
    /**
     * Find command. Finds all tasks that match keyword.
     */
    FIND("find") {
        @Override
        public String execute(TaskList tasks, String description) throws DukeException {
            TaskList found = new TaskList();
            String search = description.split(" ", 2)[1];
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getName().contains(search)) {
                    found.add(tasks.get(i));
                } else {
                    continue;
                }
            }

            return found.toString();
        }
    };

    private final String command;
    Command(String command) {
        this.command = command;
    }
    public String getCommand() {
        return this.command;
    }

    /**
     *  Executes actions according to specific commands.
     * @param tasks Current list of tasks
     * @param description Input given by user
     * @throws DukeException If input is invalid
     */
    public abstract String execute(TaskList tasks, String description) throws DukeException;



}

