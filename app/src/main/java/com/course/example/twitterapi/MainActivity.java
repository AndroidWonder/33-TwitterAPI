/**
 * This Class is used to do a Twitter search.
 * It uses the Twitter4J API which handles both the web lookup and
 * JSON parsing.
 */

package com.course.example.twitterapi;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class MainActivity extends Activity {

	private EditText text = null;
	private String str = null;
	private Twitter twitter = null;
	private Button start = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		text = (EditText) findViewById(R.id.question);

		start = (Button) findViewById(R.id.button);

		// The factory instance is re-useable and thread safe
		twitter = new TwitterFactory().getInstance();

		// user account jpepe44@gmail.com
		// Applications Consumer and Auth Access Token
		twitter.setOAuthConsumer("V6TJ18GeZnn4sxii9w08vUizs",
				"2Urd8Nnb27XeeVmR4llbtMRZzGA3OHvz8HBT3oAx7BQwOGPffH");
		twitter.setOAuthAccessToken(new AccessToken(
				"611005927-ZPCbTO2dCNNroutda5oWgTIhoeG2EE5fTjsZIyeN",
				"3QeQSvw0UmRmcBiEy0mEDQpcnH2G2rSHs3kbY1rnnZKTG"));

		start.setOnClickListener(new OnClickListener() {

			public void onClick(View v1) {
				str = text.getText().toString();
				Thread t = new Thread(background);
				t.start();
			}

		});

	}

	Runnable background = new Runnable() {
		public void run() {
			// search tweets
			try {

				Query query = new Query(str);
				QueryResult result = twitter.search(query);
				for (Status status : result.getTweets()) {
					Log.i("Twitter", "@" + status.getUser().getScreenName()
							+ ":" + status.getText());
				}

			} catch (TwitterException e) {

			}
		}
	};

}