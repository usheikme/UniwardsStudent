package xyz.uniwards.uniwards_student.EnrolmentHandling;

/**
 * Created by Umayr on 4/28/2018.
 */

public class EnrolmentResponse {
    private String en_date;
    private int student_id;
    private int uniclass_id;

    public String GetEnDate() { return en_date; }
    public void SetEnDate(String value) { this.en_date = value; }

    public int GetStudentID() { return student_id; }
    public void SetStudentID(int value) { this.student_id = value; }

    public int GetUniclassID() { return uniclass_id; }
    public void SetUniclassID(int value) { this.uniclass_id = value; }
}