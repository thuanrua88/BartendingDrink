package com.example.bartending_drink_app.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.bartending_drink_app.Activity.LoginActivity;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.evenbus.LogOutEvent;
import com.example.bartending_drink_app.evenbus.LoginEvent;
import com.example.bartending_drink_app.utils.SessionAccount;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import static com.example.bartending_drink_app.Activity.LoginActivity.KEY_EMAIL;
import static com.example.bartending_drink_app.Activity.LoginActivity.KEY_TOKEN;
import static com.example.bartending_drink_app.Activity.LoginActivity.KEY_USERNAME;


public class ProfileFragment extends Fragment {
    public static final String SHARE_PREF_NAME = "useFile";
//    public static final int REQUEST_CODE_CAMERA = 22;
//    public static final int REQUEST_CODE_GALLERY = 11;
//    private ImageView imgProfile, imgEditPhoto;
    private Button btLogout;
    private TextView tvUsername;
    private TextView tvEmail;
    private SharedPreferences sharedPreferences;
    String mCurrentPhotoPath;
    private SessionAccount sessionAccount;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onLogInEvent(LoginEvent event) {
        Log.d("TAG_EVENT_LOGIN", "--------------------------- event : " + event.getLoginSuccess() + "-----------------------");
        setUpText();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btLogout = view.findViewById(R.id.btn_logout);
        tvUsername = view.findViewById(R.id.tv_name);
        tvEmail = view.findViewById(R.id.tv_email);
        sessionAccount  = new SessionAccount(getActivity());

//        imgProfile = view.findViewById(R.id.img_profile);
//        imgEditPhoto = view.findViewById(R.id.img_edit_photo);
//        imgEditPhoto.setOnClickListener(v ->
//                showPictureDialog()
//        );
//        requestPermission();

        setUpText();
        btLogout.setOnClickListener(v -> {
            if (getStateLogin()) {
                handleActionLogout();
            }
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        tvUsername.setText(sessionAccount.getString(SessionAccount.FULL_NAME));
        tvEmail.setText(sessionAccount.getString(SessionAccount.EMAIL));
        setUpText();
    }

    private void handleActionLogout() {
        logout();
        btLogout.setText(R.string.label_login);
        tvUsername.setText(R.string.label_username);
        tvEmail.setText(R.string.label_email);
        //emit to update card and info user
        EventBus.getDefault().post(new LogOutEvent(true));
    }

    private void setUpText() {
        if (!getStateLogin()) {
            btLogout.setText(R.string.label_login);
            tvUsername.setText(R.string.label_username);
            tvEmail.setText(R.string.label_email);
        } else {
            btLogout.setText(R.string.log_out);
            //sharedPreferences = getActivity().getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
            tvUsername.setText(sessionAccount.getString(SessionAccount.FULL_NAME));
            tvEmail.setText(sessionAccount.getString(SessionAccount.EMAIL));
        }

    }

    public void logout() {
       /* if (getActivity() != null) {
            sharedPreferences = getActivity().getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_TOKEN);
        editor.remove(KEY_EMAIL);
        editor.remove(KEY_USERNAME);
        editor.commit();*/

        sessionAccount.clean();

    }

    private boolean getStateLogin() {
        if (getActivity() != null) {
            String token = sessionAccount.getString(SessionAccount.TOKEN);
            assert token != null;
            return !token.equals("");
        }
        return false;
    }


//    private void showPictureDialog() {
//        android.app.AlertDialog.Builder pictureDialog = new android.app.AlertDialog.Builder(getActivity());
//        pictureDialog.setTitle(R.string.select_action);
//        String[] pictureDialogItems = {
//                getString(R.string.take_photo),
//                getString(R.string.choose_from_gallery)};
//        pictureDialog.setItems(pictureDialogItems,
//                (dialog, which) -> {
//                    switch (which) {
//                        case 0:
//                            takePhotoFromCamera();
//                            break;
//                        case 1:
//                            choosePhotoFromGallery();
//                    }
//                });
//        pictureDialog.show();
//    }
//
//    @SuppressLint("QueryPermissionsNeeded")
//    public void takePhotoFromCamera() {
//        if (getActivity() != null) {
//            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
//                startActivityForResult(intent, REQUEST_CODE_CAMERA);
//            }
//        }
//    }
//
//    public void choosePhotoFromGallery() {
//        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Bitmap bitmap;
//        if (resultCode == getActivity().RESULT_OK) {
//            if (requestCode == REQUEST_CODE_GALLERY) {
//                if (data != null) {
//                    Uri contentURI = data.getData();
//                    try {
//                        bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), contentURI);
//                        imgProfile.setImageBitmap(bitmap);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                {
//                    Toast.makeText(getActivity(), "Data not found", Toast.LENGTH_SHORT).show();
//                }
//            } else if (requestCode == REQUEST_CODE_CAMERA) {
//                Bundle extras = data.getExtras();
//                Bitmap imageBitmap  = (Bitmap) extras.get("data");
//                imgProfile.setImageBitmap(imageBitmap);
//            }
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.FROYO)
//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = image.getAbsolutePath();
//        return image;
//    }
//
//
//    private void requestPermission() {
//        PermissionListener permissionListener = new PermissionListener() {
//            @Override
//            public void onPermissionGranted() {
//
//            }
//
//            @Override
//            public void onPermissionDenied(List<String> deniedPermissions) {
//                Toast.makeText(getContext(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
//            }
//        };
//        if (getContext() != null) {
//            TedPermission.with(getContext())
//                    .setPermissionListener(permissionListener)
//                    .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
//                    .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
//                    .check();
//        }
//    }


}