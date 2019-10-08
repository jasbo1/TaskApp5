package com.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {

    private EditText editTitle;
    private EditText editDesc;
    private  Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        editTitle = findViewById(R.id.editTitle);
        editDesc = findViewById(R.id.editDesc);

        SharedPreferences prefs = getSharedPreferences("preferences",Context.MODE_PRIVATE);
        editTitle.setText(prefs.getString("title",""));
        editDesc.setText(prefs.getString("desc",""));
        String title = prefs.getString("title", "");
        String desc = prefs.getString("desc", "");
        editTitle.setText(title);
        editDesc.setText(desc);

    }

        @Override
        public void onBackPressed() {

          SharedPreferences prefs = getSharedPreferences( "preferences" ,MODE_PRIVATE);
          prefs.edit().putString("title", editTitle.getText().toString()).apply();
            SharedPreferences.Editor editor = prefs.edit();
            String title = editTitle.getText().toString().trim();
            String desc = editDesc.getText().toString().trim();
            task = new Task(title,desc);
            editor.putString("title", title);
            editor.putString("desc",desc);
            editor.apply();
            editor.commit();
            editor.clear();


            super.onBackPressed();
        }




   /* @Override
   public void onBackPressed(){
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString("text", editTitle.getText().toString());
        editor.putString("text", editDesc.getText().toString());
        editor.commit();
        super.onBackPressed();
    }*/




    public void onClick(View view) {
        String title = editTitle.getText().toString();
        String desc = editDesc.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("preferences1", MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
        if (TextUtils.isEmpty(title)) {
            editTitle.setError("Заполните это поле");
            return;
        }
        if (TextUtils.isEmpty(desc)) {
            editDesc.setError("Заполните это поле");
            return;
        }
        Task task = new Task(title, desc);
        Intent intent = new Intent();
        intent.putExtra("task", task);
        setResult(RESULT_OK, intent);
        finish();

    }
}
