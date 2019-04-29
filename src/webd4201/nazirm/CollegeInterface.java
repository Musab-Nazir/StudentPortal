package webd4201.nazirm;

/**
 * Interface that will store information about Durham College
 * @author Musab Nazir
 * @version 1.0 (2019, Jan 11)
 * @since 1.0
 */

interface CollegeInterface {
    /**
     * The name of the educational institute
     */
    String COLLEGE_NAME = "Durham College";

    /**
     * Phone Number of the educational Institute
     */
    String PHONE_NUMBER = "(905)721-2000";

    /**
     * Minimum number for a student ID at the educational Institute
     */
    long MINIMUM_ID_NUMBER = 100000000L;

    /**
     * Maximum number for a student ID at the educational Institute
     */
    long MAXIMUM_ID_NUMBER = 999999999L;

    /**
     * Method to get the user type
     * @return a string for user type
     */
    String getTypeForDisplay();
}
