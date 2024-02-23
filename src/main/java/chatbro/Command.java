package chatbro;

/**
 * Enumerates the available commands and their respective execution methods.
 */
public enum Command {
    BYE {
        @Override
        public String execute(String userInput) {
            String tasksToSave = "";
            for (int i = 1; i <= 100; i++) {
                if (TaskManager.getList().get(i) == null) {
                    break;
                }
                tasksToSave += TaskManager.getList().get(i).toStorageFormat() + "\n";
            }
            Database.saveToFile(tasksToSave);
            return Ui.byeMessage();
        }
    },
    LIST {
        @Override
        public String execute(String userInput) {
            return Ui.listMessage();
        }
    },
    MARK {
        @Override
        public String execute(String userInput) {
            try {
                int taskNum = Integer.parseInt(userInput.split(" ")[1]);
                if (taskNum < 1 || taskNum > 100) {
                    return "You can only mark tasks within the range of 1 to 100, bro.";
                } else if (TaskManager.getList().get(taskNum) == null) {
                    return Ui.doesNotExistMessage();
                }
                Task task = TaskManager.getList().get(taskNum);
                task.markAsDone();
                return Ui.markMessage(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.formatErrorMessage("mark <task number>");
            } catch (NumberFormatException e) {
                return "Please enter a valid task number, bro.";
            }
        }
    },
    UNMARK {
        @Override
        public String execute(String userInput) {
            try {
                int taskNum = Integer.parseInt(userInput.split(" ")[1]);
                if (taskNum < 1 || taskNum > 100) {
                    return "You can only unmark tasks within the range of 1 to 100, bro.";
                } else if (TaskManager.getList().get(taskNum) == null) {
                    return Ui.doesNotExistMessage();
                }
                Task task = TaskManager.getList().get(taskNum);
                task.markAsUndone();
                return Ui.unmarkMessage(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.formatErrorMessage("unmark <task number>");
            } catch (NumberFormatException e) {
                return "Please enter a valid task number, bro.";
            }
        }
    },
    DELETE {
        @Override
        public String execute(String userInput) {
            try {
                int taskNum = Integer.parseInt(userInput.split(" ")[1]);
                Task task = TaskManager.getTask(taskNum);
                if (task == null) {
                    return Ui.doesNotExistMessage();
                }
                String deleteTaskOutput = TaskManager.deleteTask(taskNum);
                if (deleteTaskOutput.isEmpty()) {
                    return Ui.deleteMessage(task);
                } else {
                    return deleteTaskOutput;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.formatErrorMessage("delete <task number>");
            } catch (NumberFormatException e) {
                return "Please enter a valid task number, bro.";
            }
        }
    },
    ADD_TODO {
        @Override
        public String execute(String userInput) {
            String[] todoSplit = userInput.split("todo ");
            try {
                String todoName = todoSplit[1];
                if (todoName.trim().isEmpty()) { // Empty task description
                    return Ui.taskDescEmptyMessage();
                }
                Task todo = new ToDo(todoName);
                String addTaskOutput = TaskManager.addTask(todo);
                if (addTaskOutput.isEmpty()) {
                    return Ui.addMessage(todo);
                } else {
                    return addTaskOutput;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.formatErrorMessage("todo <task description>");
            }
        }
    },
    ADD_DEADLINE {
        @Override
        public String execute(String userInput) {
            String[] deadlineSplit = userInput.split(" /by ");
            try {
                String deadlineName = deadlineSplit[0].substring(9); // 9 is the length of "deadline "
                String deadlineTime = deadlineSplit[1];
                if (deadlineName.trim().isEmpty()) { // Empty task description (whitespace)
                    return Ui.taskDescEmptyMessage();
                }
                if (deadlineTime.trim().isEmpty()) { // Empty deadlineTime (whitespace)
                    return "Hey bro, deadline cannot be empty.";
                }
                try {
                    Task deadline = new Deadline(deadlineName,
                            DateTimeUtility.parseDateTime(deadlineTime));
                    String addTaskOutput = TaskManager.addTask(deadline);
                    if (addTaskOutput.isEmpty()) {
                        return Ui.addMessage(deadline);
                    } else {
                        return addTaskOutput;
                    }
                } catch (InvalidDateTimeException e) {
                    return e.getMessage();
                }
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                return Ui.formatErrorMessage("deadline <task description> /by <due date>");
            }
        }
    },
    ADD_EVENT {
        @Override
        public String execute(String userInput) {
            String[] eventFromSplit = userInput.split(" /from ");
            try {
                String eventName = eventFromSplit[0].substring(6); // 6 is the length of "event "
                String[] eventToSplit = eventFromSplit[1].split(" /to ");
                String startTime = eventToSplit[0];
                String endTime = eventToSplit[1];
                if (eventName.trim().isEmpty()) { // Empty task description (whitespace)
                    return Ui.taskDescEmptyMessage();
                }
                if (startTime.trim().isEmpty()) { // Empty start time (whitespace)
                    return Ui.startTimeEmptyMessage();
                }
                if (endTime.trim().isEmpty()) { // Empty end time (whitespace)
                    return Ui.endTimeEmptyMessage();
                }
                try {
                    Task event = new Event(eventName,
                            DateTimeUtility.parseDateTime(startTime),
                            DateTimeUtility.parseDateTime(endTime));
                    String addTaskOutput = TaskManager.addTask(event);
                    if (addTaskOutput.isEmpty()) {
                        return Ui.addMessage(event);
                    } else {
                        return addTaskOutput;
                    }
                } catch (InvalidDateTimeException e) {
                    return e.getMessage();
                }
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                return Ui.formatErrorMessage("event <task description> /from <start time> /to <end time>");
            }
        }
    },
    ADD_INTERVAL_DEADLINE {
        @Override
        public String execute(String userInput) {
            String[] intervalFromSplit = userInput.split(" /from ");
            try {
                String intervalName = intervalFromSplit[0].substring(9); // 9 is the length of "interval "
                String[] intervalToSplit = intervalFromSplit[1].split(" /to ");
                String startTime = intervalToSplit[0];
                String endTime = intervalToSplit[1];
                if (intervalName.trim().isEmpty()) { // Empty task description (whitespace)
                    return Ui.taskDescEmptyMessage();
                }
                if (startTime.trim().isEmpty()) { // Empty start time (whitespace)
                    return Ui.startTimeEmptyMessage();
                }
                if (endTime.trim().isEmpty()) { // Empty end time (whitespace)
                    return Ui.endTimeEmptyMessage();
                }
                try {
                    Task interval = new IntervalDeadline(intervalName,
                            DateTimeUtility.parseDateTime(startTime),
                            DateTimeUtility.parseDateTime(endTime));
                    String addTaskOutput = TaskManager.addTask(interval);
                    if (addTaskOutput.isEmpty()) {
                        return Ui.addMessage(interval);
                    } else {
                        return addTaskOutput;
                    }
                } catch (InvalidDateTimeException e) {
                    return e.getMessage();
                }
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                return Ui.formatErrorMessage(
                    "interval <task description> /from <start time of interval> /to <end time of interval>");
            }
        }
    },
    HELP {
        @Override
        public String execute(String userInput) {
            return Ui.helpMessage();
        }
    },
    FIND {
        @Override
        public String execute(String userInput) {
            try {
                String keyword = userInput.substring(5);
                return Finder.findTask(keyword);
            } catch (StringIndexOutOfBoundsException e) {
                return Ui.formatErrorMessage("find <keyword>");
            }
        }
    },
    PROTEIN {
        @Override
        public String execute(String userInput) {
            return "Woah a lot of protein sir!";
        }
    };

    /**
     * Template method to execute command, to be overridden by each command accordingly.
     * @param userInput User input.
     * @return Empty string by default (to be overridden by each command)
     */
    public String execute(String userInput) {
        // Empty method - to be overridden by each command
        return "";
    }
}
