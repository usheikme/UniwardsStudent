package xyz.uniwards.uniwards_student;

/**
 * Created by Umayr on 4/16/2018.
 */

public class ValidateTokenResult implements IReqResult<Boolean> {
    private ValidateTokenResponse validateTokenResponse;

    public enum Type {
        TOKEN_VALID,
        TOKEN_VERFIED,
        TOKEN_BAD,
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
        return Type.values()[Integer.parseInt(validateTokenResponse.getResponse())];
    }

}
