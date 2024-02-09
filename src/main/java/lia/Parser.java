package lia;

public class Parser {
    private Ui ui;
    private TaskList tasks;

    public Parser(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    public void parseCommand(String input) {
        try {
            if (input.equals("exit")) {
                ui.showGoodbye();
            } else if (input.equals("list")) {
                ui.showTasks(tasks.getTasks());
            } else if (input.startsWith("mark")) {
                markTaskAsDone(input);
            } else if (input.startsWith("unmark")) {
                markTaskAsNotDone(input);
            } else if (input.startsWith("todo")) {
                addTodoTask(input);
            } else if (input.startsWith("deadline")) {
                addDeadlineTask(input);
            } else if (input.startsWith("event")) {
                addEventTask(input);
            } else if (input.startsWith("delete")) {
                deleteTask(input);
            } else if (input.equals("help")) {
                ui.showHelp();
            } else {
                ui.showInvalidCommand();
            }
        } catch (LiaException e) {
            System.out.println(e.getMessage());
        }
    }

    void markTaskAsDone(String input) throws LiaException {
        String[] tokens = input.split(" ");
        int pos = Integer.parseInt(tokens[1]);

        tasks.validateTaskPosition(pos);
        tasks.markTaskAsDone(pos);
        ui.showMarkedAsDone(tasks.getTask(pos - 1));
    }

    void markTaskAsNotDone(String input) throws LiaException {
        String[] tokens = input.split(" ");
        int pos = Integer.parseInt(tokens[1]);

        tasks.validateTaskPosition(pos);
        tasks.markTaskAsNotDone(pos);
        ui.showMarkedAsNotDone(tasks.getTask(pos - 1));
    }

    void addTodoTask(String input) throws LiaException {
        String todo = input.replaceFirst("todo", "").trim();

        if (todo.isEmpty()) {
            throw new LiaException("lia.Task description cannot be empty.");
        }

        tasks.addTodoTask(todo);
        ui.showAddedTask(tasks.getLastTask(), tasks);
    }

    void addDeadlineTask(String input) throws LiaException {
        String deadline = input.replaceFirst("deadline", "").split("/by")[0].trim();

        if (deadline.isEmpty()) {
            throw new LiaException("lia.Task description cannot be empty.");
        }

        String date = input.split("/by")[1].trim();

        tasks.addDeadlineTask(deadline, date);
        ui.showAddedTask(tasks.getLastTask(), tasks);
    }

    void addEventTask(String input) throws LiaException {
        String event = input.replaceFirst("event", "").split("/from")[0].trim();

        if (event.isEmpty()) {
            throw new LiaException("lia.Event description cannot be empty.");
        }

        String range = input.split("/from")[1].trim();
        String start = range.split("/to")[0].trim();
        String end = range.split("/to")[1].trim();

        tasks.addEventTask(event, start, end);
        ui.showAddedTask(tasks.getLastTask(), tasks);
    }

    void deleteTask(String input) throws LiaException {
        String[] tokens = input.split(" ");
        int pos = Integer.parseInt(tokens[1]);

        tasks.validateTaskPosition(pos);
        Task removedTask = tasks.getTask(pos - 1);
        tasks.deleteTask(pos);
        ui.showRemovedTask(removedTask, tasks);
    }
}