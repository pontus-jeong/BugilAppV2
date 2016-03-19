package bugil.bada.bugilapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

//import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;


public class BugilTalk extends AppCompatActivity {
    private WebView web;

    private ProgressBar progress;
    private ContentLoadingProgressBar testprogress;
    //ProgressBarCircularIndeterminate ttprogress;
    Toast toast;
    private BackPressCloseHandler backPressCloseHandler;



    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



        ConnectivityManager cm =

                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);



        NetworkInfo ni = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        boolean isWifiConn = ni.isConnected();



        ni = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);



        boolean isMobileConn = ni.isConnected();
        backPressCloseHandler = new BackPressCloseHandler(this);



        if (!isWifiConn && !isMobileConn) {

            Toast.makeText(this, "인터넷에 접속되어 있지 않습니다!", Toast.LENGTH_SHORT)

                    .show();

            finish();//액티비티 종료

        } else {

            setContentView(R.layout.activity_bugil_talk);



            progress = (ProgressBar) findViewById(R.id.web_progress);
            testprogress =(ContentLoadingProgressBar) findViewById(R.id.testprogress);
            //ttprogress = (ProgressBarCircularIndeterminate) findViewById(R.id.progressBarCircularIndeterminate);



            web = (WebView) findViewById(R.id.web);

            web.getSettings().setJavaScriptEnabled(true);

            web.getSettings().setBuiltInZoomControls(true);

            web.setHorizontalScrollbarOverlay(true);

            web.setVerticalScrollbarOverlay(true);


            web.loadUrl("http://bugilstudent.azurewebsites.net/xe/");


            web.setWebViewClient(new WebViewClient() {

// 링크 클릭에 대한 반응

                @Override

                public boolean shouldOverrideUrlLoading(WebView view, String url) {

                    view.loadUrl(url);

                    return true;

                }


// 웹페이지 호출시 오류 발생에 대한 처리

                @Override

                public void onReceivedError(WebView view, int errorcode,

                                            String description, String fallingUrl) {

                    Toast.makeText(BugilTalk.this,

                            "오류 : " + description, Toast.LENGTH_SHORT).show();

                }

// 페이지 로딩 시작시 호출

                @Override

                public void onPageStarted(WebView view, String url, Bitmap favicon) {

                    progress.setVisibility(View.VISIBLE);
                    //testprogress.show();
                    //ttprogress.setVisibility(View.VISIBLE);


                }

//페이지 로딩 종료시 호출

                public void onPageFinished(WebView view, String Url) {

                    progress.setVisibility(View.GONE);
                    //testprogress.hide();
                    //ttprogress.setVisibility(View.GONE);

                }

            });

            web.setWebChromeClient(new WebChromeClient());

        }

    }

    public boolean onKeyDown(int keyCode, KeyEvent event){

        if(keyCode ==KeyEvent.KEYCODE_BACK && web.canGoBack()){

            web.goBack();

        }else if(keyCode ==KeyEvent.KEYCODE_BACK && !web.canGoBack()){

            backPressCloseHandler.onBackPressed();

        }

        return true;

    }



}