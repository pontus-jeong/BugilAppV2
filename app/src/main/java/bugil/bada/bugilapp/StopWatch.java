package bugil.bada.bugilapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

public class StopWatch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void startButton(View view) {
        ((Chronometer) findViewById(R.id.chronometer)).start();
        ((Chronometer) findViewById(R.id.schronometer)).stop();
    }

    public void stopButton(View view) {
        ((Chronometer) findViewById(R.id.chronometer)).stop();
        ((Chronometer) findViewById(R.id.schronometer)).start();
    }

    public void resetButton(View view) {
        ((Chronometer) findViewById(R.id.chronometer)).setBase(SystemClock.elapsedRealtime());
        ((Chronometer) findViewById(R.id.schronometer)).setBase(SystemClock.elapsedRealtime());
    }

}
