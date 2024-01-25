import java.util.Scanner;

public class Duke {
    private Ui chatbotUi;
    private TaskList taskList;
    private Process processor;

    public Duke() {
        chatbotUi = new Ui();
        taskList = new TaskList();
        processor = new Process(taskList, chatbotUi);
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

        System.out.println(chatbotUi.dividerWrapper(chatbotUi.bye()));
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();
    }

}

