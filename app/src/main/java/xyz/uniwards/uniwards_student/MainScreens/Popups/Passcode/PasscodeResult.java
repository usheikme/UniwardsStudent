package xyz.uniwards.uniwards_student.MainScreens.Popups.Passcode;

/**
 * Created by Umayr on 5/9/2018.
 */

public class PasscodeResult {
    private PasscodeResponse passcodeResponse;

    public enum Type {
        PASSCODE_SUCCESS,
        PASSCODE_INCORRECT,
    }

    public PasscodeResult(PasscodeResponse data)
    {
        this.passcodeResponse = data;
    }

    public String GetResult() {
        String message = "";
        switch(GetType()) {
            case PASSCODE_SUCCESS:
                message = "Success";
                break;
            case PASSCODE_INCORRECT:
                message = "Incorrect passcode";
                break;
            default:
                message = "Unknown";
                break;
        }

        return message;
    }

    public Type GetType() {
        return Type.values()[Integer.parseInt(passcodeResponse.GetResponse())];
    }
}