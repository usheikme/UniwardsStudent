package xyz.uniwards.uniwards_student.UniclassHandling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Umayr on 4/28/2018.
 */

public class UniclassesResponse {
    private String response_message;
    private List<UniclassResponse> uniclasses = null;

    public List<UniclassResponse> GetUniclasses() {
        return uniclasses;
    }

    //TODO Empty Check
    public List<String> GetUniverisityCodes() {
        List<String> uni_codes = new ArrayList<>();
        for (UniclassResponse uni : uniclasses) {
            uni_codes.add(uni.GetName());
        }

        return uni_codes;
    }

    public void SetUniclasses(List<UniclassResponse> uniclasses) { this.uniclasses = uniclasses; }

    public String GetResponse() { return this.response_message; }

}
