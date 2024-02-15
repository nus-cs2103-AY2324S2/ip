package duke.fileUtils;

/**
 * The FilePaths class contains constant file paths used in the Duke application.
 * It provides paths for various resources such as logo, help text, graphical elements,
 * and the file path for saving tasks.
 * <p>
 * These paths are used by other classes to locate and access corresponding resources.
 * </p>
 * <p>
 * Note: The paths are relative to the classpath and are assumed to be located in the 'src/main/resources' directory.
 * </p>
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 */
public final class FilePaths {

    /** The file path for the logo graphic. */
    public static final String LOGO_PATH = "graphics/logo.txt";

    /** The file path for the help text. */
    public static final String HELP_PATH = "graphics/help.txt";

    /** The file path for the horizontal line graphic. */
    public static final String HORIZONTAL_LINE_PATH = "graphics/horizontal_line.txt";

    /** The file path for saving tasks. */
    public static final String TASKS_SAVE_FILE_PATH = "./src/main/data/tasks.txt";

    /** The file path for the error graphic. */
    public static final String ERROR_GRAPHIC_PATH = "graphics/error_graphic.txt";

}
