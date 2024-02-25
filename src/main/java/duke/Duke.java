
package duke;

import java.io.IOException;

import parser.Parser;
import processor.Factory;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;
/**
 * The Duke class is responsible for running the chatbot.
 */
public class Duke {
    private Ui chatbotUi;
    private TaskList taskList;
    private Factory factory;
    private Parser parser;
    private Storage storage;
    /**
     * Constructs a Duke object with the specified Ui, TaskList, Processor, and Parser.
     * @throws IOException if an I/O error occurs while reading the file
     */
    public Duke() {
        storage = new Storage("tasks.txt");
        chatbotUi = new Ui();
        taskList = new TaskList();
        try {
            taskList = storage.loadTaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        factory = new Factory(taskList, chatbotUi);
        parser = new Parser(factory);
    }
    /**
     * Executes the chatbot continuously.
     * @throws IOException if an I/O error occurs while running the chatbot
     */
    public void run() throws IOException {
        // Greet User
        System.out.print(chatbotUi.greetingBox());
        String userInput;
        do {
            userInput = chatbotUi.getCommand();
            if (userInput.equals("bye")) {
                break;
            } else {
                parser.parse(userInput);
            }
        } while (true);
        System.out.println(chatbotUi.dividerWrapper(Ui.bye()));
    }

    /**
     * Executes a chatbot command by returning the string object instead.
     * @param userInput the user input
     * @return the response
     * @throws IOException if an I/O error occurs while running the chatbot
     */
    public String run(String userInput) throws IOException {
        if (userInput.equals("bye")) {
            try {
                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        System.exit(0);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }).start();
                return chatbotUi.dividerWrapper(Ui.bye());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Assuming that the parse method updates the chatbotUi with the response
            return chatbotUi.getResponse(userInput, parser);
        }
        return null;
    }
    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();
        duke.run();
    }
}
