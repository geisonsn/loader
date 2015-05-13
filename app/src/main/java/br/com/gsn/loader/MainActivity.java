package br.com.gsn.loader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends Activity {

    private ProgressBar progressBar;
    private Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

//        Button botao = (Button) findViewById(R.id.botao);
        botao = (Button) findViewById(R.id.progressButton);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            botao.setEnabled(false);
            new Task(v.getContext()).execute();
            Log.i("Click", new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        });
    }

    private class Task extends AsyncTask<Void, Void, String> {

        private ProgressDialog dialog;
        private Context context;

        public Task(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
//            dialog = new ProgressDialog(context);
//            dialog.setTitle("Realizando o carregamento dos dados");
//            dialog.setMessage("Aguarde o fim da requisição...");
//            dialog.show();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {

            String urlServico = "http://echo.jsontest.com/key/value/one/two";

            try {
                URL url = new URL(urlServico);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                InputStream stream = http.getInputStream();

                Reader reader = new InputStreamReader(stream, "UTF-8");
                char[] buffer = new char[1024];
                reader.read(buffer);
                return new String(buffer);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "";
        }

        @Override
        protected void onPostExecute(String retorno) {
            Activity activity = (Activity) context;
            TextView dados = (TextView) activity.findViewById(R.id.dados);
            dados.setText("Dados: " + retorno);
//            dialog.dismiss();
            progressBar.setVisibility(View.INVISIBLE);
            botao.setEnabled(true);
        }
    }
}
