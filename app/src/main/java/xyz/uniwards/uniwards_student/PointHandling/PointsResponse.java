package xyz.uniwards.uniwards_student.PointHandling;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Umayr on 4/28/2018.
 */

public class PointsResponse {
    private String response_message;
    private List<PointEntity> points = null;

    public List<PointEntity> GetPoints() {
        return points;
    }

    //TODO Empty Check
    public List<String> GetUniverisityCodes() {
        List<String> uni_codes = new ArrayList<>();
        for (PointEntity uni : points) {
            //uni_codes.add(uni.GetDate());
        }

        return uni_codes;
    }

    public void SetPoints(List<PointEntity> points) {
        this.points = points;
    }

    public String GetResponse() { return this.response_message; }
}