package com.tbl.problemsolvers.customQuestion;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
 * Use the {@link SpineerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpineerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    FragmentToActivity fragmentToActivity;

    @BindView(R.id.tvQuestion)
    TextView tvQuestion;

    @BindView(R.id.etAnswer)
    Spinner spinner;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private ArrayList<String> mParam2;

    public SpineerFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SpineerFragment newInstance(String param1, ArrayList<String> param2) {
        SpineerFragment fragment = new SpineerFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM2, param2);
        args.putString(ARG_PARAM1, param1);
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

        View view = inflater.inflate(R.layout.fragment_spineer, container, false);
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
        fragmentToActivity.dataFromFragment(spinner.getSelectedItem().toString());
        fragmentToActivity = null;
        super.onDetach();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("qq", mParam1);
        tvQuestion.setText(mParam1);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, mParam2);
        spinner.setAdapter(arrayAdapter);
    }
}