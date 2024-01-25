import java.util.Scanner;

public class MeanDuke{

    //Text art to be used
    private static final String logo =
                  "███╗░░░███╗███████╗░█████╗░███╗░░██╗░░░░░░██████╗░██╗░░░██╗██╗░░██╗███████╗\n"
                + "████╗░████║██╔════╝██╔══██╗████╗░██║░░░░░░██╔══██╗██║░░░██║██║░██╔╝██╔════╝\n"
                + "██╔████╔██║█████╗░░███████║██╔██╗██║█████╗██║░░██║██║░░░██║█████═╝░█████╗░░\n"
                + "██║╚██╔╝██║██╔══╝░░██╔══██║██║╚████║╚════╝██║░░██║██║░░░██║██╔═██╗░██╔══╝░░\n"
                + "██║░╚═╝░██║███████╗██║░░██║██║░╚███║░░░░░░██████╔╝╚██████╔╝██║░╚██╗███████╗\n"
                + "╚═╝░░░░░╚═╝╚══════╝╚═╝░░╚═╝╚═╝░░╚══╝░░░░░░╚═════╝░░╚═════╝░╚═╝░░╚═╝╚══════╝\n";
    private static final String spacer = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n";

    public static void main(String[] args) {

        //Creates an empty TaskList
        TaskList tasklist = new TaskList();

        //Prints intro
        String intro = logo + "What do you want this time?\n" + spacer;
        System.out.println(intro);


        //Reads each line of user input and perform respective actions
        Scanner inputScanner= new Scanner(System.in);
        String userInput = inputScanner.nextLine();

        while (!userInput.equals("end")) {  //Session terminates when user inputs "end"
            if (userInput.startsWith("add ")) {
                //add command: Add task to list and print out added task
                String taskString = userInput.substring(4);
                tasklist.add(taskString);
                System.out.println(spacer + "added to list: " + taskString + "\n" + spacer);
            } else if (userInput.equals("list")) {
                //list command: Print out current task list
                System.out.println(spacer + tasklist + spacer);
            } else {
                //Echo back user input
                System.out.println(spacer + userInput + "\n" + spacer);
            }
            userInput = inputScanner.nextLine();
        }

        //Outro message and end of program
        String outro = spacer + "Finally you're finished, thought you would never stop yapping.\n" + spacer;
        System.out.println(outro);
    }
}
