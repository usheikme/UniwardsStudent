package xyz.uniwards.uniwards_student.PointHandling;

import android.util.Pair;

import java.util.List;

import xyz.uniwards.uniwards_student.APIHandling.IReqResult;
import xyz.uniwards.uniwards_student.ListResultEntity;

/**
 * Created by Umayr on 5/1/2018.
 */

public class PointsResult implements IReqResult<ListResultEntity<PointEntity> > {
    private PointsResponse pointsData;

    public enum Type {
        POINT_GET_FAILED,
        POINT_REGISTER_FAILED,
        POINT_DELETE_FAILED,
        POINT_UPDATE_FAILED,
        POINT_GET_SUCCESS,
        POINT_REGISTER_SUCCESS,
        POINT_DELETE_SUCCESS,
        POINT_UPDATE_SUCCESS
    }

    public PointsResult(PointsResponse data)
    {
        this.pointsData = data;
    }

    public ListResultEntity<PointEntity> GetResult() {
        ListResultEntity<PointEntity> pointsEnt = new ListResultEntity("",
                null, ListResultEntity.Type.CARD_POINT);
        switch(GetType()) {
            case POINT_GET_FAILED:
                pointsEnt.SetResponseMessage("Failed to get points!");
                break;
            case POINT_GET_SUCCESS:
                pointsEnt.SetResponseMessage("Successfully got points!");
                pointsEnt.SetList(pointsData.GetPoints());
                break;
            default:
                pointsEnt.SetResponseMessage("An unknown error occured!");
                break;
        }

        return pointsEnt;
    }

    public Type GetType() {
        return Type.values()[Integer.parseInt(pointsData.GetResponse())];
    }

    public List<PointEntity> GetPoints() { return pointsData.GetPoints(); }
}