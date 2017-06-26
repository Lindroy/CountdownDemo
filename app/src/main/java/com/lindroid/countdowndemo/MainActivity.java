package com.lindroid.countdowndemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    //    private EditText editText;
    private Button btnCaptcha;
    private SpannableStringBuilder sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
//        editText = (EditText) findViewById(R.id.et_phone);
        btnCaptcha = (Button) findViewById(R.id.btn_captcha);
        btnCaptcha.setOnClickListener(this);
        countTimer = new CountTimer(10000, 1000);
        sb = new SpannableStringBuilder();
    }

    @Override
    public void onClick(View v) {
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
         * @param millisUntilFinished
         */
        @Override
        public void onTick(long millisUntilFinished) {
            Log.e("Tag", "millisUntilFinished=" + millisUntilFinished);
//            Log.e("Tag", "倒计时=" + (millisUntilFinished/1000));
            //处理后的倒计时数值
            int time = (int) (Math.round((double) millisUntilFinished / 1000) - 1);
//            btnCaptcha.setText(String.valueOf(time)+"s后重新发送");
            //拼接要显示的字符串
            sb.clear();
            Log.e("Tag", "字符长度=" + sb.length());
            sb.append(String.valueOf(time));
            sb.append("s后重新发送");
            int index = String.valueOf(sb).indexOf("后");
            Log.e("Tag", "字符长度2=" + sb.length() + ",index=" + index);
            //给秒数和单位设置蓝色前景色
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(context, android.R.color.holo_blue_dark));
            sb.setSpan(colorSpan, 0, index, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            btnCaptcha.setText(sb);
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
