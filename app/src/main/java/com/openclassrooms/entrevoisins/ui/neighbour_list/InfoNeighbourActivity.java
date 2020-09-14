package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.UnselectFavoriteEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoNeighbourActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_activity_info) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.name_on_avatar_neighbour) TextView nameOnAvatar;
    @BindView(R.id.title_name_neighbour) TextView titleNameNeighbour;
    @BindView(R.id.location_neighbour) TextView locationNeighbour;
    @BindView(R.id.phone_neighbour) TextView phoneNeighbour;
    @BindView(R.id.website_neighbour) TextView websiteNeighbour;
    @BindView(R.id.avatar_neighbour) ImageView avatarNeighbour;
    @BindView(R.id.text_about_me_neighbour) TextView textAboutMeNeighbour;

    private static String TAG_NEIGHBOUR_INTENT_EXTRA = "NEIGHBOUR_EXTRA";
    private Neighbour neighbour;

    private NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_neighbour);

        ButterKnife.bind(this);

        initializeToolbar();

        // Get list of Neighbour
        mApiService = DI.getNeighbourApiService();

        // Get info Neighbour to display from intent
        Intent infoNeighbour = getIntent();

        if(infoNeighbour.hasExtra(TAG_NEIGHBOUR_INTENT_EXTRA)){
            neighbour = (Neighbour) infoNeighbour.getSerializableExtra(TAG_NEIGHBOUR_INTENT_EXTRA);
            initializeInfoToDisplay();
        }

    }

    @Override
    public void onStart(){
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop(){
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void initializeInfoToDisplay(){
        //TextViews
        nameOnAvatar.setText(neighbour.getName());
        titleNameNeighbour.setText(neighbour.getName());

        if(neighbour.getAddress() != null){ locationNeighbour.setText(neighbour.getAddress());}
        if(neighbour.getPhoneNumber() != null){ phoneNeighbour.setText(neighbour.getPhoneNumber());}
        if(neighbour.getAboutMe() != null) { textAboutMeNeighbour.setText(neighbour.getAboutMe());}
        if(neighbour.getWebSite() != null) { websiteNeighbour.setText(neighbour.getWebSite());}

        // Avatar
        Glide.with(InfoNeighbourActivity.this)
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.centerCropTransform())
                .into(avatarNeighbour);

        // FloatingButton status
        if(neighbour.getFavorite()){ fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_yellow_24dp, null));}
        else{ fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_yellow_24dp, null));}
    }

    @Subscribe
    public void updateFavoriteStatusInList(UnselectFavoriteEvent event){
        // Find which item must been modified in list
        int index = mApiService.getNeighbours().indexOf(event.favorite);
        // Update "Favorite" status
        mApiService.getNeighbours().get(index).setFavorite(neighbour.getFavorite());
    }

    @OnClick(R.id.fab)
    public void updateFabDisplay(){
        // Update "Favorite" status
        neighbour.setFavorite(!neighbour.getFavorite());
        // Update display according to new value
        if(neighbour.getFavorite()){ fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_yellow_24dp, null));}
        else{ fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_yellow_24dp, null));}
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){ // Back arrow
            // Save new Favorite status for current User before ending the activity
            saveFavoriteStatus();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initializeToolbar(){
        setSupportActionBar(toolbar);
        try{
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
            toolbar.setPopupTheme(R.style.BackArrowTheme);
        } catch(NullPointerException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void onBackPressed(){
        // Save new Favorite status for current User before ending the activity
        saveFavoriteStatus();
        super.onBackPressed();
    }

    public void saveFavoriteStatus(){
        EventBus.getDefault().post(new UnselectFavoriteEvent(neighbour));
    }
}
