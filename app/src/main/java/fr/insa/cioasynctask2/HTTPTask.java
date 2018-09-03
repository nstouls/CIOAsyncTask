package fr.insa.cioasynctask2;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import static java.lang.Thread.sleep;

/**
 * Created by NS on 03/09/18.
 */
public class HTTPTask extends AsyncTask<String, Character, Void> {
    private MainActivity activity;
    public HTTPTask(MainActivity activity){
        this.activity=activity;
    }

    @Override
    protected Void doInBackground(String... url) {
//        Log.d("Reception","Processus lancé");
        String request = "GET / HTTP/1.1\nHost: "+url[0]+"\n\n";

        Socket sock = null;
        try {
            sock = new Socket(url[0],80);
            PrintWriter out = new PrintWriter(sock.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            // Envoie de l'entête HTTP Get
            out.print(request);
            out.flush();


            // Lecture artificielle caractère par caractère
            boolean doLoop = true;
            while(doLoop) {
                int c = in.read();
                if (c==-1) {
                    sleep(100); // give time to publish possible detection of the end of the trace
                } else {
                    publishProgress(new Character((char)c));
                }
                if(activity.estFini()) {
                    doLoop = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Character... values) {
        super.onProgressUpdate(values);
        StringBuilder s = new StringBuilder();
        for (Character c:values) {
            s.append(c);
        }
        activity.displayCharacter(s.toString());
    }
}
