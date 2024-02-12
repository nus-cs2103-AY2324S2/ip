import java.util.Scanner;
import java.util.ArrayList;

public class Kirby {
    public static void main(String[] args) {
        String message =
                "____________________________________________________________\nHiiiiii \uD83D\uDE00! I'm Kirby Yayyyyy \uD83C\uDF8C!\nWhat can I do for you?\n____________________________________________________________\n";

        Scanner sc = new Scanner(System.in);

        ArrayList<String> inputs = new ArrayList<>();

        System.out.println(message);

        String var = sc.nextLine();

        while(!var.equals("bye")){

            if(var.equals("list")){
                for(int i = 0; i < inputs.size(); i++){
                    System.out.println(i + 1 + ". " + inputs.get(i));
                }
                var = sc.nextLine();
                continue;
            }

            inputs.add(var);
            System.out.println("____________________________________________________________");
            System.out.println("Okiiiie! I will remember: " + var);
            System.out.println("____________________________________________________________\n");
            var = sc.nextLine();
        }

        System.out.println("Noooo \uD83E\uDD7A You are leaving already \uD83D\uDE16? Hope to see you again soon!\n____________________________________________________________");
    }
}
