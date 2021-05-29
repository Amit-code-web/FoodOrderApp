package com.example.newjustjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Restaurants extends AppCompatActivity {
    private MediaPlayer.OnCompletionListener mcompletionlistener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    private MediaPlayer mmediaplayer;
    private AudioManager mAudioManager;
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
        setContentView(R.layout.restaurants);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //this list contains the names of the restaurants.
        ArrayList<word> rest = new ArrayList<word>();
        rest.add(new word("Narayan Restaurant","नारायण रेस्टोरेंट",R.drawable.color_green,R.raw.narayan_restaurant));
        rest.add(new word("RS Bhojnalaya","आर एस भोजनालय",R.drawable.color_black,R.raw.rs_bhojanalaya));
        rest.add(new word("Maa vaishno","माँ वैष्णो",R.drawable.color_brown,R.raw.maa_vaishno));
        rest.add(new word("Haveli","हवेली",R.drawable.color_dusty_yellow,R.raw.haveli));
        rest.add(new word("Amul Parlour","अमूल पार्लर",R.drawable.color_mustard_yellow,R.raw.amul_parlour));
        rest.add(new word("Peace Cafe","पीस कैफे",R.drawable.color_red,R.raw.peace_cafe));
        rest.add(new word("Bikaneri","बीकानेरी",R.drawable.color_gray,R.raw.bikaneri));
        rest.add(new word("RS Bhojnalaya","आर एस भोजनालय",R.drawable.color_black,R.raw.rs_bhojanalaya));
        rest.add(new word("Narayan Restaurant","नारायण रेस्टोरेंट",R.drawable.color_green,R.raw.narayan_restaurant));
        rest.add(new word("RS Bhojnalaya","आर एस भोजनालय",R.drawable.color_black,R.raw.rs_bhojanalaya));
        wordAdapter itemsAdapter = new wordAdapter(this,rest);
        ListView listView = (ListView) findViewById(R.id.list1);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                word new_word=rest.get(position);
                releaseMediaPlayer();
                int result=mAudioManager.requestAudioFocus(onAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mmediaplayer = MediaPlayer.create(Restaurants.this, new_word.getaudioid());
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
        if(mmediaplayer!=null) {
            mmediaplayer.release();
            mmediaplayer = null;
            mAudioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }
}

