package com.lindroid.countdowndemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    //    private EditText editText;
    private Button btnCaptcha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
//        editText = (EditText) findViewById(R.id.et_phone);
        btnCaptcha = (Button) findViewById(R.id.btn_captcha);
        btnCaptcha.setOnClickListener(this);
        countTimer = new CountTimer(10000, 1000);
    }

    @Override
    public void onClick(View v) {
//        if (!isPhoneNumber(editText.getText().toString())) {
//            Toast.makeText(context, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
//            return;
//        }else {
//        }
        countTimer.start();
    }

    private CountTimer countTimer;

    /**
     * 点击按钮后倒计时
     */
    class CountTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        /**
         * 倒计时过程中调用
         *
         * @param millisUntilFinished
         */
        @Override
        public void onTick(long millisUntilFinished) {
            Log.e("Tag", "millisUntilFinished=" + millisUntilFinished);
            btnCaptcha.setText(millisUntilFinished / 1000 + "s后重新发送");
            //设置倒计时中的按钮外观
            btnCaptcha.setClickable(false);//倒计时过程中将按钮设置为不可点击
            btnCaptcha.setBackgroundColor(Color.parseColor("#c7c7c7"));
            btnCaptcha.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            btnCaptcha.setTextSize(16);
        }

        /**
         * 倒计时完成后调用
         */
        @Override
        public void onFinish() {
            Log.e("Tag", "倒计时完成");
            //设置倒计时结束之后的按钮样式
            btnCaptcha.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_blue_light));
            btnCaptcha.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            btnCaptcha.setTextSize(18);
            btnCaptcha.setText("重新发送");
            btnCaptcha.setClickable(true);
        }
    }

    /**
     * 判断用户输入的手机号码是否正确
     *
     * @return
     */
//    public boolean isPhoneNumber(String str) {
//        String strRegex = "[1][34578]\\d{9}";
//        boolean result = str.matches(strRegex);
//        return result;
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countTimer.cancel();
    }
}
