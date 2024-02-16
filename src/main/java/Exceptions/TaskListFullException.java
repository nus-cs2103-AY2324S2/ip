package Exceptions;

import Exceptions.DudeException;

public class TaskListFullException extends DudeException {
    //Used to indicate that the task list is full
    public TaskListFullException(String message) {
        super(message);
    }
}