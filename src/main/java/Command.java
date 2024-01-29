public enum Command {
    BYE {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            storage.saveTasks(tasks);
        }
    },
    YAP {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            tasks.yapTasks();
        }
    },
    MARK {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            // Extract task index from argument and mark the task as done
            String[] inputs = message.split(" ");
            int index = Integer.parseInt(inputs[1]);
            tasks.markTaskAsDone(index);
            storage.saveTasks(tasks);
        }
    },
    UNMARK {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            // Extract task index from argument and mark the task as not done
            String[] inputs = message.split(" ");
            int index = Integer.parseInt(inputs[1]);
            tasks.unmarkTaskAsDone(index);
            storage.saveTasks(tasks);
        }
    },
    ADD_TODO {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            // Create a new ToDo task and add it to the task list
            Task task = initTask(message, "todo");
            tasks.addTasktoTaskList(task);
            ui.triggerAddMessage(task);
            storage.saveTasks(tasks);
        }
    },
    ADD_DEADLINE {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            // Create a new Deadline task and add it to the task list
            Task task = initTask(message, "deadline");
            tasks.addTasktoTaskList(task);
            ui.triggerAddMessage(task);
            storage.saveTasks(tasks);
        }
    },
    ADD_EVENT {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            // Create a new Event task and add it to the task list
            Task task = initTask(message, "event");
            tasks.addTasktoTaskList(task);
            ui.triggerAddMessage(task);
            storage.saveTasks(tasks);
        }
    },
    DELETE {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            String[] inputs = message.split(" ");
            int index = Integer.parseInt(inputs[1]);
            Task task = tasks.removeTaskfromTaskList(index);
            ui.triggerDeleteMessage(task);
            storage.saveTasks(tasks);
        }
    };

    public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
        throw new UnsupportedOperationException("This command does not take any arguments.");
    }

    public Task initTask(String message, String taskType) {
        Task task;
        try {
            if (taskType.equals("todo")) {
                try {
                    String[] inputs = message.split("todo ");
                    task = new ToDo(inputs[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Whats the task, yapper???");
                }
            } else if (taskType.equals("deadline")) {
                try {
                    message = message.substring("deadline ".length());
                    String[] inputs = message.split("/by");
                    task = new Deadline(inputs[0].trim(), inputs[1].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Deadlines need a deadline, yapper!");
                } catch (StringIndexOutOfBoundsException e ) {
                    throw new DukeException("Whats the task, yapper???");
                }
            } else if (taskType.equals("event")) {
                try {
                    message = message.substring("event ".length());
                    String[] inputs = message.split("/from");
                    String[] innerInputs = inputs[1].split("/to");
                    task = new Event(inputs[0].trim(), innerInputs[0].trim(), innerInputs[1].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("YAPYAP, What time is your from and to?");
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Whats the task, yapper???");
                }
            } else { //should not reach here because of filter in main logic
                task = new Task(message);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return task;
    }
}
