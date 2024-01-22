public class Action {

    private final String name;
    public Action(String name) {
        this.name = name;
    }

    public void execute() throws GeneralException {

        if (this.name.contains("unmark")) {
            if (Tsundere.taskList.isEmpty()) throw new GeneralException("What you tryna unmark huh?");
            try {
                Task t = Tsundere.taskList.get(Integer.parseInt(this.name.substring(7, 8)) - 1);
                if (t.getStatusIcon().equals(" ")) {
                    System.out.println("You haven't even started this task dummy!");
                } else {
                    t.unMark();
                    System.out.println(t);
                }
            } catch (NumberFormatException e) {
                throw new GeneralException("Can't you spell?");
            } catch (IndexOutOfBoundsException e ) {
                throw new GeneralException("Im pretty sure that's the wrong task number! Check again!");
            }
        } else if (this.name.contains("mark")) {
            if (Tsundere.taskList.isEmpty()) throw new GeneralException("What you tryna mark huh?");
            try {
                Task t = Tsundere.taskList.get(Integer.parseInt(this.name.substring(5, 6)) - 1);
                if (t.getStatusIcon().equals("X")) {
                    System.out.println("You already finished this!");
                } else {
                    t.markAsDone();
                    System.out.println(t);
                }
            } catch (NumberFormatException e) {
                throw new GeneralException("Can't you spell?");
            } catch (IndexOutOfBoundsException e ) {
                throw new GeneralException("Im pretty sure that's the wrong task number! Check again!");
            }

        } else if (this.name.contains("delete")) {
            if (Tsundere.taskList.isEmpty()) throw new GeneralException("What you tryna delete huh?");
            try {
                Tsundere.taskList.remove(Integer.parseInt(this.name.substring(7, 8)) - 1);
                getListSize("deleted");
            } catch (NumberFormatException e) {
                throw new GeneralException("Can't you spell?");
            } catch (IndexOutOfBoundsException e ) {
                throw new GeneralException("Im pretty sure that's the wrong task number! Check again!");
            }

        } else if (this.name.contains("list")) {
            int size = Tsundere.taskList.size();
            if (Tsundere.taskList.isEmpty()) throw new GeneralException("Aren't you pretty free now? " +
                                                                        "Go find something to do!");
            for (int i = 0; i < size; i++) {
                Task t = Tsundere.taskList.get(i);
                System.out.println((i + 1) + ". " + t.toString());
            }
        } else if (this.name.contains("deadline")) {

            try {
                String deadline = this.name.split(" ", 2)[1];
                String[] x = deadline.split("/");

                Tsundere.taskList.add(new Deadline(x[0], x[1].split(" ", 2)[1]));
                getListSize("added");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new GeneralException("Can't you even remember the proper format for this? \n" +
                                            "deadline [task] /by [date]");
            }

        } else if (this.name.contains("event")) {
            try {
                String event = this.name.split(" ", 2)[1];
                String[] x = event.split("/");

                Tsundere.taskList.add(new Event(x[0], x[1].split(" ", 2)[1], x[2].split(" ", 2)[1]));
                getListSize("added");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new GeneralException("Can't you even remember the proper format for this? \n" +
                                            "event [task] /from [date] /to [date]");
            }

        } else if (this.name.contains("todo")) {

            try {
                String todo = this.name.split(" ", 2)[1];

                Tsundere.taskList.add(new ToDo(todo));
                getListSize("added");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new GeneralException("Can't you even remember the proper format for this? \n" +
                                            "todo [task]");
            }
        } else {
            System.out.println("Don't talk to me!\nGive me proper instructions!");
        }

    }

    public void getListSize(String str) {
        int size = Tsundere.taskList.size();
        System.out.println("Noted...");
        System.out.println(" " + Tsundere.taskList.get(size - 1).toString() + " has been " + str);
        System.out.println("Get to work! You still have " + size + " " + (size > 1 ? "tasks" : "task") + " left!");
    }

}
