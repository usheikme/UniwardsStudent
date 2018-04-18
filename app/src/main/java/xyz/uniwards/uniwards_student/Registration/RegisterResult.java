package xyz.uniwards.uniwards_student.Registration;

/**
 * Created by Umayr on 4/18/2018.
 */

public class RegisterResult {
    private RegisterResponse registerData;

    public enum Type {
        REGISTER_SUCCESS,
        REGISTER_FAILED,
        REGISTER_USERNAME_TAKEN,
        REGISTER_EMAIL_TAKEN,
        REGISTER_AUTH_REQUIRED,
        REGISTER_ALREADY_REGISTERED
    }

    public RegisterResult(RegisterResponse data)
    {
        this.registerData = data;
    }

    public String GetResult() {
        String message = "";
        switch(GetRegisterType()) {
            case REGISTER_SUCCESS:
                message = "";
                break;
            default:
                message = "Unknown";
                break;
        }

        return message;
    }

    public Type GetRegisterType() {
        return Type.values()[Integer.parseInt(registerData.GetResponse())];
    }
}
