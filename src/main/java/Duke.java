public class Duke {
    private static final String LINE = "____________________________________________________________\n";
    public static void main(String[] args) {
        String logo = "   :::   :::           :::        :::::::::       :::::::::       ::::::::::       ::::::::: \n" +
                "  :+:   :+:         :+: :+:      :+:    :+:      :+:    :+:      :+:              :+:    :+: \n" +
                "  +:+ +:+         +:+   +:+     +:+    +:+      +:+    +:+      +:+              +:+    +:+  \n" +
                "  +#++:         +#++:++#++:    +#++:++#+       +#++:++#+       +#++:++#         +#++:++#:    \n" +
                "  +#+          +#+     +#+    +#+             +#+             +#+              +#+    +#+    \n" +
                " #+#          #+#     #+#    #+#             #+#             #+#              #+#    #+#     \n" +
                "###          ###     ###    ###             ###             ##########       ###    ###      \n\n";
        System.out.println(Duke.LINE + " \uD83D\uDC4B✨What's poppin' fam, it's ya boi \n\n" + logo + "Hit me up with those deets and let's vibe together! \uD83D\uDCAF\uD83D\uDD25\uD83D\uDE1C");
        Duke.bye();
    }

    public static void bye() {
        System.out.print(Duke.LINE + "Peace out, fam!✌️ Stay lit and keep those good vibes rollin'! \uD83D\uDE80\uD83D\uDC96\n" + Duke.LINE);
    }
}

