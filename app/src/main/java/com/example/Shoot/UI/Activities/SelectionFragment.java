package com.example.Shoot.UI.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Shoot.OnFragmentInteractionListener;
import com.example.Shoot.R;
import com.example.Shoot.Storage.PreferenceController;


public class SelectionFragment extends Fragment {
    private TextView tv_silver,tv_wallet;
    private ImageView iv_silver;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private Context context;
    private OnFragmentInteractionListener mListener;
    public SelectionFragment() {
        // Required empty public constructor
    }


    public static SelectionFragment newInstance(String param1, String param2) {
        SelectionFragment fragment = new SelectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view= inflater.inflate(R.layout.fragment_selection, container, false);
        getActivity().setTitle("Select Room");
        tv_silver           =(TextView) view.findViewById(R.id.tv_silver);

        tv_silver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SilverActivity.class);
                startActivity(intent);
            }
        });
        iv_silver=(ImageView)view.findViewById(R.id.iv_silver);
        iv_silver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SilverActivity.class);
                startActivity(intent);
            }
        });

            String w = PreferenceController.getStringPreference(getActivity()
                    ,PreferenceController.PreferenceKeys.PREFERENCE_WALLET_BALANCE);
            tv_wallet.setText(w);


        return view;
    }




    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
