package com.ming.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ming.news.MainActivity;
import com.ming.news.R;


public class TranslateFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        int layoutId = arguments.getInt("layoutId");

        Log.i("x", layoutId + "");
        View view = View.inflate(getActivity(), layoutId, null);
        if (R.layout.guide3 == layoutId) {
            view.findViewById(R.id.btn_guide).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
        }

        return view;
    }


}
