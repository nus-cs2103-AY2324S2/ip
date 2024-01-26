import java.util.Scanner;
public class Tes {
    private Ui ui;

    public Tes(){
        this.ui = new Ui();
    }

    public void run() {
        this.ui.greet();

        while (true) {
            String command = this.ui.nextCommand();

            if (command.equals("bye")) {
                this.ui.close();
                break;
            }

            this.ui.respond(command);
        }
    }
    public static void main(String[] args) {
        Tes chatbot = new Tes();
        chatbot.run();
    }
}
