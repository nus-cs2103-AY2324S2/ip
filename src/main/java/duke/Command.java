package duke;

/**
 * Represents commands. Eg. Command.DELETE refers to the DELETE command.
 */
public enum Command {
    /**
     * Hello command. Displays welcome message.
     */
    HELLO("hello") {

        @Override
        public void execute(TaskList tasks, String description) throws DukeException {
            System.out.println("Hello!");
            System.out.println("What can I do for you?");
        }
    },
    /**
     * Bye command. Displays Bye message.
     */
    BYE("bye") {

        @Override
        public void execute(TaskList tasks, String description) throws DukeException {
            System.out.println("See you soon!");
        }
    },
    /**
     * Mark command. Marks a task as done.
     */
    MARK("mark") {
        @Override
        public void execute(TaskList tasks, String description) throws DukeException {
            String[] arr = description.split(" ", 2);
            if (arr.length <= 1) {
                throw new DukeException("Please use the format: mark <index>");
            }
            try {
                int index = Integer.valueOf(arr[1]);
                Task target = tasks.get(index - 1);
                target.mark();
                System.out.println("Task have been marked as done.");
                System.out.println(target);
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
        public void execute(TaskList tasks, String description) throws DukeException {
            String[] arr = description.split(" ", 2);
            if (arr.length <= 1) {
                throw new DukeException("Please use the format: unmark <index>");
            }
            try {
                int index = Integer.valueOf(arr[1]);
                Task target = tasks.get(index - 1);
                target.unmark();
                System.out.println("Task have been unmarked.");
                System.out.println(target);
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
        public void execute(TaskList tasks, String description) {

            System.out.println(tasks.toString());

        }

    },
    /**
     * Delete command. Delete task from list.
     */
    DELETE("delete") {
        @Override
        public void execute(TaskList tasks, String description) throws DukeException {
            String[] arr = description.split(" ", 2);
            if (arr.length <= 1) {
                throw new DukeException("Please use the format: delete <index>");
            }
            try {
                int index = Integer.valueOf(arr[1]);
                Task target = tasks.get(index - 1);
                tasks.remove(index - 1);
                System.out.println("Task have been removed.");
                System.out.println(target);
                int n = tasks.size();
                System.out.println("You now have " + n + " tasks.");
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
        public void execute(TaskList tasks, String description) throws DukeException {

            Task task = Parser.parseFromInput(description);
            tasks.add(task);
            int n = tasks.size();
            System.out.println("added: " + task);
            System.out.println("You now have " + n + " tasks.");
        }

    },
    /**
     * Deadline command. Adds a Deadline Task to the list.
     */
    DEADLINE("deadline") {
        @Override
        public void execute(TaskList tasks, String description) throws DukeException {

            Task task = Parser.parseFromInput(description);
            tasks.add(task);
            int n = tasks.size();
            System.out.println("added: " + task);
            System.out.println("You now have " + n + " tasks.");
        }

    },
    /**
     * Todo command. Adds a Todo task to the list.
     */
    TODO("todo") {

        @Override
        public void execute(TaskList tasks, String description) throws DukeException {
            Task task = Parser.parseFromInput(description);
            tasks.add(task);
            int n = tasks.size();
            System.out.println("added: " + task);
            System.out.println("You now have " + n + " tasks.");

        }

    },
    /**
     * Unknown command. Throws an exception if command supplied is not recognized.
     */
    UNKNOWN("unknown") {

        @Override
        public void execute(TaskList tasks, String description) throws DukeException {
            throw new DukeException("I don't know what that means");
        }

    },
    /**
     * Clear command. Clears all tasks in list.
     */
    CLEAR("clear") {
        @Override
        public void execute(TaskList tasks, String description) throws DukeException {
            tasks.clear();
            Storage.clear();
            System.out.println("List cleared.");
        }
    },
    /**
     * Find command. Finds all tasks that match keyword.
     */
    FIND("find") {
        @Override
        public void execute(TaskList tasks, String description) throws DukeException {
            TaskList found = new TaskList();
            String search = description.split(" ", 2)[1];
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getName().contains(search)) {
                    found.add(tasks.get(i));
                } else {
                    continue;
                }
            }
            System.out.println(found.toString());
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
    public abstract void execute(TaskList tasks, String description) throws DukeException;



}

