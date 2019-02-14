package com.framgia.toeic.screen.base;

import android.content.Context;
import android.content.ContextWrapper;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.File;
import java.io.IOException;

public class MediaPlayerInstance {
    private static final String FORLDER_MEDIA = "audio";
    public static MediaPlayer sMediaPlayer;

    public static MediaPlayer getInstance(){
        if (sMediaPlayer == null){
            sMediaPlayer = new MediaPlayer();
        }
        return sMediaPlayer;
    }

    public static void playMedia(Context context, int id, String extension){
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(FORLDER_MEDIA, Context.MODE_PRIVATE);
        File mypath = new File(directory, id + extension);
        try {
            MediaPlayerInstance.getInstance().setAudioStreamType(AudioManager.STREAM_MUSIC);
            MediaPlayerInstance.getInstance().reset();
            MediaPlayerInstance.getInstance().setDataSource(mypath.getPath());
            MediaPlayerInstance.getInstance().prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
