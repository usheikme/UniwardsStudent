package xyz.uniwards.uniwards_student.StudentHandle;

import java.security.PrivateKey;

/**
 * Created by Umayr on 5/2/2018.
 */

public class StudentResponse {
    private String response_message;
    private Integer id;
    private String fname;
    private String lname;
    private String mobile_no;
    private String username;
    private String password;
    private String birth;
    private Integer type;
    private String email;
    private Integer auth_status;
    private Integer uni_id;
    private Integer total_points;

    public String GetResponse() { return this.response_message; }
    public void SetResponse(String response_message) { this.response_message = response_message; }

    public Integer GetID() { return this.id; }
    public void SetID(Integer id) { this.id = id; }

    public String GetFname() { return this.fname; }
    public void SetFname(String fname) { this.fname = fname; }

    public String GetLname() { return this.lname; }
    public void SetLname(String lname) { this.lname = lname; }

    public String GetMobileNo() { return this.mobile_no; }
    public void SetMobileNo(String mobile_no) { this.mobile_no = mobile_no; }

    public String GetUsername() { return this.username; }
    public void SetUsername(String username) { this.username = username; }

    public String GetPassword() { return this.password; }
    public void SetPassword(String password) { this.password = password; }

    public String GetBirth() { return this.birth; }
    public void SetBirthe(String birth) { this.birth = birth; }

    public Integer GetType() { return this.type; }
    public void SetType(Integer type) { this.type = type; }

    public String GetEmail() { return this.email; }
    public void SetEmail(String email) { this.email = email; }

    public Integer GetAuthStatus() { return this.auth_status; }
    public void SetAuthStatus(Integer auth_status) { this.auth_status = auth_status; }

    public Integer GetUniID() { return this.uni_id; }
    public void SetUniID(Integer uni_id) { this.uni_id = uni_id; }

    public Integer GetTotalPoints() { return this.total_points; }
    public void SetotalPoints(Integer total_points) { this.total_points = total_points; }

}