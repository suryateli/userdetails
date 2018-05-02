package com.mandar.mcc.collegelogin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mandar on 26-04-2018.
 */

public class DisplayUser extends AppCompatActivity {
    Button logout;
    TextView name,roll,div;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_user);
        logout = (Button) findViewById(R.id.logout);
        name = (TextView) findViewById(R.id.name);
        roll = (TextView) findViewById(R.id.roll);
        div = (TextView) findViewById(R.id.div);

        db = openOrCreateDatabase("collegedatabase.db",MODE_PRIVATE,null);
        String r = getIntent().getStringExtra("roll");
        Log.d("hey",r);
        String sql = "Select * from user WHERE u_roll = '" + r +"';";
        Log.d("sql",sql);
        Cursor resultSet = db.rawQuery(sql,null);
        resultSet.moveToFirst();
        String n = resultSet.getString(0);
        String d = resultSet.getString(2);
        name.setText(n);
        roll.setText(r);
        div.setText(d);
        db.execSQL("UPDATE user SET u_rem = 5 WHERE u_roll = '" + r + "';");
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DisplayUser.this,SignIn.class);
                startActivity(i);
            }
        });
    }
}
