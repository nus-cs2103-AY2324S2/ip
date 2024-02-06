package chipchat.action;

import chipchat.exception.ChipchatException;

public enum CommandType {
    BYE, LIST, DELETE, MARK, UNMARK, TODO, DEADLINE, EVENT;

    public static CommandType matchTaskType(String str) throws ChipchatException {
        switch(str.toUpperCase()){
            case "T":
                return TODO;
            case "D":
                return DEADLINE;
            case "E":
                return EVENT;
            default:
                throw new ChipchatException("String doesn't match with any tasktype, ensure it's single letter");
        }
    }
}