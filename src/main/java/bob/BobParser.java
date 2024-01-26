package bob;

public class BobParser {

    public static final String TERMINATE_COMMAND = "bye";

    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";

    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String DELETE_COMMAND = "delete";

    private Bob self;
    private BobUI ui;
    private BobTaskList taskList;

    public BobParser setSelf(Bob self) {
        this.self = self;
        return this;
    }

    public BobParser setUi(BobUI ui) {
        this.ui = ui;
        return this;
    }

    public BobParser setTaskList(BobTaskList taskList) {
        this.taskList = taskList;
        return this;
    }

    public void processInput(String input) {

        final String command = input.split("\\s+")[0];

        try {
            switch (command) {
            case BobParser.TERMINATE_COMMAND:
                this.ui.terminate();
                System.exit(0);
                break;
            case BobParser.LIST_COMMAND:
                this.ui.printList(false, this.taskList.getList());
                break;
            case BobParser.MARK_COMMAND:
            case BobParser.UNMARK_COMMAND:
                this.taskList.handleTaskMarking(input);
                break;
            case BobParser.TODO_COMMAND:
            case BobParser.DEADLINE_COMMAND:
            case BobParser.EVENT_COMMAND:
                this.taskList.handleTaskCreation(input);
                break;
            case BobParser.DELETE_COMMAND:
                this.taskList.handleTaskDeletion(input);
                break;
            default:
                throw new BobException.InvalidCommand("Sorry, I'm not sure what command that is.");
            }
        } catch (BobException e) {
            System.out.println(e.getMessage());
        }
    }
}
