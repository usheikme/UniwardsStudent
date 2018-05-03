package xyz.uniwards.uniwards_student.APIHandling;

import android.os.AsyncTask;
import android.util.Log;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Umayr on 5/2/2018.
 */

public class RequestThread implements Runnable {
    private Queue<ReqThreadEntity> requestQ = new LinkedList<>();
    private Object lock = new Object();

    public RequestThread() {
    }

    public void AddRequest(ReqThreadEntity reqEmt) {
        this.requestQ.add(reqEmt);
    }

    public Integer ElementsInQ() {
        return requestQ.size();
    }

    public void run() {
        while (true) {
            try {
                if (requestQ.size() > 0) {
                    final ReqThreadEntity reqMe = requestQ.remove();
                    reqMe.GetActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            synchronized (lock) {
                                try {
                                    reqMe.GetTask().execute().get();
                                } catch (Exception e) {
                                    Log.wtf("requestQ", e.toString());
                                }
                                lock.notify();
                            }
                        }
                    });
                }
            } catch (Exception e) {
                Log.wtf("requestQ", e.toString());
            }
        }
    }
}
