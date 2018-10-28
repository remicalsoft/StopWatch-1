package com.example.mizuo.javatest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private StopWatch _stopWatch = new StopWatch();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "アプリ起動", Toast.LENGTH_SHORT).show();

        findViewById(R.id.buttonStart).setOnClickListener(this);
        findViewById(R.id.buttonStop) .setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==findViewById(R.id.buttonStart)){
            _stopWatch.start();
            Toast.makeText(this, "計測開始", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "計測終了", Toast.LENGTH_SHORT).show();
            TextView text = findViewById(R.id.text01);
            double diff = (double) _stopWatch.getDiff();

            text.setText(Double.toString(diff / 1000)+ "秒です");
        }
    }

}
