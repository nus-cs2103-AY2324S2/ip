public class Util {
    /**
     * @param string Takes in a string
     * @return an integer, NULL if it's a funky string
     */
    public static Integer parseInt(String string) {
        Integer number = null;
        try {
            number = Integer.parseInt(string, 10);
        } catch (Exception ignored) {}
        return number;
    }
}
