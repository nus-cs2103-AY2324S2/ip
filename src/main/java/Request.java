public class Request {
    private String name;

    public Request(String name) {
        this.name = name;
    }

    public void handleRequest() {
        if (this.name.contains("unmark")) {
            int taskNumber = Integer.parseInt(this.name.substring(7));
            Nicole.taskList.get(taskNumber - 1).markUndone();
        } else if (this.name.contains("mark")) {
            int taskNumber = Integer.parseInt(this.name.substring(5));
            Nicole.taskList.get(taskNumber - 1).markDone();
        } else if (!this.name.equals("list")) {
            Task newTask = new Task(this.name);
            Nicole.taskList.add(newTask);
            System.out.println(Nicole.botName + ": Oki I added " + "\"" + newTask.toString().substring(3) + "\"");
        } else {
            System.out.println(Nicole.botName + ": Your tasks are, ");
            for (int i = 0; i < Nicole.taskList.size(); i++) {
                System.out.println((i + 1) + ": " + Nicole.taskList.get(i));
            }
        }
    }
}
