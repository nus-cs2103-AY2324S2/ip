public class Parser {
    public static String getCommand(String input) {
        if (input.isEmpty()) {
            return null;
        } else {
            return input.split(" ")[0];
        }
    }

    public static Integer getInteger(String input, int idx) {
        if (input.length() <= idx) {
            return null;
        } else {
            try {
                return Integer.parseInt(input.split(" ")[idx]);
            } catch (ArrayIndexOutOfBoundsException e) {
                final String output = String.format("Expecting input of at least %d", idx + 1);
                System.out.println(output);
                return null;
            }
        }
    }
}
