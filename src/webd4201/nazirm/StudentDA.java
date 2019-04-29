package webd4201.nazirm;

import java.sql.*;
import java.util.Date;
import java.util.Vector;

/**
 * StudentDA class that accesses the database
 *
 * @author Darren Puffer, Musab nazir
 * @version 1.0 (2018 February 1st)
 * @since 1.0
 */
class StudentDA {
    private static final Vector<Student> students = new Vector<Student>();    // contains student references
    private static Student aStudent;

    private static Statement aStatement;

    // declare static variables for all webd4201.nazirm.Student instance attribute values
    /**
     * shared variable to store student ID
     */
    private static long id;
    /**
     * shared variable to store student password as a string
     */
    private static String password;
    /**
     * shared variable to store student first name
     */
    private static String first_name;
    /**
     * shared variable to store student last name
     */
    private static String last_name;
    /**
     * shared variable to store student email
     */
    private static String email;
    /**
     * shared variable to store student type
     */
    private static char type;
    /**
     * shared variable to store whether account is enabled
     */
    private static boolean enabled;
    /**
     * shared variable to store student last access date
     */
    private static Date last_access;
    /**
     * shared variable to store student enrol date
     */
    private static Date enrol_date;
    /**
     * shared variable to store student program code
     */
    private static String program_code;
    /**
     * shared variable to store student program description
     */
    private static String program_description;
    /**
     * shared variable to store student year
     */
    private static int year;

    // establish the database connection
    public static void initialize(Connection c) {
        try {
            // declare variables for the database connection
            Connection aConnection = c;
            aStatement = aConnection.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // close the database connection
    public static void terminate() {
        try {    // close the statement
            aStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Checks if student exists
     *
     * @param key student id as long
     * @return boolean depending on whether student was found
     */
    public static boolean StudentExists(long key) {
        Boolean exists = false;
        try {
            // establish database connection
            Connection con = DatabaseConnect.initialize();

            //create prepared statement
            String sqlStudentQuery = "SELECT * FROM students WHERE id = ?";
            PreparedStatement psSelect = con.prepareStatement(sqlStudentQuery);
            psSelect.setLong(1, key);

            // execute statement
            ResultSet rs2 = psSelect.executeQuery();
            exists = rs2.next();

            // close connection to the database
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    /**
     * Fetches a student record from the database
     *
     * @param key a long int for the student ID
     * @return a student object
     * @throws NotFoundException if a student with specified ID is not found in the database
     */
    public static Student retrieve(long key) throws NotFoundException { // retrieve webd4201.nazirm Student data
        aStudent = null;

        // System.out.println(sqlQuery);
        // execute the SQL query statement
        try {
            Connection con = DatabaseConnect.initialize();
            // if student exists
            String sqlStudentQuery = "SELECT * FROM students WHERE id = ?";
            PreparedStatement psSelect = con.prepareStatement(sqlStudentQuery);
            psSelect.setLong(1, key);
            ResultSet rs2 = psSelect.executeQuery();
            if (rs2.next()) {
                program_code = rs2.getString("program_code");
                year = rs2.getInt("year");

                // get the program description from the programs table
                String programDescQuery = "select * from programs WHERE ProgramCode = ?";
                PreparedStatement psProgram = con.prepareStatement(programDescQuery);
                psProgram.setString(1, program_code);
                ResultSet rs3 = psProgram.executeQuery();
                if(rs3.next()){
                    program_description = rs3.getString("programdescription");
                }

                // since student account exists, query users table to get remaining fields
                aStudent = UserDA.retrieve(key);

                // create webd4201.nazirm.Student
                try {
                    aStudent.setProgramCode(program_code);
                    aStudent.setProgramDescription(program_description);
                    aStudent.setYear(year);
                } catch (Exception e) { // something went wrong
                    e.printStackTrace();
                }
                // close result set
                rs2.close();

                // close connection to the database
                con.close();
            } else    // nothing was retrieved
            {
                throw (new NotFoundException("Problem retrieving Student record, id " +
                        key + " does not exist in the system."));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        // add marks
        StudentDA.GetMarks(aStudent);
        return aStudent;
    }

    /**
     * Retrieves all students in the database
     *
     * @return vector of all students
     */
    public static Vector<Student> retrieveAll() {   // retrieve Students and their details
        // define the SQL query statement for get all
        String sqlQuery = "SELECT * FROM Students";
        try {   // execute the SQL query statement
            ResultSet rs = aStatement.executeQuery(sqlQuery);
            boolean moreData = rs.next();

            while (moreData) {    // extract the data
                id = rs.getLong("id");

                // try tp create webd4201.nazirm.Student instance
                try {
                    aStudent = retrieve(id);
                } catch (NotFoundException e) {
                    System.out.println("Could not find record");
                }

                students.addElement(aStudent);
                moreData = rs.next();
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return students;
    }


    /**
     * creates a new student record in the database
     *
     * @param aStudent student object that is instantiated
     * @return true if record was created else return's false
     * @throws DuplicateException if a user with the same ID already exists in the database
     */
    public static boolean create(Student aStudent) throws DuplicateException {
        boolean insertedStudent = false; //insertion success flag

        // create the SQL insert statement using attribute values
        String sqlStudentInsert = "INSERT INTO students(id, program_code, program_description, year) " +
                "VALUES (?, ?, ?, ?)";

        // retrieve the student attribute values
        id = aStudent.getId();
        first_name = aStudent.getFirstName();
        last_name = aStudent.getLastName();
        email = aStudent.getEmailAddress();
        type = aStudent.getType();
        last_access = aStudent.getLastAccess();
        enrol_date = aStudent.getEnrolDate();
        enabled = aStudent.isEnabled();
        program_code = aStudent.getProgramCode();
        program_description = aStudent.getProgramDescription();
        year = aStudent.getYear();


        // see if this student already exists in the database
        try {
            retrieve(id);
            throw (new DuplicateException("There is a record with that id already in the system"));
        }
        // if webd4201.nazirm.NotFoundException, add student to database
        catch (NotFoundException e) {
            try {
                // establish db connection
                Connection con = DatabaseConnect.initialize();
                con.setAutoCommit(false);
                // create statement objects
                PreparedStatement psInsertStudent = con.prepareStatement(sqlStudentInsert);


                //Set the attributes in the SQL statements
                psInsertStudent.setLong(1, id);
                psInsertStudent.setString(2, program_code);
                psInsertStudent.setString(3, program_description);
                psInsertStudent.setInt(4, year);

                // try creating user record, if it passes, create student record
                if(UserDA.create(aStudent,con)){
                    insertedStudent = (psInsertStudent.executeUpdate() > 0)?true:false;
                }
                if(insertedStudent == false){
                    con.rollback();
                }
                else{
                    con.commit();
                }

                // close connection to the database
                con.close();
            }
            catch (SQLException ee) {
                // RollBack: Student creation failed. Delete the User record that was inserted
                System.out.println(e);
            }
        }
        return insertedStudent; //return boolean

    }

    /**
     * deletes a student record in the database
     *
     * @param aStudent a student object
     * @return amount of records deleted as an integer
     * @throws NotFoundException if a user with that id cannot be found
     */
    public static int delete(Student aStudent) throws NotFoundException {
        int records = 0;
        // retrieve the phone no (key)
        id = aStudent.getId();
        // create the SQL delete statement
        String sqlStudentDelete = "DELETE FROM students WHERE id = ?";


        // see if this student already exists in the database
        try {
            Student.retrieve(id);  //used to determine if record exists for the passed webd4201.nazirm.Student

            // connect to database
            Connection con = DatabaseConnect.initialize();
            // make prepared statement object
            PreparedStatement psStudentDelete = con.prepareStatement(sqlStudentDelete);

            // set attributes for the statement
            psStudentDelete.setLong(1, id);

            // execute the SQL update statement
            records += psStudentDelete.executeUpdate();
            // delete the user record
            UserDA.delete(aStudent);
            // close db connection
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return records;     // return number of records effected
    }

    /**
     * Updates a student record in the database
     *
     * @param aStudent Student object
     * @return number of records affected as an int
     * @throws NotFoundException if no user with that ID could be found in the database
     */
    public static int update(Student aStudent) throws NotFoundException {
        int records = 0;  //records updated in method

        // define the SQL query statement using the phone number key
        String sqlStudentUpdate = "UPDATE students SET program_code=?, year=? " +
                "WHERE id = ?";

        // see if this student exists in the database
        // webd4201.nazirm.NotFoundException is thrown by find method
        try {
            // retrieve the student argument attribute values
            id = aStudent.getId();
            Student backupStudent = Student.retrieve(id);  //determine if there is a webd4201.nazirm.Student record to be updated
            password = aStudent.getPassword();
            first_name = aStudent.getFirstName();
            last_name = aStudent.getLastName();
            email = aStudent.getEmailAddress();
            type = aStudent.getType();
            last_access = aStudent.getLastAccess();
            enrol_date = aStudent.getEnrolDate();
            enabled = aStudent.isEnabled();

            program_code = aStudent.getProgramCode();
            program_description = aStudent.getProgramDescription();
            year = aStudent.getYear();

            // connect to db and create prepared statements
            Connection con = DatabaseConnect.initialize();
            con.setAutoCommit(false);
            PreparedStatement psUpdateStudent = con.prepareStatement(sqlStudentUpdate);


            //Set the attributes in the SQL statements
            psUpdateStudent.setString(1, program_code);
            psUpdateStudent.setInt(2, year);
            psUpdateStudent.setLong(3, id);

            // if successfully updated user then update student
            records += UserDA.update(aStudent, con);
            if(records > 0){
                records += psUpdateStudent.executeUpdate();
                // student probably failed so rollback
                if(records <= 1){
                    con.rollback();
                }
                else{
                    con.commit();
                }
            }

            // close db connection
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return records;     // return number of records effected
    }

    /**
     * Method checks if a student exists and returns a Student object if both parameters
     * are correct
     *
     * @param studentNumber long int signifying student Id
     * @param password      string for student password
     * @return Student object if a match was found, returns null otherwise
     * @throws NotFoundException if no matching record is found in the database
     */
    public static Student Authenticate(long studentNumber, String password) throws NotFoundException {

        // try and retrieve student
        Student aStudent = StudentDA.retrieve(studentNumber);
        // check if passwords match
        if (aStudent.getPassword().equals(UserDA.HashPassword(password))) {
            return aStudent;
        } else {
            throw new NotFoundException("Password does not match any records");
        }

    }

    /**
     * Method that populates the marks vector for a student object
     * @param aStudent an instantiated student object
     */
    public static void GetMarks(Student aStudent){
        Vector<Mark> marks = new Vector<Mark>();
        // get id
        id = aStudent.getId();
        // prepare SQL statement
        // define the SQL query statement using the phone number key
        String sqlStudentMarks = "SELECT * FROM marks WHERE id = ?";

        // fetch data
        // connect to db and create prepared statements
        Connection con = DatabaseConnect.initialize();
        PreparedStatement psGetStudentGrades = null;
        try {
            psGetStudentGrades = con.prepareStatement(sqlStudentMarks);
            //Set the attributes in the SQL statements
            psGetStudentGrades.setLong(1, id);
            ResultSet rs = psGetStudentGrades.executeQuery();
            while(rs.next()){
                Mark aMark = new Mark();
                aMark.setCourseCode(rs.getString("coursecode"));
                aMark.setResult(rs.getInt("result"));
                marks.add(aMark);
            }

            String sqlCourseWeight = "SELECT * FROM courses WHERE coursecode = ?";
            PreparedStatement psCourseWeight = null;
            for (Mark markEntry: marks) {
                psCourseWeight = con.prepareStatement(sqlCourseWeight);
                psCourseWeight.setString(1, markEntry.getCourseCode());
                ResultSet courseInfo = psCourseWeight.executeQuery();
                if(courseInfo.next()){
                    markEntry.setGpaWeighting(courseInfo.getDouble("gpaweighting"));
                    markEntry.setCourseName(courseInfo.getString("coursetitle"));
                }
            }
            aStudent.setMarks(marks);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
