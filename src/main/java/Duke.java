import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "Chucklbot";
        System.out.println("Hello I'm " + logo + "\nWhat can I do for you? \n \nBye! Hope to see you again soon.");
        Scanner sc = new Scanner(System.in);
        String byeMessage = "Bye! Hope to see you again soon.";
        while (true) {
            String curr = sc.next();
            if (curr.equals("Bye") || curr.equals("bye")) {
                System.out.println();
                break;
            }
            System.out.println(curr);
        }


    }
}
