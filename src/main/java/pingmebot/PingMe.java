package pingmebot;

import pingmebot.task.ToDos;
import pingmebot.task.Deadline;
import pingmebot.task.Events;

public class PingMe {
    private Storage storage;
    private TaskList tasks;
    private UI ui;
    private Parser parser;

    public PingMe(String filePath) {
        this.ui = new UI();
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.bootingUp());
        } catch (PingMeException e) {
            this.tasks = new TaskList();
            ui.showError(e.getMessage());
        }
    }

    public void run() {
        ui.showWelcome();
        while (true) {
            String userInput = ui.readCommand();
            String[] words = userInput.split(" ");
            parser = new Parser(userInput);

            if (userInput.equals("bye")) {
                ui.sayGoodbye();
                break;
            } else if (userInput.equals("list")) {
                ui.listText();
                tasks.listTask();
            } else if (words[0].equals("mark")) {
                try {
                    int taskNumber = parser.parseMarkCommand(tasks.getTaskSize());
                    tasks.updateTaskToStorage(this.storage);
                    ui.markTaskText(taskNumber, this.tasks);
                } catch (PingMeException e) {
                    ui.showError(e.getMessage());
                }
            } else if (words[0].equals("unmark")) {
                try {
                    int taskNum = parser.parseUnmarkCommand(tasks.getTaskSize());
                    tasks.updateTaskToStorage(this.storage);
                    ui.unmarkTaskText(taskNum, this.tasks);
                } catch (PingMeException e) {
                    ui.showError(e.getMessage());
                }
            } else if (words[0].equals("todo")) {
                try {
                    ToDos todo = parser.parseToDoCommand();
                    tasks.addTask(todo);
                    tasks.updateTaskToStorage(this.storage);
                    ui.additionToTasksText(todo, this.tasks);
                } catch (PingMeException e) {
                    ui.showError(e.getMessage());
                }
            } else if (words[0].equals("deadline")) {
                try {
                    Deadline deadlineTask = parser.parseDeadlineCommand();
                    tasks.addTask(deadlineTask);
                    tasks.updateTaskToStorage(this.storage);
                    ui.additionToTasksText(deadlineTask, this.tasks);
                } catch (PingMeException e) {
                    ui.showError(e.getMessage());
                }
            } else if (words[0].equals("event")) {
                try {
                    Events events = parser.parseEventsCommand();
                    tasks.addTask(events);
                    tasks.updateTaskToStorage(this.storage);
                    ui.additionToTasksText(events, this.tasks);
                } catch (PingMeException e) {
                    ui.showError(e.getMessage());
                }
            } else if (words[0].equals("delete")) {
                try {
                    int taskNumber = parser.parseDeleteCommand(tasks.getTaskSize());
                    ui.deletionToTasksText(taskNumber, this.tasks);
                    tasks.updateTaskToStorage(this.storage);
                } catch (PingMeException e) {
                    ui.showError(e.getMessage());
                }
            } else {
                ui.showError("OOPS! I'm sorry, but I don't know what that means :'(");
            }
        }
    }

    public static void main(String[] args) {
        new PingMe("./data/dukeData.txt").run();
    }
}
