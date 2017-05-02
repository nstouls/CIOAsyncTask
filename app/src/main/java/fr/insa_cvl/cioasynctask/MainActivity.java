package fr.insa_cvl.cioasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void prepare(View v) {
        // Preparing the value to compute
        EditText input = (EditText)findViewById(R.id.input);
        TextView prepared = (TextView)findViewById(R.id.prepare);
        prepared.setText(input.getText());
        // Erasing last result
        TextView resultTV = (TextView)findViewById(R.id.result);
        resultTV.setText("-");
        // Animating the infinite progress bar
        ProgressBar pb = (ProgressBar)findViewById(R.id.progressBar);
        pb.setVisibility(View.VISIBLE);
    }

    public void computeFibo(View v) {
        // Getting the input value for fibo
        EditText inputET = (EditText)findViewById(R.id.input);
        Integer input = Integer.parseInt(inputET.getText().toString());

        Log.i("CIO", "Start fibo !");
        //int result = Fibonacci.fibo(input); // Can take a long time!
        FiboTask asynctask = new FiboTask(this);
        asynctask.execute(input);
        Log.i("CIO", "End fibo.");

        /*
        // Updating the result textview
        TextView resultTV = (TextView)findViewById(R.id.result);
        resultTV.setText("" + result);

        // Hidding the progress bar
        ProgressBar pb = (ProgressBar)findViewById(R.id.progressBar);
        pb.setVisibility(View.GONE);
        */
    }
}
