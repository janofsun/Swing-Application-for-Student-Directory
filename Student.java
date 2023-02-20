/**
 * @author Jie Sun andrew id: jiesun2
 */
public class Student {
    /**
     * Instance variable for andrewId.
     */
    private String andrewId;
    /**
     * Instance variable for firstName.
     */
    private String firstName;
    /**
     * Instance variable for larstName.
     */
    private String lastName;
    /**
     * Instance variable for phoneNumber.
     */
    private String phoneNumber;
    /**
     * validate input.
     * @param input String to validate
     * @return boolean indicating validation
     */
    private static boolean validate(String input) {
        if (input == null || input.trim().length() == 0) {
            throw new IllegalArgumentException("Invalid input.");
        }
        return true;
    }
    /**
     * Constructor with andrewId.
     * @param  andrewid
     */
    public Student(String andrewid) {
        super();
        andrewId = andrewid;
    }
    /**
     * Getter the string of andrewId.
     * @return andrewId
     */
    public String getAndrewId() {
        return andrewId;
    }
    /**
     * Getter the string of firstName.
     * @return firstname
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Getter the string of LastName.
     * @return lastname
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Getter the string of phone number.
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Setter for first name.
     * @param s string first name
     */
    public void setFirstName(String s) {
        if (validate(s)) {
            firstName = s;
        }
    }
    /**
     * Setter for last name.
     * @param s
     */
    public void setLastName(String s) {
        if (validate(s)) {
            lastName = s;
        }
    }
    /**
     * Setter for phoneNumber.
     * @param s
     */
    public void setPhoneNumber(String s) {
        phoneNumber = s;
    }
    /**
     * Returns string representation of student object.
     * @return string representation of student object
     */
    @Override
    public String toString() {
        return firstName + " " + lastName + " (Andrew ID: " + andrewId + ", Phone Number: " + phoneNumber + ")";
    }
}
