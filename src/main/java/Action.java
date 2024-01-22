public class Action {

    private final String name;
    public Action(String name) {
        this.name = name;
    }

    public void execute() {

        if (this.name.contains("unmark")) {
            Task t = Tsundere.taskList.get(Integer.parseInt(this.name.substring(7, 8)) - 1);
            if (t.getStatusIcon().equals(" ")) {
                System.out.println("You haven't even started this task dummy!");
            } else {
                t.unMark();
                System.out.println(t);
            }
        } else if (this.name.contains("mark")) {
            Task t = Tsundere.taskList.get(Integer.parseInt(this.name.substring(5, 6)) - 1);
            if (t.getStatusIcon().equals("X")) {
                System.out.println("You already finished this!");
            } else {
                t.markAsDone();
                System.out.println(t);
            }
        } else if (this.name.contains("list")) {
            int size = Tsundere.taskList.size();
            if (size != 0) {
                for (int i = 0; i < size; i++) {
                    Task t = Tsundere.taskList.get(i);
                    System.out.println((i + 1) + ". " + t.toString());
                }
            } else {
                System.out.println("Aren't you pretty free now? Go find something to do!");
            }
        } else if (this.name.contains("deadline")) {

            String deadline = this.name.split(" ", 2)[1];
            String[] x = deadline.split("/");

            Tsundere.taskList.add(new Deadline(x[0], x[1].split(" ",2 )[1]));
            getListSize();

        } else if (this.name.contains("event")) {

            String event = this.name.split(" ", 2)[1];
            String[] x = event.split("/");

            Tsundere.taskList.add(new Event(x[0], x[1].split(" ", 2)[1], x[2].split(" ", 2)[1]));
            getListSize();

        } else if (this.name.contains("todo")) {

            String todo = this.name.split(" ", 2)[1];

            Tsundere.taskList.add(new ToDo(todo));
            getListSize();

        } else {
            System.out.println("Don't talk to me! (,,>﹏<,,)");
        }

    }

    public void getListSize() {
        int size = Tsundere.taskList.size();
        System.out.println("Noted...");
        System.out.println(" " + Tsundere.taskList.get(size - 1).toString());
        System.out.println("Get to work! You still have " + size + " " + (size > 1 ? "tasks" : "task") + " left!");
    }

}
