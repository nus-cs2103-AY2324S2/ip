
package duke;

import java.io.IOException;



import parser.Parser;
import processor.Factory;
import tasks.TaskList;
import ui.Ui;
    /**
     * The Duke class is responsible for running the chatbot.
     */
    public class Duke {
        private final Ui chatbotUi;
        private final TaskList taskList;
        private final Factory factory;
        private final Parser parser;
        /**
         * Constructs a Duke object with the specified Ui, TaskList, Processor, and Parser.
         */
        public Duke() {
            chatbotUi = new Ui();
            taskList = new TaskList();
            factory = new Factory(taskList, chatbotUi);
            parser = new Parser(factory);
        }
        /**
         * Executes the chatbot.
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
        //    /**
        //     * Starts the chatbot.
        //     * @param stage the stage to be used for the chatbot
        //     * @throws IOException if an I/O error occurs while starting the chatbot
        //     * @see Application#start(Stage)
        //     */
        //    @Override
        //    public void start(Stage stage) {
        //        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        //        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
        //
        //        stage.setScene(scene); // Setting the stage to show our screen
        //        stage.show(); // Render the stage.
        //    }
        // ...

            public static void main(String[] args) throws IOException {
                Duke d = new Duke();
                d.run();
//                stage.setScene(scene); // Setting the stage to show our screen
//                stage.show(); // Render the stage.
            }


    }