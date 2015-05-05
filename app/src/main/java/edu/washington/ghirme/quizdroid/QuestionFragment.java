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

public class QuestionFragment extends Fragment {
    private int position;
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
            position = getArguments().getInt("position");
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
        final String[] curQuestion = Question.getQuestionForPositionAndQuestion(position, questionNumber);
        question.setText(curQuestion[0]);
        answer1.setText(curQuestion[1]);
        answer2.setText(curQuestion[2]);
        answer3.setText(curQuestion[3]);
        answer4.setText(curQuestion[4]);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // gets selected button and determines if the answer was correct
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selected = (RadioButton) rootView.findViewById(selectedId);
                boolean correct = selected.getText().equals(curQuestion[5]);
                final int correctAddition = correct ? 1 : 0;

                if (hostActivity instanceof GameplayActivity) {
                    ((GameplayActivity) hostActivity).loadAnswerFrag(selected.getText().toString(),
                            curQuestion[5], correctAnswers + correctAddition, position, questionNumber);
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


