package com.example.v.vikaApp.expanded;

import com.example.v.vikaApp.entity.CatEntity;

public class ExpandedPresenterImpl implements ExpandedPresenter {

    private ExpandedView mView;
    private ExpandedModel mModel;

    ExpandedPresenterImpl(ExpandedView view, ExpandedModel model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void actionFavourite(CatEntity catEntity) {
        mModel.doActionFavourite(catEntity, new ExpandedModel.Result() {
                    @Override
                    public void onAdd() {
                        mView.displayAddToFavourite();
                    }

                    @Override
                    public void onRemove() {
                        mView.displayRemoveFromFavourite();
                    }
                }
        );
    }
}