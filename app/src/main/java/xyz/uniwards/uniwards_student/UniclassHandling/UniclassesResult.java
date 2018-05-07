package xyz.uniwards.uniwards_student.UniclassHandling;
import android.util.Pair;

import java.util.List;

import xyz.uniwards.uniwards_student.APIHandling.IReqResult;
import xyz.uniwards.uniwards_student.ListResultEntity;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassResponse;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassesResponse;

/**
 * Created by Umayr on 5/1/2018.
 */

public class UniclassesResult implements IReqResult<ListResultEntity<UniclassEntity>> {
    private UniclassesResponse uniclassesData;

    public enum Type {
        UNICLASS_GET_FAILED,
        UNICLASS_REGISTER_FAILED,
        UNICLASS_DELETE_FAILED,
        UNICLASS_UPDATE_FAILED,
        UNICLASS_GET_SUCCESS,
        UNICLASS_REGISTER_SUCCESS,
        UNICLASS_DELETE_SUCCESS,
        UNICLASS_UPDATE_SUCCESS
    }

    public UniclassesResult(UniclassesResponse data)
    {
        this.uniclassesData = data;
    }

    public ListResultEntity<UniclassEntity> GetResult() {
        ListResultEntity<UniclassEntity> uniclassEnt = new ListResultEntity("", null, ListResultEntity.Type.CARD_UNICLASS);
        switch(GetType()) {
            case UNICLASS_GET_FAILED:
                uniclassEnt.SetResponseMessage("Failed to get uniclasses!");
                break;
            case UNICLASS_GET_SUCCESS:
                uniclassEnt.SetResponseMessage("Successfully got uniclasses!");
                uniclassEnt.SetList(uniclassesData.GetUniclasses());
                break;
            default:
                uniclassEnt.SetResponseMessage("An unknown error occured!");
                break;
        }

        return uniclassEnt;
    }

    public Type GetType() {
        return Type.values()[Integer.parseInt(uniclassesData.GetResponse())];
    }

    public List<UniclassEntity> GetUniclasses() { return uniclassesData.GetUniclasses(); }
}