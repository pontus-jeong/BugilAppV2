package bugil.bada.bugilapp.activity.main;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import net.daum.adam.publisher.AdInterstitial;
import net.daum.adam.publisher.AdView;
import net.daum.adam.publisher.impl.AdError;

import java.util.ArrayList;
import java.util.List;

import bugil.bada.bugilapp.R;
import bugil.bada.bugilapp.activity.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {
    //AdInterstitial mAdInterstitial = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);

        //mAdInterstitial = new AdInterstitial(this);
        // 2. 전면형 광고 광고단위ID를 설정한다.
        //mAdInterstitial.setClientId("DAN-rhb2tygwzr5j");



/**
        // 광고 영역에 캐시 사용 여부 : 기본 값은 true
        mAdInterstitial.setAdCache(false);

        mAdInterstitial.setOnAdLoadedListener(new AdView.OnAdLoadedListener() {
            @Override
            public void OnAdLoaded() {
                Log.i("InterstitialTab", "광고가 로딩되었습니다.");
            }
        });
        // 4. (선택)전면형 광고 다운로드 실패시에 실행할 리스너
        mAdInterstitial.setOnAdFailedListener(new AdView.OnAdFailedListener() {
            @Override
            public void OnAdFailed(AdError error, String errorMessage) {
                Toast.makeText(MainActivity.this,
                        errorMessage, Toast.LENGTH_LONG).show();
            }
        });
        // 5. (선택)전면형 광고를 닫을 시에 실행할 리스너
        mAdInterstitial.setOnAdClosedListener (new AdView.OnAdClosedListener() {
            @Override
            public void OnAdClosed() {
                Log.i("InterstitialTab", "광고를 닫았습니다. ");
            }
        });
        // 6. 전면형 광고를 불러온다.
        mAdInterstitial.loadAd();
**/
        ViewPager viewPager = (ViewPager) findViewById(R.id.mViewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
    }
/**
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdInterstitial != null) {
            mAdInterstitial = null;
        }
    }
**/

    private void setupViewPager(ViewPager viewPager) {
        Adapter mAdapter = new Adapter(getSupportFragmentManager());

        mAdapter.addFragment(getString(R.string.activity_main_fragment_simple), MainFragment.getInstance(1));
        mAdapter.addFragment(getString(R.string.activity_main_fragment_detailed), MainFragment.getInstance(2));

        viewPager.setAdapter(mAdapter);
    }

    class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        public void addFragment(String mTitle, Fragment mFragment) {
            mFragments.add(mFragment);
            mFragmentTitles.add(mTitle);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
