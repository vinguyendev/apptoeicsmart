package vinv.techsaku.toeicsmart.adapters;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vinv.techsaku.toeicsmart.R;
import vinv.techsaku.toeicsmart.models.UserTest;

public class ResultListViewAdapter extends BaseAdapter {

    final ArrayList<UserTest> listResult;

    public ResultListViewAdapter(ArrayList<UserTest> listResult) {
        this.listResult = listResult;
    }

    @Override
    public int getCount() {
        return listResult.size();
    }

    @Override
    public Object getItem(int i) {
        return listResult.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listResult.get(i).getId();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View viewResult;

        if (view == null) {
            viewResult = View.inflate(viewGroup.getContext(), R.layout.result_view, null);
        }
        else  viewResult = view;

        UserTest result = (UserTest)getItem(i);

        ((TextView)viewResult.findViewById(R.id.name_skill)).setText(result.getNameSkill());
        ((TextView)viewResult.findViewById(R.id.title_skill)).setText(result.getTitleSkill());
        ((TextView)viewResult.findViewById(R.id.date_test)).setText(result.getDateTest());
        ((TextView)viewResult.findViewById(R.id.correct_sentences)).setText(String.format("Số câu đúng: %s",result.getCorrectSentences()+""));
        ((TextView)viewResult.findViewById(R.id.correct_ratio)).setText(String.format("Điểm: %s",result.getCorrectRatio()));

        return viewResult;
    }
}
