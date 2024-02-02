package duke.format;
public class Formatter {
    public static String addIndex(Object o, int idx) {
        return String.format("%d. %s", idx, o);
    }
}
