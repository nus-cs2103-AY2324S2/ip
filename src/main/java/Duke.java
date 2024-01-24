import java.util.Scanner;

public class Duke {
    private static final String LINE = "    ____________________________________________________________\n";
    private static final String INDENT = "    ";
    public static void main(String[] args) {
        Duke.hello();
        int taskCount = 0;
        String[] tasks = new String[100];
        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                Duke.bye();
                break;
            } else if (input.equals("list")) {
                System.out.print(LINE);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(INDENT + (i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[taskCount] = input;
                taskCount++;
                System.out.print(LINE + INDENT + "added: " + input + "\n" + LINE);
            }
        }
    }

    public static void hello() {
        String logo = "       :::   :::           :::        :::::::::       :::::::::       ::::::::::       ::::::::: \n" +
                "      :+:   :+:         :+: :+:      :+:    :+:      :+:    :+:      :+:              :+:    :+: \n" +
                "      +:+ +:+         +:+   +:+     +:+    +:+      +:+    +:+      +:+              +:+    +:+  \n" +
                "      +#++:         +#++:++#++:    +#++:++#+       +#++:++#+       +#++:++#         +#++:++#:    \n" +
                "      +#+          +#+     +#+    +#+             +#+             +#+              +#+    +#+    \n" +
                "     #+#          #+#     #+#    #+#             #+#             #+#              #+#    #+#     \n" +
                "    ###          ###     ###    ###             ###             ##########       ###    ###      \n\n";
        System.out.println(LINE + "    What's poppin' fam, it's ya boi \n\n" + logo +
                "    Hit me up with those deets and let's vibe together!" + LINE);
    }

    public static void bye() {
        System.out.print(LINE + "    Peace out, fam! Stay lit and keep those good vibes rollin'!\n" + LINE);
    }
}

