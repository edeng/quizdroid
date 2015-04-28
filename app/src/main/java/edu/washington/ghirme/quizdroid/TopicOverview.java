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


public class TopicOverview extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        // Gets topic/description/questions from previous page
        String topicName = getIntent().getStringExtra("topic");
        String topicDescription = getIntent().getStringExtra("description");
        final int position = getIntent().getIntExtra("position", 0);

        // Gets views for description, topic, question
        TextView topicView = (TextView) findViewById(R.id.topic);
        TextView description = (TextView) findViewById(R.id.description);
        TextView questionView = (TextView)findViewById(R.id.questionCount);

        // Sets text for views
        topicView.setText(topicName);
        description.setText(topicDescription);

        String[][] questions = Question.getQuestionsForPosition(position);

        questionView.setText("There are " + questions.length + " questions");


        Button beginButton = (Button) findViewById(R.id.beginButton);

        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TopicOverview.this, QuestionActivity.class);
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
