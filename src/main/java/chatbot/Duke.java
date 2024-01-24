package chatbot;

import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException {
        Plana p = new Plana();
        p.greet();

        p.chat();

        p.bye();
    }
}
