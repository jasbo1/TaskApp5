package com.taskapp.onboard;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.taskapp.MainActivity;
import com.taskapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BoardFragment extends Fragment {


    public BoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        RelativeLayout relativeLayout = view.findViewById(R.id.rilative);
        ImageView imageView = view.findViewById(R.id.imageView);
        Button button1 = view.findViewById(R.id.button1);

        TextView textTitle = view.findViewById(R.id.textTitle);




        int pos = getArguments().getInt("pos");
        switch (pos) {
            case 0:
                imageView.setImageResource(R.drawable.solnce);
                textTitle.setText(" Привет");
                button1.setVisibility(View.GONE);
                relativeLayout.setBackground(getActivity().getResources().getDrawable(R.drawable.grass));

                break;
            case 1:
                imageView.setImageResource(R.drawable.smile);
                textTitle.setText(" Как дела ");
                button1.setVisibility(View.GONE);
                relativeLayout.setBackgroundColor(Color.BLUE);
                break;
            case 2:
                imageView.setImageResource(R.drawable.three);
                textTitle.setText(" Что делаеш");
                button1.setVisibility(View.VISIBLE);
                relativeLayout.setBackgroundColor(Color.YELLOW);

                    break;
                }
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("setting", Context.MODE_PRIVATE);
                preferences.edit().putBoolean("isShown",true).apply();
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
                //Toast.makeText(getContext(),"Click me ",Toast.LENGTH_LONG).show();
            }
        });

                return view;
        }


    }