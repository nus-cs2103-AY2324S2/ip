import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Tom \nWhat can I do for you?\n");
        System.out.println(line);
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskcount = 0;


        while (true){
            String input = scanner.nextLine();
            if ("bye".equals(input)){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if ("list".equals(input)) {
                for(int i=0; i< taskcount;i++){
                    System.out.println((i+1)+". " + tasks[i]);
                }
            } else{
                tasks[taskcount] = input;
                System.out.println("added: "+input);
                taskcount++;
            }


        }



    }
}
