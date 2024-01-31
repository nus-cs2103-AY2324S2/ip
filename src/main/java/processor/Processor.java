package processor;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class Processor {
    private TaskList taskList;
    private Ui chatbotUi;
    private Storage storage;

    public Processor(TaskList taskList, Ui chatbotUi) {
        this.taskList = taskList;
        this.chatbotUi = chatbotUi;
        //this.file = new File("src/data/tasks.txt");
        this.storage = new Storage("src/data/tasks.txt", taskList);
    }
    public void userInputProcessMarkUnmark(String userInput) throws IOException{
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
            storage.writeToFile();
        } catch (NumberFormatException e) {
            System.out.println(chatbotUi.dividerWrapper("You must use a number to mark."));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(chatbotUi.dividerWrapper("You must select a number within the size of the Task List."));
        }
    }

    public void userInputAddTask(String userInput) throws IOException {
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

        String times[] = userInput.split("//");


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
                if (times.length < 2) {
                    System.out.println(chatbotUi.dividerWrapper("Wrong syntax! Must be `deadline <task> //by <deadline>`"));
                    return;
                }
                String deadline = times[1].trim();

                if (!deadline.startsWith("by ")) {
                    System.out.println(chatbotUi.dividerWrapper("You must start the statement with the word `//by`."));
                    return;
                }

                try {
                    //System.out.println(deadline + " test");
                    LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline.substring(3), DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
                    String formattedDeadline = deadlineDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, ha"));
                    taskList.addDeadlineTask(substringAfterSpace, formattedDeadline);
                } catch (DateTimeParseException e) {
                    System.out.println(chatbotUi.recommenderWrapper("Added the task, but recommend using the date/time format `M/d/yyyy HHmm` for better experience."));
                    taskList.addDeadlineTask(substringAfterSpace, deadline);
                }

                System.out.println(chatbotUi.dividerWrapper("Got it. I've added this task:\n" + taskList.getTaskAtIndex(taskList.size() - 1)
                        + "\nNow you have " + taskList.size() + " tasks in the list."));
                break;

            case "event":

                if (times.length < 3) {
                    System.out.println(chatbotUi.dividerWrapper("Wrong syntax! Must be `event <task> //from <start date> //to <end date>`"));
                    return;
                }
                String start = times[1].trim();
                String end = times[2].trim();

                if (!start.startsWith("from ") || !end.startsWith("to ")) {
                    System.out.println(chatbotUi.dividerWrapper("You must start the statements with the words `//from` and `//to`."));
                    return;
                }

                try {
                    LocalDateTime startDateTime = LocalDateTime.parse(start.substring(5), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                    LocalDateTime endDateTime = LocalDateTime.parse(end.substring(3), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                    String formattedStart = startDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, ha"));
                    String formattedEnd = endDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, ha"));
                    taskList.addEventTask(substringAfterSpace, formattedStart, formattedEnd);
                } catch (DateTimeParseException e) {
                    System.out.println(chatbotUi.recommenderWrapper("Added the task, but recommend using the date/time format `M/d/yyyy HHmm` on both start and end dates for better experience."));
                    taskList.addEventTask(substringAfterSpace, start, end.substring(3)); //substring(3) to remove "to "
                }

                System.out.println(chatbotUi.dividerWrapper("Got it. I've added this task:\n" + taskList.getTaskAtIndex(taskList.size() - 1)
                        + "\nNow you have " + taskList.size() + " tasks in the list."));
                break;
            default:
                System.out.println(chatbotUi.dividerWrapper("Invalid command. Please enter a valid command."));
        }

        storage.writeToFile();

    }


    public void userInputListTasks() throws IOException {
        // if array empty
        if (taskList.size() == 0) {
            System.out.println(chatbotUi.dividerWrapper("Your list is empty"));
        }
        else {
            System.out.println(chatbotUi.dividerWrapper("Here are the tasks in your list: \n" + storage.generateTasks()));
        }
    }

    public void userInputDeleteTask(String userInput) {
        String[] array = userInput.split(" ");
        try {

            int number = Integer.parseInt(array[1]);
            Task temp = taskList.getTaskAtIndex(number - 1);
            taskList.deleteAtIndex(number - 1);
            System.out.println(chatbotUi.dividerWrapper("Noted. I've removed this task:\n" + temp + "\nNow you have "+ taskList.size() + " tasks in the list"));
            storage.writeToFile();
        } catch (NumberFormatException e) {
            System.out.println(chatbotUi.dividerWrapper("You must use a number to delete successfully."));
        } catch (IndexOutOfBoundsException e) {
            // System.out.println(e)
            if (taskList.size() == 0) {
                System.out.println(chatbotUi.dividerWrapper("Can not delete, task list is empty!"));
            }
            System.out.println(chatbotUi.dividerWrapper("You must select a number within the scope of the task list"));
        } catch (IOException e) {
            System.out.println(chatbotUi.dividerWrapper("Error writing to file."));
        }
    }

}