package com.mandar.mcc.collegelogin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by mandar on 26-04-2018.
 */

public class SignUp extends AppCompatActivity{

    Button register;
    EditText name,roll,div,pass;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        register = (Button) findViewById(R.id.register);
        name = (EditText) findViewById(R.id.name);
        roll = (EditText) findViewById(R.id.roll);
        div = (EditText) findViewById(R.id.div);
        pass = (EditText) findViewById(R.id.pass);

        db = openOrCreateDatabase("collegedatabase.db",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS user (u_name VARCHAR,u_roll VARCHAR,u_div VARCHAR,u_pass VARCHAR,u_rem INTEGER);");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n,r,d,p;
                n = "'" + name.getText().toString() + "', ";
                r = "'" + roll.getText().toString() + "', ";
                d = "'" + div.getText().toString() + "', ";
                p = "'" + pass.getText().toString() + "', ";
                String str = n + r + d + p + " 5";
                db.execSQL("INSERT INTO user VALUES(" + str + ");");
                Intent i = new Intent(SignUp.this,DisplayUser.class);
                i.putExtra("roll",roll.getText().toString());
                startActivity(i);
            }
        });
    }
}
