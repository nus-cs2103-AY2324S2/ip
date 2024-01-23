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

  /**
   * This function gets the value of a specified command string. 
   * @param input The entire string
   * @param command Command string to start search from. Search index is offset by the command's length
   * @param stopWord Optional parameter to indicate to the function that anything beyond this stopword's start index 
   * will not be the value of the command. Search index is offset by -1 to avoid collecting the stopwrod too
   * @return
   */
  public static String getValueOfCommand(String input, String command, String stopWord)  {
    int commandIndex = getIndexOf(input, command);
    // Validates against command that is at the end of the string
    if (commandIndex + command.length() >= input.length()) throw new IndexOutOfBoundsException("No value found after " + command);
    commandIndex += command.length();
    String value = "";
    if (stopWord != null) {
      int endIndex = getIndexOf(input, stopWord, -1);
      value = input.substring(commandIndex, endIndex);
    } else {
      value = input.substring(commandIndex);
    }
    value = value.trim();
    if (value.isEmpty()) throw new IllegalArgumentException("No valid value found after " + command);
    return value;
  }
}
