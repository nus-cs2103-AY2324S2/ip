package chatbot;

import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException {
        try {
            Plana p = new Plana();
            p.greet();

            p.chat();

            p.bye();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
