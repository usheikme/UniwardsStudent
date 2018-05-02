package xyz.uniwards.uniwards_student.UniclassHandling;

/**
 * Created by Umayr on 4/28/2018.
 */

public class UniclassResponse {
    private String response_message;
    private Integer id;
    private String name;
    private Integer tutor_id;
    private Integer uni_id;

    public String GetResponse() { return this.response_message; }
    public void SetResponse(String response_message) { this.response_message = response_message; }

    public Integer GetID() { return this.id; }
    public void SetID(Integer id) { this.id = id; }

    public String GetName() { return this.name; }
    public void SetName(String name) { this.name = name; }

    public Integer GetTutorID() { return this.tutor_id; }
    public void SetTutorID(Integer tutor_id) { this.tutor_id = tutor_id; }

    public Integer GetUniID() { return this.uni_id; }
    public void SetUniID(Integer uni_id) { this.uni_id = uni_id; }
}
