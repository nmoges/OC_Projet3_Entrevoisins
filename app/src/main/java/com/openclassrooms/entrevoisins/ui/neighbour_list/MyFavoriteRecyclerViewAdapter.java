package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.support.annotation.NonNull;
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
import com.openclassrooms.entrevoisins.events.UnselectFavoriteEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import org.greenrobot.eventbus.EventBus;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoriteRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriteRecyclerViewAdapter.ViewHolder>{

    private final List<Neighbour> mFavorites;

    private ListFavoriteListener mListFavoriteListener;

    private Context mContext;

    public MyFavoriteRecyclerViewAdapter(List<Neighbour> items, ListFavoriteListener listFavoriteListener, Context context){
        this.mFavorites = items;
        this.mListFavoriteListener = listFavoriteListener;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favorite, parent, false);
        return new ViewHolder(view, mListFavoriteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Neighbour favorite = mFavorites.get(position);

        holder.mFavoriteName.setText(favorite.getName());
        Glide.with(holder.mFavoriteAvatar.getContext())
                .load(favorite.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .placeholder(mContext.getResources().getDrawable(R.drawable.ic_account, null))
                .error(R.drawable.ic_account)
                .skipMemoryCache(false)
                .into(holder.mFavoriteAvatar);

        holder.mFavoriteButton.setOnClickListener((View v) -> {
                EventBus.getDefault().post(new UnselectFavoriteEvent(favorite));
            }
        );
    }

    @Override
    public int getItemCount() {
        return mFavorites.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.item_favorite_list_avatar)
        public ImageView mFavoriteAvatar;
        @BindView(R.id.item_favorite_list_name)
        public TextView mFavoriteName;
        @BindView(R.id.item_list_favorite_button)
        public ImageButton mFavoriteButton;

        public ListFavoriteListener listFavoriteListener;

        public ViewHolder(View view, ListFavoriteListener listFavoriteListener){
            super(view);
            ButterKnife.bind(this, view);

            // Enable click on item
            view.setOnClickListener(this);
            this.listFavoriteListener = listFavoriteListener;
        }

        @Override
        public void onClick(View v) {
            // Get position for clicked item on the list
            listFavoriteListener.onClickItemFavorite(getAdapterPosition());
        }
    }

    public interface ListFavoriteListener{
        void onClickItemFavorite(int position);
    }
}
