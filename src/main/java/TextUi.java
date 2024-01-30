import java.util.Scanner;
public class TextUi {
    private final static String BORDER = "***************************";
    private final static String DASH = "___________________________";
    private final static String LOGO = "Current no available Logo";
    private final static String USER_INSTRUCTION = "" +
            "TODO  :  Adds a task to task list    [ Format :  TODO_<TASK_DESCRIPTION> ]" + '\n' +
            "LIST  :  List ALL tasks from task list     [ Format : LIST ]" + '\n' +
            "MARK/UNMARK  :  Mark/Unmark a task from the task list    [ Format : MARK/UNMARK_<Task Number> ]" + '\n' +
            "EVENT :  Adds an event from and to with specific date and time" + '\n' +
            "            [ Format : EVENT_<EVENT_DESCRIPTION>_/<DATE_TIME_FROM>_/<DATE_TIME_TO> ] ( DATE_TIME = YYYY-MM-DD HHMM )" + '\n' +
            "DEADLINE  :  Adds a task with a deadline at specific date and time" + '\n' +
            "            [ Format : DEADLINE_<TASK_DESCRIPTION>_/<DATE_TIME_BY> ( DATE_TIME = YYYY-MM-DD HHMM )" + '\n' +
            "DELETE  :  Remove task from task list    [ Format :  DELETE_<TASK_NUMBER> ]" +'\n' +
            "BYE  :  Exits the app" + '\n' +
            DASH + '\n' ;
    private final static String ADD_TASK_MESSAGE = "Nice! I've added this task to your task list :" ;
    private final static String MARK_TASK_MESSAGE = "Well Done!!! I've marked this task as done :";
    private final static String UNMARK_TASK_MESSAGE = "Alright. I've marked this task as undone :";
    private final static String DELETE_TASK_MESSAGE = "NOTED. I've removed this task from your task list :";
    public void showGreetMessage() {
        System.out.println( BORDER + '\n' +
                LOGO + '\n' + '\n' +
                "------WELCOME !!!------" + '\n' +
                "I am Panda, your personal task manager. How may I assist you today?" + '\n' + '\n' +
                USER_INSTRUCTION);
    }

    public String readUserCommand() {
        Scanner sc = new Scanner(System.in);
        System.out.println(BORDER);
        System.out.println("Enter Commands :");
        String userCommand = sc.nextLine();
        System.out.println(BORDER);
        return userCommand;
    }

    public void showTotalCountMessage(TaskList taskList) {
        System.out.println("Now you have " + taskList.getList().size() + " tasks in your task list.");
    }
    public void showAddTaskMessage(Task task) {
        System.out.println(BORDER +  '\n' +
                ADD_TASK_MESSAGE + '\n' +
                task);
    }

    public void showMarkTaskMessage(Task task) {
        System.out.println(BORDER +  '\n' +
                MARK_TASK_MESSAGE + '\n' +
                task + '\n' +
                BORDER);
    }

    public void showUnmarkTaskMessage(Task task) {
        System.out.println(BORDER +  '\n' +
                UNMARK_TASK_MESSAGE + '\n' +
                task + '\n' +
                BORDER);
    }

    public void showDeleteTaskMessage(Task task) {
        System.out.println(BORDER + '\n' +
                DELETE_TASK_MESSAGE + '\n' +
                task);
    }

    public void showTaskList(TaskList taskslist) {
        System.out.println(BORDER);
        taskslist.list();
        System.out.println(BORDER);
    }

    public void showLoadingError() {
        System.out.println("There is no existing file in your computer.");
    }

    public void showErrorMessage(String m) {
        System.out.println(m);
    }

    public void showExitMessage() {
        System.out.println(BORDER);
        System.out.println("It's great working with you! See you again soon. BYE!!!");
        System.out.println(BORDER);
    }

}