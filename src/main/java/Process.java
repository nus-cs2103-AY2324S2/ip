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
            System.out.println(chatbotUi.dividerWrapper("You must select a number within the size of the Task List."));
        }
    }

    public void userInputAddTask(String userInput) {
        String[] tokens = userInput.split(" ", 2);

        if (tokens.length == 0) {
            System.out.println(chatbotUi.dividerWrapper("Can not type a blank input!"));
            return;
        }

        String command = tokens[0];

        if (!(command.equals("todo") || command.equals("deadline") || command.equals("event"))) {
            System.out.println(chatbotUi.dividerWrapper("I do not know what type of task that is!"));
            return;
        }

       if (tokens.length == 1) {
           System.out.println(chatbotUi.dividerWrapper("Description of todo can't be blank!"));
           return;
       }

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

                if (times.length != 2) {
                    System.out.println(chatbotUi.dividerWrapper("Wrong syntax! Must be `deadline <task> /by <deadline>`"));
                    return;
                }
                String deadline = times[1].trim();

                if (!deadline.startsWith("by")) {
                    System.out.println(chatbotUi.dividerWrapper("You must start the statement with the word `/by`."));
                    return;
                }
                taskList.addDeadlineTask(substringAfterSpace, deadline);
                System.out.println(chatbotUi.dividerWrapper("Got it. I've added this task:\n" + taskList.getTaskAtIndex(taskList.size() - 1)
                        + "\nNow you have " + taskList.size() + " tasks in the list."));
                break;
            case "event":

                if (times.length != 3) {
                    System.out.println(chatbotUi.dividerWrapper("Wrong syntax! Must be `event <task> /from <start date> /to <end date>`"));
                    return;
                }
                String start = times[1].trim();
                String end = times[2].trim();


                if (!start.startsWith("from")) {
                    System.out.println(chatbotUi.dividerWrapper("You must start the statement with the word `/from`."));
                    return;
                }

                if (!end.startsWith("to")) {
                    System.out.println(chatbotUi.dividerWrapper("You must end the statement with the word `/to`."));
                    return;
                }

                taskList.addEventTask(substringAfterSpace, start, end);
                System.out.println(chatbotUi.dividerWrapper("Got it. I've added this task:\n" + taskList.getTaskAtIndex(taskList.size() - 1)
                        + "\nNow you have " + taskList.size() + " tasks in the list."));
                break;
            default:
                System.out.println(chatbotUi.dividerWrapper("Invalid command. Please enter a valid command."));
        }

    }


    public void userInputListTasks() {
        // if array empty
        if (taskList.size() == 0) {
            System.out.println(chatbotUi.dividerWrapper("Your list is empty"));
        }
        else {
            System.out.println(chatbotUi.dividerWrapper("Here are the tasks in your list: \n" + taskList.showList()));
        }
    }

    public void userInputDeleteTask(String userInput) {
        String[] array = userInput.split(" ");
        try {
            int number = Integer.parseInt(array[1]);
            Task temp = taskList.getTaskAtIndex(number - 1);
            taskList.deleteAtIndex(number - 1);
            System.out.println(chatbotUi.dividerWrapper("Noted. I've removed this task:\n" + temp + "\nNow you have "+ taskList.size() + " tasks in the list"));
        } catch (NumberFormatException e) {
            System.out.println(chatbotUi.dividerWrapper("You must use a number to delete successfully."));
        } catch (IndexOutOfBoundsException e) {
            // System.out.println(e)
            if (taskList.size() == 0) {
                System.out.println(chatbotUi.dividerWrapper("Can not delete, task list is empty!"));
            }
            System.out.println(chatbotUi.dividerWrapper("You must select a number within the scope of the task list"));
        }
    }

}