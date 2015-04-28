package edu.washington.ghirme.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        // gets the question#, current amount of current answers, and the question topic position
        final int questionNumber = getIntent().getIntExtra("questionNumber", 0);
        final int correctAnswers = getIntent().getIntExtra("correctAnswers", 0);
        final int position = getIntent().getIntExtra("position", 0);

        // gets the views for question, radio button options, and submit button
        TextView question = (TextView) findViewById(R.id.question);
        TextView answer1 = (TextView) findViewById(R.id.answer1);
        TextView answer2 = (TextView) findViewById(R.id.answer2);
        TextView answer3 = (TextView) findViewById(R.id.answer3);
        TextView answer4 = (TextView) findViewById(R.id.answer4);
        final Button submit = (Button) findViewById(R.id.submitButton);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

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

        // user submits their answer and views their result
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // gets selected button and determines if the answer was correct
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selected = (RadioButton) findViewById(selectedId);
                boolean correct = selected.getText().equals(curQuestion[5]);
                final int correctAddition = correct ? 1 : 0;

                Intent i = new Intent(QuestionActivity.this, AnswerActivity.class);
                i.putExtra("questionNumber", questionNumber);
                i.putExtra("userAnswer", selected.getText());
                i.putExtra("correctAnswer", curQuestion[5]);
                i.putExtra("correctAnswers", correctAnswers + correctAddition);
                i.putExtra("position", position);
                startActivity(i);
            }
        });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
