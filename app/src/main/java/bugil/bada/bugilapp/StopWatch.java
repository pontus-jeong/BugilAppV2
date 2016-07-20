package bugil.bada.bugilapp;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class StopWatch extends AppCompatActivity {
    TextView mEllapse;
    TextView mEllapse_rest;
    //Button mBtnStart;
    Button mBtnSplit;
    FloatingActionButton mBtnReset;
    FloatingActionButton mBtnStart;
    final static int IDLE = 0;
    final static int RUNNING = 1;
    final static int PAUSE = 2;
    long mPauseTime;
    long mBaseTime;
    //TextView mSplit;
    int mSplitCount;
    int mStatus = 0;

    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mEllapse = (TextView)findViewById(R.id.ellapse);
        mEllapse_rest = (TextView)findViewById(R.id.sellapse);
        //mSplit = (TextView)findViewById(R.id.split);
        mBtnStart = (FloatingActionButton)findViewById(R.id.testbtn);
        //mBtnSplit = (Button)findViewById(R.id.btnsplit);

        mBtnReset = (FloatingActionButton) findViewById(R.id.resetbtn);
        mBtnReset.hide();
        mBtnReset.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
        mBtnStart.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorLightBlue)));
        /**fab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
            }
            });**/
        }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in.
        // TODO: Add code to check if user is signed in.
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    public void onDestroy(){
        mTimerStudy.sendEmptyMessage(0);
        mTimerRest.sendEmptyMessage(0);
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();

    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.testbtn:
                switch (mStatus) {
                    case IDLE:
                        mBaseTime = SystemClock.elapsedRealtime();
                        mTimerStudy.sendEmptyMessage(0);
                        mBtnStart.setImageResource(R.drawable.ic_pause_white_36dp);
                        mBtnStart.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorOrange)));
                        //mBtnSplit.setEnabled(true);
                        mBtnReset.show();
                        mStatus = RUNNING;
                        break;
                    case RUNNING:
                            mTimerStudy.removeMessages(0);
                        mTimerRest.sendEmptyMessage(0);
                            mPauseTime = SystemClock.elapsedRealtime();
                            mBtnStart.setImageResource(R.drawable.ic_play_arrow_white_36dp);
                        mBtnStart.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorLightBlue)));
                            //mBtnSplit.setText("초기화");
                            mStatus = PAUSE;
                                    break;
                    case PAUSE:
                        long l = SystemClock.elapsedRealtime();

                        mBaseTime += l - mPauseTime;
                        mTimerStudy.sendEmptyMessage(0);
                        mTimerRest.removeMessages(0);

                        mBtnStart.setImageResource(R.drawable.ic_pause_white_36dp);
                        mBtnStart.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorOrange)));
                        //mBtnSplit.setText("기록");
                        mStatus = RUNNING;
                        break;
                }
                break;

            case R.id.resetbtn:
                /**switch (mStatus) {
                    case RUNNING:
                        //String sSplit = mSplit.getText().toString();
                        //sSplit += String.format("%d => %s\n", mSplitCount, getStudy());
                        //mSplit.setText(sSplit);
                        //mSplitCount++;
                        break;
                    case PAUSE:**/
                        mTimerStudy.removeMessages(0);
                        mTimerRest.removeMessages(0);
                mBtnStart.setImageResource(R.drawable.ic_play_arrow_white_36dp);
                mBtnStart.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorLightBlue)));
                        //mBtnSplit.setText("기록");
                        mEllapse.setText("00:00:00");
                        mEllapse_rest.setText("00:00:00");
                        mStatus = IDLE;
                        //mSplitCount = 0;
                        //mSplit.setText("");
                        //mBtnSplit.setEnabled(false);
                        mBtnReset.hide();
                        break;
                //}

                //break;

        }
    }

    Handler mTimerRest = new Handler() {
        public void handleMessage(Message msg) {
            mEllapse_rest.setText(getRest());
            mTimerRest.sendEmptyMessage(0);
        }
    };

    Handler mTimerStudy = new Handler() {
        public void handleMessage(Message msg) {
            mEllapse.setText(getStudy());
            mTimerStudy.sendEmptyMessage(0);
        }
    };

    String getRest() {
        long ell = SystemClock.elapsedRealtime() - mPauseTime;
        String sEll = String.format("%02d:%02d:%02d", ell / 1000/60/60,
                ell / 1000 / 60, (ell / 1000) % 60);
        return sEll;
    }

    String getStudy() {
        long Snow = SystemClock.elapsedRealtime();
        long Sell = Snow - mBaseTime;
        String sSEll = String.format("%02d:%02d:%02d", Sell / 1000/60/60,
                Sell / 1000 / 60, (Sell / 1000) % 60);
        return sSEll;
    }

}
