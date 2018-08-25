package se.iuh.bich.demologin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginSuccessActivity extends AppCompatActivity {

    TextView tvHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        tvHello = findViewById(R.id.tv_Hello);

        Intent intent = getIntent();
        tvHello.setText("Hello account " + intent.getStringExtra("username"));

    }
}
