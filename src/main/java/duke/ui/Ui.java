package duke.ui;//package duke;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    String botName;
    public Ui(String botName) {
        this.botName = botName;
    }

    public static void messageWithHorizontalLines(String message) {
        System.out.println("____________________________________________________________\n" +
                message + "\n" +
                "____________________________________________________________");
    }

    public void sendWelcome() {
        String welcomeStr = " Hello! I'm " + botName + "\n What can I do for you?";
        messageWithHorizontalLines(welcomeStr);
    }

    public void sendGoodbye() {
        String byeStr = "Bye. Hope to see you again soon!";
        messageWithHorizontalLines(byeStr);
    }

    public void badUserInput() {
        messageWithHorizontalLines(botName + " does not understand you :((");
    }

    public void showTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            messageWithHorizontalLines("There are no tasks!");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println(" " + (i + 1) + "." + task);
            }
            System.out.println("____________________________________________________________");
        }
    }

    public void addNewTask(Task addedTask, int taskListSize) {
        messageWithHorizontalLines("Got it. I have added " + addedTask + " into Task List.");
    }

    public void deleteTask(Task deletedTask, int taskListSize) {
        messageWithHorizontalLines("Task has been successfully removed:\n"
                                    + " " + deletedTask + "\n"
                                    + "There are " + taskListSize + " in the task list currently.");
    }

    public void invalidTaskIndex() {
        messageWithHorizontalLines("Invalid Task Index!");
    }

    public void markAsDone(Task doneTask) {
        messageWithHorizontalLines("Nice! I've marked this task as done:\n" + "  " + doneTask);
    }

    public void markAsUndone(Task undoneTask) {
        messageWithHorizontalLines("OK, I've marked this task as not done:\n" + "  " + undoneTask);
    }

    public void noIndexDeleteTask() {
        System.out.println("Please provide the task index you want to delete.");
    }

    public void noIndexMarkAsDone() {
        System.out.println("Please provide the task index to mark as done.");
    }

    public void noIndexMarkAsUndone() {
        System.out.println("Please provide the task index to mark as not done.");
    }

    public void invalidDateInput() {
        System.out.println("Error input: Date format is invalid (Date format: YYYY-MM-DD)");
    }

    public void insufficientTodoDescription() {
        System.out.println("Please provide a description for your Todo task.");
    }

    public void insufficientDeadline() {
        System.out.println("Please provide a deadline for your Deadline task.");
    }

    public void insufficientEventStartTimeEndTime() {
        System.out.println("Please provide both starting time and ending time for your event task.");
    }

    public void insufficientEventStartTime() {
        System.out.println("Please provide a starting time for your event task.");
    }

    public void insufficientEventEndTime() {
        System.out.println("Please provide a ending time for your event task.");
    }

    public void invalidEventStartingTimeAndEndingTime() {
        System.out.println("Error input: Start time should be earlier than end time.");
    }
}

