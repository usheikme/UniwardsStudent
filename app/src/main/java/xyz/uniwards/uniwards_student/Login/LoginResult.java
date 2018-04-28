package xyz.uniwards.uniwards_student.Login;

import xyz.uniwards.uniwards_student.APIHandling.IReqResult;

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
                    message = "Success!";
                    break;
                case LOGIN_INCORRECT_PASSWORD:
                        message = "Incorrect Password!";
                        break;
                case LOGIN_NONEXISTANT_USER:
                        message = "User does not exist!";
                        break;
                case LOGIN_WRONG_USERNAME:
                        message = "Error!";
                        break;
                default:
                    message = "An error occured!";
                    break;
        }

        return message;
    }

    public Type GetLoginType() {
        return Type.values()[Integer.parseInt(loginData.GetResponse())];
    }

    public String GetToken() {
        return loginData.GetToken();
    }

}
