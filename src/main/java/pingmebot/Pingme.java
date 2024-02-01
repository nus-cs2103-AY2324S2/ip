package pingmebot;

import pingmebot.task.Deadline;
import pingmebot.task.Events;
import pingmebot.task.ToDos;

public class Pingme {
    private fileStorage storage;
    private TaskList tasks;
    private UI ui;
    private Parser parser;

    public Pingme(String filePath) {
        this.ui = new UI();
        try {
            this.storage = new fileStorage(filePath);
            this.tasks = new TaskList(storage.bootingUp());
        } catch (myBotException e) {
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
                    int taskNumber = parser.markParser(tasks.getTaskSize());
                    tasks.updateTaskToStorage(this.storage);
                    ui.markTaskText(taskNumber, this.tasks);
                } catch (myBotException e) {
                    ui.showError(e.getMessage());
                }
            } else if (words[0].equals("unmark")) {
                try {
                    int taskNum = parser.unmarkParser(tasks.getTaskSize());
                    tasks.updateTaskToStorage(this.storage);
                    ui.unmarkTaskText(taskNum, this.tasks);
                } catch (myBotException e) {
                    ui.showError(e.getMessage());
                }
            } else if (words[0].equals("todo")) {
                try {
                    ToDos todo = parser.todoParser();
                    tasks.addTask(todo);
                    tasks.updateTaskToStorage(this.storage);
                    ui.additionToTasksText(todo, this.tasks);
                } catch (myBotException e) {
                    ui.showError(e.getMessage());
                }
            } else if (words[0].equals("deadline")) {
                try {
                    Deadline deadlineTask = parser.deadlineParser();
                    tasks.addTask(deadlineTask);
                    tasks.updateTaskToStorage(this.storage);
                    ui.additionToTasksText(deadlineTask, this.tasks);
                } catch (myBotException e) {
                    ui.showError(e.getMessage());
                }
            } else if (words[0].equals("event")) {
                try {
                    Events events = parser.eventsParser();
                    tasks.addTask(events);
                    tasks.updateTaskToStorage(this.storage);
                    ui.additionToTasksText(events, this.tasks);
                } catch (myBotException e) {
                    ui.showError(e.getMessage());
                }
            } else if (words[0].equals("delete")) {
                try {
                    int taskNumber = parser.deleteParser(tasks.getTaskSize());
                    ui.deletionToTasksText(taskNumber, this.tasks);
                    tasks.updateTaskToStorage(this.storage);
                } catch (myBotException e) {
                    ui.showError(e.getMessage());
                }
            } else {
                ui.showError("OOPS! I'm sorry, but I don't know what that means :'(");
            }
        }
    }

    public static void main(String[] args) {
        new Pingme("./data/dukeData.txt").run();
    }
}
