package Martin;

import java.io.IOException;
import java.time.LocalDate;

public class Command {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public Command(TaskList taskList, Storage storage, Ui ui, Parser parser) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
        this.parser = parser;
    }

    public void handleCommand(ChatbotKeyword command, String remainingWords) throws IOException {
        switch (command) {
            case LIST:
                taskList.printList();
                break;
            case MARK:
                int doneIndex = Integer.parseInt(remainingWords);
                taskList.markAsDone(doneIndex);
                storage.rewriteFile(taskList);
                break;
            case UNMARK:
                int undoneIndex = Integer.parseInt(remainingWords);
                taskList.unmarkAsDone(undoneIndex);
                storage.rewriteFile(taskList);
                break;
            case DELETE:
                int deleteIndex = Integer.parseInt(remainingWords);
                taskList.remove(deleteIndex);
                storage.rewriteFile(taskList);
                break;
            case TODO:
                Todo todo = new Todo(remainingWords);
                taskList.add(todo);
                storage.appendToFile(todo.toFileString());
                break;
            case DEADLINE:
                String[] deadlineArray = parser.parseDeadline(remainingWords);
                LocalDate deadlineTime = LocalDate.parse(deadlineArray[1]);
                Deadline deadline = new Deadline(deadlineArray[0], deadlineTime);
                taskList.add(deadline);
                storage.appendToFile(deadline.toFileString());
                break;
            case EVENT:
                String[] eventArray = parser.parseEvent(remainingWords);
                Event event = new Event(eventArray[0], eventArray[1], eventArray[2]);
                taskList.add(event);
                storage.appendToFile(event.toFileString());
                break;
            case BYE:
                ui.sayBye();
                break;
            default:
                throw new IllegalArgumentException("I'm sorry, but I don't know what that means :-(");
        }
    }
    
}
