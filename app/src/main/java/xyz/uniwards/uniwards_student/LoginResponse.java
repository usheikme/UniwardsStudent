package xyz.uniwards.uniwards_student;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Umayr on 4/14/2018.
 */

public class LoginResponse {

    private String response_message;
    private String user_token;

    public String GetResponse() {
        return response_message;
    }
    public String GetToken() {
        return user_token;
    }

}