package xyz.uniwards.uniwards_student.VendorHandling;

/**
 * Created by Umayr on 5/7/2018.
 */

public class VendorResponse {
    private String response_message;
    private VendorEntity vendor;

    public String GetResponse() { return this.response_message; }
    public void SetResponse(String response_message) { this.response_message = response_message; }

    public VendorEntity GetVendor() { return this.vendor; }
    public void SetVendor(VendorEntity vendor) { this.vendor = vendor; }
}
