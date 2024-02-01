import java.util.List;

public class ChatSession {
    public TaskList taskList;
    public List<NamedCommand> commandList;
    public boolean continueSession;

    ChatSession(TaskList taskList) {
        this.continueSession = true;
        this.initCommands();
        this.taskList = taskList;
        this.initChat();
    }

    public void initChat() {
        String message = "____________________________________________________________\n" +
        "Hello! I'm GoldBot!\n" +
        "What can I do for you?\n" +
        "____________________________________________________________\n";
        System.out.println(message);
    }

    public void initCommands() {
        this.commandList = List.of(
            new Bye(),
            new ListTasks(), 
            new Mark(), 
            new Unmark(), 
            new ToDoCommand(), 
            new EventCommand(), 
            new DeadlineCommand(),
            new Delete()
            );
    }

    public void printMessage(String message) {
        this.printHorizontalLine();
        System.out.println(message);
        this.printHorizontalLine();
    }

    public void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void terminateChat() {
        this.continueSession = false;
    }

    public void handleMessage(String message) {
        String[] result = message.split(" ", 2);
        String command = result[0];
        String commandArgs;

        if (result.length > 1) {
            commandArgs = result[1];
        } else {
            commandArgs = "";
        }

        for (NamedCommand cmd : commandList) {
            if (command.equals(cmd.getName())) {
                try {
                    cmd.execute(this, commandArgs);
                    return;
                } catch (InvalidParametersException e) {
                    this.printMessage(e.getMessage());
                    return;
                }
            }
        }

        this.unmatchedCommand(message);
    }

    public void unmatchedCommand(String message) {
        this.printMessage("unrecognized command");
    }

    public void echo(String message) {
        this.printMessage(message);
    }

    public void addTask(String s) {
        AddTask adder = new AddTask();
        adder.execute(this, s);
    }
}
