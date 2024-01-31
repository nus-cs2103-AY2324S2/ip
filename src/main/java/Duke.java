import java.io.IOException;

public class Duke {
    private final Ui chatbotUi;
    private final TaskList taskList;
    private final Processor processor;
    private final Parser parser;

    public Duke() {
        chatbotUi = new Ui();
        taskList = new TaskList();
        processor = new Processor(taskList, chatbotUi);
        parser = new Parser(processor);
    }

    public void run() throws IOException {
        // Greet User
        System.out.print(chatbotUi.greetingBox());

        String userInput;
        do {
            userInput = chatbotUi.getCommand();
            if (userInput.equals("bye")) {
                break;
            } else {
            parser.processCommand(userInput);

            }

        } while (true);

        System.out.println(chatbotUi.dividerWrapper(Ui.bye()));
    }

    public static void main(String[] args) throws IOException {
        Duke d = new Duke();
        d.run();
    }

}

