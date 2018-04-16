package xyz.uniwards.uniwards_student;

/**
 * Created by Umayr on 4/16/2018.
 */

public class ValidateTokenResult implements IReqResult<Boolean> {
    private Type tokenType;
    private ValidateTokenResponse validateTokenResponse;

    public enum Type {
        TOKEN_VALID,
        TOKEN_INVALID,
        TOKEN_EXPIRED
    }

    public ValidateTokenResult(ValidateTokenResponse data)
    {
        this.validateTokenResponse = data;
    }

    public Boolean GetResult() {
        Type resultType = Type.valueOf(validateTokenResponse.getResponse());
        Boolean result;
        switch(resultType) {
            case TOKEN_VALID:
                result = true;
                break;
            default:
                result = false;
                break;
        }

        return result;
    }

    public Type GetType() {
        return Type.valueOf(validateTokenResponse.getResponse());
    }

    public String GetToken() {
        return "";
    }

}
