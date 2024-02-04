package Duke.main;

import Duke.util.TaskList;
import Duke.util.UI;
import Duke.util.Storage;
import Duke.commands.*;
import Duke.exceptions.*;

public class Duke {
    private final Storage storage;
    private final UI ui;
    private final TaskList tasks;
    private static Commands parseCommand(String s) throws IllegalArgumentException {
        String[] words = s.split(" ", 2);
        Commands res = null;
        switch(words[0]) {
        case "bye":
            res = new ByeCommand();
            break;
        case "list":
            res = new ListCommand();
            break;
        case "mark":
            res = new MarkCommand(words);
            break;
        case "unmark":
            res = new UnMarkCommand(words);
            break;
        case "delete":
            res = new DeleteCommand(words);
            break;
        case "todo":
            res = new ToDoCommand(words);
            break;
        case "deadline":
            res = new DeadlineCommand(words);
            break;
        case "event":
            res = new EventCommand(words);
            break;
        case "date":
            res = new DateCommand(words);
            break;
        case "find":
            res = new FindCommand(words);
        }
        if (res == null) {
            throw new IllegalArgumentException();
        }
        return res;
    }
    public Duke (String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new UI();
        this.tasks = new TaskList(storage.readFile());
    }
    public void run() {
        ui.displayIntro();
        boolean isExit = false;
        while (!isExit) {
            try {
                String echoInput = this.ui.receiveNextLine();
                Commands token = parseCommand(echoInput.trim());
                isExit = token.execute(tasks, ui, storage);
            } catch (IllegalArgumentException e){
                this.ui.displayExceptionMsg(new UnknownInputException());
            } catch (DukeException e){
                this.ui.displayExceptionMsg(e);
            }
        }
    }
}


