package xyz.uniwards.uniwards_student.VendorHandling;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Umayr on 4/28/2018.
 */

public class VendorsResponse {
    private List<VendorResponse> vendors = null;

    public List<VendorResponse> GetVendors() {
        return vendors;
    }

    //TODO Empty Check
    public List<String> GetUniverisityCodes() {
        List<String> uni_codes = new ArrayList<>();
        for (VendorResponse uni : vendors) {
            uni_codes.add(uni.GetEmail());
        }

        return uni_codes;
    }

    public void SetVendors(List<VendorResponse> vendors) {
        this.vendors = vendors;
    }

}
