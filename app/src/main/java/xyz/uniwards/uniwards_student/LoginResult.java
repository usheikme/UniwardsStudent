package xyz.uniwards.uniwards_student;

/**
 * Created by Umayr on 4/16/2018.
 */

public class LoginResult implements IReqResult<String> {
    private LoginResponse loginData;

    public enum Type {
        LOGIN_SUCCESS,
        LOGIN_INCORRECT_PASSWORD,
        LOGIN_NONEXISTANT_USER,
        LOGIN_WRONG_USERNAME,
        LOGIN_UNREGISTERED
    }

    public LoginResult(LoginResponse data)
    {
        this.loginData = data;
    }

    public String GetResult() {
        String message = "";
            switch(GetLoginType()) {
            case LOGIN_SUCCESS:
                message = "";
                break;
            default:
                message = "Unknown";
                break;
        }

        return message;
    }

    public Type GetLoginType() {
        return Type.values()[Integer.parseInt(loginData.getResponse())];
    }

    public String GetToken() {
        return loginData.getToken();
    }

}
