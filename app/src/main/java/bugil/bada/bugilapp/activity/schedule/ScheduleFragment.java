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
                mAdapter.addItem("삼일절", "2016.03.01 (화)" ,true);
                mAdapter.addItem("개교기념식/입학식", "2016.03.02 (수)");
                mAdapter.addItem("1학기 과제 연구 편성", "2016.03.03 (목)");
                mAdapter.addItem("전국연합학력평가(1,2,3)", "2016.03.10 (목)");
                mAdapter.addItem("개교기념휴무/1학기 방과후학교신청", "2016.03.11 (금)" ,true);
                mAdapter.addItem("정부반장 및 장학증서 수여", "2016.03.14 (월)");
                mAdapter.addItem("정기외박", "2016.03.25 (금)" ,true);
                mAdapter.addItem("학생회장선거", "2016.03.30 (수)");
                break;
            case 4:
                mAdapter.addItem("학부모 아카데미", "2016.04.02 (토)" ,true);
                mAdapter.addItem("전국연합학력평가(3)", "2016.04.06 (수)");
                mAdapter.addItem("국회의원선거일", "2016.04.16 (토)" ,true);
                mAdapter.addItem("듣기평가(1)", "2016.04.19 (화)");
                mAdapter.addItem("듣기평가(2)", "2016.04.20 (수)");
                mAdapter.addItem("듣기평가(3)", "2016.04.21 (목)");
                mAdapter.addItem("정기외박", "2016.04.29 (금)" ,true);
                break;
            case 5:
                mAdapter.addItem("어린이날", "2016.05.05 (목)" ,true);
                mAdapter.addItem("학부모컨퍼런스", "2016.05.07 (토)" ,true);
                mAdapter.addItem("중간고사(1,2,3)", "2016.05.10 (화)");
                mAdapter.addItem("중간고사(1,2,3)", "2016.05.11 (수)");
                mAdapter.addItem("중간고사(1,2,3)", "2016.05.12 (목)");
                mAdapter.addItem("북일정신함양", "2016.05.13 (금)");
                mAdapter.addItem("석가탄신일", "2016.05.14 (토)" ,true);
                mAdapter.addItem("대학연계R&E신청(~26) / STSY 참가(~21)", "2016.05.16 (월)");
                mAdapter.addItem("학생회간부수련회(~22)", "2016.05.21 (토)" ,true);
                mAdapter.addItem("체육대회 정기외박", "2016.05.27 (금)" ,true);
                mAdapter.addItem("1차 학교 설명회", "2016.05.28 (토)" ,true);
                break;
            case 6:
                mAdapter.addItem("전국연합학력평가(1,2) /수능모의평가(3)", "2016.06.02 (목)");
                mAdapter.addItem("현충일", "2016.06.06 (월)" ,true);
                mAdapter.addItem("하계방과후학교계획", "2016.06.07 (화)");
                mAdapter.addItem("국가수준 학업성취도 평가(2)", "2016.06.21 (화)");
                mAdapter.addItem("1차공개수업", "2016.06.23 (목)");
                mAdapter.addItem("정기외박", "2016.06.24 (금)" ,true);
                mAdapter.addItem("하계방과후학교신청", "2016.06.25 (토)" ,true);
                break;
            case 7:
                mAdapter.addItem("학부모컨퍼런스", "2016.07.02 (토)" ,true);
                mAdapter.addItem("HRSY참가(~8)", "2016.07.03 (일)" ,true);
                mAdapter.addItem("2학기방과후계획", "2016.07.04 (월)");
                mAdapter.addItem("기말고사(1,2) 전국연합학력평가(3)", "2016.07.06 (수)");
                mAdapter.addItem("기말고사(1,2,3)", "2016.07.07 (목)");
                mAdapter.addItem("기말고사(1,2,3)", "2016.07.08 (금)");
                mAdapter.addItem("기말고사(3) 2학기과제연구편성", "2016.07.11 (월)");
                mAdapter.addItem("과제연구보고서제출", "2016.07.14 (목)");
                mAdapter.addItem("하계방학식", "2016.07.15 (금)" ,true);
                mAdapter.addItem("2차학교설명회 2학기/ 방과후신청", "2016.07.16 (토)" ,true);
                mAdapter.addItem("하계방과후학교개강(~29)", "2016.07.18 (월)");
                break;
            case 8:
                mAdapter.addItem("SRC참가(~20)", "2016.08.14 (일)" ,true);
                mAdapter.addItem("광복절", "2016.08.15 (월)" ,true);
                mAdapter.addItem("개학", "2016.08.16 (화)");
                mAdapter.addItem("2학기과제연구개강", "2016.08.18 (목)");
                mAdapter.addItem("3차학교설명회", "2016.08.27 (토)" ,true);
                mAdapter.addItem("정부반장 및 장학증서수여", "2016.08.29 (월)");
                break;
            case 9:
                mAdapter.addItem("전국연합학력평가(1,2) /수능모의평가(3)", "2016.09.01 (목)");
                mAdapter.addItem("학부모컨퍼런스", "2016.09.03 (토)" ,true);
                mAdapter.addItem("SRC/HRSY신청 및 선발(~23)", "2016.09.05 (월)");
                mAdapter.addItem("추석연휴", "2016.09.14 (수)" ,true);
                mAdapter.addItem("추석", "2016.09.15 (목)" ,true);
                mAdapter.addItem("추석연휴", "2016.09.16 (금)" ,true);
                mAdapter.addItem("듣기평가(1)", "2016.09.20 (화)");
                mAdapter.addItem("듣기평가(2)", "2016.09.21 (수)");
                mAdapter.addItem("듣기평가(3)", "2016.09.22 (목)");
                mAdapter.addItem("4차학교설명회", "2016.09.24 (토)" ,true);
                mAdapter.addItem("교원능력개발학생 만족도조사(~28)", "2016.09.26 (월)");
                mAdapter.addItem("2차공개수업(~30)", "2016.09.29 (목)");
                mAdapter.addItem("정기외박", "2016.09.30 (금)" ,true);
                break;
            case 10:
                mAdapter.addItem("개천절 /교원능력개발학부모만족도조사(~7)", "2016.10.03 (월)" ,true);
                mAdapter.addItem("5차학교설명회", "2016.10.08 (토)" ,true);
                mAdapter.addItem("한글날", "2016.10.09 (일)" ,true);
                mAdapter.addItem("전국연합학력평가(3)", "2016.10.11 (화)");
                mAdapter.addItem("중간고사(1,2,3)", "2016.10.12 (수)");
                mAdapter.addItem("중간고사(1,2,3)", "2016.10.13 (목)");
                mAdapter.addItem("중간고사(1,2,3)", "2016.10.14 (금)");
                mAdapter.addItem("신입생원서접수 (~19)", "2016.10.16 (일)" ,true);
                mAdapter.addItem("동료교원평가(~21) 화청교환학생신청 및 선정(~28)", "2016.10.17 (월)");
                mAdapter.addItem("북일정신함양", "2016.10.19 (수)");
                mAdapter.addItem("신입생1단계 합격자발표", "2016.10.21 (금)");
                mAdapter.addItem("1단계합격자 자소서 및 교사추천서(~27)", "2016.10.25 (화)");
                mAdapter.addItem("SRC 1차연구(~11/5)", "2016.10.30 (일)" ,true);
                break;
            case 11:
                mAdapter.addItem("신입생 전형(~6) /정기외박", "2016.11.01 (화)" ,true);
                mAdapter.addItem("재량휴업(~4 /SRC1차연구(~8)", "2016.11.02 (수)" ,true);
                mAdapter.addItem("광역면접일", "2016.11.05 (토)" ,true);
                mAdapter.addItem("전국면접일", "2016.11.06 (일)" ,true);
                mAdapter.addItem("동계방과후학교계획 (~11)", "2016.11.07 (월)");
                mAdapter.addItem("신입생최종합격자 발표", "2016.11.11 (금)");
                mAdapter.addItem("화청문화교류(~25)", "2016.11.16 (수)");
                mAdapter.addItem("2017대입수능", "2016.11.17 (목)");
                mAdapter.addItem("신입생소집(국제)", "2016.11.19 (토)" ,true);
                mAdapter.addItem("3학년 기말고사(~23)", "2016.11.21 (월)");
                mAdapter.addItem("전국연합학력평가(1,2)", "2016.11.23 (수)");
                mAdapter.addItem("학술/예술제(~25)", "2016.11.24 (목)");
                mAdapter.addItem("동계방과후학교신청", "2016.11.26 (토)" ,true);
                break;
            case 12:
                mAdapter.addItem("정기외박", "2016.12.02 (금)" ,true);
                mAdapter.addItem("신입생소집(일반) / 학부모컨퍼런스", "2016.12.03 (토)" ,true);
                mAdapter.addItem("기말고사(1,2)", "2016.12.21 (수)");
                mAdapter.addItem("기말고사(1,2)", "2016.12.22 (목)");
                mAdapter.addItem("기말고사(1,2) / 정기외박 /3학년방학", "2016.12.23 (금)" ,true);
                mAdapter.addItem("성탄절", "2016.12.25 (일)" ,true);
                mAdapter.addItem("과제연구보고서제출", "2016.12.29 (목)");
                mAdapter.addItem("1,2년 방학", "2016.12.30 (금)" ,true);
                break;
            case 1:
                mAdapter.addItem("동계방과후학교개강 (~20)", "2017.01.02 (월)");
                mAdapter.addItem("신입생실력고사", "2017.01.14 (토)" ,true);
                mAdapter.addItem("STSY참가 (~21일)", "2017.01.15 (일)" ,true);
                mAdapter.addItem("교직원연수(~24)", "2017.01.23 (월)");
                mAdapter.addItem("설연휴", "2017.01.27 (금)" ,true);
                mAdapter.addItem("설날", "2017.01.28 (토)" ,true);
                mAdapter.addItem("설대체휴일", "2017.01.30 (월)" ,true);
                break;
            case 2:
                mAdapter.addItem("개학", "2017.02.01 (수)");
                mAdapter.addItem("졸업식(3)", "2017.02.09 (목)" ,true);
                mAdapter.addItem("1,2학년 종업식", "2017.02.10 (금)" ,true);
                mAdapter.addItem("신입생적응캠프 (~24)", "2017.02.20 (월)");
                break;
        }

        return recyclerView;
    }
}
