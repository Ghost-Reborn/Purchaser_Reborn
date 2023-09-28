package in.ghostreborn.purchaserreborn;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        EditText inputName = findViewById(R.id.input_name);
        EditText inputEmail = findViewById(R.id.input_email);
        EditText inputPassword = findViewById(R.id.input_password);
        SwitchCompat isSeller = findViewById(R.id.switch_isSeller);
        Button buttonAdd = findViewById(R.id.btn_add);
        buttonAdd.setOnClickListener(view -> {
            AdminHelper helper = new AdminHelper(getApplicationContext());
            helper.addPerson(
                    inputName.getText().toString(),
                    inputEmail.getText().toString(),
                    inputPassword.getText().toString(),
                    isSeller.isChecked() ? 1 : 0
            );
            Cursor cursor = helper.getAllPerson();
            while (cursor.moveToNext()){
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String password = cursor.getString(3);
                String isSellers = cursor.getString(4);
                Log.e(Constants.LOG_TAG, "id: " + id);
                Log.e(Constants.LOG_TAG, "user: " + name);
                Log.e(Constants.LOG_TAG, "email: " + email);
                Log.e(Constants.LOG_TAG, "pass: " + password);
                Log.e(Constants.LOG_TAG, "isSeller: " + isSellers);
            }
        });

    }
}