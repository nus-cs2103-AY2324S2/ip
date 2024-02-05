import java.util.Scanner;

public class  Dude {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        System.out.println(greet());

        // Initialise Scanner to reach command line input
        Scanner sc = new Scanner(System.in);
        while(true){

            String s = sc.nextLine();

            switch (s){
                case "bye":
                    System.out.println(bye());
                    return;
                default:
                    System.out.println(get_echo_msg(s));
            }
        }

    }




    private static String greet(){
        return "\t-----------------------------------\n" +
                "\tHello! I'm Dude\n" +
                "\tWhat can I do for you?\n" +
                "\t-----------------------------------";
    }


    private static String bye(){
        String bye_msg = "\t-----------------------------------";
        bye_msg += "\n\tBye. Hope to see you again soon!";
        bye_msg +=  "\n\t-----------------------------------";

        return bye_msg;
    }


    private static String get_echo_msg(String msg){
        return "\t-----------------------------------\n" +
                "\t" + msg + "\n" +
                "\t-----------------------------------";
    }

}
