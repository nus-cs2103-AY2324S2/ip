package duke;

import java.io.IOException;

import parser.Parser;
import processor.Factory;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * IreneAI is a cleaver chatbot for managing your daily tasks.
 */
public class Duke {
    private Ui chatbotUi;
    private TaskList taskList;
    private Factory factory;
    private Parser parser;
    private Storage storage;

    /**
     * Constructs a Duke object with the specified Ui, TaskList, Processor, and Parser.
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
     * Executes the chatbot.
     * @throws IOException if an i/o error occurs while running IreneAI
     */
    public void run() throws IOException {
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

    public String run(String userInput) throws IOException {
        if (userInput.equals("bye")) {
            System.exit(1);
        } else {
            return chatbotUi.getResponse(userInput, parser);
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();
        duke.run();
    }
}
