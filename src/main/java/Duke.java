import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // emoji alphabet character mappings
        HashMap<String, String> EMOJI_MAP = getStringStringHashMap();

        // storing user input
        ArrayList<Task> al = new ArrayList<>();

        // chatbot chat placeholder
        String PLACEHOLDER = "\t----------------------------------------\n" +
                "\t\t%s\n" +
                "\t----------------------------------------";

        // welcome message
        String wcMsg = String.format(
                PLACEHOLDER,
                "Hello! I'm " +
                        EMOJI_MAP.get("S") +
                        EMOJI_MAP.get("I") +
                        EMOJI_MAP.get("M") +
                        EMOJI_MAP.get("P") +
                        EMOJI_MAP.get("L") +
                        EMOJI_MAP.get("E") +
                        EMOJI_MAP.get("E") +
                        "\n" +
                        "\t\tHow can I simplee-fy your life?"
        );
        System.out.println(wcMsg);

        // taking in user input and echoing
        Scanner scanner = new Scanner(System.in);
        String userIn = scanner.nextLine();
        while (!userIn.equals("bye")) {
            String echoMsg;

            String[] tokens = userIn.split(" ");
            switch (tokens[0]) {
                case "list":   // echos stored user inputs
                    StringBuilder strItems = new StringBuilder();
                    for (int i = 0; i < al.size() - 1; i++) {
                        strItems.append(String.format("%d. %s\n\t\t", i + 1, al.get(i)));
                    }
                    strItems.append(String.format("%d. %s", al.size(), al.get(al.size() - 1)));
                    echoMsg = String.format(PLACEHOLDER, strItems);
                    break;
                case "mark": {
                    int itemIndex = Integer.parseInt(tokens[1]) - 1;
                    al.get(itemIndex).done();

                    echoMsg = String.format(
                            PLACEHOLDER,
                            "Nice! I've marked this task as done:\n\t\t\t" +
                                    al.get(itemIndex));
                    break;
                }
                case "unmark": {
                    int itemIndex = Integer.parseInt(tokens[1]) - 1;
                    al.get(itemIndex).undone();

                    echoMsg = String.format(
                            PLACEHOLDER,
                            "OK, I've marked this task as not done yet:\n\t\t\t" +
                                    al.get(itemIndex));
                    break;
                }
                default:   // echo and store the user input
                    al.add(new Task(userIn));
                    echoMsg = String.format(
                            PLACEHOLDER,
                            "added: " + userIn);
                    break;
            }
            System.out.println(echoMsg);

            userIn = scanner.nextLine();
        }

        // goodbye message
        String gbMsg = String.format(
                PLACEHOLDER,
                "Bye. Hope to simp for you again soon!"
        );
        System.out.println(gbMsg);
    }

    private static HashMap<String, String> getStringStringHashMap() {
        HashMap<String, String> EMOJI_MAP = new HashMap<>();
        EMOJI_MAP.put("A", "\uD83C\uDD70");
        EMOJI_MAP.put("B", "\uD83C\uDD71");
        EMOJI_MAP.put("C", "\uD83C\uDD72");
        EMOJI_MAP.put("D", "\uD83C\uDD73");
        EMOJI_MAP.put("E", "\uD83C\uDD74");
        EMOJI_MAP.put("F", "\uD83C\uDD75");
        EMOJI_MAP.put("G", "\uD83C\uDD76");
        EMOJI_MAP.put("H", "\uD83C\uDD77");
        EMOJI_MAP.put("I", "\uD83C\uDD78");
        EMOJI_MAP.put("J", "\uD83C\uDD79");
        EMOJI_MAP.put("K", "\uD83C\uDD7A");
        EMOJI_MAP.put("L", "\uD83C\uDD7B");
        EMOJI_MAP.put("M", "\uD83C\uDD7C");
        EMOJI_MAP.put("N", "\uD83C\uDD7D");
        EMOJI_MAP.put("O", "\uD83C\uDD7E");
        EMOJI_MAP.put("P", "\uD83C\uDD7F");
        EMOJI_MAP.put("Q", "\uD83C\uDD80");
        EMOJI_MAP.put("R", "\uD83C\uDD81");
        EMOJI_MAP.put("S", "\uD83C\uDD82");
        EMOJI_MAP.put("T", "\uD83C\uDD83");
        EMOJI_MAP.put("U", "\uD83C\uDD84");
        EMOJI_MAP.put("V", "\uD83C\uDD85");
        EMOJI_MAP.put("W", "\uD83C\uDD86");
        EMOJI_MAP.put("X", "\uD83C\uDD87");
        EMOJI_MAP.put("Y", "\uD83C\uDD88");
        EMOJI_MAP.put("Z", "\uD83C\uDD89");

        return EMOJI_MAP;
    }
}