package com.example.dbsample_02;

import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String dbName, tbName;

    SQLiteDatabase sDB;

    TextView resultTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText dbInputName = findViewById(R.id.dbNameET);
        final EditText tbInputName = findViewById(R.id.tbNameET);

        Button createDBbtn = findViewById(R.id.btnCreateDB);

        createDBbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dbName = dbInputName.getText().toString();
                createDB(dbName);
            }
        });

        Button createTBbtn = findViewById(R.id.btnCreateTB);

        resultTxt = findViewById(R.id.tv00);

        createTBbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                tbName = tbInputName.getText().toString();
                createTB(tbName);
            }
        });
    }

    //    DB 생성과정
    private void createDB(String name) {
        msgOutput("[" + name + "}" + "데이터베이스 생성 중");
        try {
            openOrCreateDatabase(name, MODE_PRIVATE, null);
            msgOutput("[" + name + "}" + "데이터베이스 생성 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    테이블 생성 과정
    private void createTB(String name) {
        msgOutput("[" + name + "}" + "테이블 생성 중");
        sDB.execSQL("create table if not exists " + name + "("
                + " no INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " name TEXT, "
                + " address TEXT, "
                + " tel TEXT);");
    }

    private void msgOutput(String msg) {
        Log.d("MainActivity", msg);
        resultTxt.append("\n" + msg);
    }
}
