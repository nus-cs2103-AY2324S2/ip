package squid.constants;

/**
 * The class of constants for Strings regarding date/time formatting.
 */
public class Format {
    public static final String DATE = "d MMM yyyy";
    public static final String TIME = "h:mma";

    public static final String[] DATES = {
        "dd-M-yyyy", "dd-MM-yyyy", "dd-MMM-yyyy", "dd-MMMM-yyyy", "dd-MMMMM-yyyy",
        "dd-M-yy", "dd-MM-yy", "dd-MMM-yy", "dd-MMMM-yy", "dd-MMMMM-yy",

        "dd M yyyy", "dd MM yyyy", "dd MMM yyyy", "dd MMMM yyyy", "dd MMMMM yyyy",
        "dd M yy", "dd MM yy", "dd MMM yy", "dd MMMM yy", "dd MMMMM yy",

        "dd/M/yyyy", "dd/MM/yyyy", "dd/MMM/yyyy", "dd/MMMM/yyyy", "dd/MMMMM/yyyy",
        "dd/M/yy", "dd/MM/yy", "dd/MMM/yy", "dd/MMMM/yy", "dd/MMMMM/yy",

        "dd.M.yyyy", "dd.MM.yyyy", "dd.MMM.yyyy", "dd.MMMM.yyyy", "dd.MMMMM.yyyy",
        "dd.M.yy", "dd.MM.yy", "dd.MMM.yy", "dd.MMMM.yy", "dd.MMMMM.yy",

        "dd,M,yyyy", "dd,MM,yyyy", "dd,MMM,yyyy", "dd,MMMM,yyyy", "dd,MMMMM,yyyy",
        "dd,M,yy", "dd,MM,yy", "dd,MMM,yy", "dd,MMMM,yy", "dd,MMMMM,yy",


        "M-dd-yyyy", "MM-dd-yyyy", "MMM-dd-yyyy", "MMMM-dd-yyyy", "MMMMM-dd-yyyy",
        "M-dd-yy", "MM-dd-yy", "MMM-dd-yy", "MMMM-dd-yy", "MMMMM-dd-yy",

        "M dd yyyy" , "MM dd yyyy", "MMM dd yyyy", "MMMM dd yyyy", "MMMMM dd yyyy",
        "M dd yy" , "MM dd yy", "MMM dd yy", "MMMM dd yy", "MMMMM dd yy",

        "M/dd/yyyy", "MM/dd/yyyy", "MMM/dd/yyyy", "MMMM/dd/yyyy", "MMMMM/dd/yyyy",
        "M/dd/yy", "MM/dd/yy", "MMM/dd/yy", "MMMM/dd/yy", "MMMMM/dd/yy",

        "M.dd.yyyy", "MM.dd.yyyy", "MMM.dd.yyyy", "MMMM.dd.yyyy", "MMMMM.dd.yyyy",
        "M.dd.yy", "MM.dd.yy", "MMM.dd.yy", "MMMM.dd.yy", "MMMMM.dd.yy",

        "M,dd,yyyy", "MM,dd,yyyy", "MMM,dd,yyyy", "MMMM,dd,yyyy", "MMMMM,dd,yyyy",
        "M,dd,yy", "MM,dd,yy", "MMM,dd,yy", "MMMM,dd,yy", "MMMMM,dd,yy",
    };

    public static final String[] TIMES = {
        "H:mm", "H:mm:[ss] a", "Ha", "H a", "HHmm", "H:mm a", "H:mma",
        "h:mm", "h:mm:[ss] a", "ha", "h a", "hmm", "h:mm a", "h:mma",
    };
}
