import java.io.IOException;
public class Kervyn {

    private Storage storage;
    private Ui ui;
    public Kervyn(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

    }

    public void run() throws IOException {
        ui.startChatBot();
    }
    public static void main(String[] args) throws IOException {
        new Kervyn("data/tasks.txt").run();
    }
}
