import java.util.Scanner;
import java.util.ArrayList;

public class Kirby {
    public static void main(String[] args) {
        String message =
                "____________________________________________________________\nHiiiiii \uD83D\uDE00! I'm Kirby Yayyyyy \uD83C\uDF8C!\nWhat can I do for you?\n____________________________________________________________\n";

        Scanner sc = new Scanner(System.in);

        ArrayList<Task> inputs = new ArrayList<>();

        System.out.println(message);

        String var = sc.nextLine();

        while(!var.equals("bye")){

            //list command
            if(var.equals("list")){

                for(int i = 0; i < inputs.size(); i++){
                    System.out.println(i + 1 + ". " + inputs.get(i));
                }

                System.out.println();

                var = sc.nextLine();
                continue;
            }

            try {
                //mark command
                if (var.split(" ")[0].equals("mark")) {
                    int temp = Integer.parseInt(var.split(" ")[1]);
                    inputs.get(temp - 1).setDone(true);

                    System.out.println("Hoooray! You did it:");
                    System.out.println(temp + ". " + inputs.get(temp - 1));
                    System.out.println();

                    var = sc.nextLine();
                    continue;
                }

                //unmark command
                if (var.split(" ")[0].equals("unmark")) {
                    int temp = Integer.parseInt(var.split(" ")[1]);
                    inputs.get(temp - 1).setDone(false);

                    System.out.println("Aww, don't give up! I believe you can do it:");
                    System.out.println(temp + ". " + inputs.get(temp - 1));
                    System.out.println();

                    var = sc.nextLine();
                    continue;
                }
            } catch(IndexOutOfBoundsException e){
                System.out.println("Sorry that item doesn't exist");
                var = sc.nextLine();
                continue;
            } catch(NumberFormatException e){
                System.out.println("You have to indicate the number on the list");
                var = sc.nextLine();
                continue;
            }

            inputs.add(new Task(var));
            System.out.println("____________________________________________________________");
            System.out.println("Okiiiie! I will remember: " + var);
            System.out.println("____________________________________________________________\n");
            var = sc.nextLine();
        }

        //bye command
        System.out.println("Noooo \uD83E\uDD7A You are leaving already \uD83D\uDE16? Hope to see you again soon!\n____________________________________________________________");
    }
}
