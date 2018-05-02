package xyz.uniwards.uniwards_student.RewardHandling;

/**
 * Created by Umayr on 4/28/2018.
 */

public class RewardResponse {
    private String response_message;
    private String desc;
    private Integer id;
    private String name;
    private String requirement;
    private Integer tier;
    private Integer type;
    private Integer value;

    public String GetResponse() { return this.response_message; }
    public void SetResponse(String response_message) { this.response_message = response_message; }

    public String GetDesc() { return desc; }
    public void SetDesc(String desc) { this.desc = desc; }

    public Integer GetID() { return id; }
    public void SetID(Integer id) { this.id = id; }

    public String GetName() { return name; }
    public void SetName(String name) { this.name = name; }

    public String GetRequirement() { return requirement; }
    public void SetRequirement(String requirement) { this.requirement = requirement; }

    public Integer GetTier() { return tier; }
    public void SetTier(Integer tier) { this.tier = tier; }

    public Integer GetType() { return type; }
    public void SetType(Integer type) { this.type = type; }

    public Integer GetValue() { return value; }
    public void SetValue(Integer value) { this.value = value; }
}
