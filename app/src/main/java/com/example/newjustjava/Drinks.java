package com.example.newjustjava;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Drinks extends AppCompatActivity {
    private MediaPlayer.OnCompletionListener mcompletionlistener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    private MediaPlayer mmediaplayer;
    private AudioManager maudiomanager;
    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mmediaplayer.pause();
                mmediaplayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mmediaplayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drinks);
        maudiomanager = (AudioManager) getSystemService(AUDIO_SERVICE);
        ArrayList<word> dri=new ArrayList<word>();
        dri.add(new word("Daal Soup","दाल का सूप",R.drawable.family_grandmother,R.raw.daal_soup));
        dri.add(new word("Coca Cola","कोको कोला",R.drawable.family_son,R.raw.coca_cola));
        dri.add(new word("Daal Soup","दाल का सूप",R.drawable.family_grandmother,R.raw.daal_soup));
        dri.add(new word("Coca Cola","कोको कोला",R.drawable.family_son,R.raw.coca_cola));
        dri.add(new word("Daal Soup","दाल का सूप",R.drawable.family_grandmother,R.raw.daal_soup));
        dri.add(new word("Coca Cola","कोको कोला",R.drawable.family_son,R.raw.coca_cola));
        dri.add(new word("Daal Soup","दाल का सूप",R.drawable.family_grandmother,R.raw.daal_soup));
        dri.add(new word("Coca Cola","कोको कोला",R.drawable.family_son,R.raw.coca_cola));
        dri.add(new word("Daal Soup","दाल का सूप",R.drawable.family_grandmother,R.raw.daal_soup));
        dri.add(new word("Coca Cola","कोको कोला",R.drawable.family_son,R.raw.coca_cola));
        wordAdapter items=new wordAdapter(this,dri);
        ListView listView=(ListView)findViewById(R.id.list3);
        listView.setAdapter(items);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                word new_word = dri.get(position);
                releaseMediaPlayer();
                int result=maudiomanager.requestAudioFocus(onAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    MediaPlayer mmediaplayer = MediaPlayer.create(Drinks.this, new_word.getaudioid());
                    mmediaplayer.start();
                    mmediaplayer.setOnCompletionListener(mcompletionlistener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer()
    {
        if(mmediaplayer!=null)
        {
            mmediaplayer.release();
            mmediaplayer=null;
            maudiomanager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }
}