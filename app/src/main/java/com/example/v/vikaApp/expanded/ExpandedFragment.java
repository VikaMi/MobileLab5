package com.example.v.vikaApp.expanded;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.v.vikaApp.MainActivity;
import com.example.v.vikaApp.R;
import com.example.v.vikaApp.entity.CatEntity;
import com.example.v.vikaApp.fullscreen.FullscreenPhotoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExpandedFragment extends Fragment implements ExpandedView {

    @BindView(R.id.image_view_expanded)
    ImageView mImageView;
    @BindView(R.id.text_view_expanded)
    TextView mTextView;
    @BindView(R.id.favourite_action_button)
    Button mButtonFavourites;
    private ExpandedPresenter mExpandedPresenter;
    private CatEntity mCatEntity;
    public static final String CAT_ENTITY = "CAT_ENTITY";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_item_expanded, container, false);
        ButterKnife.bind(this, view);
        createPresenter();
        getCat();
        displayItems();
        return view;
    }

    public static ExpandedFragment newInstance(CatEntity catEntity) {
        Bundle args = new Bundle();
        ExpandedFragment fragment = new ExpandedFragment();
        args.putParcelable(CAT_ENTITY, catEntity);
        fragment.setArguments(args);
        return fragment;
    }

    private void getCat() {
        Bundle bundle = this.getArguments();
        if (bundle!=null)
            mCatEntity = bundle.getParcelable(CAT_ENTITY);
    }

    @OnClick({R.id.image_view_expanded, R.id.favourite_action_button})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_view_expanded:
                startFullPhotoFragment();
                break;
            case R.id.favourite_action_button:
                mExpandedPresenter.actionFavourite(mCatEntity);
                break;
        }
    }

    private void startFullPhotoFragment() {
        if (getActivity()==null) return;
        ((MainActivity) getActivity()).setFragment(FullscreenPhotoFragment.newInstance(mCatEntity));
    }

    private void displayItems() {
        if (mCatEntity != null) {
            final int[] codes = {100, 101, 200, 201, 202, 204, 500, 404, 300, 425, 426,
                    207, 307, 410, 411, 420, 421, 422, 423, 424};

            RequestOptions glideOptions = new RequestOptions();
            for (final int code : codes) {
                if (getContext() != null) {

                    Glide.with(getContext())
                            .load(mCatEntity.getUrl(code))
                            .apply(glideOptions.centerCrop())
                            .into(mImageView);
                    mTextView.setText(mCatEntity.getId());
                }
            }
        } else {
            Toast.makeText(getContext(), getString(R.string.no_cat), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void displayAddToFavourite() {
        mButtonFavourites.setText(getString(R.string.favourite_button_action_rm));
        Toast.makeText(getContext(), getString(R.string.favourite_button_success),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayRemoveFromFavourite() {
        mButtonFavourites.setText(getString(R.string.favourite_button_action_add));
        Toast.makeText(getContext(), getString(R.string.favourite_button_rm),
                Toast.LENGTH_SHORT).show();
    }

    private void createPresenter() {
        ExpandedModel expandedModel = new ExpandedModelImpl(getContext());
        mExpandedPresenter = new ExpandedPresenterImpl(this, expandedModel);
    }
}