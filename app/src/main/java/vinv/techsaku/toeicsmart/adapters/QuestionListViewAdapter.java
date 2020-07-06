package vinv.techsaku.toeicsmart.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import vinv.techsaku.toeicsmart.R;
import vinv.techsaku.toeicsmart.models.Question;

public class QuestionListViewAdapter extends BaseAdapter {

    final ArrayList<Question> listQuestion;

    public QuestionListViewAdapter(ArrayList<Question> listQuestion) {
        this.listQuestion = listQuestion;
    }

    @Override
    public int getCount() {
        return listQuestion.size();
    }

    @Override
    public Object getItem(int i) {
        return listQuestion.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listQuestion.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View viewQuestion;

        if (view == null) {
            viewQuestion = View.inflate(viewGroup.getContext(), R.layout.question_view, null);
        }
        else viewQuestion = view;

        Question question = (Question)getItem(i);

        ((TextView)viewQuestion.findViewById(R.id.content_question)).setText(String.format("%s. %s", question.getCodeQuestion(),question.getContent()));
        ((RadioButton)viewQuestion.findViewById(R.id.keyA)).setText(String.format("(%s) %s", question.getAnswers().get(0).getCode(), question.getAnswers().get(0).getContent()));
        ((RadioButton)viewQuestion.findViewById(R.id.keyB)).setText(String.format("(%s) %s", question.getAnswers().get(1).getCode(), question.getAnswers().get(1).getContent()));
        ((RadioButton)viewQuestion.findViewById(R.id.keyC)).setText(String.format("(%s) %s", question.getAnswers().get(2).getCode(), question.getAnswers().get(2).getContent()));
        ((RadioButton)viewQuestion.findViewById(R.id.keyD)).setText(String.format("(%s) %s", question.getAnswers().get(3).getCode(), question.getAnswers().get(3).getContent()));

        ((RadioButton)viewQuestion.findViewById(R.id.keyA)).setChecked(false);
        ((RadioButton)viewQuestion.findViewById(R.id.keyB)).setChecked(false);
        ((RadioButton)viewQuestion.findViewById(R.id.keyC)).setChecked(false);
        ((RadioButton)viewQuestion.findViewById(R.id.keyD)).setChecked(false);

        return viewQuestion;
    }
}
