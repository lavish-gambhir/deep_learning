package com.like.eyes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String EXTRA_MESSAGE = "com.like.eyes.extra.MESSAGE";
    private static final int TEXT_REQUEST = 1;

    private EditText messageEditText;
    private TextView replyHeadTextView, replyTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "----------");
        Log.d(TAG, "onCreate");

        messageEditText = (EditText) findViewById(R.id.editText_main);
        replyHeadTextView = (TextView) findViewById(R.id.text_header_reply);
        replyTextView = (TextView) findViewById(R.id.text_message_reply);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    public void launchSecondActivity(View view) {
        Log.d(TAG, "Button clicked");
        Intent intent = new Intent(this, SecondBaba.class);
        String message = messageEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE ,message);
        startActivityForResult(intent, TEXT_REQUEST);
    }
}
