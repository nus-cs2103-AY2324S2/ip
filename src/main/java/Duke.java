import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import task.Command;
import task.DukeException;
import task.TaskList;

public class Duke {
    private static final String chatbotName = "Sylvia";

    public Duke() {
        this.list = new TaskList();
    }

    private TaskList list;

    private void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + chatbotName + "\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Duke chatbot = new Duke();
        chatbot.greet();
        String input = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean loopSignal = true;

        while (loopSignal) {
            try {
                input = reader.readLine();
                // get the first word of the input
                String[] words = input.split(" ", 2);
                Command command = Command.newCommand(words[0], words.length > 1 ? words[1] : "");
                System.out.println("____________________________________________________________");
                loopSignal = command.execute(chatbot.list);
                System.out.println("____________________________________________________________");

            } catch (IOException e) {
                System.out.println("____________________________________________________________");
                System.out.println("Sorry, something went wrong: " + e.getMessage());
                System.out.println("____________________________________________________________");
                break;
            } catch (DukeException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getBotMessage());
                System.out.println("____________________________________________________________");
            }

        }
    }
}
