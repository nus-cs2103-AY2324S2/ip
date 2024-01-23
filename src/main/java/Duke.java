import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Hello! My name is Mitsuki, nice to meet you!\n" + "What can I do for you today?\n");

        String answer = "nil";

        String[] list = new String[100];
        int i = 0;

        while (!answer.equals("bye")) {
            answer = scan.nextLine();
            if (!answer.equals("bye") && !answer.equals("list")) {
                list[i] = answer;
                i++;
                System.out.println("Added: '" + answer + "' to your list!");
            } else if (answer.equals("list")) {
                for (int j = 1; j <= i; j++) {
                    System.out.println(j + ". " + list[j - 1]);
                }
            }
        }


        System.out.println("Bye! Hope to see you again soon!\n");
        System.exit(0);

    }
}
