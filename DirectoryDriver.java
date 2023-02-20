import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
/**
 * Swing GUI for the Student Directory GUI.
 * @author Jie Sun
 */
public class DirectoryDriver extends JFrame implements ActionListener {
    /**
     * Reference to the add Buttons.
     */
    private JButton addButton;
    /**
     * Reference to the delete Buttons.
     */
    private JButton deleteButton;
    /**
     * Reference to the search First Name Button.
     */
    private JButton firstNButton;
    /**
     * Reference to the search Last Name Button.
     */
    private JButton lastNButton;
    /**
     * Reference to the search Andrew ID Button.
     */
    private JButton idButton;
    /**
     * Reference to the Results text area.
     */
    private JTextArea textArea;
    /**
     * Reference to the First Name adding field.
     */
    private JTextField firstNameField;
    /**
     * Reference to the Last Name adding field.
     */
    private JTextField lastNameField;
    /**
     * Reference to the Andrew ID adding field.
     */
    private JTextField andrewIdField;
    /**
     * Reference to the Optional Phone Number adding field.
     */
    private JTextField phoneField;
    /**
     * Reference to the deletion field.
     */
    private JTextField deleteAndrewIdField;
    /**
     * Reference to the search key field.
     */
    private JTextField searchKeyField;
    /**
     * Reference to the Student Directory.
     */
    private static Directory directory = new Directory();
    /**
     * Initialize the GUI Layout.
     */
    public DirectoryDriver() {
        setTitle("Student Directory");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font labelFont = new Font(Font.SERIF, Font.BOLD, 15);
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        JPanel addLine = new JPanel();
        TitledBorder addBorder = new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 2),
                            "Add a new student", TitledBorder.LEFT, TitledBorder.TOP, labelFont, Color.DARK_GRAY);
        addLine.setBorder(addBorder);
        // First Name Field, Add a new student
        JLabel firstName = new JLabel("First Name:");
        firstName.setForeground(Color.DARK_GRAY);
        addLine.add(firstName);
        firstNameField = new JTextField(7);
        addLine.add(firstNameField);
        // Last Name Field, Add a new student
        JLabel lastName = new JLabel("Last Name:");
        lastName.setForeground(Color.DARK_GRAY);
        addLine.add(lastName);
        lastNameField = new JTextField(7);
        addLine.add(lastNameField);
        // andrewId, Add a new student
        JLabel andrewId = new JLabel("Andrew ID:");
        andrewId.setForeground(Color.DARK_GRAY);
        addLine.add(andrewId);
        andrewIdField = new JTextField(7);
        addLine.add(andrewIdField);
        // Phone Number, Add a new student
        JLabel phoneNumber = new JLabel("Phone Number:");
        phoneNumber.setForeground(Color.DARK_GRAY);
        addLine.add(phoneNumber);
        phoneField = new JTextField(7);
        addLine.add(phoneField);

        addButton = new JButton("Add");
        addButton.addActionListener(this);
        addLine.add(addButton);

        pane.add(addLine);

        JPanel deleteLine = new JPanel();
        TitledBorder deleteBorder = new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 2),
                                "Delete a student", TitledBorder.LEFT, TitledBorder.TOP, labelFont, Color.DARK_GRAY);
        deleteLine.setBorder(deleteBorder);
        // andrewId, Add a new student
        JLabel deleteAndrewId = new JLabel("Andrew ID:");
        deleteAndrewId.setForeground(Color.DARK_GRAY);
        deleteLine.add(deleteAndrewId);
        deleteAndrewIdField = new JTextField(7);
        deleteLine.add(deleteAndrewIdField);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        deleteLine.add(deleteButton);

        pane.add(deleteLine);

        JPanel searchLine = new JPanel();
        TitledBorder searchBorder = new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 2),
                                    "Search student(s)", TitledBorder.LEFT, TitledBorder.TOP, labelFont, Color.DARK_GRAY);
        searchLine.setBorder(searchBorder);
        // andrewId, Add a new student
        JLabel searchKey = new JLabel("Search Key:");
        searchKey.setForeground(Color.DARK_GRAY);
        searchLine.add(searchKey);
        searchKeyField = new JTextField(7);
        searchLine.add(searchKeyField);

        firstNButton = new JButton("By First Name");
        firstNButton.addActionListener(this);
        searchLine.add(firstNButton);
        lastNButton = new JButton("By Last Name");
        lastNButton.addActionListener(this);
        searchLine.add(lastNButton);
        idButton = new JButton("By Andrew ID");
        idButton.addActionListener(this);
        searchLine.add(idButton);

        pane.add(searchLine);

        JPanel  resultPanel = new JPanel();
        TitledBorder resultBorder = new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 2),
                                    "Results", TitledBorder.LEFT, TitledBorder.TOP, labelFont, Color.DARK_GRAY);
        resultPanel.setBorder(resultBorder);
        textArea = new JTextArea(10, 40);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JScrollPane scroller = new JScrollPane(textArea);
        resultPanel.add(scroller);
        pane.add(resultPanel);

        setContentPane(pane);
        setVisible(true);
        searchKeyField.requestFocusInWindow();
        JRootPane rootPane = SwingUtilities.getRootPane(searchKeyField);
        rootPane.setDefaultButton(idButton);
    }
    /**
     * Validate the arguments.
     * @param input String to validate
     * @param field Field to validate
     * @param textArea TextArea to display
     * @return boolean indicating validation
     */
    private static boolean validate(String input, String field, JTextArea textArea) {
        if (input == null || input.trim().length() == 0) {
            textArea.setText(field + " Missing\n");
            return false;
        }
        return true;
    }
    /**
     * Search Method and Error Messages.
     * @param searchKeyField JTextField  to validate
     * @param field Field to validate
     * @param textArea TextArea to display
     */
    private static void searchDisplay(JTextField searchKeyField, String field, JTextArea textArea) {
        List<Student> students = directory.searchByFirstName(searchKeyField.getText());
        if (!students.isEmpty()) {
            searchKeyField.setText("");
        } else {
            // No matching students, successfully operation
            textArea.append("Student with " + field + " " + searchKeyField.getText() + " does not exist.\n");
            return;
        }
        for (Student student : students) {
            textArea.append(student.toString() + "\n");
        }
        return;
    }
    /**
     * Main method that instantiates GUI Object.
     * @param args command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        if (args.length == 1) {
            FileReader fr = new FileReader(args[0]);
            BufferedReader bf = new BufferedReader(fr);
            csvReader(bf);
        }
        new DirectoryDriver();
    }
    /**
     * Method to be invoked when buttons are clicked.
     * @param e event object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            if (validate(firstNameField.getText(), "First Name", textArea) && validate(lastNameField.getText(),
                     "Last Name", textArea) && validate(andrewIdField.getText(), "Andrew ID", textArea)) {
                if (directory.searchByAndrewId(andrewIdField.getText()) != null) {
                    textArea.setText("Andrew ID " + andrewIdField.getText() + " already exists.\n");
                    return;
                }
                Student newStudent = new Student(andrewIdField.getText());
                newStudent.setFirstName(firstNameField.getText());
                newStudent.setLastName(lastNameField.getText());
                if (phoneField.getText() == null || phoneField.getText().trim().isEmpty()) {
                    newStudent.setPhoneNumber("");
                } else {
                    newStudent.setPhoneNumber(phoneField.getText());
                }
                directory.addStudent(newStudent);
                firstNameField.setText("");
                lastNameField.setText("");
                andrewIdField.setText("");
                phoneField.setText("");
                textArea.append(newStudent.toString() + " is added successfully.\n");
            }
            return;
        }
        if (e.getSource() == deleteButton) {
            if (validate(deleteAndrewIdField.getText(), "Andrew ID", textArea)) {
                try {
                    Student deleteStudent = directory.searchByAndrewId(deleteAndrewIdField.getText());
                    directory.deleteStudent(deleteAndrewIdField.getText());
                    textArea.append(deleteStudent.toString() + " is deleted successfully.\n");
                    deleteAndrewIdField.setText("");
                } catch (Exception exception) {
                    textArea.append("Student with Andrew ID " + deleteAndrewIdField.getText() + " does not exist.\n");
                }
            }
            return;
        }
        if (e.getSource() == firstNButton) {
            if (validate(searchKeyField.getText(), "First Name", textArea)) {
                searchDisplay(searchKeyField, "First Name", textArea);
            }
            return;
        }
        if (e.getSource() == lastNButton) {
            if (validate(searchKeyField.getText(), "Last Name", textArea)) {
                searchDisplay(searchKeyField, "Last Name", textArea);
            }
            return;
        }
        if (e.getSource() == idButton) {
            if (validate(searchKeyField.getText(), "Andrew ID", textArea)) {
                try {
                    Student student = directory.searchByAndrewId(searchKeyField.getText());
                    textArea.append(student.toString() + "\n");
                    searchKeyField.setText("");
                } catch (Exception exception) {
                    textArea.append("Student with Andrew ID " + searchKeyField.getText() + " does not exist.\n");
                }
            }
            return;
        }
    }
    /**
     * Read and Parse the CSV file containing, and load student entries.
     * Header format is - "First Name","Last Name","Andrew ID","Phone Number".
     * @param csvFile
     * @throws IOException
     */
    private static void csvReader(BufferedReader csvFile) throws IOException {
        boolean eof = false;
        String line = csvFile.readLine();
        if (line == null) {
            eof = true;
        }
        int header = 0;
        while (!eof) {
            line = csvFile.readLine();
            if (line == null) {
                eof = true;
            } else {
                String[] fields = new String[4];
                int beginIndex = 0;
                for (int i = 0; i < 3; i++) {
                    int endIndex = line.indexOf(',', beginIndex);
                    fields[i] = line.substring(beginIndex + 1, endIndex - 1);
                    beginIndex = endIndex + 1;
                }
                fields[3] = line.substring(beginIndex + 1, line.length() - 1);
                Student newStudent = new Student(fields[2]);
                newStudent.setFirstName(fields[0]);
                newStudent.setLastName(fields[1]);
                newStudent.setPhoneNumber(fields[3]);
                directory.addStudent(newStudent);
            }
        }
    }
}
