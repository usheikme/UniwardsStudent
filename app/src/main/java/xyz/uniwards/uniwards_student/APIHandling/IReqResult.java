package xyz.uniwards.uniwards_student.APIHandling;

/**
 * Created by Umayr on 4/16/2018.
 */

public interface IReqResult<T> {
    enum Type{}
    T GetResult();
}
