package com.openclassrooms.entrevoisins.ui;

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
import android.support.v7.widget.Toolbar;
import java.util.Objects;

/**
 * Activity which allows user to create a new Neighbour and add it to the list of Neighbour,
 * or to modify an existing Neighbour
 */
public class AddNeighbourActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_activity_add)
    Toolbar toolbar;
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

        initializeToolbar();

        mApiService = DI.getNeighbourApiService();

        init();

        Intent infoNeighbour = getIntent();
        if(infoNeighbour.hasExtra(TAG_NEIGHBOUR_INTENT_EXTRA)){ // Modify existing Neighbour
            // Update edit fields
            restoreNeighbourInfo(infoNeighbour);
            // Update toolbar
            Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.modify_neighbour));
        }
        else{ // Create New Neighbour
            Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.new_neighbour));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method which initialize toolbar by adding a back icon
     */
    public void initializeToolbar(){
        setSupportActionBar(toolbar);
        try{
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            toolbar.setPopupTheme(R.style.BackArrowTheme);
        } catch (NullPointerException exception){
            exception.printStackTrace();
        }
    }

    /**
     * Initialize elements
     */
    private void init() {
        mNeighbourImage = randomImage();

        Glide.with(this).load(mNeighbourImage).placeholder(R.drawable.ic_account)
                .apply(RequestOptions.circleCropTransform()).into(mAvatar);

        Objects.requireNonNull(mNameInput.getEditText()).addTextChangedListener(new TextWatcher() {
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

    /**
     * This methods does :
     *      - add a new Neighbour in the Neighbour list
     *      - modify an existing Neighbour from the Neighbour list
     */
    @OnClick(R.id.create)
    void addNeighbour() {
        if(neighbourToModify == null){
            // Case New Neighbour (neighbourToModify not initialized)
            Neighbour neighbour = new Neighbour(
                    System.currentTimeMillis(),
                    Objects.requireNonNull(mNameInput.getEditText()).getText().toString(),
                    mNeighbourImage,
                    Objects.requireNonNull(mAddressInput.getEditText()).getText().toString(),
                    Objects.requireNonNull(mPhoneInput.getEditText()).getText().toString(),
                    Objects.requireNonNull(mAboutMeInput.getEditText()).getText().toString(),
                    false,
                    Objects.requireNonNull(mWebSiteInput.getEditText()).getText().toString()
            );
            // Added to the list
            mApiService.createNeighbour(neighbour);
        }
        else{
            // Case Modification existing Neighbour (neighbourToModify initialized)
            // Store updates
            neighbourToModify.setName(Objects.requireNonNull(mNameInput.getEditText()).getText().toString());
            neighbourToModify.setPhoneNumber(Objects.requireNonNull(mPhoneInput.getEditText()).getText().toString());
            neighbourToModify.setAddress(Objects.requireNonNull(mAddressInput.getEditText()).getText().toString());
            neighbourToModify.setAboutMe(Objects.requireNonNull(mAboutMeInput.getEditText()).getText().toString());
            neighbourToModify.setWebSite(Objects.requireNonNull(mWebSiteInput.getEditText()).getText().toString());

            // Send updates to InfoNeighbourActivity
            Intent updateIntent = new Intent();
            updateIntent.putExtra("UPDATE", neighbourToModify);
            setResult(Activity.RESULT_OK, updateIntent);
        }
        finish();
    }

    /**
     * Generate a random image. Useful to mock image picker
     * @return : String
     */
    String randomImage() {
        return "https://i.pravatar.cc/150?u="+ System.currentTimeMillis();
    }

    /**
     * Used to navigate to this activity
     * @param activity : FragmentActivity
     */
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddNeighbourActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

    /**
     * Initializes elements displayed with selected Neighbour to modify
     * @param infoNeighbour : Intent
     */
    public void restoreNeighbourInfo(Intent infoNeighbour){

        neighbourToModify = (Neighbour) infoNeighbour.getSerializableExtra(TAG_NEIGHBOUR_INTENT_EXTRA);

        // Load all text fields
        Objects.requireNonNull(mNameInput.getEditText()).setText(neighbourToModify.getName(), null);
        Objects.requireNonNull(mPhoneInput.getEditText()).setText(neighbourToModify.getPhoneNumber(), null);
        Objects.requireNonNull(mAddressInput.getEditText()).setText(neighbourToModify.getAddress(), null);
        Objects.requireNonNull(mAboutMeInput.getEditText()).setText(neighbourToModify.getAboutMe(), null);
        Objects.requireNonNull(mWebSiteInput.getEditText()).setText(neighbourToModify.getWebSite(), null);

        // Load image
        Glide.with(this)
                .load(neighbourToModify.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .placeholder(getResources().getDrawable(R.drawable.ic_account, null))
                .error(R.drawable.ic_account)
                .skipMemoryCache(false)
                .into(mAvatar);
    }

}
