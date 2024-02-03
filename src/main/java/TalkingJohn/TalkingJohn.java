package TalkingJohn;

import java.util.ArrayList;
import java.util.List;


public class TalkingJohn {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    public TalkingJohn(String filePath) {
        this.ui = new Ui();
        List<Task> taskArr = new ArrayList<>();
        this.storage = new Storage(filePath, taskArr);
        this.taskList = new TaskList(storage.loadTasksFromFile(), ui);
    }

    public void run() {
        Parser parser = new Parser(ui, storage, taskList);
        parser.handleInput();
    }

    public static void main(String[] args) {
        TalkingJohn chatBot = new TalkingJohn("./data/TalkingJohn.txt");
        chatBot.run();
    }
}
