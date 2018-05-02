package com.mandar.mcc.collegelogin;

/**
 * Created by mandar on 26-04-2018.
 */

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mandar on 26-04-2018.
 */

public class SignIn extends AppCompatActivity{

    Button login;
    EditText roll,pass;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        login = (Button) findViewById(R.id.login);
        roll = (EditText) findViewById(R.id.roll);
        pass = (EditText) findViewById(R.id.pass);

        db = openOrCreateDatabase("collegedatabase.db",MODE_PRIVATE,null);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n,r,d,p;
                r = roll.getText().toString();
                p = pass.getText().toString();
                String sql = "Select * from user WHERE u_roll = '" + r +"' AND u_pass='"+ p +"';";
                Log.d("sql",sql);
                Cursor resultSet = db.rawQuery(sql,null);
                if(resultSet.getCount() > 0) {
                    Intent i = new Intent(SignIn.this,DisplayUser.class);
                    i.putExtra("roll",roll.getText().toString());
                    startActivity(i);
                }
                else {
                    String s = "Select * from user WHERE u_roll = '" + r +"';";
                    Log.d("sql",s);
                    Cursor result = db.rawQuery(s,null);
                    if(result.getCount() == 0) {
                        Toast t2 = Toast.makeText(SignIn.this,"User not found",Toast.LENGTH_SHORT);
                        t2.show();
                    }
                    else {
                        result.moveToFirst();
                        int tries = result.getInt(4);
                        if (tries == 0) {
                            Toast t1 = Toast.makeText(SignIn.this, "Maximum attempts", Toast.LENGTH_SHORT);
                            t1.show();
                        } else {
                            tries -= 1;
                            db.execSQL("UPDATE user SET u_rem = " + tries + " WHERE u_roll = '" + r + "';");
                            Toast t = Toast.makeText(SignIn.this, tries + " tries left", Toast.LENGTH_SHORT);
                            t.show();
                        }
                    }
                }
            }
        });
    }
}
