package webd4201.nazirm;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Student class that inherits from the User class
 * @author musab nazir
 * @version 1.0 (2018 February 1st)
 * @since 1.0
 */

public class Student extends User {

    //region Fields and Attributes
    /**
     * This is a shared default program code
     */
    protected static final String DEFAULT_PROGRAM_CODE = "UNDC";
    /**
     * shared default program description
     */
    protected static  final String DEFAULT_PROGRAM_DESCRIPTION = "Undeclared";
    /**
     * default shared year attribute
     */
    protected static final int DEFAULT_YEAR = 1;

    /**
     * private attribute of student programCode
     */
    private String programCode;

    /**
     * Private program description
     */
    private String programDescription;

    /**
     * A private list of student marks
     */
    private Vector<Mark> marks;

    /**
     * private attribute of the student's year
     */
    private int year;
    //endregion

    //region Constructors
    /**
     * Parametrized constructor that requires every parameter to be provided
     * @param id user ID as a long int
     * @param password user password as a string
     * @param firstName user's first name as a string
     * @param lastName user's last name as a string
     * @param emailAddress user's email address as a string
     * @param type user's account type as a char
     * @param enabled boolean flag for whether then account is active or not
     * @param lastAccess date object of when the user last logged in
     * @param enrolDate date object of when the user was created
     * @param programCode student's program code as a string
     * @param programDescription student's program name as a string
     * @param marks student's marks as a vector
     * @param year which year the student is currently as integer
     * @throws InvalidNameException if either first name or last name is numeric
     * @throws InvalidException if any parameter fails validation
     */
    private Student(long id, String password, String firstName, String lastName, String emailAddress, char type,
                    boolean enabled, Date lastAccess, Date enrolDate, String programCode, String programDescription,
                    Vector marks, int year) throws InvalidNameException, InvalidException {
        super(id, password, firstName, lastName, emailAddress, type, enabled, lastAccess, enrolDate);
        setProgramCode(programCode);
        setProgramDescription(programDescription);
        setMarks(marks);
        setYear(year);
    }

    /**
     * Parametrized constructor that has everything BUT the marks provided
     * @param id user ID as a long int
     * @param password user password as a string
     * @param firstName user's first name as a string
     * @param lastName user's last name as a string
     * @param emailAddress user's email address as a string
     * @param type user's account type as a char
     * @param enabled boolean flag for whether then account is active or not
     * @param lastAccess date object of when the user last logged in
     * @param enrolDate date object of when the user was created
     * @param programCode student's program code as a string
     * @param programDescription student's program name as a string
     * @param year which year the student is currently as integer
     * @throws InvalidNameException if either first name or last name is numeric
     * @throws InvalidException if any parameter fails validation
     */
    public Student(long id, String password, String firstName, String lastName, String emailAddress, char type,
                   boolean enabled, Date lastAccess, Date enrolDate, String programCode, String programDescription,
                   int year) throws InvalidNameException, InvalidException {
        this(id, password, firstName, lastName, emailAddress, type, enabled, lastAccess, enrolDate,programCode,
                programDescription,new Vector<Mark>(), year);
    }

    /**
     * Default constructor
     * @throws InvalidNameException if either first name or last name is numeric
     * @throws InvalidException if any parameter fails validation
     */
    public Student() throws InvalidNameException, InvalidException {
        this(DEFAULT_ID,DEFAULT_PASSWORD,DEFAULT_FIRST_NAME,DEFAULT_LAST_NAME,DEFAULT_EMAIL_ADDRESS,DEFAULT_TYPE,
                DEFAULT_ENABLED_STATUS, new Date(), new Date(),DEFAULT_PROGRAM_CODE,DEFAULT_PROGRAM_DESCRIPTION,
                DEFAULT_YEAR);
    }
    //endregion

    //region Public Methods
    /**
     * toString Override for a student object
     */
    @Override
    public String toString() {
        String yearSuffix;
        if(this.getYear() == 1){
            yearSuffix = "st";
        }
        else if(this.getYear()==2){
            yearSuffix = "nd";
        }
        else if(this.getYear()==3){
            yearSuffix = "rd";
        }
        else{
            yearSuffix = "th";
        }
        return "Student Info For:\t"  +
                "\n\tName:" + this.getFirstName() + " " + this.getLastName() + " ("+ this.getId() + ")\n\t"+
                "Currently in " + this.getYear() + yearSuffix+ " year of \"" + this.getProgramDescription() + "\" ("+
                this.getProgramCode() + ") at " + COLLEGE_NAME+"\n\t"+ "Created On:" + this.getEnrolDate() + "\n\t" +
                "Last Access:" + this.getLastAccess();
    }

    /**
     * Retrieves student from the database using method in StudentDA class
     * @param id student id as long int
     * @return a Student object
     * @throws NotFoundException if no student with that ID was found in the database
     */
    public static Student retrieve(long id) throws NotFoundException {

        Student aStudent;
        aStudent = StudentDA.retrieve(id);
        return aStudent;
    }

    /**
     * Updates a student in the database. Calls the update method StudentDA class
     * @throws NotFoundException if no student with that ID was found in the database
     */
    public int update() throws NotFoundException{
        int records = StudentDA.update(this);
        return records;
    }

    /**
     * deletes a student record. Calls the delete method StudentDA class
     * @throws NotFoundException if no student with that ID was found in the database
     */
    public void delete() throws NotFoundException{
        StudentDA.delete(this);
    }

    /**
     * creates a new student record in the database using StudentDA class create method
     * @throws DuplicateException if a student with that ID already exists in the database
     */
    public void create() throws DuplicateException {

        StudentDA.create(this);
    }

    /**
     * Logs in a student using the authenticate method in StudentDA class
     * @param id a long int for student id
     * @param password a string for student password
     * @return a student object
     * @throws NotFoundException when no matching record is found
     */
    public static Student Authenticate(long id, String password) throws NotFoundException {
        Student aStudent;
        aStudent = StudentDA.Authenticate(id, password);
        return aStudent;
    }

    /**
     * Calculates a students GPA
     * @return GPA as a formatted string
     */
    public String CalculateGPA(){
        double GPA = 0;
        double totalHours = 0;
        try {
            // for each mark the student has,
            for(Mark markObject : marks){
                // find grade
                double grade = markObject.getResult();
                double gradePoint = 0;

                // get grade ratings
                if(grade >= 90) {gradePoint = 5.0;}
                else if(grade >= 85) {gradePoint = 4.5;}
                else if(grade >= 80) {gradePoint = 4.0;}
                else if(grade >= 75) {gradePoint = 3.5;}
                else if(grade >= 70) {gradePoint = 3.0;}
                else if(grade >= 65) {gradePoint = 2.5;}
                else if(grade >= 60) {gradePoint = 2.0;}
                else if(grade >= 55) {gradePoint = 1.5;}
                else if(grade >= 50) {gradePoint = 1.0;}

                // calculate total quality points
                GPA += gradePoint * markObject.getGpaWeighting();
                totalHours += markObject.getGpaWeighting();
            }
        }
        catch (Exception en){
            en.printStackTrace();
        }
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(GPA/totalHours);
    }

    /**
     * Calls the same name method in StudentDA to verify a student exists
     * @param id user id as a long
     * @return true or false if the student exists
     */
    public static boolean StudentExists(long id){
        return StudentDA.StudentExists(id);
    }

    /**
     * initialize a database connection
     * @param c a connection object
     */
    public static void initialize(Connection c)
    {
        StudentDA.initialize(c);
    }

    /**
     * closes database connection
     */
    public static void terminate()
    {

        StudentDA.terminate();
    }
    //endregion

    //region Sets and Gets
    /**
     * Returns the student's program code
     * @return string of program code
     */
    public String getProgramCode() {
        return programCode;
    }

    /**
     * Sets the program code
     * @param programCode student's program code as a string
     */
    public final void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    /**
     * Gets the program description
     * @return program description in string format
     */
    public String getProgramDescription() {
        return programDescription;
    }

    /**
     * sets program description
     * @param programDescription student's program name as a string
     */
    public final void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    /**
     * returns all the grades marks for the student
     * @return a vector of student's grades
     */
    public Vector getMarks() {
        return marks;
    }

    /**
     * Sets the student's marks
     * @param marks a vector of student's grades
     */
    public void setMarks(Vector marks) {
        this.marks = marks;
    }

    /**
     * returns the year of the program the student is in
     * @return year in int format
     */
    public int getYear() {
        return year;
    }

    /**
     * sets student program year
     * @param year which year the student is enrolled in as an integer
     */
    public void setYear(int year) {
        this.year = year;
    }
    //endregion
}
