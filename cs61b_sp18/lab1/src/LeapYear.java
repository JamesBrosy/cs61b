/** Class that determines whether or not a year is a leap year.
 *  @author YOUR NAME HERE
 */
public class LeapYear {

    private static final int LEAP_YEAR_CRITERIA_1 = 400;
    private static final int LEAP_YEAR_CRITERIA_2_1 = 4;
    private static final int LEAP_YEAR_CRITERIA_2_2 = 100;

    /**
     * determine if %year is a leap year.
     * @param year int
     */
    public static boolean isLeapYear(int year) {
        return year % LEAP_YEAR_CRITERIA_1 == 0 || (
                year % LEAP_YEAR_CRITERIA_2_1 == 0 && year % LEAP_YEAR_CRITERIA_2_2 != 0);
    }

    /** Calls isLeapYear to print correct statement.
     *  @param  year to be analyzed
     */
    private static void checkLeapYear(int year) {
        if (isLeapYear(year)) {
            System.out.printf("%d is a leap year.\n", year);
        } else {
            System.out.printf("%d is not a leap year.\n", year);
        }
    }

    /** Must be provided an integer as a command line argument ARGS. */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter command line arguments.");
            System.out.println("e.g. java Year 2000");
        }
        for (int i = 0; i < args.length; i++) {
            try {
                int year = Integer.parseInt(args[i]);
                checkLeapYear(year);
            } catch (NumberFormatException e) {
                System.out.printf("%s is not a valid number.\n", args[i]);
            }
        }
    }
}


