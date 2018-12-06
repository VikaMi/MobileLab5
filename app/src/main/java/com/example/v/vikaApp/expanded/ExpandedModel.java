package com.example.v.vikaApp.expanded;

import com.example.v.vikaApp.entity.CatEntity;

public interface ExpandedModel {

    void doActionFavourite(CatEntity catEntity, Result result);

    void addFavourite(CatEntity catEntity);

    void rmFavourite(CatEntity catEntity);

    interface Result {
        void onAdd();

        void onRemove();
    }
}
