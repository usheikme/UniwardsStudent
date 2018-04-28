package xyz.uniwards.uniwards_student.UniversityHandling;

/**
 * Created by Umayr on 4/28/2018.
 */

public class UniversityResponse {

    private String address;
    private Integer id;
    private String mobile_no;
    private String name;
    private String uni_code;

    public String GetAddress() {
        return address;
    }

    public void SetAddress(String address) {
        this.address = address;
    }

    public Integer GetId() {
        return id;
    }

    public void SetId(Integer id) {
        this.id = id;
    }

    public String GetMobileNo() {
        return mobile_no;
    }

    public void SetMobileNo(String mobileNo) {
        this.mobile_no = mobileNo;
    }

    public String GetName() {
        return name;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public String GetUniCode() {
        return uni_code;
    }

    public void SetUniCode(String uniCode) {
        this.uni_code = uniCode;
    }

}