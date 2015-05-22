package edu.washington.ghirme.quizdroid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private static final int SETTINGS_RESULT = 1;
    Button settingButton;
    private ListView topicList;

    PendingIntent alarmIntent = null;
    BroadcastReceiver alarmReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, Intent intent) {
            Toast.makeText(MainActivity.this, "This is the URL if my preferences work:(", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        registerReceiver(alarmReceiver, new IntentFilter("SoundDaAlarm"));
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 0, new Intent(), 0);
        am.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 100000, alarmIntent);



//        Button btnSettings=(Button)findViewById(R.id.buttonSettings);
//        // start the UserSettingActivity when user clicks on Button
//        btnSettings.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Intent i = new Intent(getApplicationContext(), UserSettingsActivity.class);
//                startActivityForResult(i, SETTINGS_RESULT);
//            }
//        });



        QuizApp app = (QuizApp) getApplication();
        final List<Topic> topics = app.getAllTopics();

        String[] quizTopics = new String[topics.size()];
        for (int i = 0; i < topics.size(); i++) {
            quizTopics[i] = topics.get(i).getTitle();
        }

        // Finds the topic list view
        topicList = (ListView) findViewById(R.id.quizView);

        // Populates the topic list view with stored quiz topics
        //ArrayAdapter<String> items = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, quizTopics);
        ArrayAdapter<String> items = new IconArrayAdapter(this, quizTopics);
        topicList.setAdapter(items);

        // Begins the quiz as soon as the user clicks on a topic
        topicList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, GameplayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("topic", topics.get(position));
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode==SETTINGS_RESULT)
//        {
//            displayUserSettings();
//        }
//
//    }
//
//    private void displayUserSettings() {
//        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
//
//        String  settings = "";
//
//        settings += "URL: " + sharedPrefs.getString("jsonURL", "NOURL");
//
//        settings += "\nInterval"+ sharedPrefs.getString("interval", "15");
//
//        TextView textViewSetting = (TextView) findViewById(R.id.textViewSettings);
//
//        textViewSetting.setText(settings);
//    }


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
