import java.util.HashMap;

public class Duke {
    public static void main(String[] args) {
        // emoji alphabet character mappings
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

        // chatbot chat placeholder
        String PLACEHOLDER = "----------------------------------------\n" +
                "%s\n" +
                "----------------------------------------\n";

        // chatbot speech
        System.out.printf(
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
                        "How can I simplee-fy your life?"
        );
        System.out.printf(
                PLACEHOLDER,
                "Bye. Hope to simp for you again soon!"
        );
    }
}