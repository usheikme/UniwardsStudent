package xyz.uniwards.uniwards_student;

import java.util.List;

import xyz.uniwards.uniwards_student.PointHandling.PointResponse;

/**
 * Created by Umayr on 5/1/2018.
 */

public class ListResultEntity<T> {
    private String response_message;
    private T point_list;

    public ListResultEntity(String response_message, T point_list) {
        this.response_message = response_message;
        this.point_list = point_list;
    }

    public String GetResponseMessage() { return this.response_message; }
    public void SetResponseMessage(String response_message) { this.response_message = response_message; }

    public T GetList() { return this.point_list; }
    public void SetList(T point_list) { this.point_list = point_list; }

}
