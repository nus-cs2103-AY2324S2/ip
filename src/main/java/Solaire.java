import java.util.Scanner;

public class Solaire {
    private TaskList taskList;
    private Ui ui;

    enum UserCommands {
        GREET, BYE, MARK, UNMARK, LIST, TODO, EVENT, DEADLINE, DELETE
    }

    public Solaire() {
        this.ui = new Ui(new Scanner(System.in));
        this.taskList = new TaskList(Storage.loadFromLocal());
    }

    public void startConversation() {
        ui.greet(); // UI

        while (true) {
            String input = ui.acceptInput();

            if (input.equals("bye")) {
                break;
            }
            processInput(input);
            Storage.write(this.taskList.getTaskList());
        }

        ui.waveBye(); // UI
    }

    /* This can stay in Solaire for now */
    private void processInput(String input) {
        String[] inputCommand = input.split(" ", 2);
        try {
            UserCommands command = UserCommands.valueOf(inputCommand[0].toUpperCase());

            switch (command) {
            case GREET:
            ui.greet();
                break;
            case BYE:
            ui.waveBye();
                break;
            case MARK:
            taskList.markDone(Integer.parseInt(inputCommand[1]));
                break;
            case UNMARK:
            taskList.unmarkDone(Integer.parseInt(inputCommand[1]));
                break;
            case LIST:
            taskList.showList();
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
            taskList.processTaskCommand(input);
                break;
            case DELETE:
            taskList.processRemoveFromList(input);
                break;
            default:
            System.out.print("Unsupported command pattern\n");
                break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("I am not yet familiar with these commands");
        }

    }
}
