package baron;

import baron.managers.TaskManager;

public class Baron {

    private TaskManager taskManager;
    public Baron() {
        this.taskManager = new TaskManager();
    }

    public String getResponse(String input) {
        String output = taskManager.handleInput(input);
        return output;
    }
}
