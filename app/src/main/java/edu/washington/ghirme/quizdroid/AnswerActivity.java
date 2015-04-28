package edu.washington.ghirme.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class AnswerActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        // Get question#, user's answer, correct answer, # of correct answers, and current topic Qs
        final int questionNumber = getIntent().getIntExtra("questionNumber", 0);
        String userAnswer = getIntent().getStringExtra("userAnswer");
        String correctAnswer = getIntent().getStringExtra("correctAnswer");
        final int correctAnswerCount = getIntent().getIntExtra("correctAnswers", 0);
        final int position = getIntent().getIntExtra("position", 0);
        String[][] curTopicQuestions = Question.getQuestionsForPosition(position);

        // access text views and buttons on answer activity
        TextView userAnswerView = (TextView) findViewById(R.id.userAnswer);
        TextView correctAnswerView = (TextView) findViewById(R.id.correctAnswer);
        TextView score = (TextView) findViewById(R.id.score);
        Button next = (Button) findViewById(R.id.nextButton);

        // Sets correct page on the layout
        userAnswerView.setText(userAnswer);
        correctAnswerView.setText(correctAnswer);
        score.setText(correctAnswerCount + " answers correct out of " + (questionNumber + 1));
        final boolean finished = (questionNumber == curTopicQuestions.length - 1);

        if (finished) {
            next.setText("Finish");
        }


        // when the user presses the next or finish button, either goes to next Q or back to main
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!finished) {
                    Intent i = new Intent(AnswerActivity.this, QuestionActivity.class);
                    i.putExtra("questionNumber", questionNumber + 1);
                    i.putExtra("correctAnswers", correctAnswerCount);
                    i.putExtra("position", position);
                    startActivity(i);
                } else {
                    Intent i = new Intent(AnswerActivity.this, MainActivity.class);
                    startActivity(i);
                }
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
