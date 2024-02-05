public class Parser {
    public String[] getCommand(String userMessage) {
        String[] result = new String[2];
        Boolean foundSplit = false;
        for (int i = 0; i < userMessage.length(); i++) {
            if (userMessage.charAt(i) == ' ') {
                result[0] = userMessage.substring(0, i);
                result[1] = userMessage.substring(i + 1, userMessage.length());
                foundSplit = true;
                break;
            }
        }
        if (!foundSplit) {
            result[0] = userMessage;
        }
        return result;
    }

    public String getCmdDetails(String cmd, String details) throws DukeException {
        if (details == null || details.trim().length() == 0) {
            throw new DukeException("Please enter a description for the " + cmd + " command");
        }
        return details.trim();
    }
}
