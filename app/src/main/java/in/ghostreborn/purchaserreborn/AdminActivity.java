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
                String name = cursor.getString(0);
                Log.e(Constants.LOG_TAG, "User: " + name);
            }
        });

    }
}