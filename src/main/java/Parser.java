public class Parser {
    public static boolean isMark(String[] words) {
        return words.length == 2 && words[0].equals("mark") && isNumeric(words[1]);
    }

    public static boolean isUnmark(String[] words) {
        return words.length == 2 && words[0].equals("unmark") && isNumeric(words[1]);
    }

    private static boolean isNumeric(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
