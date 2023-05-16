package com.tbl.problemsolvers.customQuestion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tbl.problemsolvers.R;
import com.tbl.problemsolvers.utils.LuncherActivityResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageSelectedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageSelectedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    @BindView(R.id.image1)
    ImageView imageView1;

    @BindView(R.id.image2)
    ImageView imageView2;

    @BindView(R.id.image3)
    ImageView imageView3;

    @BindView(R.id.image5)
    ImageView imageView5;

    @BindView(R.id.image6)
    ImageView imageView6;

    @BindView(R.id.image7)
    ImageView imageView7;


    String picturePath;
    FragmentToActivity fragmentToActivity;

    public ImageSelectedFragment() {
        // Required empty public constructor
    }

    protected final LuncherActivityResult<Intent, ActivityResult> activityLauncher = LuncherActivityResult.registerActivityForResult(this);
    private int REQUEST_CODE_PERMISSIONS = 1001;

    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};

    // TODO: Rename and change types and number of parameters
    public static ImageSelectedFragment newInstance(String param1, String param2) {
        ImageSelectedFragment fragment = new ImageSelectedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentToActivity = (FragmentToActivity) context;
    }

    @Override
    public void onDetach() {

        fragmentToActivity.dataFromFragment(picturePath);
        fragmentToActivity = null;
        super.onDetach();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_image_selected, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.image1)
    public void im1() {
        imageView1.setBackgroundResource(R.drawable.simple_rect_black_border);
        imageView2.setBackgroundResource(R.drawable.simple_rect_whit_border);
        imageView3.setBackgroundResource(R.drawable.simple_rect_whit_border);
        picturePath = "1st image selected";
        Log.e("selectedImage", picturePath);
    }

    @OnClick(R.id.image2)
    public void im2() {
        imageView2.setBackgroundResource(R.drawable.simple_rect_black_border);
        imageView3.setBackgroundResource(R.drawable.simple_rect_whit_border);
        imageView1.setBackgroundResource(R.drawable.simple_rect_whit_border);

        picturePath = "2nd image selected";
        Log.e("selectedImage", picturePath);

    }

    @OnClick(R.id.image3)
    public void im3() {
        imageView3.setBackgroundResource(R.drawable.simple_rect_black_border);
        imageView2.setBackgroundResource(R.drawable.simple_rect_whit_border);
        imageView1.setBackgroundResource(R.drawable.simple_rect_whit_border);

        picturePath = "3rd image selected";
        Log.e("selectedImage", picturePath);
    }


    int i = 0;
    @OnClick(R.id.image5)
    public void im25() {
        if (i == 0) {
            imageView5.setBackgroundResource(R.drawable.simple_rect_black_border);
            i++;
        } else if (i == 1) {
            imageView5.setBackgroundResource(R.drawable.simple_rect_whit_border);
            i = 0;
        }

    }

    int j = 0;
    @OnClick(R.id.image6)
    public void im26() {
        if (j == 0) {
            imageView6.setBackgroundResource(R.drawable.simple_rect_black_border);
            j++;
        } else if (j == 1) {
            imageView6.setBackgroundResource(R.drawable.simple_rect_whit_border);
            j = 0;
        }

    }

    int k = 0;
    @OnClick(R.id.image7)
    public void im27() {
        if (k == 0) {
            imageView7.setBackgroundResource(R.drawable.simple_rect_black_border);
            k++;
        } else if (k == 1) {
            imageView7.setBackgroundResource(R.drawable.simple_rect_whit_border);
            k = 0;
        }

    }
   /* @OnClick(R.id.image4)
    public void im4() {
        imageView3.setImageResource(R.drawable.ic_baseline_add_a_photo_24);
        imageView2.setImageResource(R.drawable.ic_baseline_add_a_photo_24);
        imageView1.setImageResource(R.drawable.ic_baseline_add_a_photo_24);
        imageView4.setBackgroundResource(R.drawable.ic_baseline_call_missed_outgoing_24);
        picturePath = "4th image selected";
        Log.e("selectedImage",picturePath);
    }*/

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}