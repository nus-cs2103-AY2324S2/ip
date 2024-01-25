class Process {
    private TaskList taskList;
    private Ui chatbotUi;

    public Process(TaskList taskList, Ui chatbotUi) {
        this.taskList = taskList;
        this.chatbotUi = chatbotUi;
    }

    public void userInputProcessMarkUnmark(String userInput) throws NumberFormatException {
        String[] array = userInput.split(" ");
        String command = array[0];

        try {

            int number = Integer.parseInt(array[1]);

            if (command.equals("mark")) {
                taskList.setMark(number - 1);
                System.out.println(chatbotUi.dividerWrapper(chatbotUi.mark() + "\n" + taskList.showTaskAtIndex(number - 1)));
            } else if (command.equals("unmark")) {
                taskList.setUnmark(number - 1);
                System.out.println(chatbotUi.dividerWrapper(chatbotUi.unmark() + "\n" + taskList.showTaskAtIndex(number - 1)));
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    public void userInputAddTask(String userInput) {
        taskList.add(new Task(userInput, false));
        System.out.println(chatbotUi.dividerWrapper("added: " + userInput));
    }
}
