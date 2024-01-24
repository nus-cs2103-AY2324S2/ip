import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String line = "------------------------------" ;
        Scanner obj = new Scanner(System.in);
        System.out.println("\n Hello! I'm Leo\n" +
                " What can I do for you?");
        System.out.println(line);
        while(obj.hasNextLine()){
            String res = obj.nextLine();
            if (res.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                return;
            } else {
                System.out.println(res);
            }
            System.out.println(line);
        }

    }
}
