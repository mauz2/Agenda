package android.usuario.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btAdNovo, btMostrarAg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btAdNovo = (Button) findViewById(R.id.btAdNovo);
        this.btAdNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadActivity.class);
                intent.putExtra("situacao", "inserir");
                startActivity(intent);
            }
        });

        this.btMostrarAg = (Button) findViewById(R.id.btMostrarAg);
        this.btMostrarAg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaActivity.class);
                startActivity(intent);
            }
        });

    }
}
