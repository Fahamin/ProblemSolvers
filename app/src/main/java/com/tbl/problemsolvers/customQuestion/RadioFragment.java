package com.tbl.problemsolvers.customQuestion;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tbl.problemsolvers.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RadioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RadioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    FragmentToActivity fragmentToActivity;

    @BindView(R.id.tvQuestion)
    TextView tvQuestion;

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    @BindView(R.id.radioButton)
    RadioButton radioButton;

    @BindView(R.id.radioButton2)
    RadioButton radioButton2;

    View view;
    String value="a";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private ArrayList<String> mParam2;

    public RadioFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RadioFragment newInstance(String param1, ArrayList<String>  param2) {
        RadioFragment fragment = new RadioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putStringArrayList(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getStringArrayList(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_radio, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentToActivity = (FragmentToActivity) context;
    }

    @Override
    public void onDetach() {
        int position = radioGroup.indexOfChild((RadioButton) view.findViewById(radioGroup.getCheckedRadioButtonId()));
        if (position != -1) {
            value = mParam2.get(position);
        }
        fragmentToActivity.dataFromFragment(value);
        fragmentToActivity = null;
        super.onDetach();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("qq", mParam1);

        tvQuestion.setText(mParam1);
        radioButton.setText(mParam2.get(0).toString());
        radioButton2.setText(mParam2.get(1).toString());

    }
}