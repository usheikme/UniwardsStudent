package xyz.uniwards.uniwards_student.PointHandling;

/**
 * Created by Umayr on 4/28/2018.
 */

public class PointResponse {
    private String en_date;
    private long student_id;
    private long uniclass_id;

    public String GetEnDate() { return en_date; }
    public void SetEnDate(String value) { this.en_date = value; }

    public long GetStudentID() { return student_id; }
    public void SetStudentID(long value) { this.student_id = value; }

    public long GetUniclassID() { return uniclass_id; }
    public void SetUniclassID(long value) { this.uniclass_id = value; }
}
