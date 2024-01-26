package bob;

import java.util.Scanner;

public class Bob {

    private BobUI ui;
    private BobStorage storage;
    private BobParser parser;
    private BobTaskList taskList;

    public Bob(BobUI ui, BobStorage storage, BobParser parser, BobTaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.parser = parser;
        this.taskList = taskList;
    }

    /**
     * Main logic of the chatbot.
     */
    public void start() {

        try {
            this.taskList.setList(this.storage.loadSavedTasks());
        } catch (BobException.FileAccessError e) {
            System.out.println(e.getMessage());
        } catch (BobException.CorruptedSaveData e) {
            System.out.println(e.getMessage());
        }

        this.ui.greet();

        while (this.ui.acceptingInput()) {

            String input = this.ui.getUserInput().trim();
            this.parser.processInput(input);

        }
    }

    public static void main(String[] args) {

        BobUI ui = new BobUI(new Scanner(System.in));
        BobStorage storage = new BobStorage();
        BobParser parser = new BobParser();
        BobTaskList taskList = new BobTaskList(storage, ui);

        Bob bob = new Bob(ui, storage, parser, taskList);
        parser.setSelf(bob).setUi(ui).setTaskList(taskList);

        bob.start();
    }
}
