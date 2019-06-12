package com.example.lynn.second;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static MyDatabaseHelper myDatabaseHelper;
    public static SQLiteDatabase database;
    public static MyView myView;
    public static MyListener listener = new MyListener();
    public static MyThread myThread;
    public static String word;
    public static Button[] buttons;
    public static Button first;
    public static Button button;
    public static Button solve;

    public static String scramble(String input) {
        char[] characters = input.toCharArray();

        for(int counter=0;counter<100;counter++) {
            int position1 = (int)(characters.length*Math.random());

            int position2 = (int)(characters.length*Math.random());

            char temp = characters[position1];
            characters[position1] = characters[position2];
            characters[position2] = temp;
        }

        return(new String(characters));
    }

    public static String getWord() {
        java.util.List<String> words = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM words;",new String[]{});

        cursor.moveToFirst();

        do {
            int wordIndex = cursor.getColumnIndex("word");

            String word = cursor.getString(wordIndex);

            words.add(word);
        } while (cursor.moveToNext());

        return(words.get((int)(words.size()*Math.random())));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myDatabaseHelper = new MyDatabaseHelper(this);

        database = myDatabaseHelper.getReadableDatabase();

        myThread = new MyThread();

        setContentView(myView = new MyView(this));
    }

    protected void onDestroy() {
        super.onDestroy();

        myThread.stop();
    }
}
