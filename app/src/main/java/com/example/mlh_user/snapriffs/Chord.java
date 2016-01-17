package com.example.mlh_user.snapriffs;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Joseph Buchoff on 1/16/2016.
 */
public class Chord {
    // Name of the chord
    public String name;

    // MediaPlayer to play the chord
    MediaPlayer chordPlayer;

    // The int for the chord audio resource
    int chordAudio;

    /*
    General constructor for Chord
     */
    public Chord(String name, int chordAudio, Context context)
    {
        this.name = name;
        this.chordAudio = chordAudio;

        // Initialize the chord player
        chordPlayer = MediaPlayer.create(context, chordAudio);
    }

    /*
    Takes in the context for the calling environment
        and plays the chord
     */
    public void playChord(Context context)
    {
        if (!chordPlayer.isPlaying())
            chordPlayer.start();
    }
}