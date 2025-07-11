package xyz.uniwards.uniwards_student.MainScreens.Popups.DropEnrolment;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import xyz.uniwards.uniwards_student.APIHandling.ReqThreadEntity;
import xyz.uniwards.uniwards_student.DashCardHandle.AsyncDashCard;
import xyz.uniwards.uniwards_student.Globals;
import xyz.uniwards.uniwards_student.MainScreens.Popups.Enrol.AsyncEnrol;
import xyz.uniwards.uniwards_student.MainScreens.Popups.Enrol.EnrolAdaptor;
import xyz.uniwards.uniwards_student.R;

public class PopupDropActivity extends AppCompatActivity {
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    protected DropAdaptor mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    public PopupDropActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_drop);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_drop);
        mLayoutManager = new LinearLayoutManager(this);
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        InitExitButtonAsClickable(this);

        ReqThreadEntity request = new ReqThreadEntity(this, new AsyncDrop(this, this.mAdapter, this.mRecyclerView));
        Globals.getInstance().GetReqThread().AddRequest(request);
    }


    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(this, SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(this);
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(this);
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void InitExitButtonAsClickable(final Activity thisAct) {
        final Button button_dfinish =findViewById(R.id.button_dfinish);
        button_dfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {thisAct.finish();
            }
        });
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

}