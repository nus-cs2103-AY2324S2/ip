import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Sam {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;


    private void processCommand(String userInput) {
        if (userInput.trim().isEmpty()) {
            throw new IllegalArgumentException("Command cannot be empty.");
        }
        String[] parts = userInput.split(" ");
        String commandStr = parts[0];

        CommandType command;
        try {
            command = CommandType.valueOf(commandStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("I'm sorry, but I don't understand what that means.");
        }

        switch (command) {
            case LIST:
                tasks.displayList();
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                tasks.addTask(userInput);
                break;
            case MARK:
                tasks.markTask(userInput);
                break;
            case UNMARK:
                tasks.unmarkTask(userInput);
                break;
            case DELETE:
                tasks.deleteTask(userInput);
                break;
            default:
                throw new IllegalArgumentException("Hey, please choose from the following commands\n" +
                        "if you want to add task, please use todo, deadline or event\n" +
                        "if you want to mark or unmark task, please use mark or unmark\n" +
                        "if you want delete a task, please use delete\n" +
                        "if you want to view the existing task list, please enter list.");
        }
    }

    public Sam(String FILE_PATH) {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.load());
    }
    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                ui.bye();
                break;
            }
            try{
                processCommand(userInput);
                storage.save(tasks);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
    public static void main(String[] args) {
        new Sam("./data/Sam.txt").run();
    }
}
