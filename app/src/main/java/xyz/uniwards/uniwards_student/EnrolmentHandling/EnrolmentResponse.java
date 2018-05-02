package xyz.uniwards.uniwards_student.EnrolmentHandling;

/**
 * Created by Umayr on 4/28/2018.
 */

public class EnrolmentResponse {
    private String response_message;
    private String date;
    private int student_id;
    private int uniclass_id;

    public String GetResponse() { return this.response_message; }
    public void SetResponse(String response_message) { this.response_message = response_message; }

    public String GetDate() { return date; }
    public void SetDate(String value) { this.date = value; }

    public int GetStudentID() { return student_id; }
    public void SetStudentID(int value) { this.student_id = value; }

    public int GetUniclassID() { return uniclass_id; }
    public void SetUniclassID(int value) { this.uniclass_id = value; }
}