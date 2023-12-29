import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;



public class MainController implements Initializable{



		@FXML
		private ComboBox<CourseType> addStudent_Course;

	    @FXML
	    private ComboBox<Student> addStudent_Student;

	    @FXML
	    private TextField addStudent_StudentID;
	    
	    @FXML
	    private TextField addStudent_age;

	    @FXML
	    private Button addStudent_addBtn;

	    @FXML
	    private Button addStudent_clearBtn;
	    
	    @FXML
	    private TableView<Student> addStudent_tableView;
	    
	    @FXML
	    private TableColumn<Student, CourseType> addStudent_col_course;
	    
	    @FXML
	    private TableColumn<Student, Integer> addStudent_col_Age;

	    @FXML
	    private TableColumn<Student, String> addStudent_col_StudentID;

	    @FXML
	    private TableColumn<Student, String> addStudent_col_firstName;

	    @FXML
	    private TableColumn<Student, Gender> addStudent_col_gender;

	    @FXML
	    private TableColumn<Student, Integer> addStudent_col_grade;

	    @FXML
	    private TableColumn<Student, String> addStudent_col_lastName;

	    @FXML
	    private TableColumn<Student, Module> addStudent_col_module;
	    
	    
	    @FXML
	    private Button addStudent_deleteBtn;

	    @FXML
	    private TextField addStudent_firstName;

	    @FXML
	    private AnchorPane addStudent_form;

	    @FXML
	    private ComboBox<Gender> addStudent_gender;

	    @FXML
	    private TextField addStudent_grade;

	    @FXML
	    private TextField addStudent_lastName;

	    @FXML
	    private TextField addStudent_search;
	    
	    @FXML
	    private ComboBox<Module> addStudent_addModule;

	    @FXML
	    private Button addStudent_updateBtn;
	    
	    @FXML
	    private Button closeButton;

	    @FXML
	    private Button minimizeButton;

	 // Add a student
	    @FXML
	    private void addStudentAdd(ActionEvent event) {
	        // Retrieve data from input fields
	        String studentID = addStudent_StudentID.getText();

	        // Check if a student with the same ID already exists
	        if (isStudentIDUnique(studentID)) {
	            String firstName = addStudent_firstName.getText();
	            String lastName = addStudent_lastName.getText();
	            Gender gender = addStudent_gender.getValue();
	            CourseType course = addStudent_Course.getValue();
	            Module module = addStudent_addModule.getValue();
	            int grade = Integer.parseInt(addStudent_grade.getText());
	            int age = Integer.parseInt(addStudent_age.getText());

	            // Create a new student and add it to the list
	            Student newStudent = new Student(studentID, firstName, lastName, gender, course, module, grade, age);
	            students.add(newStudent);

	            // Refresh the table view
	            addStudent_tableView.refresh();

	            // Clear the form fields
	            addStudent_clearForm();
	        } else {
	            // Display an error message or handle the duplicate ID case
	            // For example, you can show a dialog, display a message, or log an error
	            System.out.println("Error: Student with the same ID already exists.");
	        }
	    }

	 // Check if a student with the given ID already exists
	    private boolean isStudentIDUnique(String studentID) {
	        for (Student student : students) {
	            if (student.hasSameID(studentID)) {
	                return false; // Student with the same ID already exists
	            }
	        }
	        return true; // Student ID is unique
	    }



	    @FXML
	    void addStudentCourseList(ActionEvent event) {

	    }
	    
	 // Delete a selected student
	    @FXML
	    private void addStudentDelete(ActionEvent event) {
	        // Get the selected student
	        Student selectedStudent = addStudent_tableView.getSelectionModel().getSelectedItem();

	        // Remove the selected student from the list
	        students.remove(selectedStudent);

	        // Refresh the table view
	        addStudent_tableView.refresh();

	        // Clear the form fields or perform other actions
	        addStudent_clearForm();
	    }

	    @FXML
	    void clearForm(ActionEvent event) {
	    	addStudent_clearForm();
	    	addStudent_age.clear();  // Clear age field
	    	addStudent_Course.getSelectionModel().clearSelection();  // Clear course field
	    }
	    
	    @FXML
	    void addStudentGenderList(ActionEvent event) {

	    }

	    @FXML
	    void addStudentModuleList(ActionEvent event) {

	    }

	    @FXML
	    void addStudentReset(ActionEvent event) {

	    }

	    @FXML
	    void addStudentSearch(KeyEvent event) {

	    }
	    
	    @FXML
	    private ChoiceBox<?> searchFilter;

	    @FXML
	    void addStudentSelect(MouseEvent event) {

	    }

	    @FXML
	    void addStudentUpdate(ActionEvent event) {

	    }

	    @FXML
	    void handleCloseButtonAction(ActionEvent event) {
	        // Get a handle to the stage
	        Stage stage = (Stage) closeButton.getScene().getWindow();
	        // Close the stage
	        stage.close();
	    }

	    @FXML
	    void handleMinimizeButtonAction(ActionEvent event) {
	        // Get a handle to the stage
	        Stage stage = (Stage) minimizeButton.getScene().getWindow();
	        // Minimize the stage
	        stage.setIconified(true);
	    }
	    
	    

	    private ObservableList<Student> students = FXCollections.observableArrayList();
	    private ObservableList<Gender> genderList = FXCollections.observableArrayList(Gender.values());
	    private ObservableList<CourseType> courseTypeList = FXCollections.observableArrayList(CourseType.values());
	    private ObservableList<Module> moduleList = FXCollections.observableArrayList(Module.values());
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// Initialize columns
	    addStudent_col_Age.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
	    addStudent_col_StudentID.setCellValueFactory(cellData -> cellData.getValue().studentIDProperty());
	    addStudent_col_firstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
	    addStudent_col_lastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
	    addStudent_col_gender.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().genderProperty()));
	    addStudent_col_grade.setCellValueFactory(cellData -> cellData.getValue().gradeProperty().asObject());
	    addStudent_col_module.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().moduleProperty()));
	    addStudent_col_course.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().courseProperty()));
	    
	    // Set items for ComboBoxes
        addStudent_gender.setItems(genderList);
        addStudent_Course.setItems(courseTypeList);
        addStudent_addModule.setItems(moduleList);

	    // Call the method to populate the table with data
	    addStudent();
	    
	 // Set up the event handler for row selection
	    addStudent_tableView.setOnMouseClicked(event -> {
	        // Check if a row is selected
	        if (!addStudent_tableView.getSelectionModel().isEmpty()) {
	            // Get the selected student
	            Student selectedStudent = addStudent_tableView.getSelectionModel().getSelectedItem();

	            // Populate text fields with selected student's information
	            addStudent_StudentID.setText(selectedStudent.studentIDProperty().getValue());
	            addStudent_firstName.setText(selectedStudent.firstNameProperty().getValue());
	            addStudent_lastName.setText(selectedStudent.lastNameProperty().getValue());
	            addStudent_gender.setValue(selectedStudent.genderProperty());
	            addStudent_addModule.setValue(selectedStudent.moduleProperty());
	            addStudent_grade.setText(Integer.toString(selectedStudent.gradeProperty().get()));
	            addStudent_age.setText(Integer.toString(selectedStudent.ageProperty().get()));
	            addStudent_Course.setValue(selectedStudent.courseProperty());
	        }
	    });
	    
		//set data to the TableView
		addStudent_tableView.setItems(students);}
	
	
	// Populate table with sample data (you would replace this with your actual data)
		@FXML
		private void addStudent() {
		    students.addAll(
		            new Student("1", "John", "Doe", Gender.Male, CourseType.CS, Module.CI505, 90, 20),
		            new Student("2", "Jane", "Smith", Gender.Female, CourseType.PD, Module.BJ646, 85, 22),
		            new Student("3", "Michael", "Johnson", Gender.Male, CourseType.MS, Module.CI505, 88, 21),
		            new Student("4", "Emily", "Williams", Gender.Female, CourseType.FS, Module.XY896, 92, 23),
		            new Student("5", "Daniel", "Brown", Gender.Male, CourseType.CS, Module.BJ646, 87, 20),
		            new Student("6", "Sophia", "Jones", Gender.Female, CourseType.PD, Module.XY896, 89, 22),
		            new Student("7", "Matthew", "Taylor", Gender.Male, CourseType.MS, Module.CI505, 91, 21),
		            new Student("8", "Olivia", "Anderson", Gender.Female, CourseType.FS, Module.XY896, 93, 23),
		            new Student("9", "Ethan", "Wilson", Gender.Male, CourseType.CS, Module.XY896, 86, 20),
		            new Student("10", "Ava", "Martin", Gender.Female, CourseType.PD, Module.DL765, 88, 22),
		            new Student("11", "Logan", "Miller", Gender.Male, CourseType.MS, Module.CI505, 90, 21),
		            new Student("12", "Isabella", "Moore", Gender.Female, CourseType.FS, Module.DL765, 94, 23),
		            new Student("13", "Mason", "Davis", Gender.Male, CourseType.CS, Module.BJ646, 89, 20),
		            new Student("14", "Emma", "Jackson", Gender.Female, CourseType.PD, Module.BJ646, 87, 22),
		            new Student("15", "Noah", "White", Gender.Male, CourseType.MS, Module.DL765, 92, 21),
		            new Student("16", "Abigail", "Harris", Gender.Female, CourseType.FS, Module.DL765, 95, 23),
		            new Student("17", "Liam", "Smith", Gender.Male, CourseType.CS, Module.CI505, 88, 20),
		            new Student("18", "Avery", "Williams", Gender.Female, CourseType.PD, Module.XY896, 86, 22),
		            new Student("19", "Carter", "Taylor", Gender.Male, CourseType.MS, Module.BJ646, 93, 21),
		            new Student("20", "Harper", "Johnson", Gender.Female, CourseType.FS, Module.DL765, 96, 23),
		            new Student("21", "Jackson", "Brown", Gender.Male, CourseType.CS, Module.CI505, 90, 20),
		            new Student("22", "Madison", "Davis", Gender.Female, CourseType.PD, Module.BJ646, 88, 22),
		            new Student("23", "Aiden", "Wilson", Gender.Male, CourseType.MS, Module.BJ646, 94, 21),
		            new Student("24", "Ella", "Moore", Gender.Female, CourseType.FS, Module.CI505, 97, 23),
		            new Student("25", "Lucas", "Miller", Gender.Male, CourseType.CS, Module.CI505, 87, 20)
		    );
		 // Refresh the table view to reflect the changes in the data
		    addStudent_tableView.refresh();
		    
		 // Clear form fields or perform other actions
		    addStudent_clearForm();
		}

		// Add a method to clear the form fields
		private void addStudent_clearForm() {
		    addStudent_StudentID.clear();
		    addStudent_firstName.clear();
		    addStudent_lastName.clear();
		    addStudent_gender.getSelectionModel().clearSelection();
		    addStudent_addModule.getSelectionModel().clearSelection();
		    addStudent_grade.clear();
		}
		
		


}
