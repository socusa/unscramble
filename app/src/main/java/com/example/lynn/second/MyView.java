package com.example.lynn.second;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import static com.example.lynn.second.MainActivity.*;

public class MyView extends LinearLayout {

    public MyView(Context context) {
        super(context);

        word = getWord();

        word = word.toUpperCase();

        String temp = scramble(word);

        buttons = new Button[word.length()];

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(1000/buttons.length,100);

        for(int counter=0;counter<buttons.length;counter++) {
            buttons[counter] = new Button(context);

            buttons[counter].setLayoutParams(params);

            buttons[counter].setText(temp.substring(counter,counter+1));

            buttons[counter].setTextSize(20);

            buttons[counter].setOnClickListener(listener);

            buttons[counter].setId(View.generateViewId());

            addView(buttons[counter]);
        }

        button = new Button(context);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(200,100);

        layoutParams.addRule(RelativeLayout.BELOW,buttons[0].getId());

        layoutParams.leftMargin = 200;

        button.setLayoutParams(layoutParams);

        button.setText("You got it");

        button.setVisibility(INVISIBLE);

        button.setId(View.generateViewId());

        addView(button);

        solve = new Button(context);

        layoutParams = new RelativeLayout.LayoutParams(200,100);

        layoutParams.addRule(RelativeLayout.BELOW,button.getId());

        solve.setLayoutParams(layoutParams);

        solve.setOnClickListener(listener);

        solve.setText("Solve");

        solve.setY(200);

        addView(solve);
    }

}
