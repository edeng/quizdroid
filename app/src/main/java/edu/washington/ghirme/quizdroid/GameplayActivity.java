package edu.washington.ghirme.quizdroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;


public class GameplayActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        OverviewFragment overviewFragment = new OverviewFragment();
        overviewFragment.setArguments(bundle);

        ft.add(R.id.container, overviewFragment);
        ft.commit();
    }

    public void loadQuestionFrag(int questionNumber, int correctAnswers, Topic topic) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Bundle topicBundle = new Bundle();
        topicBundle.putInt("questionNumber", questionNumber);
        topicBundle.putInt("correctAnswers", correctAnswers);
        topicBundle.putSerializable("topic", topic);

        QuestionFragment questionFragment = new QuestionFragment();
        questionFragment.setArguments(topicBundle);

        ft.replace(R.id.container, questionFragment);
        ft.commit();
    }

    public void loadAnswerFrag(String userAnswer, String correctAnswer, int correctAnswers,
                               Topic topic, int questionNumber) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Bundle topicBundle = new Bundle();
        topicBundle.putString("userAnswer", userAnswer);
        topicBundle.putString("correctAnswer", correctAnswer);
        topicBundle.putInt("correctAnswers", correctAnswers);
        topicBundle.putInt("questionNumber", questionNumber);
        topicBundle.putSerializable("topic", topic);

        AnswerFragment answerFragment = new AnswerFragment();
        answerFragment.setArguments(topicBundle);

        ft.replace(R.id.container, answerFragment);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gameplay, menu);
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
