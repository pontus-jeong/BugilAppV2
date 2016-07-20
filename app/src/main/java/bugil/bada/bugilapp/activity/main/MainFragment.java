package bugil.bada.bugilapp.activity.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import bugil.bada.bugilapp.BugilTalk;
import bugil.bada.bugilapp.Notice;
import bugil.bada.bugilapp.R;
import bugil.bada.bugilapp.SchoolNotice;
import bugil.bada.bugilapp.StopWatch;
import bugil.bada.bugilapp.activity.bap.BapActivity;
import bugil.bada.bugilapp.activity.exam.ExamTimeActivity;
import bugil.bada.bugilapp.activity.notice.NoticeActivity;
import bugil.bada.bugilapp.activity.schedule.ScheduleActivity;
import bugil.bada.bugilapp.activity.timetable.TimeTableActivity;
import bugil.bada.bugilapp.talk;
import bugil.bada.bugilapp.tool.BapTool;
import bugil.bada.bugilapp.tool.Preference;
import bugil.bada.bugilapp.tool.RecyclerItemClickListener;
import bugil.bada.bugilapp.tool.TimeTableTool;
import bugil.bada.bugilapp.tool.Tools;


/**
 * Created by whdghks913 on 2015-11-30.
 */
public class MainFragment extends Fragment {
    private ConnectivityManager cManager;
    private NetworkInfo mobile;
    private NetworkInfo wifi;




    public static Fragment getInstance(int code) {
        MainFragment mFragment = new MainFragment();

        Bundle args = new Bundle();
        args.putInt("code", code);
        mFragment.setArguments(args);

        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recyclerview, container, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        final MainAdapter mAdapter = new MainAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View mView, int position) {
                boolean isSimple = mAdapter.getItemData(position).isSimple;



                if (isSimple) {
                    switch (position) {
                        case 0:

                            startActivity(new Intent(getActivity(), BugilTalk.class));
                            break;
                        case 1:
                            startActivity(new Intent(getActivity(), BapActivity.class));
                            break;
                        case 2:

                           startActivity(new Intent(getActivity(), TimeTableActivity.class));
                            break;

                        case 3:
                            startActivity(new Intent(getActivity(), StopWatch.class));
                            break;
                    }
                } else {
                    switch (position) {
                        case 0:
                            startActivity(new Intent(getActivity(), ScheduleActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(getActivity(), SchoolNotice.class));
                            break;
                        case 2:
                            startActivity(new Intent(getActivity(), talk.class));
                            break;
                        //break;
                        //case 2:
                        //startActivity(new Intent(getActivity(), ExamTimeActivity.class));
                        //break;

                    }
                }
            }
        }));

        Bundle args = getArguments();
        int code = args.getInt("code");
        Preference mPref = new Preference(getActivity());

        if (code == 1) {
            mAdapter.addItem(R.drawable.btalkico,
                    getString(R.string.title_activity_bugil_talk),
                    getString(R.string.message_activity_bugil_talk), true);


            // SimpleView
            if (mPref.getBoolean("simpleShowBap", true)) {
                BapTool.todayBapData mBapData = BapTool.getTodayBap(getActivity());
                mAdapter.addItem(R.drawable.mealico,
                        getString(R.string.title_activity_bap),
                        getString(R.string.message_activity_bap),
                        mBapData.title,
                        mBapData.info);
            } else {
                mAdapter.addItem(R.drawable.mealico,
                        getString(R.string.title_activity_bap),
                        getString(R.string.message_activity_bap), true);
            }


            if (mPref.getBoolean("simpleShowTimeTable", true)) {
                TimeTableTool.todayTimeTableData mTimeTableData = TimeTableTool.getTodayTimeTable(getActivity());
                mAdapter.addItem(R.drawable.ttico,
                        getString(R.string.title_activity_time_table),
                        getString(R.string.message_activity_time_table),
                        mTimeTableData.title,
                        mTimeTableData.info);
            } else {
                mAdapter.addItem(R.drawable.ttico,
                        getString(R.string.title_activity_time_table),
                        getString(R.string.message_activity_time_table), true);
            }
            mAdapter.addItem(R.drawable.timerico,
                    getString(R.string.title_activity_stop_watch),
                    getString(R.string.message_activity_stop_watch), true);

        } else {
            // DetailedView
            /**mAdapter.addItem(R.drawable.notice,
             getString(R.string.title_activity_notice),
             getString(R.string.message_activity_notice));**/
            mAdapter.addItem(R.drawable.calico,
                    getString(R.string.title_activity_schedule),
                    getString(R.string.message_activity_schedule));
            mAdapter.addItem(R.drawable.bico1,
                    getString(R.string.title_activity_notice1),
                    getString(R.string.message_activity_notice1));
            mAdapter.addItem(R.drawable.bico1,
                    "테스트",
                    "테스트");


            /**mAdapter.addItem(R.drawable.bugil,
             getString(R.string.title_activity_exam_range),
             getString(R.string.message_activity_exam_range));**/
        }

        return recyclerView;
    }

    public void callBrowser(String url) {
        try {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}


