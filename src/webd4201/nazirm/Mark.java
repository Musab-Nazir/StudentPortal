package webd4201.nazirm;

import java.text.DecimalFormat;

/**
 * Class that defines the mark objects for students
 */
public class Mark {
    /**
     * Variable to hold minimum gpa
     */
    public static final double MINIMUM_GPA = 0.0;

    /**
     * Variable to hold maximum gpa
     */
    public static final double MAXIMUM_GPA = 5.0;

    /**
     * string used to set a decimal format for GPA display
     */
    private String pattern = "#.#";
    private DecimalFormat GPA = new DecimalFormat(pattern);

    /**
     * course code for which this mark is
     */
    private String courseCode;

    /**
     * course name for which this mark is
     */
    private String courseName;

    /**
     * final grade rounded to int
     */
    private int result;

    /**
     * weighing for the course
     */
    private double gpaWeighting;

    /**
     * Default constructor
     */
    public Mark(){}

    /**
     * Parametrized constructor
     * @param courseCode string that holds the course code
     * @param courseName string that holds the full name of the course
     * @param result    actual mark for the course as an integer
     * @param gpaWeighting the weighing of the particular course as a decimal value
     */
    public Mark(String courseCode, String courseName, int result, double gpaWeighting) {
        setCourseCode(courseCode);
        setCourseName(courseName);
        setResult(result);
        setGpaWeighting(gpaWeighting);
    }

    /**
     * Override for the toString
     * @return the marks as a human readable string
     */
    @Override
    public String toString()
    {
        String courseCode = String.format("%-15s", getCourseCode());
        String courseName = String.format("%-35s", getCourseName());
        String result = String.format("%d", getResult());
        String gpa = String.format("%g", getGpaWeighting());

        return ("\n"+courseCode + courseName + result +" " + gpa );
    }

    // Sets and Gets


    /**
     * Gets GPA.
     *
     * @return Value of GPA.
     */
    public DecimalFormat getGPA() {
        return GPA;
    }

    /**
     * Sets new pattern.
     *
     * @param pattern New value of pattern.
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    /**
     * Sets new result.
     *
     * @param result New value of result.
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * Gets courseCode.
     *
     * @return Value of courseCode.
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Gets courseName.
     *
     * @return Value of courseName.
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Gets gpaWeighting.
     *
     * @return Value of gpaWeighting.
     */
    public double getGpaWeighting() {
        return gpaWeighting;
    }

    /**
     * Sets new gpaWeighting.
     *
     * @param gpaWeighting New value of gpaWeighting.
     */
    public void setGpaWeighting(double gpaWeighting) {
        this.gpaWeighting = gpaWeighting;
    }

    /**
     * Gets pattern.
     *
     * @return Value of pattern.
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Sets new courseName.
     *
     * @param courseName New value of courseName.
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Sets new courseCode.
     *
     * @param courseCode New value of courseCode.
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Gets result.
     *
     * @return Value of result.
     */
    public int getResult() {
        return result;
    }

    /**
     * Sets new GPA.
     *
     * @param GPA New value of GPA.
     */
    public void setGPA(DecimalFormat GPA) {
        this.GPA = GPA;
    }
}
