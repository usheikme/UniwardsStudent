package xyz.uniwards.uniwards_student.VendorHandling;

/**
 * Created by Umayr on 4/28/2018.
 */

public class VendorEntity {
    private String email;
    private int id;
    private String mobile;
    private String name;
    private int type;
    private String website;

    public String GetEmail() { return email; }
    public void SetEmail(String value) { this.email = value; }

    public long GetID() { return id; }
    public void SetID(int value) { this.id = value; }

    public String GetMobile() { return mobile; }
    public void SetMobile(String value) { this.mobile = value; }

    public String GetName() { return name; }
    public void SetName(String value) { this.name = value; }

    public long GetType() { return type; }
    public void SetType(int value) { this.type = value; }

    public String GetWebsite() { return website; }
    public void SetWebsite(String value) { this.website = value; }
}
