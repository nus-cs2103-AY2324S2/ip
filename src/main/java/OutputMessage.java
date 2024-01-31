import java.util.ArrayList;
public class OutputMessage {
    private final String name;
    public OutputMessage(String name){
        this.name = name;
    }
    private static void printMessageWithLines(String content){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + content);
        System.out.println("\t____________________________________________________________\n" );
    }
    public void greet(){
        String message = "\tGreetings! I am " + name + "." +"\n\tHow may I be of service to you today?";
        printMessageWithLines(message);
    }
    public static void leave() {
        String message = "\tFarewell. Wishing for the opportunity to meet you again soon.";
        printMessageWithLines(message);
    }
    public static void informInvalidCommand(){
        String message = "I regret to inform you that I currently lack an understanding of the intended meaning behind"
                + "that statement.";
        printMessageWithLines(message);
    }
    public static void informBadTodoInput(){
        String message = "It is imperative that the description of a to-do is not left empty.";
        printMessageWithLines(message);
    }
    public static void informBadDeadlineInput(){
        String message = "It is imperative that the description and the date of a deadline is not left empty.";
        printMessageWithLines(message);
    }
    public static void informBadEventInput(){
        String message = "It is imperative that the description and from-to information of an event is not left empty.";
        printMessageWithLines(message);
    }
    public static void displayFullList(TaskList taskList){
        if (taskList != null) {
            taskList.showList();
        } else {
            System.out.println("\tTaskList is null.");
        }
    }

}
