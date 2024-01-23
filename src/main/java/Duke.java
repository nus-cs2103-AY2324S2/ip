public class Duke {

    /**
     * Prints a message to the terminal, decorated with the Louie icon.
     *
     * @param message The message to print.
     */
    public static void print(String message) {
        boolean isFirst = true;
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (String s : message.split("\n")) {
            sb.append("    ");
            if (isFirst) {
                sb.append("(>^.^<)");
                isFirst = false;
            } else {
                sb.append("       ");
            }
            sb.append(" ").append(s).append("\n");
        }
        sb.append("\n");
        System.out.print(sb);
    }
    public static void main(String[] args) {
        Duke.print("Hello, my name is... Louie!!!!\n" + 
                   "What can I do for you today?");
        Duke.print("Ok, going to sleep...");

    }
}
