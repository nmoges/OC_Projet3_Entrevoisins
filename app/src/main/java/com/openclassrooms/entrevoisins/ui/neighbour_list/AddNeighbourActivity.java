package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.app.Activity;
import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNeighbourActivity extends AppCompatActivity {

    @BindView(R.id.avatar)
    ImageView mAvatar;
    @BindView(R.id.nameLyt)
    TextInputLayout mNameInput;
    @BindView(R.id.phoneNumberLyt)
    TextInputLayout mPhoneInput;
    @BindView(R.id.addressLyt)
    TextInputLayout mAddressInput;
    @BindView(R.id.aboutMeLyt)
    TextInputLayout mAboutMeInput;
    @BindView(R.id.webSiteLyt)
    TextInputLayout mWebSiteInput;

    @BindView(R.id.create)
    MaterialButton addButton;

    private NeighbourApiService mApiService;
    private String mNeighbourImage;

    // Parameters to recover data from existing Neighbour
    private static String TAG_NEIGHBOUR_INTENT_EXTRA = "NEIGHBOUR_EXTRA";
    private Neighbour neighbourToModify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_neighbour);

        ButterKnife.bind(this);

        // Enable display of back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mApiService = DI.getNeighbourApiService();

        init();

        Intent infoNeighbour = getIntent();
        if(infoNeighbour.hasExtra(TAG_NEIGHBOUR_INTENT_EXTRA)){
            restoreNeighbourInfo(infoNeighbour);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        mNeighbourImage = randomImage();
        Glide.with(this).load(mNeighbourImage).placeholder(R.drawable.ic_account)
                .apply(RequestOptions.circleCropTransform()).into(mAvatar);
        mNameInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                addButton.setEnabled(s.length() > 0);
            }
        });

    }

    @OnClick(R.id.create)
    void addNeighbour() {
        if(neighbourToModify == null){
            // Case New Neighbour (neighbourToModify not initialized)
            Neighbour neighbour = new Neighbour(
                    System.currentTimeMillis(),
                    mNameInput.getEditText().getText().toString(),
                    mNeighbourImage,
                    mAddressInput.getEditText().getText().toString(),
                    mPhoneInput.getEditText().getText().toString(),
                    mAboutMeInput.getEditText().getText().toString(),
                    false,
                    mWebSiteInput.getEditText().getText().toString()
            );
            // Added to the list
            mApiService.createNeighbour(neighbour);
        }
        else{
            // Case Modification existing Neighbour (neighbourToModify initialized)

            // Store updates
            neighbourToModify.setName(mNameInput.getEditText().getText().toString());
            neighbourToModify.setPhoneNumber(mPhoneInput.getEditText().getText().toString());
            neighbourToModify.setAddress(mAddressInput.getEditText().getText().toString());
            neighbourToModify.setAboutMe(mAboutMeInput.getEditText().getText().toString());
            neighbourToModify.setWebSite(mWebSiteInput.getEditText().getText().toString());

            // Send updates to InfoNeighbourActivity
            Intent updateIntent = new Intent();
            setResult(Activity.RESULT_OK, updateIntent);
        }
        finish();
    }

    /**
     * Generate a random image. Useful to mock image picker
     * @return String
     */
    String randomImage() {
        return "https://i.pravatar.cc/150?u="+ System.currentTimeMillis();
    }

    /**
     * Used to navigate to this activity
     * @param activity
     */
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddNeighbourActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

    public void restoreNeighbourInfo(Intent infoNeighbour){

        neighbourToModify = (Neighbour) infoNeighbour.getSerializableExtra(TAG_NEIGHBOUR_INTENT_EXTRA);

        try{

            mNameInput.getEditText().setText(neighbourToModify.getName(), null);
            mPhoneInput.getEditText().setText(neighbourToModify.getPhoneNumber(), null);
            mAddressInput.getEditText().setText(neighbourToModify.getAddress(), null);
            mAboutMeInput.getEditText().setText(neighbourToModify.getAboutMe(), null);
            mWebSiteInput.getEditText().setText(neighbourToModify.getWebSite(), null);

            Glide.with(this)
                    .load(neighbourToModify.getAvatarUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .placeholder(getResources().getDrawable(R.drawable.ic_account, null))
                    .error(R.drawable.ic_account)
                    .skipMemoryCache(false)
                    .into(mAvatar);

        } catch(NullPointerException exception){
            exception.printStackTrace();
        }
    }

}
