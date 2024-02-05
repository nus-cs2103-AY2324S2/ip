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
                taskList.add(new Todo(remainingWords));
                storage.rewriteFile(taskList);
                break;
            case DEADLINE:
                String[] deadlineArray = parser.deadlineParse(remainingWords);
                LocalDate deadlineTime = LocalDate.parse(deadlineArray[1]);
                taskList.add(new Deadline(deadlineArray[0], deadlineTime));
                storage.rewriteFile(taskList);
                break;
            case EVENT:
                String[] eventArray = parser.eventParse(remainingWords);
                taskList.add(new Event(eventArray[0], eventArray[1], eventArray[2]));
                storage.rewriteFile(taskList);
                break;
            case BYE:
                ui.sayBye();
                break;
            default:
                throw new IllegalArgumentException("I'm sorry, but I don't know what that means :-(");
        }
    }
    
}
