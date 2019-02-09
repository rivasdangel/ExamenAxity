package mx.leonardorivasd.examen_axity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvProducto = (TextView) findViewById(R.id.idProducto); //Textview mostrar Nombre de producto
        TextView tvDepartamento = (TextView) findViewById(R.id.idDepartamento); //Textview mostrar Departament de producto
        TextView tvId = (TextView) findViewById(R.id.idCodigo); //Textview mostrar Id de producto
        TextView tvPrecio = (TextView) findViewById(R.id.idPrecio); //Textview mostrar Precio de producto

        new GetData(tvProducto,tvDepartamento, tvId, tvPrecio, MainActivity.this).execute(); //Instancia de la clase GetData para obtener los datos de la URL
    }
}
