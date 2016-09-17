package com.example.android.demobd2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DataBaseManager manager;

    private Button btn_qry;
    private Button btn_ins;
    private Button btn_del;
    private Button btn_upd;
    private TextView query;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        manager = new DataBaseManager(this);

        btn_qry = (Button) findViewById(R.id.button);
        btn_ins = (Button) findViewById(R.id.button2);
        btn_del = (Button) findViewById(R.id.button3);
        btn_upd = (Button) findViewById(R.id.button4);
        query = (TextView) findViewById(R.id.textView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void frasesQry(View view) {
        String r = manager.frasesQry();
        query.setText(r);
    }

    public void frasesIns(View view) {
        manager.frasesIns("abel", "abcde@hotmail.com","4610286");
        query.setText("Ok");
    }

    public void frasesDel(View view) {
        manager.frasesDel(3);
        frasesQry(view);
    }

    public void frasesUpd(View view) {
        manager.frasesUpd("jorge", "salazar@gmail.com","4614424", 3);
        query.setText("cambios Ok");
    }

}
