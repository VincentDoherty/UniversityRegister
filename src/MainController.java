import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
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
	    private ComboBox<CourseType> courseFilter;

	    @FXML
	    private ComboBox<Gender> genderFilter;
	    
	    @FXML
	    private TextField maxAgeFilter;

	    @FXML
	    private TextField minAgeFilter;
	    
	    @FXML
	    private TextField startingLetterTextField;
	    
	    @FXML
	    private ComboBox<Module> moduleFilter;

	    @FXML
	    private TextField searchFilter;
	    
	    @FXML
	    private Button resetFiltersButton;
	    
	    @FXML
	    private Label averageGradeLabel;
	    
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
	        Optional<Student> existingStudent = students.stream()
	                .filter(student -> student.hasSameID(studentID))
	                .findAny();

	        existingStudent.ifPresentOrElse(
	            // If a student with the same ID exists
	            student -> System.out.println("Error: Student with the same ID already exists."),
	            // If no student with the same ID exists
	            () -> {
	                // Retrieve other data from input fields
	                String firstName = addStudent_firstName.getText();
	                String lastName = addStudent_lastName.getText();
	                Gender gender = addStudent_gender.getValue();
	                CourseType course = addStudent_Course.getValue();
	                Module module = addStudent_addModule.getValue();
	                int grade = parseInt(addStudent_grade.getText()).orElse(0);
	                int age = parseInt(addStudent_age.getText()).orElse(0);

	                // Create a new student and add it to the list
	                Student newStudent = new Student(studentID, firstName, lastName, gender, course, module, grade, age);
	                originalStudents.add(newStudent);
	                students.add(newStudent);

	                // Refresh the table view
	                addStudent_tableView.refresh();

	                // Clear the form fields
	                addStudent_clearForm();

	                // Refresh the table view with updated data after adding a student
	    	        updateTableView(new ArrayList<>(students));
	            }
	        );
	    }

	    // Helper method to parse an integer from a string, returning OptionalInt
	    private OptionalInt parseInt(String s) {
	        try {
	            return OptionalInt.of(Integer.parseInt(s));
	        } catch (NumberFormatException e) {
	            return OptionalInt.empty();
	        }
	    }

	    @FXML
	    void addStudentCourseList(ActionEvent event) {

	    }
	    
	 // Delete a selected student
	    @FXML
	    private void addStudentDelete(ActionEvent event) {
	        // Get the selected student
	        Student selectedStudent = addStudent_tableView.getSelectionModel().getSelectedItem();

	        // Create new lists without the selected student
	        List<Student> updatedOriginalStudents = originalStudents.stream()
	                .filter(student -> !student.equals(selectedStudent))
	                .collect(Collectors.toList());

	        List<Student> updatedStudents = students.stream()
	                .filter(student -> !student.equals(selectedStudent))
	                .collect(Collectors.toList());

	        // Clear the form fields or perform other actions
	        addStudent_clearForm();

	        // Refresh the table view with updated data after adding a student
	        updateTableView(new ArrayList<>(updatedStudents));

	        // Update the original students list
	        originalStudents = FXCollections.observableArrayList(updatedOriginalStudents);
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
	    void addStudentSelect(MouseEvent event) {

	    }

	    @FXML
	    private void addStudentUpdate(ActionEvent event) {
	        // Retrieve data from input fields
	        String studentIDToUpdate = addStudent_StudentID.getText();

	        // Find the index of the student with the specified ID
	        OptionalInt studentIndex = IntStream.range(0, students.size())
	                .filter(i -> students.get(i).hasSameID(studentIDToUpdate))
	                .findFirst();

	        studentIndex.ifPresentOrElse(
	            // If the student with the specified ID is found
	            index -> {
	                // Update the student's information by creating a new list with the updated student
	                List<Student> updatedStudents = IntStream.range(0, students.size())
	                        .mapToObj(i -> (i == index) ? updateStudentInfo(students.get(i)) : students.get(i))
	                        .collect(Collectors.toList());

	                // Update the original students list with the updated students
	                originalStudents.setAll(updatedStudents);

	                // Refresh the table view
	                updateTableView(updatedStudents);

	                // Clear the form fields
	                addStudent_clearForm();
	            },
	            // If no student with the specified ID is found
	            () -> System.out.println("Error: Student with ID " + studentIDToUpdate + " not found.")
	        );
	    }


	    // Helper method to update student information
	    private Student updateStudentInfo(Student student) {
	        return new Student(
	            student.getStudentID(),
	            addStudent_firstName.getText(),
	            addStudent_lastName.getText(),
	            addStudent_gender.getValue(),
	            addStudent_Course.getValue(),
	            addStudent_addModule.getValue(),
	            parseInt(addStudent_grade.getText()).orElse(0),
	            parseInt(addStudent_age.getText()).orElse(0)
	        );
	    }

	    @FXML
	    private void handleCloseButtonAction(ActionEvent event) {
	        // Get a handle to the stage
	        Stage stage = (Stage) closeButton.getScene().getWindow();

	        // Close the stage using a functional interface
	        closeStage(stage);
	    }

	    // Functional interface for closing a stage
	    @FunctionalInterface
	    interface StageCloser {
	        void close(Stage stage);
	    }

	    // Method to close the stage using a functional interface
	    private void closeStage(Stage stage) {
	        // Define the behavior for closing the stage
	        StageCloser closer = Stage::close;

	        // Perform the action
	        closer.close(stage);
	    }


	    @FXML
	    private void handleMinimizeButtonAction(ActionEvent event) {
	        // Get a handle to the stage
	        Stage stage = (Stage) minimizeButton.getScene().getWindow();

	        // Minimize the stage using a Runnable
	        Runnable minimizeAction = () -> stage.setIconified(true);

	        // Perform the action
	        minimizeAction.run();
	    }
		
		// Method to get students by gender
		public List<Student> getStudentsByGender(Gender gender) {
		    return students.stream()
		            .filter(student -> student.getGender() == gender)
		            .collect(Collectors.toList());
		}
		
		// Get students within the specified age range
		private List<Student> getStudentsByAgeRange(int minAge, int maxAge) {
		    return students.stream()
		            .filter(student -> student.getAge() >= minAge && student.getAge() <= maxAge)
		            .collect(Collectors.toList());
		} 
		
		public List<Student> getStudentsByNameStartsWith(char startingLetter) {
		    return students.stream()
		            .filter(student -> startsWithIgnoreCase(student.getFirstName(), startingLetter)
		                    || startsWithIgnoreCase(student.getLastName(), startingLetter))
		            .collect(Collectors.toList());
		}

		private boolean startsWithIgnoreCase(String s, char startingLetter) {
		    return !s.isEmpty() && Character.toLowerCase(s.charAt(0)) == Character.toLowerCase(startingLetter);
		}


		@FXML
		private void handleModuleQuery(ActionEvent event) {
		    Module selectedModule = moduleFilter.getValue();

		    if (selectedModule != null) {
		        List<Student> result = students.stream()
		            .filter(student -> student.getModule().equals(selectedModule))
		            .sorted(Comparator.comparingInt(Student::getGrade).reversed())
		            .collect(Collectors.toList());

		        updateTableView(result);
		    }
		}

		
		@FXML
		private void handleCourseAndNameQuery(ActionEvent event) {
		    Optional<CourseType> selectedCourse = Optional.ofNullable(courseFilter.getValue());
		    String searchText = searchFilter.getText();

		    // Use Optional to handle null checks
		    if (selectedCourse.isPresent() && !searchText.isEmpty()) {
		        List<Student> result = students.stream()
		                .filter(student -> student.getCourse() == selectedCourse.get())
		                .filter(student -> student.getFirstName().toLowerCase().contains(searchText.toLowerCase()))
		                .collect(Collectors.toList());

		        // Update the TableView with the result
		        updateTableView(result);
		    }
		}

		
		@FXML
		private void handleNameStartsWithQuery(ActionEvent event) {
		    
		    String startingLetterInput = startingLetterTextField.getText();

		    // Use Optional to handle null checks and empty input
		    Optional<String> startingLetterOption = Optional.ofNullable(startingLetterInput)
		            .filter(letter -> !letter.isEmpty())
		            .map(letter -> letter.substring(0, 1).toLowerCase());

		    // If a valid starting letter is provided
		    startingLetterOption.ifPresent(startingLetter -> {
		        // Call the method to get students whose names start with the specified letter
		        List<Student> result = getStudentsByNameStartsWith(startingLetter.charAt(0));

		        
		        updateTableView(result);
		    });
		}


		
		@FXML
		private void handleGenderFilter(ActionEvent event) {
		    
		    Optional<Gender> selectedGenderOption = Optional.ofNullable(genderFilter.getValue());

		    // Use Optional to check if a gender is selected
		    selectedGenderOption.ifPresent(selectedGender -> {
		        // Call the method to get students with the specified gender
		        List<Student> result = getStudentsByGender(selectedGender);

		        
		        updateTableView(result);
		    });
		}

		
		@FXML
		private void handleAgeFilter(ActionEvent event) {
		    String minAgeInput = minAgeFilter.getText();
		    String maxAgeInput = maxAgeFilter.getText();

		    // Use Optional to handle null checks
		    Optional<Integer> minAgeOption = parseAndValidate(minAgeInput);
		    Optional<Integer> maxAgeOption = parseAndValidate(maxAgeInput);

		    // Use functional constructs to check if both minimum and maximum age inputs are provided
		    if (minAgeOption.isPresent() && maxAgeOption.isPresent()) {
		        int minAge = minAgeOption.get();
		        int maxAge = maxAgeOption.get();

		        // Call the method to get students within the specified age range
		        List<Student> result = getStudentsByAgeRange(minAge, maxAge);

		        updateTableView(result);
		    } else {
		        // Handle the case where either minimum or maximum age is not provided
		        System.out.println("Error: Please enter both minimum and maximum age.");
		    }
		} 

		// Helper method for parsing and validating age input
		private Optional<Integer> parseAndValidate(String ageInput) {
		    return Optional.ofNullable(ageInput)
		            .map(Integer::parseInt)
		            .filter(age -> age >= 0) // Additional validation for non-negative age
		            .or(() -> {
		                System.out.println("Error: Please enter a valid numeric value for age.");
		                return Optional.empty();
		            });
		}

		
		private double calculateAverageGrade(List<Student> studentList) {
		    return studentList.stream()
		            .mapToDouble(Student::getGrade)
		            .average()
		            .orElse(0.0);
		}
		
		private void updateTableView(List<Student> data) {
	        System.out.println("Updating TableView with data");

	        // Clear the existing items and add the data to the table in one fluent operation
	        addStudent_tableView.getItems().setAll(data);

	        // Refresh the table view
	        addStudent_tableView.refresh();
	    }

				
	    private ObservableList<Student> students = FXCollections.observableArrayList();
	    private ObservableList<Gender> genderList = FXCollections.observableArrayList(Gender.values());
	    private ObservableList<CourseType> courseTypeList = FXCollections.observableArrayList(CourseType.values());
	    private ObservableList<Module> moduleList = FXCollections.observableArrayList(Module.values());
	    private ObservableList<Student> originalStudents = FXCollections.observableArrayList();
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		System.out.println("Initializing controller");
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
        genderFilter.setItems(genderList);
        courseFilter.setItems(courseTypeList);
        moduleFilter.setItems(moduleList);
        
        averageGradeLabel.textProperty().bind(Bindings.createStringBinding(() ->
        String.format("%.2f",
            calculateAverageGrade(students)),
        students));

        

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
		addStudent_tableView.setItems(students);
		
	}
	
	
	// Populate table with sample data
		@FXML
		private void addStudent() {
			System.out.println("Adding students");
			List<Student> allStudents = Arrays.asList(
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
		            new Student("14", "Emma", "Jackson", Gender.other, CourseType.PD, Module.BJ646, 87, 22),
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
			// Add the original students to the observable list
	        originalStudents.addAll(allStudents);

			// Add the students to the observable list
		    students.addAll(allStudents);

		    // Set items to the TableView
		    addStudent_tableView.setItems(students);

		    // Refresh the table view to reflect the changes in the data
		    addStudent_tableView.refresh();

		    // Clear form fields or perform other actions
		    addStudent_clearForm();
		    
		    // Bind numeric input filter to minAgeFilter
	        addNumericInputFilter(minAgeFilter);

	        // Bind numeric input filter to maxAgeFilter
	        addNumericInputFilter(maxAgeFilter);
	        addNumericInputFilter(addStudent_StudentID);
	        addNumericInputFilter(addStudent_grade);
	        addNumericInputFilter(addStudent_age);
		    
		}
		
		private void addNumericInputFilter(TextField textField) {
	        textField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
	            String input = event.getCharacter();

	            // Check if the input is not numeric
	            if (!input.matches("\\d")) {
	                event.consume(); // Consume the event to prevent non-numeric input
	            }
	        });
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
		// Method to reset filters
		@FXML
		private void resetFilters(ActionEvent event) {
			System.out.println("Reset Filters button clicked");
		    // Clear selected values in filter ComboBoxes
		    genderFilter.getSelectionModel().clearSelection();
			courseFilter.getSelectionModel().clearSelection();
			moduleFilter.getSelectionModel().clearSelection();
		    minAgeFilter.clear();
		    maxAgeFilter.clear();
		    searchFilter.clear();
				    
		 // Update TableView to show original students and any new students added
		    updateTableView(new ArrayList<>(originalStudents));
				}
		
		


}
