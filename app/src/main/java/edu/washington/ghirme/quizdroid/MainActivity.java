package edu.washington.ghirme.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    private String[] quizTopics = {"Math", "Physics", "Marvel Super Heroes"};
    private String[] quizDescription = {"Worst topic of all time", "Find out how close you compare to Albert",
                                        "Find out how much you know about Marvel\'s own superheroes"};
    private ListView topicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Finds the topic list view
        topicList = (ListView) findViewById(R.id.quizView);

        // Populates the topic list view with stored quiz topics
        ArrayAdapter<String> items = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, quizTopics);
        topicList.setAdapter(items);

        // Begins the quiz as soon as the user clicks on a topic
        topicList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, GameplayActivity.class);
                i.putExtra("topic", quizTopics[position]);
                i.putExtra("description", quizDescription[position]);
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
