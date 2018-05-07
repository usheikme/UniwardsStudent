package xyz.uniwards.uniwards_student.UniclassHandling;

/**
 * Created by Umayr on 4/28/2018.
 */

public class UniclassEntity {
    private Integer id;
    private String name;
    private Integer tutor_id;
    private Integer uni_id;

    public Integer GetID() { return this.id; }
    public void SetID(Integer id) { this.id = id; }

    public String GetName() { return this.name; }
    public void SetName(String name) { this.name = name; }

    public Integer GetTutorID() { return this.tutor_id; }
    public void SetTutorID(Integer tutor_id) { this.tutor_id = tutor_id; }

    public Integer GetUniID() { return this.uni_id; }
    public void SetUniID(Integer uni_id) { this.uni_id = uni_id; }
}
