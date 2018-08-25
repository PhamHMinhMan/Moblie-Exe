package se.iuh.bich.demologin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtUsername, edtPassword;
    Button btnLogin, btnExit;
    CheckBox cbEnabled;

    // tao hop thoai
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPassword = findViewById(R.id.edt_Password);
        edtUsername = findViewById(R.id.edt_Username);
        btnLogin = findViewById(R.id.btn_Login);
        btnExit = findViewById(R.id.btn_Exit);
        btnLogin.setOnClickListener(this);
        btnExit.setOnClickListener(this);

        cbEnabled = findViewById(R.id.cb_Enabled);
        // khi check show password -> password hien thi cho nguoi nhin
        // nguoc lai se an theo dang password khong cho nguoi khac thay
        cbEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    edtPassword.setInputType(129);
                }
            }
        });

    }

    private AlertDialog.Builder getBuilder(String title, String message) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(title);
        b.setMessage(message);
        b.setIcon(R.drawable.ic_launcher_background);
        return b;
    }

    private void exit() {
        builder = getBuilder("Notification", "Are you sure?");
        // chon nut yes nam ben phai
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        // chon nut no nam ben trai
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        // hien thi
        Dialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(View view) {

            switch (view.getId()) {
                // neu chon nut login
                case R.id.btn_Login:
                    // neu du lieu da duoc nhap
                    if(edtUsername.getText().length() >0 && edtPassword.getText().length() > 0) {
                        Intent intent = new Intent(MainActivity.this, LoginSuccessActivity.class);
                        // truyen du lieu sang activity khac
                        intent.putExtra("username", edtUsername.getText().toString());
                        // chuyen trang activity LoginSuccessActivity
                        startActivity(intent);
                    }
                    else { // du lieu chua duoc nhap
//                        Toast.makeText(this, "Mời bạn nhập dữ liệu!", Toast.LENGTH_SHORT).show();
                        builder = getBuilder("Notification", "Enter full, please!");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        Dialog dialog = builder.create();
                        dialog.show();
                    }
                    break;

                // neu chon exit
                case R.id.btn_Exit:
                    exit();
                    break;
            }
        
    }
}
