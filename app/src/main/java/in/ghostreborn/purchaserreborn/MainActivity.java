package in.ghostreborn.purchaserreborn;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(view -> login());

    }

    public void login() {
        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        boolean isSeller = checkUser(username, password);

        if (username.equals(Constants.ADMIN_LOGIN) && password.equals(Constants.ADMIN_PASSWORD)) {
            startActivity(new Intent(this, AdminActivity.class).setAction(Constants.INTENT_ACTION));
        } else if (isSeller){
            startActivity(new Intent(this, SellerActivity.class).setAction(Constants.INTENT_ACTION));
        } else {
            startActivity(new Intent(this, CustomerActivity.class).setAction(Constants.INTENT_ACTION));
        }
    }

    private boolean checkUser(String userName, String password){
        AdminHelper helper = new AdminHelper(this);
        Cursor cursor = helper.getAllPerson();
        while (cursor.moveToNext()){
            if (userName.equals(cursor.getString(1))){
                if (password.equals(cursor.getString(3))){
                    if (cursor.getInt(4) == 1){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}