package vinv.techsaku.toeicsmart.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vinv.techsaku.toeicsmart.R;
import vinv.techsaku.toeicsmart.models.Exam;

public class ExamListViewAdapter extends BaseAdapter {

    final ArrayList<Exam> listExam;

    public ExamListViewAdapter(ArrayList<Exam> listExam) {
        this.listExam = listExam;
    }

    @Override
    public int getCount() {
        return listExam.size();
    }

    @Override
    public Object getItem(int i) {
        return listExam.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listExam.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View viewExam;

        if (view == null) {
            viewExam = View.inflate(viewGroup.getContext(), R.layout.exam_view, null);
        }

        else viewExam = view;

        Exam exam = (Exam)getItem(i);

        ((TextView)viewExam.findViewById(R.id.nameExam)).setText(String.format("%s - %s",exam.getName(),exam.getBook()));

        return viewExam;
    }
}
