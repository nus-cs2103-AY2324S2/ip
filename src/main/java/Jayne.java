import java.util.Scanner;

public class Jayne {

    private Ui ui;
    private TaskList taskList;
    private  Storage storage;
    public Jayne(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.taskList = new TaskList(storage);
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showWelcome();
        Parser parser = new Parser(taskList, ui);
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new JayneException("Input cannot be empty.");
                }
                isExit = parser.parse(input);
            } catch (JayneException e) {
                System.out.println("\nHuh?!?!? " + e.getMessage() + "\n");
            }
        }
    }
    public static void main(String[] args) {
        new Jayne("./data/jayne.txt").run();
    }
}
