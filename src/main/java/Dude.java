import java.util.Scanner;

public class  Dude {

    static TaskList taskList = new TaskList();

    public static void main(String[] args) {

        String logo =   "888888ba                 dP          \n" +
                        "88    `8b                88          \n" +
                        "88     88 dP    dP .d888b88 .d8888b. \n" +
                        "88     88 88    88 88'  `88 88ooood8 \n" +
                        "88    .8P 88.  .88 88.  .88 88.  ... \n" +
                        "8888888P  `88888P' `88888P8 `88888P'";

        System.out.println("--------------------------------------\n");
        System.out.println(logo + "\n");
        System.out.println("--------------------------------------");
        System.out.println("Dude v1.0 by Tahsin Hasem.\n");

        System.out.println(greet());

        // Initialise Scanner to reach command line input
        Scanner sc = new Scanner(System.in);
        while(true){

            String msg = sc.nextLine();
            String first_arg = msg.split(" ")[0];

            switch (first_arg){
                case "bye":
                    System.out.println(bye());
                    return;
                case "list":
                    System.out.println(list());
                    break;
                case "mark":
                    System.out.println(mark_as_done(msg));
                    break;
                default:
                    System.out.println(add_task(msg));
            }
        }

    }

    private static String greet(){
        return "\t-----------------------------------\n" +
                "\tHello! I'm Dude\n" +
                "\tWhat can I do for you?\n" +
                "\t-----------------------------------\n";
    }

    private static String add_task(String msg){
        try {
            return taskList.add_task(msg);
        } catch (TaskListFullException e) {
            return e.getMessage();
        }
    }

    private static String list(){
        return taskList.toString();
    }


    private static String bye(){
        String bye_msg = "\t-----------------------------------";
        bye_msg += "\n\tBye. Hope to see you again soon!";
        bye_msg +=  "\n\t-----------------------------------";

        return bye_msg;
    }

    private static String mark_as_done(String msg){
        //Try to retrieve the index
        int index = 0;
        try{
            index = Integer.parseInt(msg.split(" ")[1]);
        } catch (IndexOutOfBoundsException e) {
            return "\t-----------------------------------\n" +
                    "\tPlease provide a valid task ID. Has to be an integer.\n" +
                    "\t-----------------------------------";
        }

        //This will run only when the index is valid, as catch block will return the error message
        try {
            return taskList.mark_as_done(index);
        } catch (IndexOutOfBoundsException e) {
            return "\t-----------------------------------\n" +
                    "\t" + e.getMessage() +"\n" +
                    "\t-----------------------------------";
        }
    }

    private static String get_echo_msg(String msg){
        return "\t-----------------------------------\n" +
                "\t" + msg + "\n" +
                "\t-----------------------------------";
    }

}
