package xyz.uniwards.uniwards_student.APIHandling;

import android.app.Activity;
import android.os.AsyncTask;

/**
 * Created by Umayr on 5/2/2018.
 */

public class ReqThreadEntity {
    private Activity reqActivity;
    private AsyncTask<Void, Void, Void> reqTask;

    public ReqThreadEntity(Activity reqActivity, AsyncTask<Void, Void, Void> reqTask) {
        this.reqActivity = reqActivity;
        this.reqTask = reqTask;
    }

    public Activity GetActivity() { return this.reqActivity; }
    public void SetActivity(Activity reqActivity) {this.reqActivity = reqActivity; }

    public AsyncTask<Void, Void, Void> GetTask() { return this.reqTask; }
    public void SetTask(AsyncTask<Void, Void, Void> reqTask) {this.reqTask = reqTask; }
}
