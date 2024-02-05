package duke;

import duke.task.Task;

import java.util.Scanner;
public class TextUi {
    private static final String BORDER = "***************************";
    private static final String DASH = "___________________________";
    private static final String LOGO = "Current no available Logo";
    private static final String USER_INSTRUCTION = ""
            + "TODO  :  Adds a task to task list    [ Format :  TODO_<TASK_DESCRIPTION> ]"
            + '\n'
            + "LIST  :  List ALL tasks from task list     [ Format : LIST ]"
            + '\n'
            + "MARK/UNMARK  :  Mark/Unmark a task from the task list    "
            + "[ Format : MARK/UNMARK_<Task Number> ]"
            + '\n'
            + "EVENT :  Adds an event from and to with specific date and time"
            + '\n'
            + "            [ Format : EVENT_<EVENT_DESCRIPTION>_/<DATE_TIME_FROM>_/<DATE_TIME_TO> ] "
            + "( DATE_TIME = YYYY-MM-DD HHMM )"
            + '\n'
            + "DEADLINE  :  Adds a task with a deadline at specific date and time"
            + '\n'
            + "            [ Format : DEADLINE_<TASK_DESCRIPTION>_/<DATE_TIME_BY> "
            + "( DATE_TIME = YYYY-MM-DD HHMM )"
            + '\n'
            + "DELETE  :  Remove task from task list    "
            + "[ Format :  DELETE_<TASK_NUMBER> ]"
            +'\n'
            + "BYE  :  Exits the app" + '\n'
            + DASH + '\n' ;
    private static final String ADD_TASK_MESSAGE =
            "Nice! I've added this task to your task list :" ;
    private static final String MARK_TASK_MESSAGE =
            "Well Done!!! I've marked this task as done :";
    private static final String UNMARK_TASK_MESSAGE =
            "Alright. I've marked this task as undone :";
    private static final String DELETE_TASK_MESSAGE =
            "NOTED. I've removed this task from your task list :";
    private static final String FIND_TASK_MESSAGE = "Here are some similar tasks I have found in your task list:";

    public String showBorder() {
        return BORDER;
    }

    public String showGreetMessage() {
        return BORDER + '\n' +
                LOGO + '\n' + '\n' +
                "------WELCOME !!!------" + '\n' +
                "I am Pororo, your personal task manager. How may I assist you today?" +
                '\n' + '\n' + USER_INSTRUCTION;
    }

    public String readUserCommand() {
        Scanner sc = new Scanner(System.in);
        System.out.println(BORDER);
        System.out.println("Enter Commands :");
        String userCommand = sc.nextLine();
        System.out.println(BORDER);
        return userCommand;
    }

    public String showTotalCountMessage(TaskList taskList) {
        return "Now you have " + taskList.getList().size() +
                " tasks in your task list.";
    }

    public String showAddTaskMessage(Task task) {
        return BORDER +  '\n' +
                ADD_TASK_MESSAGE + '\n' +
                task;
    }

    public String showMarkTaskMessage(Task task) {
        return BORDER +  '\n' +
                MARK_TASK_MESSAGE + '\n' +
                task + '\n' +
                BORDER;
    }

    public String showUnmarkTaskMessage(Task task) {
        return BORDER +  '\n' +
                UNMARK_TASK_MESSAGE + '\n' +
                task + '\n' +
                BORDER;
    }

    public String showDeleteTaskMessage(Task task) {
        return BORDER + '\n' +
                DELETE_TASK_MESSAGE + '\n' +
                task;
    }

    public String showTaskList(TaskList taskslist) {
        taskslist.list();
        return BORDER;
    }

    public String showFindMessage() {
        return BORDER + '\n' +
                FIND_TASK_MESSAGE;
    }

    public String showLoadingError() {
        return "There is no existing file in your computer.";
    }

    public String showErrorMessage(String m) {
        return m;
    }

    public String showExitMessage() {
        return BORDER + '\n'
                + "It's great working with you! "
                + "See you again soon. BYE!!!"
                + BORDER;
    }

}

