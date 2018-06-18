package android.usuario.agenda;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListaActivity extends AppCompatActivity {

    ListView lista;

    Cursor cursor;
    String codigo;
    AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        final Controller c = Controller.getInstancia(getBaseContext());
        cursor = c.lista();

        String[] campos = new String[]{"_id", "descricao", "tipo", "hora", "data"};

        int[] idViews = new int[]{R.id.id, R.id.descricao, R.id.tipo, R.id.hora, R.id.data};

        SimpleCursorAdapter ad = new SimpleCursorAdapter(
                getBaseContext(),
                R.layout.layout_lista,
                cursor,
                campos,
                idViews,
                0);

        this.lista = (ListView) findViewById(R.id.listaDados);
        this.lista.setAdapter(ad);

        this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                cursor.moveToPosition(i);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow("_id"));

                AlertDialog.Builder builder = new AlertDialog.Builder(ListaActivity.this);
                builder.setMessage("O que deseja fazer?");
                builder.setPositiveButton("Alterar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ListaActivity.this, CadActivity.class);
                        intent.putExtra("situacao", "alterar");
                        intent.putExtra("codigo", codigo);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ListaActivity.this);
                        builder.setMessage("Desela excluir o registro?");
                        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String result = c.excluir(Integer.parseInt(codigo));
                                Toast.makeText(ListaActivity.this, result, Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(getIntent());
                            }
                        });
                        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alerta = builder.create();
                        alerta.show();

                    }
                });

                alerta = builder.create();
                alerta.show();
            }
        });

    }
}
