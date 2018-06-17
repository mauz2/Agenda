package android.usuario.agenda;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadActivity extends AppCompatActivity {

    EditText ctDescricao, ctTipo, ctHora, ctData;
    Button btSalvar;
    Cursor cursor;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad);

        this.ctDescricao = (EditText) findViewById(R.id.ctDescricao);
        this.ctTipo = (EditText) findViewById(R.id.ctTipo);
        this.ctHora = (EditText) findViewById(R.id.ctHora);
        this.ctData = (EditText) findViewById(R.id.ctData);
        this.btSalvar = (Button) findViewById(R.id.btSalvar);

        this.btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Controller c = new Controller(getBaseContext());
                Controller c = Controller.getInstancia(getBaseContext());
                String descricao = ctDescricao.getText().toString();
                String tipo = ctTipo.getText().toString();
                String hora = ctHora.getText().toString();
                String data = ctData.getText().toString();
                String resultado;

                if(getIntent().getStringExtra("situacao").equals("alterar")){
                    resultado = c.alterar(Integer.parseInt(codigo), descricao, tipo, hora, data);
                } else {
                    resultado = c.inserir(descricao, tipo, hora, data);
                }

                Toast.makeText(CadActivity.this, resultado, Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(CadActivity.this, ListaActivity.class);
                startActivity(intent);
            }
        });

        if(getIntent().getStringExtra("situacao").equals("alterar")){
            Controller c = new Controller(getBaseContext());
            codigo = getIntent().getStringExtra("codigo");
            cursor = c.buscaId(Integer.parseInt(codigo));
            ctDescricao.setText(cursor.getString(cursor.getColumnIndexOrThrow("descricao")));
            ctTipo.setText(cursor.getString(cursor.getColumnIndexOrThrow("tipo")));
            ctHora.setText(cursor.getString(cursor.getColumnIndexOrThrow("hora")));
            ctData.setText(cursor.getString(cursor.getColumnIndexOrThrow("data")));
        }

    }
}
