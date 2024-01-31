public class Duke {
    private final Ui chatbotUi;
    private final TaskList taskList;
    private final Parser processor;

    public Duke() {
        chatbotUi = new Ui();
        taskList = new TaskList();
        processor = new Parser(taskList, chatbotUi);
    }

    public void run() {
        // Greet User
        System.out.print(chatbotUi.greetingBox());

        String userInput;
        do {
            userInput = chatbotUi.getCommand();
            if (userInput.equals("bye")) {
                break;
            }
            if (userInput.startsWith("delete")) {
                processor.userInputDeleteTask(userInput);
                continue;
            }

            if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
                processor.userInputProcessMarkUnmark(userInput);
                continue;
            }
            if (userInput.equals("list")) {
                processor.userInputListTasks();
                continue;
            }
            processor.userInputAddTask(userInput);
        } while (true);

        System.out.println(chatbotUi.dividerWrapper(Ui.bye()));
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();
    }

}

