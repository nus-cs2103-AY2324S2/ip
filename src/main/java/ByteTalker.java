import java.io.FileNotFoundException;
import java.io.IOException;

public class ByteTalker {
    private TaskList tasks;
    private Storage storage;
    //private TaskList tasks;
    private Ui ui;
    boolean isExit = false;

    public ByteTalker(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        storage.setupDirectoryAndFile();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundErrorMsg();
            isExit = true;
        }
    }

    public void markTask(String[] splitMessage) {
        int index = Integer.parseInt(splitMessage[1]) - 1;
        try {
            this.tasks.changeTaskStatus(index, true);
            this.storage.storeTasks(this.tasks.getTasks());
            ui.markTaskMsg(this.tasks.getTask(index));
        } catch (IOException e) {
            ui.showErrorMsgStoreTasks();
            this.tasks.changeTaskStatus(index, false);
        }
    }

    public void unmarkTask(String[] splitMessage) {
        int index = Integer.parseInt(splitMessage[1]) - 1;
        try {
            this.tasks.changeTaskStatus(index, false);
            this.storage.storeTasks(this.tasks.getTasks());
            ui.unmarkTaskMsg(this.tasks.getTask(index));
        } catch (IOException e) {
            ui.showErrorMsgStoreTasks();
            this.tasks.changeTaskStatus(index, true);
        }
    }

    public void addTask(String[] splitMessage) {
        try {
            Task task = null;
            boolean isTodo = splitMessage[0].equals("todo");
            boolean isDeadline = splitMessage[0].equals("deadline");
            boolean isEvent = splitMessage[0].equals("event");
            if (isTodo) {
                task = addTodo(splitMessage);
            } else if (isDeadline) {
                task = addDescription(splitMessage);
            } else if (isEvent) {
                task = addEvent(splitMessage);
            } else {
                throw new ByteTalkerException.UnsupportedTaskException("This is unsupported task");
            }
            try {
                if (task != null) {
                    this.tasks.addTask(task, this.tasks.getSize());
                }
                this.storage.storeTasks(this.tasks.getTasks());
                ui.addTaskMsg(task, this.tasks.getSize());
            } catch (IOException e) {
                ui.showErrorMsgStoreTasks();
                this.tasks.removeTask(this.tasks.getSize() - 1);
            }
        } catch (ByteTalkerException.UnsupportedTaskException ex) {
            System.out.println("    " + ex.getMessage() + ". Please only enter the supported types of task.");
        }
    }

    public Todo addTodo(String[] splitMessage) {
        try {
            if (splitMessage.length == 1) {
                throw new ByteTalkerException.TodoNoTaskException("No Task");
            }
            String content = " ";
            for (int i = 1; i < splitMessage.length; i++) {
                content = content + splitMessage[i] + " ";
            }
            content = content.strip();
            return new Todo(content);
        } catch (ByteTalkerException.TodoNoTaskException ex) {
            System.out.println("    " + ex.getMessage() + ". Please enter the task, too.");
            return null;
        }
    }

    public Deadline addDescription(String[] splitMessage) {
        String content = " ";
        String deadline = "";
        for (int i = 1; i < splitMessage.length; i++) {
            boolean isContentFilled = splitMessage[i].equals("/by");
            if (isContentFilled) {
                content = deadline.strip();
                deadline = "";
            } else {
                deadline = deadline + splitMessage[i] + " ";
            }
        }
        deadline = deadline.strip();
        return new Deadline(content, Parser.parseDateTime(deadline));
    }

    public Event addEvent(String[] splitMessage) {
        String content = " ";
        String from = "";
        String to = "";
        for (int i = 1; i < splitMessage.length; i++) {
            boolean isContentFilled = splitMessage[i].equals("/from");
            boolean isFromFilled = splitMessage[i].equals("/to");
            if (isContentFilled) {
                content = to.strip();
                to = "";
            } else if (isFromFilled) {
                from = to.strip();
                to = "";
            } else {
                to += splitMessage[i] + " ";
            }
        }
        to = to.strip();
        return new Event(content, Parser.parseDateTime(from), Parser.parseDateTime(to));
    }

    public void deleteTask(int position) {
        Task task = this.tasks.getTask(position - 1);
        try {
            this.tasks.removeTask(position - 1);
            this.storage.storeTasks(this.tasks.getTasks());
            ui.deleteTaskMsg(task, this.tasks.getSize());
        } catch (IOException e) {
            ui.showErrorMsgStoreTasks();
            this.tasks.addTask(task, position - 1);
        }
    }

    public void run() {
        ui.showWelcome();
        while (!isExit) {
            String userInputString = ui.storeUserInput();
            String[] splitMessage = Parser.parse(userInputString);
            if (userInputString.equals("bye")) {
                break;
            } else if (userInputString.equals("list")) {
                this.ui.returnList(this.tasks.getTasks());
            } else if (splitMessage[0].equals("mark")) {
                markTask(splitMessage);
            } else if (splitMessage[0].equals("unmark")) {
                unmarkTask(splitMessage);
            } else if (splitMessage[0].equals("delete")) {
                deleteTask(Integer.parseInt(splitMessage[1]));
            } else {
                addTask(splitMessage);
            }
        }
        ui.showBye();
    }

    public static void main(String[] args) {
        new ByteTalker("./data/ByteTalker.txt").run();
    }
}
