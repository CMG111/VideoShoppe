package thomas.sullivan.videoshoppe.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import thomas.sullivan.videoshoppe.activity.R;
import thomas.sullivan.videoshoppe.resources.UserDatabase;

public class LoginScreen extends AppCompatActivity {

    UserDatabase database;
    EditText editUsername,editPassword;
    Button btnLogin;
    Button btnTest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        database = new UserDatabase(this);


        editUsername = (EditText)findViewById(R.id.editText_Username);
        editPassword = (EditText)findViewById(R.id.editText_Password);
        btnLogin = (Button)findViewById(R.id.button_Login);
        btnTest2 = (Button)findViewById(R.id.button_test2);

        // Debugging fake data
        //database.createEmployee("0001", "John", "Doe", "admin", "admin", true);

        //Wipes database
        //database.wipeDatabase();

        login();

    }

    public void login()
    {
        btnLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String usernameEntry = editUsername.getText().toString();
                        String passwordEntry = editPassword.getText().toString();
                        String userID = database.searchCredentials(usernameEntry, passwordEntry);

                        if(userID.equals("invalid")){
                            toastMessage("Invalid Credentials.");
                        }
                        else{
                            toastMessage("Logging in.");
                            database.setCurrentUser(userID);
                            openMainMenu();
                        }
                    }
                }
        );

        btnTest2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        test2();
                    }
                }
        );
    }

    public void openMainMenu()
    {
        Intent intent = new Intent(LoginScreen.this, MainMenu.class);
        startActivity(intent);
    }


    //Views database
    public void test2()
    {
        String result = database.debugger();
        toastMessage(result);
    }


    public void toastMessage(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}
