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
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.UnselectFavoriteEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements MyFavoriteRecyclerViewAdapter.ListFavoriteListener {

    private NeighbourApiService mApiService;
    private List<Neighbour> mFavorites;
    private RecyclerView mRecyclerView;

    private static String TAG_NEIGHBOUR_INTENT_EXTRA = "NEIGHBOUR_EXTRA";

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
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
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

    @Subscribe
    public void onUnselectFavorite(UnselectFavoriteEvent event){
        // Remove from displayed favorite list
        mFavorites.remove(event.favorite);

        // Update status in user list
        mApiService.updateFavoriteStatus(event.favorite);

        // Update RecyclerView display
        try{ mRecyclerView.getAdapter().notifyDataSetChanged(); }
        catch (NullPointerException exception){ exception.printStackTrace(); }
    }

    private void initFavoritesList(){
        mFavorites = new ArrayList<>();
        // Get all favorite neighbours
        for(int i = 0; i < mApiService.getNeighbours().size(); i++){
            if(mApiService.getNeighbours().get(i).getFavorite()){
                mFavorites.add(mApiService.getNeighbours().get(i));
            }
        }
        mRecyclerView.setAdapter(new MyFavoriteRecyclerViewAdapter(mFavorites, this));
    }

    @Override
    public void onClickItemFavorite(int position) {
        Intent launchActivityInfo = new Intent(getActivity(), InfoNeighbourActivity.class);
        launchActivityInfo.putExtra(TAG_NEIGHBOUR_INTENT_EXTRA, mFavorites.get(position));
        startActivity(launchActivityInfo);
    }
}
