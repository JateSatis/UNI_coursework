package com.example.uni_coursework.screens.addRoom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.uni_coursework.R;
import com.example.uni_coursework.databinding.FragmentAddRoomBinding;
import com.example.uni_coursework.model.rooms.OnUploadCompleteListener;

import java.util.ArrayList;

public class AddRoomFragment extends Fragment {
    private FragmentAddRoomBinding binding;
    private NavController navController;

    private AddRoomViewModel viewModel = new AddRoomViewModel();
    private ArrayList<Uri> imageUris = new ArrayList<>();
    private ArrayList<String> imagesNames = new ArrayList<>();

    private ImagePagerAdapter imagePagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddRoomBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        imagePagerAdapter = new ImagePagerAdapter(requireContext(), new ArrayList<>(), onRoomImageListener);

        binding.addRoomViewPager.setAdapter(imagePagerAdapter);

        Uri harmonyLogoUri = Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.mipmap.harmony_logo);
        imagePagerAdapter.addImage(harmonyLogoUri);

        binding.addRoomSaveButton.setOnClickListener(_view -> {
            String title = binding.addRoomTitleInput.getText().toString();
            String beds = binding.addRoomBedsAmountInput.getText().toString();
            Integer cost = Integer.parseInt(binding.addRoomCostInput.getText().toString());
            String booker = viewModel.getUserUid();
            viewModel.addRoom(title, beds, cost, booker, imagesNames, false, documentReference -> {
                viewModel.uploadImages(documentReference.getId(), imageUris, imagesNames, onUploadCompleteListener);
            });
        });
    }

    private void openGalleryForRoomPhotos() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGES_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGES_REQUEST) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount();
                for (int i = 0; i < count; i++) {
                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
                    imageUris.add(imageUri);
                    imagePagerAdapter.addImage(imageUri);
                    imagesNames.add(System.currentTimeMillis() + "_" + i);
                }
            } else if (data.getData() != null) {
                Uri imageUri = data.getData();
                imageUris.add(imageUri);
                imagePagerAdapter.addImage(imageUri);
                imagesNames.add(System.currentTimeMillis() + "_" + 0);
            }
        }
    }

    private OnRoomImageListener onRoomImageListener = this::openGalleryForRoomPhotos;

    private OnUploadCompleteListener onUploadCompleteListener = new OnUploadCompleteListener() {
        @Override
        public void onUploadComplete() {
            Toast.makeText(requireContext(), "Комната успешно добавлена!", Toast.LENGTH_LONG).show();
            navController.navigateUp();
        }
    };

    private static final int PICK_IMAGES_REQUEST = 1;
}