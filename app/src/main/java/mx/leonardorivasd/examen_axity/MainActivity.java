package mx.leonardorivasd.examen_axity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvProducto = (TextView) findViewById(R.id.idProducto);
        TextView tvDepartamento = (TextView) findViewById(R.id.idDepartamento);
        TextView tvId = (TextView) findViewById(R.id.idCodigo);
        TextView tvPrecio = (TextView) findViewById(R.id.idPrecio);

        new GetData(tvProducto,tvDepartamento, tvId, tvPrecio, MainActivity.this).execute();
    }
}
