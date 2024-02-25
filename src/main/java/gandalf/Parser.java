package gandalf;

/**
 * Class to interpret user's input
 */
public class Parser {
    private String taskType;
    private String taskName;
    private String startDate;
    private String endDate;
    private final String userInput;

    public Parser(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Interprets the instance-level userInput field by checking for double-spacing and structure it to the
     * different information types
     */
    public void interpret() {
        StringBuilder builderTaskType = new StringBuilder();
        StringBuilder builderTaskName = new StringBuilder();
        StringBuilder builderStartDate = new StringBuilder();
        StringBuilder builderEndDate = new StringBuilder();
        StringBuilder[] parsedInput = {builderTaskType, builderTaskName, builderStartDate, builderEndDate};
        int currInfo = 0;
        for (int i = 0; i < userInput.length(); i++) {
            char currChar = userInput.charAt(i);
            if (currChar == ' ') {
                if (userInput.charAt(i - 1) == ' ') {
                    currInfo++;
                    continue;
                }
            }
            parsedInput[currInfo].append(currChar);
        }
        this.taskType = builderTaskType.toString().trim().toLowerCase();
        this.taskName = builderTaskName.toString().trim();
        this.startDate = builderStartDate.toString().trim();
        this.endDate = builderEndDate.toString().trim();
    }

    public String getTaskType() {
        return this.taskType;
    }
    public String getTaskName() {
        return this.taskName;
    }
    public String getStartDate() {
        return this.startDate;
    }
    public String getEndDate() {
        return this.endDate;
    }
}
