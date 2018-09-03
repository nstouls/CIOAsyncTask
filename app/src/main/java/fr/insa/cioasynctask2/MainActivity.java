package fr.insa.cioasynctask2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView urlTV;
    private TextView resultTV;
    private Button getBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlTV = findViewById(R.id.url_asked);
        resultTV = findViewById(R.id.result);
        getBtn = findViewById(R.id.getBtn);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HTTPTask tache = new HTTPTask(MainActivity.this);
                tache.execute(urlTV.getText().toString());
            }
        });

    }

    public void displayCharacter(String s){
        resultTV.append(s);
    }

    public boolean estFini() {
        return resultTV.getText().toString().indexOf("</html>")>=0;
    }
}
