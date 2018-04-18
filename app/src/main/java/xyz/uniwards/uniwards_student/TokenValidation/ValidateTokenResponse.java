package xyz.uniwards.uniwards_student.TokenValidation;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Umayr on 4/14/2018.
 */

public class ValidateTokenResponse {

    private String response_message;

    public String GetResponse() {
        return response_message;
    }

}