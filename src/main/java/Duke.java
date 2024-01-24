import java.util.Scanner;

public class Duke {
    private static final String LINE = "    ____________________________________________________________\n";
    private static final String INDENT = "    ";
    public static void main(String[] args) {
        Duke.hello();
        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                Duke.bye();
                break;
            } else {
                System.out.print(Duke.LINE + Duke.INDENT + input + "\n" + Duke.LINE);
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
        System.out.println(Duke.LINE + "    What's poppin' fam, it's ya boi \n\n" + logo +
                "    Hit me up with those deets and let's vibe together!" + Duke.LINE);
    }

    public static void bye() {
        System.out.print(Duke.LINE + "    Peace out, fam! Stay lit and keep those good vibes rollin'!\n" + Duke.LINE);
    }
}

