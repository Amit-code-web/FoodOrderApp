package com.example.newjustjava;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Snacks extends AppCompatActivity {
    private MediaPlayer.OnCompletionListener mcompletionlistener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
     private MediaPlayer mmediaplayer;
     private AudioManager maudiomanager;
     AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
         @Override
         public void onAudioFocusChange(int focusChange) {
             if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
             {
                 mmediaplayer.pause();
                 mmediaplayer.seekTo(0);
             }
             else if(focusChange==AudioManager.AUDIOFOCUS_GAIN)
             {
                 mmediaplayer.start();
             }
             else if(focusChange==AudioManager.AUDIOFOCUS_LOSS)
             {
                 releaseMediaPlayer();
             }
         }
     };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snacks);
        maudiomanager = (AudioManager) getSystemService(AUDIO_SERVICE);
        ArrayList<word> sna=new ArrayList<word>();
        sna.add(new word("Samosa","समोसा",R.drawable.number_five,R.raw.samosa));
        sna.add(new word("Dhokla","ढोकला",R.drawable.number_eight,R.raw.dhokla));
        sna.add(new word("Samosa","समोसा",R.drawable.number_five,R.raw.samosa));
        sna.add(new word("Dhokla","ढोकला",R.drawable.number_eight,R.raw.dhokla));
        sna.add(new word("Samosa","समोसा",R.drawable.number_five,R.raw.samosa));
        sna.add(new word("Dhokla","ढोकला",R.drawable.number_eight,R.raw.dhokla));
        sna.add(new word("Samosa","समोसा",R.drawable.number_five,R.raw.samosa));
        sna.add(new word("Dhokla","ढोकला",R.drawable.number_eight,R.raw.dhokla));
        sna.add(new word("Samosa","समोसा",R.drawable.number_five,R.raw.samosa));
        sna.add(new word("Dhokla","ढोकला",R.drawable.number_eight,R.raw.dhokla));
        wordAdapter items=new wordAdapter(this,sna);
        ListView listView=(ListView)findViewById(R.id.list4);
        listView.setAdapter(items);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                word new_word=sna.get(position);
                releaseMediaPlayer();
                int result=maudiomanager.requestAudioFocus(onAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mmediaplayer = MediaPlayer.create(Snacks.this, new_word.getaudioid());
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