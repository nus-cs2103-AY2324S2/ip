import java.util.Scanner;

public class Derek {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String indent = "    ";

        String welcomeMessage = String.join(
            "\n",
            indent + line,
            indent + "Hello! I'm DEREK",
            indent + "What can I do for you?",
            indent + line,
            ""
        );

        String exitMessage = String.join(
            "\n",
            indent + line,
            indent + "Bye. Hope to see you again soon!",
            indent + line,
            ""
        );

        System.out.println(welcomeMessage);

        // user input loop
        Scanner scanner = new Scanner(System.in);

        String userPrompt = "";
        while(true) {
            userPrompt = scanner.nextLine();

            if ("bye".equalsIgnoreCase(userPrompt)) {
                break;
            }

            System.out.println(String.join(
                "\n",
                indent + line,
                indent + userPrompt,
                indent + line,
                ""
            ));
        }

        scanner.close();

        System.out.println(exitMessage);
    }
}
