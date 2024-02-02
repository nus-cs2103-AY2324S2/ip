package hirwan;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Parser {
    static final int COMMAND_LIST = 1;
    static final int TASK_TODO = 2;
    static final int TASK_DEADLINE = 3;
    static final int TASK_EVENT  = 4;
    static final int COMMAND_MARK = 5;
    static final int COMMAND_UNMARK = 6;
    static final int COMMAND_DELETE = 7;
    static final int COMMAND_BYE = 8;
    public static int translate(String text) {

        if (text.equals("list")) {
            return COMMAND_LIST;
        } else if (text.startsWith("todo")) {
            return TASK_TODO;
        } else if (text.startsWith("deadline")) {
            return TASK_DEADLINE;
        } else if (text.startsWith("event")) {
            return TASK_EVENT;
        } else if (text.startsWith("mark")) {
            return COMMAND_MARK;
        } else if (text.startsWith("unmark")) {
            return COMMAND_UNMARK;
        } else if (text.startsWith("delete")) {
            return COMMAND_DELETE;
        } else if (text.equals("bye")) {
            return COMMAND_BYE;
        } else {
            return 9;
        }
    }
}