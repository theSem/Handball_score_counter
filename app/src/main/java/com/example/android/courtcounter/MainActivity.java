/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.courtcounter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This activity keeps track of the basketball score for 2 teams.
 */
public class MainActivity extends AppCompatActivity {

    // Tracks the score for Team A
    int scoreTeamA = 0;

    // Tracks the score for Team B
    int scoreTeamB = 0;

    boolean lastScoreTeamA = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button teamAButton = (Button) findViewById(R.id.team_a_point);
        Button teamBButton = (Button) findViewById(R.id.team_b_point);

        teamAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lastScoreTeamA){
                    scoreTeamA++;
                } else {
                    lastScoreTeamA = true;
                }
                updateScores();
            }
        });

        teamBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!lastScoreTeamA){
                    scoreTeamB++;
                } else {
                    lastScoreTeamA = false;
                }
                updateScores();
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

        //noinspection SimpSlifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Resets the score for both teams back to 0.
     */
    public void resetScore(View v) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        updateScores();
    }

    /**
     * Displays the given score for Team A.
     */
    public void updateScores() {
        TextView teamALabel = (TextView) findViewById(R.id.team_a_label);
        TextView teamBLabel = (TextView) findViewById(R.id.team_b_label);
        TextView scoreViewA = (TextView) findViewById(R.id.team_a_score);
        TextView scoreViewB = (TextView) findViewById(R.id.team_b_score);
        Button teamAButton = (Button) findViewById(R.id.team_a_point);
        Button teamBButton = (Button) findViewById(R.id.team_b_point);

        scoreViewA.setText(String.valueOf(scoreTeamA));
        scoreViewB.setText(String.valueOf(scoreTeamB));

        if (lastScoreTeamA){
            teamBButton.setText(R.string.side_out);
            teamAButton.setText(R.string.one_point);
            teamALabel.setBackgroundColor(getResources().getColor(R.color.primary));
            teamBLabel.setBackgroundColor(Color.TRANSPARENT);
        } else {
            teamAButton.setText(R.string.side_out);
            teamBButton.setText(R.string.one_point);
            teamBLabel.setBackgroundColor(getResources().getColor(R.color.primary));
            teamALabel.setBackgroundColor(Color.TRANSPARENT);
        }
    }

}
