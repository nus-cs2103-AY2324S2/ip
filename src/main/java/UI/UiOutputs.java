package UI;

import java.sql.Statement;

public class UiOutputs {

    public static final String NAME = "Osiris";

    public static final String SEPERATOR = "----------------------------------------";

    public static final String[] NAMEASCIIArt = {
            "    ,o888888o.       d888888o.    8 8888 8 888888888o.    8 8888    d888888o.   ",
            " . 8888     `88.   .`8888:' `88.  8 8888 8 8888    `88.   8 8888  .`8888:' `88. ",
            ",8 8888       `8b  8.`8888.   Y8  8 8888 8 8888     `88   8 8888  8.`8888.   Y8 ",
            "88 8888        `8b `8.`8888.      8 8888 8 8888     ,88   8 8888  `8.`8888.     ",
            "88 8888         88  `8.`8888.     8 8888 8 8888.   ,88'   8 8888   `8.`8888.    ",
            "88 8888         88   `8.`8888.    8 8888 8 888888888P'    8 8888    `8.`8888.   ",
            "88 8888        ,8P    `8.`8888.   8 8888 8 8888`8b        8 8888     `8.`8888.  ",
            "`8 8888       ,8P 8b   `8.`8888.  8 8888 8 8888 `8b.      8 8888 8b   `8.`8888. ",
            " ` 8888     ,88'  `8b.  ;8.`8888  8 8888 8 8888   `8b.    8 8888 `8b.  ;8.`8888 ",
            "    `8888888P'     `Y8888P ,88P'  8 8888 8 8888     `88.  8 8888  `Y8888P ,88P' "
    };

    public static final String INTRODUCTIONS = String.format("Hello! I'm %s.\nWhat can I do for you?", UiOutputs.NAME);

    public static final String GOODBYES = "Bye. Hope to see you again soon!";

    public static final String UNSUPPORTEDCOMMANDSOUTPUT = "Sorry, but I am unable to comprehend the instruction at my current development stage. Please enter something else.";
}
