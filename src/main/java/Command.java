import java.time.format.DateTimeParseException;

public enum Command {
    List, Mark, Unmark, Delete, Todo, Deadline, Event, Bye, Invalid;

    public static final boolean handleCommand(Command command, String detail, Ui ui, TaskList taskList, Storage storage){
        boolean isDone = false;
        try {
            switch (command){
            case List:
                if (taskList.size() == 0){
                    throw new ToothlessException("Human list is empty like my tummy right now :/");
                }
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    ui.showTask(taskList.getTask(i), i);
                }
                break;
            case Mark:{
                int taskIndex = getTaskIndex(detail);
                if (taskIndex >= taskList.size() || taskIndex < 0 || detail.equals("")){
                    throw new ToothlessException("Human trying to mark nothing ^O^. Foolish");
                }
                Task t = taskList.getTask(taskIndex);
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                ui.showTask(t, taskIndex);
                break;
            }
            case Unmark:{
                int taskIndex = getTaskIndex(detail);
                if (taskIndex >= taskList.size() || taskIndex < 0 || detail.equals("")){
                    throw new ToothlessException("Human trying to unmark nothing ^O^. Silly");
                }
                Task t = taskList.getTask(taskIndex);
                t.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                ui.showTask(t, taskIndex);
                break;
            }
            case Delete:{
                int taskIndex = getTaskIndex(detail);
                if (taskIndex >= taskList.size() || taskIndex < 0 || detail.equals("")){
                    throw new ToothlessException("Human trying to delete nothing ^O^. Absurd");
                }
                Task t = taskList.getTask(taskIndex);
                taskList.removeTask(taskIndex);
                System.out.println("Noted. I've removed this task:");
                ui.showTask(t, taskIndex);
                System.out.format("Now you have %d tasks in the list.\n", taskList.size());
                break;
            }
            case Todo:
            case Deadline:
            case Event:{
                Task t = createTask(command, detail);
                taskList.addTask(t);
                System.out.println("Got it. I've added this task:");
                ui.showTask(t, taskList.size() - 1);
                System.out.format("Now you have %d tasks in the list.\n", taskList.size());
                break;
            }
            case Bye:
                isDone = true;
                ui.showFarewell();
                storage.writeTasks(taskList);
                break;
            case Invalid:
                throw new ToothlessException("Me dragon, no understand this action :P");
            }
        } catch (ToothlessException e){
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e){
            System.out.println("Type in date and time in this format: yyyy-mm-ddTHh:mm");
        } finally {
            ui.showLine();
            return isDone;
        }
    }

    public static final Task createTask(Command c, String detail) throws ToothlessException, DateTimeParseException{
        if(detail.equals("")) {
            throw new ToothlessException("Human task no name :(");
        }
        switch (c) {
        case Todo:{
            return new Todo(detail);
        }
        case Deadline:{
            int dateIndex = detail.indexOf("/by");
            if (dateIndex == -1){
                throw new ToothlessException("Human deadline no deadline @_@");
            }
            String description = detail.substring(0, dateIndex - 1);
            String date = detail.substring(dateIndex + 4);
            return new Deadline(description, date);
        }
        case Event: {
            int date1Index = detail.indexOf("/from");
            if (date1Index == -1){
                throw new ToothlessException("Human event no start date D:");
            }
            String description = detail.substring(0, date1Index - 1);
            detail = detail.substring(date1Index + 6);
            int date2Index = detail.indexOf("/to");
            if (date2Index == -1){
                throw new ToothlessException("Human event no end date D:");
            }
            String startDate = detail.substring(0, date2Index - 1);
            String endDate = detail.substring(date2Index + 4);
            return new Event(description, startDate, endDate);
        }
        default:
            throw new ToothlessException("Can't create task!");
        }
    }

    public static int getTaskIndex(String detail) throws ToothlessException{
        try {
            int taskIndex = Integer.valueOf(detail);
            return taskIndex - 1;
        } catch (NumberFormatException e){
            throw new ToothlessException("Number put is not number.\nPlease put real number ._.");
        }
    }
}
