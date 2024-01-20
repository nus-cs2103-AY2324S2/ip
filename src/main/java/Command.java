public abstract class Command {
    abstract void execute(TaskList list);

    public static class ByeCommand extends Command{
        @Override
        public void execute(TaskList list) {
            System.out.println("Bye! Have a good time!");
        }
    }

    public static class InvalidCommand extends Command {
        String message;
        public InvalidCommand(String message) {
            this.message = message;
        }
        @Override
        public void execute(TaskList list) {
            System.out.println(message);
        }
    }

    public static class ListCommand extends Command{
        @Override
        public void execute(TaskList list) {
            System.out.print(list);
        }
    }

    public static class MarkCommand extends Command{
        private int id;
        public MarkCommand(int id) {
            this.id = id;
        }
        @Override
        public void execute(TaskList list) {
            try {
                Task done = list.getTask(id);
                done.setDone();
            } catch (DukeException.IllegalParamException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static class UnmarkCommand extends Command {
        private int id;

        public UnmarkCommand(int id) {
            this.id = id;
        }

        public void execute(TaskList list) {
            try {
                Task notDone = list.getTask(this.id);
                notDone.setNotDone();
            } catch (DukeException.IllegalParamException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static class ToDoCommand extends Command{
        private String description;

        public ToDoCommand(String name) {
            this.description = name;
        }

        public void execute(TaskList list) {
            Task.ToDos newTask = new Task.ToDos(this.description);
            list.add(newTask);
        }
    }
    public static class DeadlineCommand extends Command {
        private String description;
        private String by;

        public DeadlineCommand(String description, String by) {
            this.description = description;
            this.by = by;
        }

        @Override
        public void execute(TaskList list) {
            Task.Deadlines newDeadline = new Task.Deadlines(description, by);
            list.add(newDeadline);
        }
    }

    public static class EventCommand extends Command {
        private String task;
        private String start;
        private String end;

        public EventCommand(String task, String start, String end) {
            this.task = task;
            this.start = start;
            this.end = end;
        }

        @Override
        public void execute(TaskList list) {
            Task newEvent = new Task.Events(task, start, end);
            list.add(newEvent);
        }
    }
}
