package duke.main;

import duke.util.TaskList;
import duke.util.UI;
import duke.util.Storage;
import duke.commands.*;
import duke.exceptions.*;

public class Duke {
    private final Storage storage;
    private final UI ui;
    private final TaskList tasks;
    private static Command parseCommand(String s) throws IllegalArgumentException {
        String[] words = s.split(" ", 2);
        Command result = null;
        switch(words[0]) {
        case "bye":
            result = new ByeCommand();
            break;
        case "list":
            result = new ListCommand();
            break;
        case "mark":
            result = new MarkCommand(words);
            break;
        case "unmark":
            result = new UnMarkCommand(words);
            break;
        case "delete":
            result = new DeleteCommand(words);
            break;
        case "todo":
            result = new ToDoCommand(words);
            break;
        case "deadline":
            result = new DeadlineCommand(words);
            break;
        case "event":
            result = new EventCommand(words);
            break;
        case "date":
            result = new DateCommand(words);
            break;
        case "find":
            result = new FindCommand(words);
        }
        if (result == null) {
            throw new IllegalArgumentException();
        }
        return result;
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
                Command token = parseCommand(echoInput.trim());
                isExit = token.execute(tasks, ui, storage);
            } catch (IllegalArgumentException e){
                this.ui.displayExceptionMsg(new UnknownInputException());
            } catch (DukeException e){
                this.ui.displayExceptionMsg(e);
            }
        }
    }
}


