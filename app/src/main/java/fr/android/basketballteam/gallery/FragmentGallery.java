package fr.android.basketballteam.gallery;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import java.io.File;

import fr.android.basketballteam.R;
import fr.android.basketballteam.home.HomeFragListener;

public class FragmentGallery extends Fragment {

    private GalleryFragListener activityMain;

    private LinearLayout layout;

    /** Constructor */
    public FragmentGallery(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_gallery, container, false);
        layout = view.findViewById(R.id.galleryContent);

        // View Creation
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GalleryFragListener) {
            activityMain = (GalleryFragListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement GalleryFragListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityMain = null;
    }
}
