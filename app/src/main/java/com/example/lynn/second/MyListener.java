package com.example.lynn.second;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.view.View.VISIBLE;
import static com.example.lynn.second.MainActivity.*;

public class MyListener implements View.OnClickListener {

    public void startAnimation() {
        button.setVisibility(VISIBLE);

        button.setY(400);

        AnimatorSet set = new AnimatorSet();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(10,40,20);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float)valueAnimator.getAnimatedValue();

                button.setTextSize(animatedValue);
            }
        });

        set.play(valueAnimator);

        ObjectAnimator animation1 = ObjectAnimator.ofFloat(button,"scaleX",1,4,10,15,2);

        set.play(animation1).with(valueAnimator);

        ObjectAnimator animation2 = ObjectAnimator.ofFloat(button,"rotationX",0,90,45,500,360);

        set.play(animation2).with(valueAnimator);

        set.setDuration(10000);

        set.start();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void onClick(View v) {
        Button source = (Button)v;

        if (source == solve) {
            for (int counter=0;counter<buttons.length;counter++)
                buttons[counter].setText(word.substring(counter,counter+1));

            startAnimation();
        } else if (first == null)
            first = source;
        else {
            Button second = source;

            String temp = first.getText().toString();
            first.setText(second.getText());
            second.setText(temp);

            String candidate = "";

            for(int counter=0;counter<buttons.length;counter++)
                candidate += buttons[counter].getText();

            if (candidate.equals(word)) {
                Toast toast = Toast.makeText(source.getContext(),"You got it",Toast.LENGTH_LONG);

                toast.show();

                startAnimation();
            }
        }

    }
}
