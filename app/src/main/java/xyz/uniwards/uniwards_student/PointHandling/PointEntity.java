package xyz.uniwards.uniwards_student.PointHandling;

/**
 * Created by Umayr on 4/28/2018.
 */

public class PointEntity {
    private String date;
    private int student_id;
    private int reward_id;
    private int tutor_id;

    public String GetDate() { return date; }
    public void SetDate(String value) { this.date = value; }

    public int GetStudentID() { return student_id; }
    public void SetStudentID(int value) { this.student_id = value; }

    public int GetTutorID() { return tutor_id; }
    public void SetTutorID(int value) { this.tutor_id = value; }

    public int GetRewardID() { return reward_id; }
    public void SetRewardID(int value) { this.reward_id = value; }
}
