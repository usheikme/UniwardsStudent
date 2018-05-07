package xyz.uniwards.uniwards_student.StudentHandle;

/**
 * Created by Umayr on 5/7/2018.
 */

public class StudentResponse {
    private String response_message;
    private StudentEntity student;

    public String GetResponse() { return this.response_message; }
    public void SetResponse(String response_message) { this.response_message = response_message; }

    public StudentEntity GetStudent() { return this.student; }
    public void SetStudent(StudentEntity student) { this.student = student; }
}
