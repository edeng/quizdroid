package edu.washington.ghirme.quizdroid;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class OverviewFragment extends Fragment {
    private Topic topic;
    private Activity hostActivity;

    public OverviewFragment() {
        // Required empty public constructor
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            topic = (Topic) getArguments().getSerializable("topic");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_overview, container, false);

        // Gets views for description, topic, question
        TextView topicView = (TextView) rootView.findViewById(R.id.topic);
        TextView descriptionView = (TextView) rootView.findViewById(R.id.description);
        TextView questionView = (TextView) rootView.findViewById(R.id.questionCount);

        // Sets text for views
        topicView.setText(topic.getTitle());
        descriptionView.setText(topic.getDescription());

        questionView.setText("There are " + topic.getQuestions().size() + " questions");


        Button beginButton = (Button) rootView.findViewById(R.id.beginButton);

        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hostActivity instanceof GameplayActivity) {
                    ((GameplayActivity) hostActivity).loadQuestionFrag(0, 0, topic);
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


