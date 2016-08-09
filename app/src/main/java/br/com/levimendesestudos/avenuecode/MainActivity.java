package br.com.levimendesestudos.avenuecode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etPesquisa)
    EditText etPesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
