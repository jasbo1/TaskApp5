package com.taskapp.ui.anime;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.taskapp.R;
import com.taskapp.ui.anime.AnimeViewModels;

public class AnimeFragment extends Fragment {

    private AnimeViewModels animeViewModels;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        animeViewModels =
                ViewModelProviders.of(this).get(AnimeViewModels.class);
        View root = inflater.inflate(R.layout.fragment_anime, container, false);
        final TextView textView = root.findViewById(R.id.text_anime);
        animeViewModels.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}