import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public record Student(
	String StudentID,
    String FirstName,
    String LastName,
    Gender Gender,
    CourseType Course,
    Module Module,
    int Grade,
    int Age) {
	
    public String getStudentID() {
        return StudentID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public Gender getGender() {
        return Gender;
    }

    public CourseType getCourse() {
        return Course;
    }

    public Module getModule() {
        return Module;
    }

    public int getGrade() {
        return Grade;
    }

    public int getAge() {
        return Age;
    }

public StringProperty studentIDProperty() {
    return new SimpleStringProperty(StudentID);
}

public StringProperty firstNameProperty() {
    return new SimpleStringProperty(FirstName);
}

public StringProperty lastNameProperty() {
    return new SimpleStringProperty(LastName);
}

public Gender genderProperty() {
    return Gender;
}

public CourseType courseProperty() {
    return Course;
}
public Module moduleProperty() {
    return Module;
}
public IntegerProperty gradeProperty() {
    return new SimpleIntegerProperty(Grade);
}
public IntegerProperty ageProperty() {
    return new SimpleIntegerProperty(Age);
}
//Method to check equality based on StudentID
public boolean hasSameID(String otherID) {
    return StudentID.equals(otherID);
}}
enum Gender {
	Male, Female, other
}
enum CourseType {
CS, PD, MS, FS
}
enum Module {
	BJ646, CI505, DL765, XY896
}

