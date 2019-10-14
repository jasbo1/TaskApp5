package com.taskapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {
    //public static final String RESULT_KEY = "task";


    private EditText editTitle;
    private EditText editDesc;
    private  Task task;
    boolean isShow=false;
    Button button_red;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editTitle = findViewById(R.id.editTitle);
        editDesc = findViewById(R.id.editDesc);
        button_red=findViewById(R.id.button_red);

        task = (Task) getIntent().getSerializableExtra("task");


        if (task != null) {
            editTitle.setText(task.getTitle());
            editDesc.setText(task.getDesc());
            isShow = true;
            button_red.setText("Редактировать");


    /* SharedPreferences prefs = getSharedPreferences("preferences",Context.MODE_PRIVATE);
        editTitle.setText(prefs.getString("title",""));
        editDesc.setText(prefs.getString("desc",""));
        String title = prefs.getString("title", "");
        String desc = prefs.getString("desc", "");
        editTitle.setText(title);
       editDesc.setText(desc);*/

//    }
//
//        @Override
//        public void onBackPressed() {
//
//          SharedPreferences prefs = getSharedPreferences( "preferences" ,MODE_PRIVATE);
//          prefs.edit().putString("title", editTitle.getText().toString()).apply();
//            SharedPreferences.Editor editor = prefs.edit();
//            String title = editTitle.getText().toString().trim();
//            String desc = editDesc.getText().toString().trim();
//            task = new Task(title,desc);
//            editor.putString("title", title);
//            editor.putString("desc",desc);
//            editor.apply();
//            editor.commit();
//            editor.clear();
//
//
//            super.onBackPressed();
        }

    }


       public void onClick(View view) {
           String title = editTitle.getText().toString().trim();
           String desc = editDesc.getText().toString().trim();

               if (TextUtils.isEmpty(title)) {
                   editTitle.setError("Заполните это поле");

                   return;
               }
               if (TextUtils.isEmpty(desc)) {
                   editDesc.setError("Заполните это поле");
                   return;
               }

               if( task!= null) {
                   task.setTitle(title);
                   task.setDesc(desc);
                   App.getInstance().getDatabase().taskDao().update(task);
               }else {
                   task = new Task( title,desc);
                   App.getInstance().getDatabase().taskDao().update(task);

           }
               Task task = new Task(title, desc);
               App.getInstance().getDatabase().taskDao().insert(task);

               Intent intent = new Intent();
               intent.putExtra("task", task);
               SharedPreferences sharedPreferences = getSharedPreferences("preferences1", MODE_PRIVATE);
               sharedPreferences.edit().clear().commit();
               setResult(RESULT_OK, intent);
               finish();

           }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
