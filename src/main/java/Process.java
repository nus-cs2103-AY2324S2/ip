import java.time.*;
import java.time.format.DateTimeFormatter;

class Process {
    private TaskList taskList;
    private Ui chatbotUi;

    public Process(TaskList taskList, Ui chatbotUi) {
        this.taskList = taskList;
        this.chatbotUi = chatbotUi;
    }

    public void userInputProcessMarkUnmark(String userInput){
        String[] array = userInput.split(" ");
        String command = array[0];

        try {
            int number = Integer.parseInt(array[1]);

            if (command.equals("mark")) {
                taskList.markTask(number - 1);
                System.out.println(chatbotUi.dividerWrapper(chatbotUi.mark() + "\n" + taskList.getTaskAtIndex(number - 1)));
            } else if (command.equals("unmark")) {
                taskList.unmarkTask(number - 1);
                System.out.println(chatbotUi.dividerWrapper(chatbotUi.unmark() + "\n" + taskList.getTaskAtIndex(number - 1)));
            }
        } catch (NumberFormatException e) {
            System.out.println(chatbotUi.dividerWrapper("You must use a number to mark."));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(chatbotUi.dividerWrapper("You must select a number below the size of the Task List."));
        }
    }

    public void userInputAddTask(String userInput) {
        String[] tokens = userInput.split(" ", 2);
        if (tokens.length <= 1) {
            System.out.println(chatbotUi.dividerWrapper("Description of todo can't be blank"));
            return;
        }
        String command = tokens[0];
        String description = tokens[1].trim();
        String times[] = userInput.split("/");

        String desc = times[0];
        int firstSpaceIndex = desc.indexOf(' ');
        String substringAfterSpace = desc.substring(firstSpaceIndex + 1);

        switch (command) {
            case "todo":
                taskList.addTask(new Task(description, false));
                System.out.println(chatbotUi.dividerWrapper("Got it. I've added this task:\n" + taskList.getTaskAtIndex(taskList.size() - 1)
                        + "\nNow you have " + taskList.size() + " tasks in the list."));
                break;
            case "deadline":
                String deadline = times[1].trim();
                taskList.addDeadlineTask(substringAfterSpace, deadline);
                System.out.println(chatbotUi.dividerWrapper("Got it. I've added this task:\n" + taskList.getTaskAtIndex(taskList.size() - 1)
                        + "\nNow you have " + taskList.size() + " tasks in the list."));
                break;
            case "event":
                String start = times[1].trim();
                String end = times[2].trim();
                taskList.addEventTask(substringAfterSpace, start, end);
                System.out.println(chatbotUi.dividerWrapper("Got it. I've added this task:\n" + taskList.getTaskAtIndex(taskList.size() - 1)
                        + "\nNow you have " + taskList.size() + " tasks in the list."));
                break;
            default:
                System.out.println(chatbotUi.dividerWrapper("Invalid command. Please enter a valid command."));
        }

    }


    public void userInputListTasks() {
        System.out.println(chatbotUi.dividerWrapper("Here are the tasks in your list: \n" + taskList.showList()));
    }

}