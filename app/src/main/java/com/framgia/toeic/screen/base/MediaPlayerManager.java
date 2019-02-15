package com.framgia.toeic.screen.base;

import android.content.Context;
import android.content.ContextWrapper;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.File;
import java.io.IOException;

public class MediaPlayerManager {
    private static final String FORLDER_MEDIA = "audio";
    public static MediaPlayerManager sMediaPlayerManager;
    private MediaPlayer mMediaPlayer;

    public MediaPlayerManager(MediaPlayer mediaPlayer) {
        mMediaPlayer = mediaPlayer;
    }

    public static MediaPlayerManager getInstance(MediaPlayer mediaPlayer) {
        if (sMediaPlayerManager == null) {
            sMediaPlayerManager = new MediaPlayerManager(mediaPlayer);
        }
        return sMediaPlayerManager;
    }

    public void playMedia(Context context, int id, String extension) throws IOException {
        ContextWrapper contextWrapper = new ContextWrapper(context);
        File directory = contextWrapper.getDir(FORLDER_MEDIA, Context.MODE_PRIVATE);
        File mypath = new File(directory, id + extension);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.reset();
        mMediaPlayer.setDataSource(mypath.getPath());
        mMediaPlayer.prepare();
    }

    public void startMedia() {
        mMediaPlayer.start();
    }

    public void pause() {
        mMediaPlayer.pause();
    }

    public void stop() {
        mMediaPlayer.stop();
    }

    public int getDurationMedia() {
        return mMediaPlayer.getDuration();
    }

    public void seekTo(int position) {
        mMediaPlayer.seekTo(position);
    }

    public int getCurrentPosition(){
        return mMediaPlayer.getCurrentPosition();
    }

    public boolean isPlaying(){
        return mMediaPlayer.isPlaying();
    }
}
