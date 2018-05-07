package xyz.uniwards.uniwards_student.PointHandling;

/**
 * Created by Umayr on 5/7/2018.
 */

public class PointResponse {
    private String response_message;
    private PointEntity point;

    public String GetResponse() { return this.response_message; }
    public void SetResponse(String response_message) { this.response_message = response_message; }

    public PointEntity GetPoint() { return this.point; }
    public void SetPoint(PointEntity point) { this.point = point; }
}
