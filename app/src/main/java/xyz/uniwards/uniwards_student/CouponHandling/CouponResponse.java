package xyz.uniwards.uniwards_student.CouponHandling;

/**
 * Created by Umayr on 4/28/2018.
 */

public class CouponResponse {
    private String response_message;
    private Integer code;
    private String desc;
    private String expiry;
    private Integer id;
    private String name;
    private Integer point_cost;
    private Integer vendor_id;

    public String GetResponse() { return this.response_message; }
    public void SetResponse(String response_message) { this.response_message = response_message; }

    public Integer GetCode() { return code; }
    public void SetCode(Integer code) { this.code = code; }

    public String GetDesc() { return desc; }
    public void SetDesc(String desc) { this.desc = desc; }

    public Object GetExpiry() { return expiry; }
    public void SetExpiry(String expiry) { this.expiry = expiry; }

    public Integer GetID() { return id; }
    public void SetID(Integer id) { this.id = id; }

    public String GetName() { return name; }
    public void SetName(String name) { this.name = name; }

    public Integer GetPointCost() { return point_cost; }
    public void SetPointCost(Integer value) { this.point_cost = value; }

    public Integer GetVendorID() { return vendor_id; }
    public void SetVendorID(Integer vendor_id) { this.vendor_id = vendor_id; }
}
