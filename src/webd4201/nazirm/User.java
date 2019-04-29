package webd4201.nazirm;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * User class that implements the college interface. The class is abstract
 * and made to be used as a parent for other classes
 * @author Musab nazir
 * @version 1.0 (2018 February 1st)
 * @since 1.0
 */
public abstract class User implements CollegeInterface {


    //region Fields and Attributes
    /**
     * shared variable minimum password length
     */
    protected static final int MINIMUM_PASSWORD_LENGTH = 8;
    /**
     * shared variable maximum password length
     */
    protected static final int MAXIMUM_PASSWORD_LENGTH = 50;

    /**
     * Default ID number for user
     */
    static final long DEFAULT_ID = 100454543L;

    /**
     * Default password for user
     */
    static final String DEFAULT_PASSWORD = "password";

    /**
     * Default first name for user
     */
    static final String DEFAULT_FIRST_NAME = "John";

    /**
     * Default last name for user
     */
    static final String DEFAULT_LAST_NAME = "Doe";

    /**
     * Default email for user
     */
    static final String DEFAULT_EMAIL_ADDRESS = "john.doe@dcmail.ca";

    /**
     * Default account status for user
     */
    static final boolean DEFAULT_ENABLED_STATUS = true;

    /**
     * Default account type for user
     */
    static final char DEFAULT_TYPE = 's';

    /**
     * Constant for ID length verification
     */
    public static final byte ID_NUMBER_LENGTH = 9;

    /**
     * Setting Date format to be Canada locale in medium length
     */
    public static final DateFormat DF = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CANADA);


    /**
     * instance variable to hold user id
     */
    private long id;

    /**
     * instance variable to hold user password
     */
    private String password;

    /**
     * instance variable to hold user first name
     */
    private String firstName;

    /**
     * instance variable to hold user last name
     */
    private String lastName;

    /**
     * instance variable to hold user's email address
     */
    private String emailAddress;

    /**
     * instance variable to hold user account type
     */
    private char type;

    /**
     * instance flag to enable or disable user account
     */
    private boolean enabled;

    /**
     * date object when user last logged in
     */
    private Date lastAccess;

    /**
     * date object when user account was created
     */
    private Date enrolDate;
    //endregion

    //region Constructors
    /**
     * parametrized constructor for User Class, that takes all attributes
     * @param id: The user id number as a long int
     * @param password: user's password as a string
     * @param firstName: user's first name as a string
     * @param lastName: user's last name as a string
     * @param emailAddress: user's email address as a string
     * @param type: user account type as a char (s for student e.g.)
     * @param enabled: for disabling and enabling accounts T/F
     * @param lastAccess: date the user last logged in
     * @param enrolDate: date the account was created
     * @throws InvalidNameException if the provided name is numeric
     * @throws InvalidException if any input fails validation
     */

    User(long id, String password, String firstName, String lastName,
         String emailAddress, char type, boolean enabled, Date lastAccess, Date enrolDate) throws
            InvalidException, InvalidNameException {
        setId(id);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setEmailAddress(emailAddress);
        setType(type);
        setEnabled(enabled);
        setEnrolDate(enrolDate);
        setLastAccess(lastAccess);
    }

    /**
     * Default constructor for User class
     * @throws InvalidNameException if the provided name is numeric
     * @throws InvalidException if any input fails validation
     */
    User() throws InvalidNameException, InvalidException {
        this(DEFAULT_ID,DEFAULT_PASSWORD,DEFAULT_FIRST_NAME,DEFAULT_LAST_NAME,DEFAULT_EMAIL_ADDRESS,
                DEFAULT_TYPE,DEFAULT_ENABLED_STATUS,new Date(), new Date());
    }
    //endregion

    //region Public Methods

    /**
     * Retrives user from the database using method in UserDA class
     * @param id user id as long int
     * @return a Student object
     * @throws NotFoundException if no student with that ID was found in the database
     */
    public static Student retrieve(long id) throws NotFoundException {

        Student aStudent;
        aStudent = UserDA.retrieve(id);
        return aStudent;
    }

    /**
     * Updates a user record in the database
     * @param aStudent a student object
     * @param con a connection object (for rollback)
     * @throws NotFoundException when no matching user is found in the database
     */
    public static void update(Student aStudent, Connection con) throws NotFoundException{
        UserDA.update(aStudent, con);
    }

    /**
     * deletes a user record. Calls the delete method UserDA class
     * @param aStudent a student object
     * @throws NotFoundException if no user with that ID was found in the database
     */
    public static void delete(Student aStudent) throws NotFoundException{
        UserDA.delete(aStudent);
    }

    /**
     * creates a user record in the database via the same method in UserDA class
     * @param aStudent a student object
     * @param con a connection object (for rollback)
     * @throws DuplicateException if a record with the same id already exists
     */
    public static void create(Student aStudent, Connection con) throws DuplicateException {

        UserDA.create(aStudent, con);
    }

    /**
     * toString override
     * @return a string summarizing the state of the object
     */
    @Override
    public String toString() {
        return "User Info For:\t" + id +
                "\n\tName:" + firstName + " " + lastName + "\n\t"+
                "Email Address: " + " " + emailAddress +"\n\t"+
                "Created On:" + enrolDate + "\n\t" +
                "Last Access:" + lastAccess;
    }

    /**
     * prints the toString output to console
     */
    public void dump(){
        System.out.println(this.toString());
    }

    /**
     * overloaded but not used in this class
     * @return empty string
     */
    public String getTypeForDisplay(){
        return "";
    }

    /**
     * Method to verify if the user ID is valid
     * @param id user id as a long int
     * @return a boolean depending on whether the id is valid or not
     */
    public static boolean verifyId(long id){
        boolean result = true;
        if(id < MINIMUM_ID_NUMBER || id > MAXIMUM_ID_NUMBER){
            result = false;
        }
        return result;
    }

    public static boolean verifyPassword(String pass){
        boolean result = true;
        if(pass.length() < MINIMUM_PASSWORD_LENGTH || pass.length() > MAXIMUM_PASSWORD_LENGTH){
            result = false;
        }
        return result;
    }

    /**
     * Helper function for email validation
     * @param email string of user email address
     * @return boolean depending on if email is valid or not
     */
    public static boolean isValidEmailAddress(String email){
        boolean result = true;
        try
        {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    //endregion

    //region Getters and Setters
    /**
     * Getter (Accessor) for user id
     * @return user id as a long int
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for ID of a user
     * @param id a long int for user ID
     * @throws InvalidException if the user ID is invalid
     */
    private void setId(long id) throws InvalidException {
        if( id >= MINIMUM_ID_NUMBER && id <= MAXIMUM_ID_NUMBER){
            this.id = id;
        }
        else
        {
            throw new InvalidException("ID must be between " + MINIMUM_ID_NUMBER + " and " + MAXIMUM_ID_NUMBER);
        }
    }

    /**
     * Gets the user's password
     * @return password as a string
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets the user's password. Throws an exception if the password
     * is too short (password less than 16) or long (password greater than 50)
     * @param password string that will hold user password
     */
    public final void setPassword(String password){
        if(password.length() < MINIMUM_PASSWORD_LENGTH || password.length() > MAXIMUM_PASSWORD_LENGTH){
            System.out.println("Error, invalid password");
        }
        else {
            this.password = password;
        }
    }

    /**
     * Getter (Accessor) for user first name
     * @return a string of user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for first Name. Throws exception if name is empty or a number
     * @param firstName Holds user's first name as a string
     * @throws InvalidNameException if the first name is a number
     */
    private void setFirstName(String firstName) throws InvalidNameException {
        if(firstName.isEmpty()){
            throw new InvalidNameException("First Name cannot be empty");
        }

        else {
            // could use regex /*firstName.matches("\\d+")*/
            try{
                Double.parseDouble(firstName);
                throw new InvalidNameException("First Name cannot be numeric");
            }
            catch (NumberFormatException e){
                this.firstName = firstName;
            }
        }

    }

    /**
     * Getter (Accessor) for user last name
     * @return a string of user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for last Name.Throws exception if name is empty or a number
     * @param lastName User's last name as a string
     * @throws InvalidNameException If last name is a number
     */
    private void setLastName(String lastName)throws InvalidNameException {
        if(lastName.isEmpty()){
            throw new InvalidNameException("Last Name cannot be empty");
        }
        else {
            // could use regex /*lastName.matches("\\d+")*/
            try{
                Double.parseDouble(lastName);
                throw new InvalidNameException("Last Name cannot be numeric");
            }
            catch (NumberFormatException e){
                this.lastName = lastName;
            }
        }
    }

    /**
     * Getter (Accessor) for user's email address
     * @return a string containing user's email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Setter (mutator) for user's email address
     * @param emailAddress string that holds user's email address
     */
    private void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Getter (Accessor) for user's last access date
     * @return a date object
     */
    public Date getLastAccess() {
        return lastAccess;
    }

    /**
     * Setter (mutator) for user's last access
     * @param lastAccess date object to hold the time user last logged in
     */
    private void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    /**
     * Getter (Accessor) for user's creation date
     * @return a date object
     */
    public Date getEnrolDate() {
        return enrolDate;
    }

    /**
     * Setter (mutator) for user's creation access
     * @param enrolDate date object to store the user's account creation date
     */
    private void setEnrolDate(Date enrolDate) {
        this.enrolDate = enrolDate;
    }


    /**
     * Gets instance flag to enable or disable user account.
     *
     * @return Value of instance flag to enable or disable user account.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets new instance flag to enable or disable user account.
     *
     * @param enabled New value of instance flag to enable or disable user account.
     */
    private void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Gets instance variable to hold user account type.
     *
     * @return Value of instance variable to hold user account type.
     */
    public char getType() {
        return type;
    }

    /**
     * Sets new instance variable to hold user account type.
     *
     * @param type New value of instance variable to hold user account type.
     */
    private void setType(char type) {
        this.type = type;
    }
    //endregion

}
