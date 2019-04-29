package webd4201.nazirm;

import java.util.Date;

/**
 * Faculty class that inherits from the User class
 * @author musab nazir
 * @version 1.0 (2018 February 1st)
 * @since 1.0
 */

public class Faculty extends User {

    /**
     * Default school code for faculty objects
     */
    private static final String DEFAULT_SCHOOL_CODE = "SET";

    /**
     * Default school description for faculty objects
     */
    private static  final String DEFAULT_SCHOOL_DESCRIPTION = "School of Engineering & Technology";

    /**
     * Default office for faculty objects
     */
    private static final String DEFAULT_OFFICE = "H-140";


    /**
     * Default phone extension for faculty objects
     */
    private static final int DEFAULT_PHONE_EXTENSION = 1234;


    /**
     * instance variable to hold school code for faculty objects
     */
    private String schoolCode;

    /**
     * instance variable to hold school description for faculty objects
     */
    private String schoolDescription;

    /**
     * instance variable to hold office location for faculty objects
     */
    private String office;

    /**
     * instance variable to hold phone extension for faculty objects
     */
    private int phoneExt;

    // Constructors

    /**
     * Default constructor that calls the parent constructor and manually sets all attributes to default values
     * @throws InvalidNameException if Name value provided is invalid (numeric)
     * @throws InvalidException if user ID is not in acceptable range
     */
    public Faculty() throws InvalidNameException, InvalidException {
        super();
        setSchoolCode(DEFAULT_SCHOOL_CODE);
        setSchoolDescription(DEFAULT_SCHOOL_DESCRIPTION);
        setOffice(DEFAULT_OFFICE);
        setPhoneExt(DEFAULT_PHONE_EXTENSION);
    }

    /**
     * Parametrized constructor that takes values for all attributes. It uses the paramentrized constructor in the parent
     * class and then manually sets the remaining attributes
     * @param id user ID as a long int
     * @param password user password as a string
     * @param firstName user's first name as a string
     * @param lastName user's last name as a string
     * @param emailAddress user's email address as a string
     * @param type user's account type as a char
     * @param enabled boolean flag for whether then account is active or not
     * @param lastAccess date object of when the user last logged in
     * @param enrolDate date object of when the user was created
     * @param schoolCode school code as a string
     * @param schoolDescription full name for the school as a string
     * @param office office number as a string
     * @param phoneExt phone extension as an integer
     * @throws InvalidNameException if either first name or last name is numeric
     * @throws InvalidException if any parameter fails validation
     */
    public Faculty(long id, String password, String firstName, String lastName, String emailAddress, char type,
                   boolean enabled, Date lastAccess, Date enrolDate, String schoolCode, String schoolDescription,
                   String office, int phoneExt) throws InvalidNameException, InvalidException {
        super(id, password, firstName, lastName, emailAddress, type, enabled, lastAccess, enrolDate);
        this.schoolCode = schoolCode;
        this.schoolDescription = schoolDescription;
        this.office = office;
        this.phoneExt = phoneExt;
    }

    /**
     * override for getTypeForDisplay
     * @return human readable string of the type of account
     */
    @Override
    public String getTypeForDisplay() {
        return "Faculty";
    }

    /**
     * override for toString
     * @return formatted string that summarizes the object state
     */
    @Override
    public String toString() {
        String output = super.toString();
        output = output.replaceAll("User",this.getTypeForDisplay());
        output += "\n\t" + getSchoolDescription() +" (" + getSchoolCode() + ")\n\t" + "Office: " + getOffice() + "\n\t"
                + this.PHONE_NUMBER + " x" + getPhoneExt();
        return output;
    }

    /*
      Sets and Gets
     */

    /**
     * gets the school code
     * @return school code as a string
     */
    private String getSchoolCode() {
        return schoolCode;
    }

    /**
     * Sets school code
     * @param schoolCode school code as a string
     */
    private void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    /**
     * returns full name for the school
     * @return school description
     */
    private String getSchoolDescription() {
        return schoolDescription;
    }

    /**
     *  sets the full name of the school
     * @param schoolDescription full name of the school as a string
     */
    private void setSchoolDescription(String schoolDescription) {
        this.schoolDescription = schoolDescription;
    }

    /**
     * gets the office number of the faculty object
     * @return string of the office number
     */
    private String getOffice() {
        return office;
    }

    /**
     * sets the office number for a faculty object
     * @param office office location as a string
     */
    private void setOffice(String office) {
        this.office = office;
    }

    /**
     * gets the phone ext of the faculty member
     * @return phone ext as an int
     */
    private int getPhoneExt() {
        return phoneExt;
    }

    /**
     * sets the phone extension for the faculty member
     * @param phoneExt phone extension as an integer
     */
    private void setPhoneExt(int phoneExt) {
        this.phoneExt = phoneExt;
    }
}
