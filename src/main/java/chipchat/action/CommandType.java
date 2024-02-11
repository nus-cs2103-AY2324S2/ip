package chipchat.action;

import chipchat.exception.ChipchatException;

/**
 * Represents the types of commands/actions that users can instruct to the application
 */
public enum CommandType {
    BYE, LIST, DELETE, MARK, UNMARK, FIND, TODO, DEADLINE, EVENT;
}