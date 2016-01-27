package bugil.bada.bugilapp;

import bugil.bada.bugilapp.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class BugilTalk extends Activity {
    WebView bWeb;
    private Handler mmHandler;
    private ProgressDialog mmProgressDialog;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //액션바 숨기기
        Window win = getWindow();
        win.requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_bugil_talk);
        //핸들러 사용
        mmHandler = new Handler();

        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mmProgressDialog = ProgressDialog.show(BugilTalk.this,"",
                        "잠시만 기다려 주세요.",true);
                mmHandler.postDelayed( new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            if (mmProgressDialog!=null&&mmProgressDialog.isShowing()){
                                mmProgressDialog.dismiss();
                            }
                        }
                        catch ( Exception e )
                        {
                            e.printStackTrace();
                        }
                    }
                }, 2000);
            }
        } );
        //webview 출력
        bWeb = (WebView)findViewById(R.id.boardweb);
        bWeb.setWebViewClient(new BBWeb());
        WebSettings goo = bWeb.getSettings();
        goo.setJavaScriptEnabled(true);
        goo.setBuiltInZoomControls(true);
        bWeb.loadUrl("http://bugilstudent.azurewebsites.net/xe/");

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (bWeb.canGoBack()) {
                bWeb.goBack();
            } else {
                bWeb.clearCache(true);
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
//webview Client가 url를 불러오게 하기
class BBWeb extends WebViewClient {
    public boolean shouldOverrideUrlLoading(WebView bbview, String qurl) {
        bbview.loadUrl(qurl);
        return true;
    }
}
