package Utils;
public class StringUtils {
  public static int getIndexOf(String input, String toFind) {
    int index = input.indexOf(toFind);
    if (index == -1) throw new IllegalArgumentException("No " + toFind + " specified");
    return index;
  }
  public static int getIndexOf(String input, String toFind, int offset) {
    int index = input.indexOf(toFind) + offset;
    if (index < 0 || index >= input.length()) throw new IllegalArgumentException("No " + toFind + " specified");
    return index;
  }
}
