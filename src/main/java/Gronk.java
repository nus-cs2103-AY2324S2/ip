import java.util.Scanner;

public class Gronk {
    private Storage storage;
    private UserInterface userInterface;
    private Parser parser;
    private Scanner messageReader;


    public Gronk(String filepath) {
        this.storage = new Storage(filepath);
        this.storage.loadFromFile();
        this.parser = new Parser(this.storage.returnTasks());
        this.userInterface = new UserInterface();
        this.messageReader = new Scanner(System.in);

        this.userInterface.printHello();
        String nextMessage = "";
        String replyMessage = "";

        while (true) {
            nextMessage = this.messageReader.nextLine();
            if (nextMessage.equals("bye")) {
                this.storage.saveToFile();
                break;
            } else {
                replyMessage = this.parser.parse(nextMessage);
                this.userInterface.printMessage(replyMessage);
            }
        }

        this.userInterface.printGoodbye();
    }

    public static void main(String[] args) {
        Gronk main = new Gronk("tasks.txt");
    }
}
