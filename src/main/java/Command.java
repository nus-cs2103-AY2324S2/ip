/**
 * Enumerates the available commands and their respective execution methods.
 */
public enum Command {
    BYE {
        @Override
        public void execute(String userInput) {
            ChatBro.isRunning = false;
            String tasksToSave = "";
            for (int i = 1; i <= 100; i++) {
                if (TaskManager.getList().get(i) == null) {
                    break;
                }
                tasksToSave += TaskManager.getList().get(i).toStorageFormat() + "\n";
            }
            Database.saveToFile(tasksToSave);
            Ui.printBye();
            Parser.closeScanner();
        }
    },
    LIST {
        @Override
        public void execute(String userInput) {
            Ui.printList();
        }
    },
    MARK {
        @Override
        public void execute(String userInput) {
            try {
                int taskNum = Integer.parseInt(userInput.split(" ")[1]);
                if (taskNum < 1 || taskNum > 100) {
                    Ui.printMessage("You can only mark tasks within the range of 1 to 100, bro.");
                    return;
                } else if (TaskManager.getList().get(taskNum) == null) {
                    Ui.printDoesNotExistError();
                    return;
                }
                Task task = TaskManager.getList().get(taskNum);
                task.markAsDone();
                Ui.printMarkMessage(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printFormatError("mark <task number>");
            } catch (NumberFormatException e) {
                Ui.printMessage("Please enter a valid task number, bro.");
            }
        }
    },
    UNMARK {
        @Override
        public void execute(String userInput) {
            try {
                int taskNum = Integer.parseInt(userInput.split(" ")[1]);
                if (taskNum < 1 || taskNum > 100) {
                    Ui.printMessage("You can only unmark tasks within the range of 1 to 100, bro.");
                    return;
                } else if (TaskManager.getList().get(taskNum) == null) {
                    Ui.printDoesNotExistError();
                    return;
                }
                Task task = TaskManager.getList().get(taskNum);
                task.markAsUndone();
                Ui.printUnmarkMessage(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printFormatError("unmark <task number>");
            } catch (NumberFormatException e) {
                Ui.printMessage("Please enter a valid task number, bro.");
            }
        }
    },
    DELETE {
        @Override
        public void execute(String userInput) {
            try {
                int taskNum = Integer.parseInt(userInput.split(" ")[1]);
                Task task = TaskManager.getTask(taskNum);
                if (task == null) {
                    Ui.printDoesNotExistError();
                    return;
                }
                TaskManager.deleteTask(taskNum);
                Ui.printDeleteMessage(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printFormatError("delete <task number>");
            } catch (NumberFormatException e) {
                Ui.printMessage("Please enter a valid task number, bro.");
            }
        }
    },
    ADD_TODO {
        @Override
        public void execute(String userInput) {
            String[] todoSplit = userInput.split("todo ");
            try {
                String todoName = todoSplit[1];
                if (todoName.trim().isEmpty()) { // Empty task description
                    Ui.printMessage("Hey bro, task description cannot be empty.");
                    return;
                }
                Task todo = new ToDo(todoName);
                TaskManager.addTask(todo);
                Ui.printAddMessage(todo);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printFormatError("todo <task description>");
            }
        }
    },
    ADD_DEADLINE {
        @Override
        public void execute(String userInput) {
            String[] deadlineSplit = userInput.split(" /by ");
            try {
                String deadlineName = deadlineSplit[0].substring(9); // 9 is the length of "deadline "
                if (deadlineName.trim().isEmpty()) { // Empty task description (whitespace)
                    Ui.printMessage("Hey bro, task description cannot be empty.");
                    return;
                }
                if (deadlineSplit[1].trim().isEmpty()) { // Empty deadline (whitespace)
                    Ui.printMessage("Hey bro, deadline cannot be empty.");
                    return;
                }
                Task deadline = new Deadline(deadlineName, deadlineSplit[1]);
                TaskManager.addTask(deadline);
                Ui.printAddMessage(deadline);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                Ui.printFormatError("deadline <task description> /by <due date>");
            }
        }
    },
    ADD_EVENT {
        @Override
        public void execute(String userInput) {
            String[] eventFromSplit = userInput.split(" /from ");
            try {
                String eventName = eventFromSplit[0].substring(6); // 6 is the length of "event "
                String[] eventToSplit = eventFromSplit[1].split(" /to "); // ETS[0] is 'from', ETS[1] is 'to'
                if (eventName.trim().isEmpty()) { // Empty task description (whitespace)
                    Ui.printMessage("Hey bro, task description cannot be empty.");
                    return;
                }
                if (eventToSplit[0].trim().isEmpty()) { // Empty start time (whitespace)
                    Ui.printMessage("Hey bro, the start time cannot be empty.");
                    return;
                }
                if (eventToSplit[1].trim().isEmpty()) { // Empty end time (whitespace)
                    Ui.printMessage("Hey bro, the end time cannot be empty.");
                    return;
                }
                Task event = new Event(eventName, eventToSplit[0], eventToSplit[1]);
                TaskManager.addTask(event);
                Ui.printAddMessage(event);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                Ui.printFormatError("event <task description> /from <start time> /to <end time>");
            }
        }
    };
    public void execute(String userInput) {
        // Empty method
    }
}
