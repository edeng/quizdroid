package edu.washington.ghirme.quizdroid;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class QuestionFragment extends Fragment {
    private Topic topic;
    private int questionNumber;
    private int correctAnswers;
    private Activity hostActivity;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            topic = (Topic) getArguments().getSerializable("topic");
            questionNumber = getArguments().getInt("questionNumber");
            correctAnswers = getArguments().getInt("correctAnswers");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_questions, container, false);

        // gets the views for question, radio button options, and submit button
        TextView question = (TextView) rootView.findViewById(R.id.question);
        TextView answer1 = (TextView) rootView.findViewById(R.id.answer1);
        TextView answer2 = (TextView) rootView.findViewById(R.id.answer2);
        TextView answer3 = (TextView) rootView.findViewById(R.id.answer3);
        TextView answer4 = (TextView) rootView.findViewById(R.id.answer4);
        final Button submit = (Button) rootView.findViewById(R.id.submitButton);
        final RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup);

        // shows the next submit button as soon as an option is selected
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                submit.setVisibility(View.VISIBLE);
            }
        });

        // populates views with question and answer options
        final Quiz curQuestion = topic.getQuestions().get(questionNumber);
        question.setText(curQuestion.getText());
        answer1.setText(curQuestion.getAnswer1());
        answer2.setText(curQuestion.getAnswer2());
        answer3.setText(curQuestion.getAnswer3());
        answer4.setText(curQuestion.getAnswer4());

        String answer = "";
        switch(curQuestion.getCorrectAnswer()) {
            case 1: answer = curQuestion.getAnswer1();
                    break;
            case 2: answer = curQuestion.getAnswer2();
                    break;
            case 3: answer = curQuestion.getAnswer3();
                    break;
            case 4: answer = curQuestion.getAnswer4();
                    break;
        }

        final String correctAnswer = answer;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // gets selected button and determines if the answer was correct
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selected = (RadioButton) rootView.findViewById(selectedId);
                boolean correct = selected.getText().equals(correctAnswer);
                final int correctAddition = correct ? 1 : 0;

                if (hostActivity instanceof GameplayActivity) {
                    ((GameplayActivity) hostActivity).loadAnswerFrag(selected.getText().toString(),
                            correctAnswer, correctAnswers + correctAddition, topic, questionNumber);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.hostActivity = activity;
    }
}


