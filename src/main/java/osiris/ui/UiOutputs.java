package osiris.ui;

/**
 * The UiOutputs class provides static constants for various output messages used in the UI.
 */
public class UiOutputs {

    /** The name of the Osiris chatbot. */
    public static final String NAME = "osiris";

    /** The separator used in UI outputs. */
    public static final String SEPERATOR = "----------------------------------------";

    /** ASCII art representation of the Osiris name. */
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

    /** Introduction message displayed to the user. */
    public static final String INTRODUCTIONS = String.format("Hello! I'm %s.\nWhat can I do for you?", UiOutputs.NAME);

    /** Prompt message for the user to interact with Osiris. */
    public static final String MESSAGEOSIRISPROMPT = "Message Osiris ... ";

    /** Goodbye message displayed to the user. */
    public static final String GOODBYES = "Bye. Hope to see you again soon!";

    /** Message displayed for unsupported commands. */
    public static final String UNSUPPORTEDCOMMANDSOUTPUT = "Sorry, but I am unable to comprehend the instruction at my "
            + "current development stage. Please enter something else.";
}
