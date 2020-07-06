package vinv.techsaku.toeicsmart.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vinv.techsaku.toeicsmart.MainActivity;
import vinv.techsaku.toeicsmart.R;
import vinv.techsaku.toeicsmart.adapters.QuestionListViewAdapter;
import vinv.techsaku.toeicsmart.models.Answer;
import vinv.techsaku.toeicsmart.models.Question;
import vinv.techsaku.toeicsmart.models.SkillTest;
import vinv.techsaku.toeicsmart.models.UserSkillTest;
import vinv.techsaku.toeicsmart.networks.DataServices;
import vinv.techsaku.toeicsmart.utils.AppConfig;

public class TestActivity extends AppCompatActivity {

    ListView listQuestion;
    QuestionListViewAdapter questionListViewAdapter;
    ProgressBar progressBar;
    LinearLayout viewQuestion,viewExplain,viewResultTest, viewContentQuestion;
    ArrayList<Question> questions;
    int codeQuestion = 0;
    Question questionCurrent;
    TextView content_question,key, explain, vocabularies, translate, resultTest;
    RadioButton keyA, keyB, keyC, keyD;
    Button btnBack, btnResult, btnNext, resultHome;
    int user_id;
    int part_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
        progressBar = findViewById(R.id.progressBar);
        viewQuestion = findViewById(R.id.viewQuestion);
        fetchDataQuestions();

        SharedPreferences sharedPreferences = this.getSharedPreferences("profile", Context.MODE_PRIVATE);
        String idUser = sharedPreferences.getString("id","1");
        user_id = Integer.parseInt(idUser);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (codeQuestion < questions.size()-1) {
                    codeQuestion++;
                    questionCurrent = questions.get(codeQuestion);
                    resetRadioButton();
                    setQuestionView();
                    viewExplain.setVisibility(View.GONE);
                }
                else {
                    submitTest();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (codeQuestion > 0) {
                    codeQuestion--;
                }
                questionCurrent = questions.get(codeQuestion);
                resetRadioButton();
                setQuestionView();
                viewExplain.setVisibility(View.GONE);
                viewResultTest.setVisibility(View.GONE);
                viewContentQuestion.setVisibility(View.VISIBLE);
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewExplain.setVisibility(View.VISIBLE);
            }
        });

        resultHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    void init() {
        content_question = (TextView)findViewById(R.id.content_question);
        keyA = (RadioButton)findViewById(R.id.keyA);
        keyB = (RadioButton)findViewById(R.id.keyB);
        keyC = (RadioButton)findViewById(R.id.keyC);
        keyD = (RadioButton)findViewById(R.id.keyD);
        btnBack = (Button)findViewById(R.id.btnBack);
        btnResult = (Button)findViewById(R.id.btnResult);
        btnNext = (Button)findViewById(R.id.btnNext);
        viewExplain = (LinearLayout)findViewById(R.id.viewExplain);
        viewResultTest = (LinearLayout)findViewById(R.id.viewResultTest);
        viewContentQuestion = (LinearLayout)findViewById(R.id.viewContentQuestion);
        explain = (TextView)findViewById(R.id.explain);
        vocabularies = (TextView)findViewById(R.id.vocabularies);
        key = (TextView)findViewById(R.id.key);
        translate = (TextView)findViewById(R.id.translate);
        keyA.setOnCheckedChangeListener(listenerRadio);
        keyB.setOnCheckedChangeListener(listenerRadio);
        keyC.setOnCheckedChangeListener(listenerRadio);
        keyD.setOnCheckedChangeListener(listenerRadio);
        resultHome = (Button)findViewById(R.id.resultHome);
        resultTest = (TextView)findViewById(R.id.resultTest);
    }


    void resetRadioButton() {
        keyA.setChecked(false);
        keyB.setChecked(false);
        keyC.setChecked(false);
        keyD.setChecked(false);
    }

    void setQuestionView() {
        content_question.setText(String.format("%s. %s", questionCurrent.getCodeQuestion(),questionCurrent.getContent()));
        keyA.setText(String.format("(%s) %s", questionCurrent.getAnswers().get(0).getCode(), questionCurrent.getAnswers().get(0).getContent()));
        keyB.setText(String.format("(%s) %s", questionCurrent.getAnswers().get(1).getCode(), questionCurrent.getAnswers().get(1).getContent()));
        keyC.setText(String.format("(%s) %s", questionCurrent.getAnswers().get(2).getCode(), questionCurrent.getAnswers().get(2).getContent()));
        keyD.setText(String.format("(%s) %s", questionCurrent.getAnswers().get(3).getCode(), questionCurrent.getAnswers().get(3).getContent()));
        explain.setText(String.format("Giải thích: %s", questionCurrent.getExplain()));
        vocabularies.setText(String.format("Từ vựng: %s", questionCurrent.getVocabularies()));
        translate.setText(String.format("Tạm dịch: %s", questionCurrent.getTranslate()));
        key.setText(String.format("Key: %s", questionCurrent.getKey()));
        viewExplain.setEnabled(false);
        viewResultTest.setVisibility(View.GONE);
        if (questionCurrent.getAnswer()!=null) {
            switch (questionCurrent.getAnswer()) {
                case "A":
                    keyA.setChecked(true);
                    break;
                case "B":
                    keyB.setChecked(true);
                    break;
                case "C":
                    keyC.setChecked(true);
                    break;
                case "D":
                    keyD.setChecked(true);
                    break;
            }
        }
    }

    void fetchDataQuestions() {
        Intent intent = getIntent();
        int examId = intent.getIntExtra("examId",0);
        System.out.println(examId);

        DataServices.getAPIService().getSkillTest("Bearer " + AppConfig.getToken(TestActivity.this),examId,5).enqueue(new Callback<SkillTest>() {
            @Override
            public void onResponse(Call<SkillTest> call, Response<SkillTest> response) {
                SkillTest skillTest = response.body();
                part_id = skillTest.getId();
                questions = skillTest.getQuestions();
                progressBar.setVisibility(View.GONE);
                viewQuestion.setVisibility(View.VISIBLE);
                viewContentQuestion.setVisibility(View.VISIBLE);
                questionCurrent = questions.get(codeQuestion);
                setQuestionView();
            }

            @Override
            public void onFailure(Call<SkillTest> call, Throwable t) {

            }
        });

    }

    CompoundButton.OnCheckedChangeListener listenerRadio = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            viewExplain.setEnabled(true);
            if (b) {
                String ans = compoundButton.getText().toString().substring(1,2);
                questionCurrent.setAnswer(ans);
            }
        }
    };

    void submitTest() {
        int numberCorrect = 0;
        final int totalQuestion = questions.size();
        for (Question question : questions) {
            if (question.getAnswer()!= null && question.getAnswer().equals(question.getKey())) {
                numberCorrect++;
            }
        }

        String correct_ratio = numberCorrect + "/" + totalQuestion;

        final int finalNumberCorrect = numberCorrect;
        DataServices.getAPIService().postUserSkillTest("Bearer " + AppConfig.getToken(TestActivity.this),
                user_id, part_id, numberCorrect,correct_ratio ).enqueue(new Callback<UserSkillTest>() {
            @Override
            public void onResponse(Call<UserSkillTest> call, Response<UserSkillTest> response) {
                System.out.println(finalNumberCorrect);
                resultTest.setText(String.format("Kết quả: %s/%s", finalNumberCorrect +"", totalQuestion+ ""));
                viewContentQuestion.setVisibility(View.GONE);
                viewResultTest.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<UserSkillTest> call, Throwable t) {

            }
        });

    }

}