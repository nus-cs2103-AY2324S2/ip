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
            for (int i = 0; i < Tsundere.taskList.size(); i++) {
                Task t = Tsundere.taskList.get(i);
                System.out.println((i + 1) + ". " + t.toString());
            }
        } else {
            System.out.println("Got it: " + this.name);
            Tsundere.taskList.add(new Task(this.name));
        }

    }
}
