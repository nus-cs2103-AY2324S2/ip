package duke;

public enum Command {
    HELLO("hello") {

        @Override
        public void execute(TaskList tasks, String description) throws DukeException {
            System.out.println("Hello!");
            System.out.println("What can I do for you?");
        }
    },
    BYE("bye") {

        @Override
        public void execute(TaskList tasks, String description) throws DukeException {
            System.out.println("See you soon!");
        }
    },
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
    LIST("list") {
        @Override
        public void execute(TaskList tasks, String description) {

            System.out.println(tasks.toString());

        }

    },
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
    UNKNOWN("unknown") {

        @Override
        public void execute(TaskList tasks, String description) throws DukeException {
            throw new DukeException("I don't know what that means");
        }

    },
    CLEAR("clear") {
        @Override
        public void execute(TaskList tasks, String description) throws DukeException {
            tasks.clear();
            Storage.clear();
            System.out.println("List cleared.");
        }
    };

    private final String command;
    Command(String command) {
        this.command = command;
    }
    public String getCommand() {
        return this.command;
    }
    public abstract void execute(TaskList tasks, String description) throws DukeException;



}

