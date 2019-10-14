package com.taskapp.ui.home;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.taskapp.App;
import com.taskapp.FormActivity;
import com.taskapp.LottieActivity;
import com.taskapp.MainActivity;
import com.taskapp.R;
import com.taskapp.Task;
import com.taskapp.TaskAdapter;
import com.taskapp.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<Task> list;
    private int pos;
   private CheckBox checkBox;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        initList();

        return root;
    }

    private void initList() {
        list = App.getInstance().getDatabase().taskDao().getAll();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TaskAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                pos = position;
                Task task = list.get(position);
                Log.e("TAG", "onItemClicked");
                Intent intent = new Intent(getContext(), FormActivity.class);
                intent.putExtra("task", task);
                startActivityForResult(intent, 101);


            }


            @Override
            public void onItemLongClick(int position) {

                Log.e("TAG", "onItemLongClicked");

                showAlert(list.get(position));

            }
        });

    }


    public void sortList() {
        list.clear();
        list.addAll(App.getInstance().getDatabase().taskDao().getAllSorted());
        
        adapter.notifyDataSetChanged();
    }


    private void showAlert(final Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Вы хотите удалить")
                .setNegativeButton("Отмена", null)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        list.remove(task);
                        App.getInstance().getDatabase().taskDao().delete(task);
                        adapter.notifyDataSetChanged();
                    }
                });
        builder.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("TAG", "onActivityResult fragment");
        if (resultCode == Activity.RESULT_OK) {

            Task task = (Task) data.getSerializableExtra("task");
            if (requestCode == 100) {
                list.add(task);

            } else if (requestCode == 101) {

                list.set(pos, task);
            }

            adapter.notifyDataSetChanged();

        }
    }





}
