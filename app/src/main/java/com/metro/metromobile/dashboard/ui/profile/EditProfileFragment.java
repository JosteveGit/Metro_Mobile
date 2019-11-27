package com.metro.metromobile.dashboard.ui.profile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;

import com.metro.metromobile.R;
import com.metro.metromobile.injector.Injector;
import com.metro.metromobile.model.response.BaseResponse;
import com.metro.metromobile.rest.ServiceGenerator;
import com.metro.metromobile.shared.base.BaseFragment;
import com.metro.metromobile.utils.manager.SessionManager;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileFragment extends BaseFragment {

    private static final int GALLERY_REQUEST_CODE = 100;
    private ProfileViewModel profileViewModel;
    TextView changeDp;
    CircleImageView userImage;
    @BindView(R.id.profile_fname)
    AppCompatEditText profileFname;
    @BindView(R.id.profile_lname)
    AppCompatEditText profileLname;
    @BindView(R.id.profile_about)
    AppCompatEditText profileAbout;
    @BindView(R.id.profile_phone)
    AppCompatEditText profilePhone;
    @BindView(R.id.profile_email)
    AppCompatEditText profileEmail;
    @BindView(R.id.save_changes_btn)
    AppCompatButton saveChangesBtn;

    Uri selectedImage;

    String val =null;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_edit_profile;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changeDp = view.findViewById(R.id.edit_profile_btn);
        userImage = view.findViewById(R.id.user_image);
        profilePhone.setText(SessionManager.getUserPhone());
        profileEmail.setText(SessionManager.getUserEmail());

        changeDp.setOnClickListener(view1 -> {
            //pickFromGallery();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
                    requestPermissions(permissions, 100);
                } else {
                    pickFromGallery();
                }
            } else {
                pickFromGallery();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickFromGallery();
            } else {
                showErrorMessageToast("Permission Denied");
            }
        }
    }

    private void pickFromGallery() {
        //Create an Intent with action as ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        // Launching the Intent
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    //data.getData returns the content URI for the selected Image
                    selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    // Get the cursor
                    Cursor cursor = getContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();
                    //Get the column index of MediaStore.Images.Media.DATA
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    //Gets the String value in the column
                    String imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();
                    // Set the Image in ImageView after decoding the String
                    userImage.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
                    val=convertImageToStringForServer(BitmapFactory.decodeFile(imgDecodableString));
                    break;
            }
    }


    @OnClick(R.id.save_changes_btn)
    public void saveChanges() {
        String firstName = profileFname.getText().toString();
        String lastName = profileLname.getText().toString();
        String about = profileAbout.getText().toString();
        if (TextUtils.isEmpty(firstName)) {
            showErrorMessageToast("First Name is a compulsory field");
            return;
        }
        if (TextUtils.isEmpty(lastName)) {
            showErrorMessageToast("Last name is a compulsory field");
            return;
        }
        if (TextUtils.isEmpty(about)) {
            showErrorMessageToast("About me is a compulsory field");
            return;
        }
        doSendDetailsToServer(firstName,lastName,about);
    }

    public static String convertImageToStringForServer(Bitmap imageBitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if(imageBitmap != null) {
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 60, stream);
            byte[] byteArray = stream.toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        }else{
            return null;
        }
    }

    private void doSendDetailsToServer(String firstName, String lastName, String about) {

        ServiceGenerator serviceGenerator = Injector.provideRetrofit().create(ServiceGenerator.class);
        Call<BaseResponse> call = serviceGenerator.updateUserProfile(
                firstName,
                lastName,
                about,
                val
        );
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                processDetails(response);
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                showErrorMessageToast(getString(R.string.error_text));

            }
        });
    }

    private void processDetails(Response<BaseResponse> response) {
        if (response.body() != null) {
            if (response.body().getStatus()) {
                showSuccessMessageToast("Update Successful");
            } else {
                showErrorMessageToast(response.body().getMessage());
            }
        } else {
            showErrorMessageToast(getString(R.string.error_text));
        }
    }

}