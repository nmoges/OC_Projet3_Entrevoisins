package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import org.greenrobot.eventbus.EventBus;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;

    private ListNeighbourListener listNeighbourListener;

    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items, ListNeighbourListener listNeighbourListener) {
        mNeighbours = items;
        this.listNeighbourListener = listNeighbourListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view, listNeighbourListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());

        Glide.with(holder.mNeighbourAvatar.getContext())
                    .load(neighbour.getAvatarUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.mNeighbourAvatar);

        holder.mDeleteButton.setOnClickListener((View v) -> {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        );
    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ListNeighbourListener listNeighbourListener;

        public ViewHolder(View view, ListNeighbourListener listNeighbourListener) {
            super(view);
            ButterKnife.bind(this, view);

            // Enable click on item
            view.setOnClickListener(this);
            this.listNeighbourListener = listNeighbourListener;
        }

        @Override
        public void onClick(View v) {
            // Get position for clicked item on the list
            listNeighbourListener.onClickItemNeighbour(getAdapterPosition());
        }
    }

    public interface ListNeighbourListener {
        void onClickItemNeighbour(int position);
    }
}
