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



public class Notice extends Activity {
    WebView cWeb;
    private Handler mmHandler;
    private ProgressDialog mmProgressDialog;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //액션바 숨기기
        Window win = getWindow();
        win.requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_notice2);
        //핸들러 사용
        mmHandler = new Handler();

        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mmProgressDialog = ProgressDialog.show(Notice.this,"",
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
        cWeb = (WebView)findViewById(R.id.noticeweb);
        cWeb.setWebViewClient(new CCWeb());
        WebSettings goo = cWeb.getSettings();
        goo.setJavaScriptEnabled(true);
        goo.setBuiltInZoomControls(true);
        cWeb.loadUrl("http://m.bugil.hs.kr/mobile/news/notice_list.jsp");

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (cWeb.canGoBack()) {
                cWeb.goBack();
            } else {
                cWeb.clearCache(true);
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
//webview Client가 url를 불러오게 하기
class CCWeb extends WebViewClient {
    public boolean shouldOverrideUrlLoading(WebView ccview, String qurl) {
        ccview.loadUrl(qurl);
        return true;
    }
}
