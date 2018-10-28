package com.example.mizuo.javatest;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private StopWatch _stopWatch = new StopWatch(); //ストップウォッチのインスタンス
    private boolean _isStarted;                     //現在スタート状態か否かを示す
    private Button _button;                         //ボタンインスタンス
    private TextView _textView;                     //textViewインスタンス
    private Handler _handler = new Handler();       //別スレッドの処理をUIに伝達するためのハンドラー

    // 最初に呼ばれるメソッド
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _button = findViewById(R.id.button); //レイアウトされたボタンのインスタンスを探し出して格納
        _button.setOnClickListener(this);    //ボタンの押下イベントを自分のクラス(onClick)に伝達するようにセット
        _textView = findViewById(R.id.text_time);   //レイアウトされたテキストのインスタンスを探し出して格納
    }

    // ボタンが押されたときにコールされる
    @Override
    public void onClick(View v) {
        if(_isStarted){ //もしスタートしていたら
            onStoped(); //止める処理をコール
        } else {        //もしスタートしていなければ
            onStarted();//スタートする処理をコール
        }
        _isStarted = !_isStarted; //スタートしている状態を入れ替える(スタート状態/ストップ状態)
    }

    //スタート状態になった時にコールされる
    private void onStarted(){
        _stopWatch.start();             //ストップウォッチをスタート
        _button.setText("ストップ");    //ボタンのテキストを「ストップ」にする
        runTextUpdater();               //別スレッドで連続してテキストに経過時間を格納する処理をスタート
    }

    //ストップ状態になった時にコールされる
    private void onStoped(){
        _button.setText("スタート");    //ボタンのテキストを「スタート」にする
        updateText();                   //テキストの文字を更新する
    }

    //別スレッドで連続してテキストに経過時間を格納する処理をスタート
    private void runTextUpdater(){
        //別スレッドを作成
        new Thread(() -> {
            while(_isStarted){ //スタート状態の間ループ
                Sleeper.sleep(20); //20ms待機
                _handler.post(()->updateText()); //現在の経過時間をテキストに表示
            }
        }).start(); //スレッドをスタート
    }

    //現在の経過時間をテキストに更新する
    private void updateText(){
        //String.formatで文字列を好きなフォーマットで表示できる。
        _textView.setText(String.format("%06.3f秒", _stopWatch.getDiff()/1000.));
    }

}
