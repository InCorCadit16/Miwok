package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    private WordAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?","tinnə oyaase`nə",R.raw.phrase_what_is_your_name));
        words.add(new Word("Me name is...","oyaaset...",R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?","mickəksəs?",R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I\'m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?","əənəs`aa",R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I\'m coming.","həə`əənəm",R.raw.phrase_yes_im_coming));
        words.add(new Word("I\'m coming.","əənəm",R.raw.phrase_im_coming));
        adapter = new WordAdapter(this,words,R.color.category_phrases,audioManager);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.releaseMediaPlayer();
        Log.v("Stopped","releaseMediaPlayer");
    }
}
