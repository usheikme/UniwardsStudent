package xyz.uniwards.uniwards_student.CouponHandling;

/**
 * Created by Umayr on 4/28/2018.
 */

public class CouponResponse {
    private long code;
    private String desc;
    private String expiry;
    private long id;
    private String name;
    private long point_cost;
    private long vendor_id;

    public long GetCode() { return code; }
    public void SetCode(long value) { this.code = value; }

    public String GetDesc() { return desc; }
    public void SetDesc(String value) { this.desc = value; }

    public Object GetExpiry() { return expiry; }
    public void SetExpiry(String value) { this.expiry = value; }

    public long GetID() { return id; }
    public void SetID(long value) { this.id = value; }

    public String GetName() { return name; }
    public void SetName(String value) { this.name = value; }

    public long GetPointCost() { return point_cost; }
    public void SetPointCost(long value) { this.point_cost = value; }

    public long GetVendorID() { return vendor_id; }
    public void SetVendorID(long value) { this.vendor_id = value; }
}
