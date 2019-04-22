package com.alanvan.bakingapp.ui.step_detail;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Pair;

import com.alanvan.bakingapp.injection.Injector;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.model.Step;
import com.alanvan.bakingapp.repository.RecipeRepository;
import com.alanvan.bakingapp.utils.RxUtils;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

import io.reactivex.Observable;

public class StepDetailViewModel extends ViewModel implements ExoPlayer.EventListener {

    @SuppressLint("StaticFieldLeak")
    private Context appContext = Injector.getContextComponent().appContext();

    private MediaSessionCompat mediaSession;
    private ExoPlayer player;
    private RecipeRepository recipeRepository = Injector.getAppComponent().repositoryManager().getRecipeRepository();

    public Observable<Uri> loadVideoUri(int recipeId, int stepId) {

        return recipeRepository.getStep(recipeId, stepId).map(step -> {
            if (!step.getVideoURL().isEmpty()) {
                return step.getVideoURL();
            } else if (!step.getThumbnailURL().isEmpty()) {
                return step.getThumbnailURL();
            } else {
                return "";
            }
        }).map(Uri::parse);
    }

    public Observable<String> loadStepInstruction(int recipeId, int stepId) {
        return recipeRepository.getStep(recipeId, stepId).map(Step::getDescription);
    }

    public Observable<Integer> getLastStepId(int recipeId) {
        return recipeRepository.getLastStepId(recipeId);
    }

    public void initializePlayer(Uri videoUri, PlayerView playerView) {
        if (player == null) {

            TrackSelector trackSelector = new DefaultTrackSelector();
            player = ExoPlayerFactory.newSimpleInstance(appContext, trackSelector);
            playerView.setPlayer(player);
            player.addListener(this);

            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(appContext,
                    Util.getUserAgent(appContext, "Baking Time"));

            MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri);

            player.prepare(mediaSource);
            player.setPlayWhenReady(false);
        }
    }

    public void initializeMediaSession() {
        mediaSession = new MediaSessionCompat(appContext, "StepVideo");

        mediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        mediaSession.setMediaButtonReceiver(null);

        PlaybackStateCompat.Builder stateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE
                );

        mediaSession.setPlaybackState(stateBuilder.build());
        mediaSession.setCallback(new MediaSessionCompat.Callback() {
            @Override
            public void onPlay() {
                if (player != null) {
                    player.setPlayWhenReady(true);
                }
            }

            @Override
            public void onPause() {
                if (player != null) {
                    player.setPlayWhenReady(false);
                }
            }

            @Override
            public void onSkipToPrevious() {
                if (player != null) {
                    player.seekTo(0);
                }
            }
        });
        mediaSession.setActive(true);
    }

    public void releasePlayer() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }

        if (mediaSession != null) {
            mediaSession.setActive(false);
        }
    }
}
