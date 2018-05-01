package xyz.uniwards.uniwards_student.UniversityHandling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Umayr on 4/18/2018.
 */



public class UniversitiesResponse {
    private String response_message;
    private List<UniversityResponse> universities = null;

    public List<UniversityResponse> GetUniversities() {
        return universities;
    }

    //TODO Empty Check
    public List<String> GetUniverisityCodes() {
        List<String> uni_codes = new ArrayList<>();
        for (UniversityResponse uni : universities) {
            uni_codes.add(uni.GetUniCode());
        }

        return uni_codes;
    }

    public void SetUniversities(List<UniversityResponse> universities) {
        this.universities = universities;
    }

    public String GetResponse() { return this.response_message; }

}


