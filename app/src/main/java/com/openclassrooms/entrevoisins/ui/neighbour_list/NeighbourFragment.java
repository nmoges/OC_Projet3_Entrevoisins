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
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.List;

public class NeighbourFragment extends Fragment implements MyNeighbourRecyclerViewAdapter.ListNeighbourListener {

    //private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;

    private static String TAG_NEIGHBOUR_INTENT_EXTRA = "NEIGHBOUR_EXTRA";

    private ListNeighbourActivity mActivity;
    /**
     * Create and return a new instance
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance() {
        return new NeighbourFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mActivity = (ListNeighbourActivity) getActivity();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        mNeighbours = mActivity.getmApiService().getNeighbours();
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours, this, mActivity));
    }

    /**
     * Update the list of FavoriteFragment
     */
    private void notifyFavoriteListChange(Neighbour neighbour){
        mActivity.getMPagerAdapter().getFavoriteFragment().updateAfterDelete(neighbour);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mActivity.getmApiService().deleteNeighbour(event.neighbour);

        // Update Neighbour list
        mRecyclerView.getAdapter().notifyDataSetChanged();

        // If deleted Neighbour was a "Favorite", update "Favorite" list
        if(event.neighbour.getFavorite()){notifyFavoriteListChange(event.neighbour);}
    }

    /**
     * MyNeighbourRecyclerViewAdapter.ListNeighbourListener interface implementation
     * @param position
     */
    @Override
    public void onClickItemNeighbour(int position) {
        Intent launchActivityInfo = new Intent(getActivity(), InfoNeighbourActivity.class);
        launchActivityInfo.putExtra(TAG_NEIGHBOUR_INTENT_EXTRA, mNeighbours.get(position));
        startActivity(launchActivityInfo);
    }
}
