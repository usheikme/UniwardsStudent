package xyz.uniwards.uniwards_student;

import android.content.Intent;

/**
 * Created by Umayr on 4/18/2018.
 */

public class RegisterEntity {
    private String fname;
    private String lname;
    private String mobile;
    private String birthdate;
    private String username;
    private String email;
    private String password;
    private Integer st_type;
    private Integer auth_status;
    private Integer uni_id;

    public RegisterEntity(String fname, String lname, String mobile, String birthdate, String username, String email, String password,
                          Integer st_type, Integer auth_status, Integer uni_id) {
        this.fname = fname;
        this.lname = lname;
        this.mobile = mobile;
        this.birthdate = birthdate;
        this.username = username;
        this.email = email;
        this.password = password;
        this.st_type = st_type;
        this.auth_status = auth_status;
        this.uni_id = uni_id;
    }

    public String GetFname() { return this.fname; }
    public String GetLname() { return this.lname; }
    public String GetMobile() { return this.mobile; }
    public String GetBirthdate() { return this.birthdate; }
    public String GetUsername() { return this.username; }
    public String GetEmail() { return this.email; }
    public String GetPassword() { return this.password; }
    public Integer GetST_type() { return this.st_type; }
    public Integer GetAuth_Status() { return this.auth_status; }
    public Integer GetUni_id() { return this.uni_id; }

    public void SetFname(String fname) { this.fname = fname; }
    public void SetLname(String lname) { this.lname = lname; }
    public void SetMobile(String mobile) { this.mobile = mobile; }
    public void SetBirthdate(String birthdate) { this.birthdate = birthdate; }
    public void SetUsername(String username) { this.username = username; }
    public void SetEmail(String email) { this.email = email; }
    public void SetPassword(String password) { this.password = password; }
    public void SetST_type(Integer st_type) { this.st_type = st_type; }
    public void SetAuth_status(Integer auth_status) { this.auth_status = auth_status; }
    public void SetUni_id(Integer uni_id) { this.uni_id = uni_id; }
}
