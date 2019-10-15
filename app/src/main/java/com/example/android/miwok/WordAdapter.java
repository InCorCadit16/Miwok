package com.example.android.miwok;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by InCorCadit16 on 02.11.2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    //@param context is the current context for created adapter.
    //@param words is array list that you display. It contains Word objects.
    private int layoutColor;
    private MediaPlayer player;
    private AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Permanent loss of audio focus
                        // Pause playback immediately
                        releaseMediaPlayer();
                        // Wait 30 seconds before stopping playback
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        // Pause playback
                        player.pause();
                        player.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Lower the volume, keep playing
                        player.pause();
                        player.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Your app has been granted audio focus again
                        // Raise volume to normal, restart playback if necessary
                        player.start();
                    }
                }
            };
    public WordAdapter(Activity context, ArrayList<Word> words, int layoutColor, AudioManager audioManager) {
        super(context, 0, words);
        this.layoutColor = layoutColor;
        this.audioManager = audioManager;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }
        //{@link Word} of word in position
        final Word currentWord = getItem(position);
        LinearLayout currentLinearLayout = (LinearLayout) convertView.findViewById(R.id.words_linear_layout);
        currentLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releaseMediaPlayer();
                int result = audioManager.requestAudioFocus(afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback
                    player = MediaPlayer.create(getContext(), currentWord.getMediaFileResourceId());
                    player.start();
                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            releaseMediaPlayer();
                        }
                    });
                }
            }
        });
        currentLinearLayout.setBackgroundResource(layoutColor);
        //set miwok translation as text in miwok_text_view
        ((TextView) convertView.findViewById(R.id.miwok_text_view)).setText(currentWord.getMiwokTranslation());
        //set default(english) translation as text in default_text_view
        ((TextView) convertView.findViewById(R.id.default_text_view)).setText(currentWord.getDefaultTranslation());
        ImageView imageView = (ImageView) convertView.findViewById(R.id.list_item_icon);
        if(currentWord.hasResourceId())
           imageView.setImageResource(currentWord.getResourceId());
        else imageView.setVisibility(View.GONE);
        //return updated list item
        return convertView;
    }

    public void releaseMediaPlayer() {
        if(player != null) {
            player.release();
            player = null;
            audioManager.abandonAudioFocus(afChangeListener);
        }
    }


}
