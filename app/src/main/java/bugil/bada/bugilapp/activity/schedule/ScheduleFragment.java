package bugil.bada.bugilapp.activity.schedule;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import bugil.bada.bugilapp.R;
import bugil.bada.bugilapp.tool.RecyclerItemClickListener;

/**
 * Created by whdghks913 on 2015-12-10.
 */
public class ScheduleFragment extends Fragment {

    public static Fragment getInstance(int month) {
        ScheduleFragment mFragment = new ScheduleFragment();

        Bundle args = new Bundle();
        args.putInt("month", month);
        mFragment.setArguments(args);

        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recyclerview, container, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        final ScheduleAdapter mAdapter = new ScheduleAdapter();
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View mView, int position) {
                try {
                    String date = mAdapter.getItemData(position).date;
                    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy.MM.dd (E)", Locale.KOREA);

                    Calendar mCalendar = Calendar.getInstance();
                    long nowTime = mCalendar.getTimeInMillis();

                    mCalendar.setTime(mFormat.parse(date));
                    long touchTime = mCalendar.getTimeInMillis();

                    long diff = (touchTime - nowTime);

                    boolean isPast = false;
                    if (diff < 0) {
                        diff = -diff;
                        isPast = true;
                    }

                    int diffDays = (int) (diff /= 24 * 60 * 60 * 1000);
                    String mText = "";

                    if (diffDays == 0)
                        mText += "오늘 일정입니다.";
                    else if (isPast)
                        mText = "선택하신 날짜는 " + diffDays + "일전 날짜입니다";
                    else
                        mText = "선택하신 날짜까지 " + diffDays + "일 남았습니다";

                    Snackbar.make(mView, mText, Snackbar.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }));

        Bundle args = getArguments();
        int month = args.getInt("month");

        switch (month) {
            case 3:
                mAdapter.addItem("입학식", "2015.03.02 (월)");
                mAdapter.addItem("전국연합학력평가(전학년)", "2015.03.11 (수)");
                mAdapter.addItem("임시외박(~15)", "2015.03.12 (목)", true);
                mAdapter.addItem("1학기 방과후 신청/개교기념일 휴무", "2015.03.13 (금)");
                mAdapter.addItem("학부모 상담주간(~27)", "2015.03.23 (월)");
                mAdapter.addItem("학부모총회/정기외박", "2015.03.27 (금)", true);
                break;
            case 4:
                mAdapter.addItem("전국연합학력평가(3학년)", "2015.04.09 (목)");
                mAdapter.addItem("벚꽃제(학교 개방의 날)", "2015.04.11 (토)", true);
                mAdapter.addItem("학부모컨퍼런스/정기외박", "2015.04.09 (금)", true);
                break;
            case 5:
                mAdapter.addItem("1회고사(전학년)", "2015.05.04 (월)");
                mAdapter.addItem("1회고사(전학년)", "2015.05.06 (수)");
                mAdapter.addItem("1회고사(전학년)", "2015.05.07 (목)");
                mAdapter.addItem("북일정신함양프로그램", "2015.05.08 (금)");
                mAdapter.addItem("체육대회/정기외박", "2015.05.22 (금)", true);
                mAdapter.addItem("1차 학교 설명회", "2015.05.30 (토)");
                break;
            case 6:
                mAdapter.addItem("전국연합학력평가(1,2학년)/대수능모의평가(3학년)", "2015.06.04 (목)");
                mAdapter.addItem("학업성취도평가(2학년)", "2015.06.24 (수)");
                mAdapter.addItem("수업공개기간(~26)", "2015.06.25 (목)");
                mAdapter.addItem("정기외박", "2015.06.26 (금)", true);
                mAdapter.addItem("하계 방과후 신청", "2015.06.27 (토)");
                break;
            case 7:
                mAdapter.addItem("전국연합학력평가(3학년)", "2015.07.09 (목)");
                mAdapter.addItem("2회고사(전학년)", "2015.07.10 (금)");
                mAdapter.addItem("2회고사(전학년)", "2015.07.13 (월)");
                mAdapter.addItem("2회고사(전학년)", "2015.07.14 (화)");
                mAdapter.addItem("하계방학식", "2015.07.17 (금)", true);
                mAdapter.addItem("2차 학교설명회/2학기 방과후 신청", "2015.07.18 (토)");
                mAdapter.addItem("하계 방과후학교(~8/7)", "2015.07.27 (월)");
                break;
            case 8:
                mAdapter.addItem("개학/학부모 상담주간(~28)", "2015.08.17 (월)");
                mAdapter.addItem("1회고사(3학년)", "2015.08.19 (수)");
                mAdapter.addItem("1회고사(3학년)", "2015.08.20 (목)");
                mAdapter.addItem("1회고사(3학년)", "2015.08.21 (금)");
                mAdapter.addItem("3차 학교설명회", "2015.08.22 (토)");
                break;
            case 9:
                mAdapter.addItem("전국연합학력평가(1,2학년)/대수능모의평가(3학년)", "2015.09.02 (수)");
                mAdapter.addItem("학부모컨퍼런스", "2015.09.05 (토)");
                mAdapter.addItem("4차 학교설명회", "2015.09.12 (토)");
                mAdapter.addItem("수업공개기간(~25)", "2015.09.24 (목)");
                mAdapter.addItem("정기외박", "2015.09.25 (금)", true);
                mAdapter.addItem("추석연휴", "2015.09.26 (토)", true);
                mAdapter.addItem("추석연휴", "2015.09.27 (일)", true);
                mAdapter.addItem("추석연휴", "2015.09.28 (월)", true);
                mAdapter.addItem("대체휴일", "2015.09.29 (화)", true);
                break;
            case 10:
                mAdapter.addItem("5차 학교설명회", "2015.10.03 (토)");
                mAdapter.addItem("1회고사(1,2학년)", "2015.10.06 (월)");
                mAdapter.addItem("1회고사(1,2학년)", "2015.10.07 (화)");
                mAdapter.addItem("1회고사(1,2학년)", "2015.10.08 (수)");
                mAdapter.addItem("북일정신함양프로그램", "2015.10.09 (목)");
                mAdapter.addItem("전국연합학력평가(3학년)", "2015.10.13 (화)");
                mAdapter.addItem("정기외박", "2015.10.28 (수)", true);
                mAdapter.addItem("재량휴업(1,2학년)", "2015.10.29 (목)", true);
                mAdapter.addItem("재량휴업(1,2학년)", "2015.10.30 (금)", true);
                break;
            case 11:
                mAdapter.addItem("재량휴업(1,2학년)", "2015.11.02 (월)", true);
                mAdapter.addItem("2016 대입수능시험", "2015.11.12 (목)");
                mAdapter.addItem("2회고사(3학년)", "2015.11.16 (월)");
                mAdapter.addItem("전국연합학력평가(1,2학년)/2회고사(3학년)", "2015.11.17 (화)");
                mAdapter.addItem("2회고사(3학년)", "2015.11.18 (수)");
                mAdapter.addItem("학술예술제", "2015.11.26 (목)");
                mAdapter.addItem("학술예술제/정기외박", "2015.11.27 (금)", true);
                mAdapter.addItem("동계방과후 신청", "2015.11.28 (토)");
                break;
            case 12:
                mAdapter.addItem("개학/학부모 상담주간(~28)", "2015.08.17 (월)");
                mAdapter.addItem("2회고사(1,2학년)", "2015.12.21 (월)");
                mAdapter.addItem("2회고사(1,2학년)", "2015.12.22 (화)");
                mAdapter.addItem("2회고사(1,2학년)", "2015.12.23 (수)");
                mAdapter.addItem("방학식(3학년)", "2015.12.24 (목)", true);
                mAdapter.addItem("방학식(1,2학년)", "2015.12.30 (수)", true);
                break;
            case 1:
                mAdapter.addItem("신정", "2016.01.01 (금)", true);
                mAdapter.addItem("시무식/동계방과후학교(~22일)", "2016.01.04 (월)");
                mAdapter.addItem("STAY 참가(~15일)", "2016.01.11 (월)");
                mAdapter.addItem("2016학년도 신입생 진단평가", "2016.01.16 (토)");
                mAdapter.addItem("교직원 연수(~26일)", "2016.01.25 (월)");
                break;
            case 2:
                mAdapter.addItem("개학식", "2016.02.01 (월)");
                mAdapter.addItem("졸업식", "2016.02.04 (목)");
                mAdapter.addItem("종업식", "2016.02.05 (금)", true);
                mAdapter.addItem("설날", "2016.02.08 (월)", true);
                mAdapter.addItem("설연휴", "2016.02.09 (화)", true);
                mAdapter.addItem("설날대체휴무", "2016.02.10 (수)", true);
                mAdapter.addItem("신입생 적응캠프(~26일)", "2016.02.22 (월)");
                break;
        }

        return recyclerView;
    }
}
