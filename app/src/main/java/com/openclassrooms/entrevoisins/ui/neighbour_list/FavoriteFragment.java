package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.UnselectFavoriteEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements MyFavoriteRecyclerViewAdapter.ListFavoriteListener {

    private List<Neighbour> mFavorites = new ArrayList<>();
    private RecyclerView mRecyclerView;

    // To access NeighbourApiService from fragment
    private ListNeighbourActivity mActivity;

    // Tag for intent
    private String TAG_NEIGHBOUR_INTENT_EXTRA = "NEIGHBOUR_EXTRA";
    /**
     * Create and return a new instance
     * @return @{@link FavoriteFragment}
     */
    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mActivity = (ListNeighbourActivity) getActivity();

        return view;
    }


    @Override
    public void onStart(){
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        initFavoritesList();
    }

    @Override
    public void onStop(){
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void initFavoritesList(){

        mFavorites.clear();

        // Get all favorite neighbours
        for(int i = 0; i < mActivity.getmApiService().getNeighbours().size(); i++){
            if(mActivity.getmApiService().getNeighbours().get(i).getFavorite()){
                mFavorites.add(mActivity.getmApiService().getNeighbours().get(i));
            }
        }

        // Initialize adapter display
        mRecyclerView.setAdapter(new MyFavoriteRecyclerViewAdapter(mFavorites, this, getContext()));
    }

    public void updateAfterDelete(Neighbour neighbour){

        mFavorites.remove(neighbour);
        // Update RecyclerView display
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Subscribe
    public void onUnselectFavorite(UnselectFavoriteEvent event){
        // Remove from displayed favorite list
        mFavorites.remove(event.favorite);

        // Update status in user list
        mActivity.getmApiService().updateFavoriteStatus(event.favorite);

        // Update RecyclerView display
        try{ mRecyclerView.getAdapter().notifyDataSetChanged(); }
        catch (NullPointerException exception){ exception.printStackTrace(); }
    }

    /**
     * Update background display depending of if mFavorites list contains elements or not.
     */

    @Override
    public void onClickItemFavorite(int position) {
        Intent launchActivityInfo = new Intent(getActivity(), InfoNeighbourActivity.class);
        launchActivityInfo.putExtra(TAG_NEIGHBOUR_INTENT_EXTRA, mFavorites.get(position));
        startActivity(launchActivityInfo);
    }
}
