package com.example.v.vikaApp.expanded;

import android.content.Context;

import com.example.v.vikaApp.entity.CatEntity;
import com.example.v.vikaApp.prefs.Preferences;

public class ExpandedModelImpl implements ExpandedModel {

    private final Preferences mPreferences;

    ExpandedModelImpl(Context context) {
        this.mPreferences = new Preferences(context);
    }

    @Override
    public void doActionFavourite(CatEntity catEntity, Result result) {
        if (mPreferences.getSharedPrefs().contains(catEntity.getId())) {
            rmFavourite(catEntity);
            result.onRemove();
        } else {
            addFavourite(catEntity);
            result.onAdd();
        }
    }

    @Override
    public void addFavourite(CatEntity catEntity) {
        mPreferences.putIntoSharedPrefs(catEntity);
    }

    @Override
    public void rmFavourite(CatEntity catEntity) {
        mPreferences.rmFromSharedPrefs(catEntity);
    }

}
