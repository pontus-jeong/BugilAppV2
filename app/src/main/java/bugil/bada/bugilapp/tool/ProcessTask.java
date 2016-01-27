package bugil.bada.bugilapp.tool;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import toast.library.meal.MealLibrary;

/**
 * Created by 종환 on 2015-02-17.
 */
public abstract class ProcessTask extends AsyncTask<Integer, Integer, Long> {
    final Context mContext;

    final String CountryCode = "cne.go.kr"; // 접속 할 교육청 도메인
    final String schulCode = "N100000241"; // 학교 고유 코드
    final String schulCrseScCode = "4"; // 학교 종류 코드 1
    final String schulKndScCode = "04"; // 학교 종류 코드 2

    String[] Calender, Breakfast, Lunch, Dinner;

    public abstract void onPreDownload();

    public abstract void onUpdate(int progress);

    public abstract void onFinish(long result);

    public ProcessTask(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        onPreDownload();
    }

    /**
     * params[0] : year
     * params[1] : month
     * params[2] : day
     */
    @Override
    protected Long doInBackground(Integer... params) {
        publishProgress(5);

        final String CountryCode = "cne.go.kr"; // 접속 할 교육청 도메인
        final String schulCode = "N100000241"; // 학교 고유 코드
        final String schulCrseScCode = "4"; // 학교 종류 코드 1
        final String schulKndScCode = "04"; // 학교 종류 코드 2

        final String year = Integer.toString(params[0]);
        String month = Integer.toString(params[1] + 1);
        String day = Integer.toString(params[2]);

        if (month.length() <= 1)
            month = "0" + month;
        if (day.length() <= 1)
            day = "0" + day;

        publishProgress(25);

        try {
            Calender = MealLibrary.getDateNew(CountryCode, schulCode,
                    schulCrseScCode, schulKndScCode, "1", year, month, day);

            publishProgress(40);

            Breakfast = MealLibrary.getMealNew(CountryCode, schulCode,
                    schulCrseScCode, schulKndScCode, "1", year, month, day);

            publishProgress(60);

            Lunch = MealLibrary.getMealNew(CountryCode, schulCode,
                    schulCrseScCode, schulKndScCode, "2", year, month, day);

            publishProgress(80);

            Dinner = MealLibrary.getMealNew(CountryCode, schulCode,
                    schulCrseScCode, schulKndScCode, "3", year, month, day);

            BapTool.saveBapData(mContext, Calender, Breakfast, Lunch, Dinner);

            publishProgress(100);

        } catch (Exception e) {
            Log.e("ProcessTask Error", "Message : " + e.getMessage());
            Log.e("ProcessTask Error", "LocalizedMessage : " + e.getLocalizedMessage());

            e.printStackTrace();
            return -1l;
        }
        return 0l;
    }

    @Override
    protected void onProgressUpdate(Integer... params) {
        onUpdate(params[0]);
    }

    @Override
    protected void onPostExecute(Long result) {
        super.onPostExecute(result);

        onFinish(result);
    }
}
