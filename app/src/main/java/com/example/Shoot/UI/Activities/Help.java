package com.example.Shoot.UI.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.Shoot.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Help extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText et_feedback;
    private Button bt_submitt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        toolbar = findViewById(R.id.toolbar2);
        et_feedback = findViewById(R.id.et_feedback);
        bt_submitt = findViewById(R.id.bt_submitt);

        ToolbarActions();

        bt_submitt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et_feedback.getText().toString();
                if (TextUtils.isEmpty(text)) {
                    Toast.makeText(Help.this, "Field is empty", Toast.LENGTH_SHORT).show();
                } else {
                    gotoWhatsapp(text);
                }
            }
        });


    }

    private void gotoWhatsapp(String text) {
        try {
            String url2 = "https://api.whatsapp.com/send?phone=" + "+918105757124" + "&text=" + URLEncoder.encode(
                     "Feedback : " + text + "\n\n"
                    , "UTF-8");

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.putExtra(Intent.EXTRA_TEXT, "extra text");
            i.setData(Uri.parse(url2));
            startActivity(i);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void ToolbarActions() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}