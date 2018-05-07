package xyz.uniwards.uniwards_student.UniclassHandling;

/**
 * Created by Umayr on 5/7/2018.
 */

public class UniclassResponse {
    private String response_message;
    private UniclassEntity uniclass;

    public String GetResponse() { return this.response_message; }
    public void SetResponse(String response_message) { this.response_message = response_message; }

    public UniclassEntity GetUniclass() { return this.uniclass; }
    public void SetUniclass(UniclassEntity uniclass) { this.uniclass = uniclass; }
}
