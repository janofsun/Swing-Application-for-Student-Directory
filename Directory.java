import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Jie Sun andrewId: jiesun2
 */
public class Directory {
    /**
     * construct a Hashmap to save the andrewid directory.
     */
    private Map<String, Student> andrewIdMap = new HashMap<String, Student>();
    /**
     * construct a Hashmap to save the firstName directory.
     */
    private Map<String, ArrayList<Student>> firstNameMap = new HashMap<String, ArrayList<Student>>();
    /**
     * construct a Hashmap to save the lastName directory.
     */
    private Map<String, ArrayList<Student>> lastNameMap = new HashMap<String, ArrayList<Student>>();
    /**
     * The default Constructor.
     */
    public Directory() {
    }
    /**
     *  Add a new student to the Directory. If the student already exists, throw an exception.
     *  If the student does not exist, and the s object is valid, then generate a copy of this
     *  student, then add it to the above Directories.
     * @param s
     */
    public void addStudent(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("Student must not be null");
        }
        if (andrewIdMap.containsKey(s.getAndrewId())) {
            throw new IllegalArgumentException("Andrew Id already exists.");
        }
        // Set new copy of this student
        Student copy = new Student(s.getAndrewId());
        copy.setFirstName(s.getFirstName());
        copy.setLastName(s.getLastName());
        copy.setPhoneNumber(s.getPhoneNumber());
        andrewIdMap.put(s.getAndrewId(), copy);
        // Get the copy of firstName and lastName lists
        ArrayList<Student> copyflist = new ArrayList<Student>();
        ArrayList<Student> copyllist = new ArrayList<Student>();
        if (firstNameMap.containsKey(s.getFirstName())) {
            copyflist = firstNameMap.get(s.getFirstName());
            copyflist.add(copy);
        } else {
            copyflist.add(copy);
            firstNameMap.put(s.getFirstName(), copyflist);
        }
        if (lastNameMap.containsKey(s.getLastName())) {
            copyllist = lastNameMap.get(s.getLastName());
            copyllist.add(copy);
        } else {
            copyllist.add(copy);
            lastNameMap.put(s.getLastName(), copyllist);
        }
    }
    /**
     * Delete a student from the Directory. If the student does not exist, throws an exception.
     * @param andrewId
     */
    public void deleteStudent(String andrewId) {
        if (andrewId == null || andrewId.isEmpty()) {
            throw new IllegalArgumentException("Student does not exist.");
        }
        if (!andrewIdMap.containsKey(andrewId)) {
            throw new IllegalArgumentException("AndrewId does not exist");
        }
        Student person = andrewIdMap.get(andrewId);
        if (firstNameMap.containsKey(person.getFirstName())) {
            ArrayList<Student> copyflist = firstNameMap.get(person.getFirstName());
            copyflist.remove(person);
            firstNameMap.put(person.getFirstName(), copyflist);
        }
        if (lastNameMap.containsKey(person.getLastName())) {
            ArrayList<Student> copyllist = lastNameMap.get(person.getLastName());
            copyllist.remove(person);
            lastNameMap.put(person.getLastName(), copyllist);
        }
        andrewIdMap.remove(andrewId);
    }
    /**
     * Delete a student from the Directory. If the student does not exist, throws an exception.
     * @param andrewId
     * @return a student instance
     */
    public Student searchByAndrewId(String andrewId) {
        if (andrewId == null || andrewId.isEmpty()) {
            throw new IllegalArgumentException("Invalid andrew id");
        }
        if (!andrewIdMap.containsKey(andrewId)) {
            return null;
        }
        Student res = new Student(andrewId);
        try {
            Student person = andrewIdMap.get(andrewId);
            res.setFirstName(person.getFirstName());
            res.setLastName(person.getLastName());
            res.setPhoneNumber(person.getPhoneNumber());
        } catch (IllegalArgumentException e) {
            throw e;
        }
        return res;
    }
    /**
     * Delete a student from the Directory. If the student does not exist, throws an exception.
     * @param firstName
     * @return a list of students
     * @throws IllegalArgumentException if the input first name is null, empty or if there are
     * no students with that first name in the directory.
     */
    public List<Student> searchByFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("Invalid First Name");
        }
        ArrayList<Student> res = new ArrayList<Student>();
        if (!firstNameMap.containsKey(firstName)) {
            return res;
        }
        try {
            ArrayList<Student> firststNameList = firstNameMap.get(firstName);
            res = (ArrayList<Student>) searchMethod(firstName, firststNameList);
        } catch (IllegalArgumentException e) {
            throw e;
        }
        return res;
    }
    /**
     * Delete a student from the Directory. If the student does not exist, throws an exception.
     * @param lastName
     * @return a list of students
     */
    public List<Student> searchByLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Invalid Last Name");
        }
        ArrayList<Student> res = new ArrayList<Student>();
        if (!lastNameMap.containsKey(lastName)) {
            return res;
        }
        try {
            ArrayList<Student> lastNameList = lastNameMap.get(lastName);
            res = (ArrayList<Student>) searchMethod(lastName, lastNameList);
        } catch (IllegalArgumentException e) {
            throw e;
        }
        return res;
    }
    /**
     * SearchFirstName/LastName helper method.
     * @param keyword
     * @param nameList
     * @return a list of students
     */
    private List<Student> searchMethod(String keyword, ArrayList<Student> nameList) {
        ArrayList<Student> result = new ArrayList<Student>();
            for (Student person : nameList) {
                Student temp = new Student(person.getAndrewId());
                temp.setFirstName(person.getFirstName());
                temp.setLastName(person.getLastName());
                temp.setPhoneNumber(person.getPhoneNumber());
                result.add(temp);
            }
        return result;
    }
    /**
     * returns the number of students in the directory.
     * @return the number of students
     */
    public int size() {
        return andrewIdMap.size();
    }
}
