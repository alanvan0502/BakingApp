package com.alanvan.bakingapp.injection.component;

import com.alanvan.bakingapp.injection.module.AppContextModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vuhoang on 12/21/16.
 */
@Singleton
@Component(modules = {
        AppContextModule.class,
})

public interface ContextComponent {

}
