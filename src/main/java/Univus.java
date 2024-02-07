import java.util.Scanner;



public class Univus {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Scanner scanner;

    public Univus(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = storage.loadFromFile();
        this.scanner = new Scanner(System.in);
    }

    public void run() throws UnivusException {
        ui.greet();
        while (true) {
            String message = scanner.nextLine();
            if (message.equals("bye")) {
                ui.bye();
                scanner.close();
                break;
            } else {
                Parser.parse(taskList, message);
            }
        }
        storage.saveToFile(taskList);
    }




    public static void main(String[] args) throws UnivusException {
        new Univus("./data/Univus.txt").run();
    }
}
