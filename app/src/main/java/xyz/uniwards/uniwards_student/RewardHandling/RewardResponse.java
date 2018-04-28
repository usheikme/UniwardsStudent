package xyz.uniwards.uniwards_student.RewardHandling;

/**
 * Created by Umayr on 4/28/2018.
 */

public class RewardResponse {
    private String desc;
    private int id;
    private int name;
    private String requirement;
    private int tier;
    private int type;
    private int value;

    public String GetDesc() { return desc; }
    public void SetDesc(String value) { this.desc = value; }

    public long GetID() { return id; }
    public void SetID(int value) { this.id = value; }

    public long GetName() { return name; }
    public void SetName(int value) { this.name = value; }

    public String GetRequirement() { return requirement; }
    public void SetRequirement(String value) { this.requirement = value; }

    public long GetTier() { return tier; }
    public void SetTier(int value) { this.tier = value; }

    public long GetType() { return type; }
    public void SetType(int value) { this.type = value; }

    public long GetValue() { return value; }
    public void SetValue(int value) { this.value = value; }
}
