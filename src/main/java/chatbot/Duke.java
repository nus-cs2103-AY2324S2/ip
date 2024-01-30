package chatbot;

import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException {
        try {
            Plana p = new Plana();
            p.init();
        } catch (ClassNotFoundException e) {
            System.out.println("Invalid save file:\n" +e.getMessage());
        } catch (IOException e) {
            System.out.println("Storage failed with error:\n" + e.getMessage());
        }
    }
}
