package xyz.uniwards.uniwards_student.UniversityHandling;

/**
 * Created by Umayr on 5/7/2018.
 */

public class UniversityResponse {
    private String response_message;
    private UniversityEntity university;

    public String GetResponse() { return this.response_message; }
    public void SetResponse(String response_message) { this.response_message = response_message; }

    public UniversityEntity GetUniversity() { return this.university; }
    public void SetUniversity(UniversityEntity university) { this.university = university; }
}
