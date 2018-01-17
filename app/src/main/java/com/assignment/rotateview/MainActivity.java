package com.assignment.rotateview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;

import static java.lang.Math.toDegrees;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {


    private double mCurrAngle = 0;
    private double mPrevAngle = 0;
    @BindView(R.id.ivWheel)
    ImageView wheel;
    @BindView(R.id.btnDegree)
    Button btnAngle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setmCurrAngle();
    }

    @OnTouch(R.id.ivWheel)
    public boolean onTouch(View view, MotionEvent event) {
        float xc = wheel.getWidth() / 2;
        float yc = wheel.getHeight() / 2;

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                wheel.clearAnimation();
                btnAngle.clearAnimation();
                mCurrAngle = toDegrees(Math.atan2(x - xc, yc - y));
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                mPrevAngle = mCurrAngle;
                mCurrAngle = toDegrees(Math.atan2(x - xc, yc - y));
                animate(mPrevAngle, mCurrAngle, 0);
                System.out.println(mCurrAngle);
                setmCurrAngle();
                break;
            }
            case MotionEvent.ACTION_UP: {
                mPrevAngle = mCurrAngle = 0;
                break;
            }
        }
        Log.e("mCurrAngle", "" + mCurrAngle);
        return true;
    }

    private void animate(double fromDegrees, double toDegrees, long durationMillis) {
        RotateAnimation rotate = new RotateAnimation((float) fromDegrees, (float) toDegrees,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(durationMillis);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        wheel.startAnimation(rotate);
        btnAngle.startAnimation(rotate);
        System.out.println(mCurrAngle);
    }

    public void setmCurrAngle() {
        String angle = String.valueOf((int) mCurrAngle);
        btnAngle.setText(angle);

    }
}
