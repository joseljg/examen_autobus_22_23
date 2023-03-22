package es.joseljg.examen_autobus_22_23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Pantalla2Activity extends AppCompatActivity {

    // variables que he recibido de la pantalla 1
    private String temporada;
    private int num_pasajeros;
    private double ganancias;
    private double perdidas;
    private double ganado;

    private double iva;
    private double total_neto;

    private TextView txt_p2_ingresos_numero = null;
    private TextView txt_p2_gastos_numero = null;
    private TextView txt_p2_ganado_numero = null;

    private TextView txt_p2_impuestos_numero = null;
    private TextView txt_p2_total_neto_numero = null;
    private TextView txt_p2_impuestos = null;
    private TextView txt_p2_total_neto = null;
    private EditText edt_iva = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);
        //--------------------------------------------------------
        txt_p2_ingresos_numero = (TextView) findViewById(R.id.txt_p2_ingresos_numero);
        txt_p2_gastos_numero = (TextView) findViewById(R.id.txt_p2_gastos_numero);
        txt_p2_ganado_numero = (TextView) findViewById(R.id.txt_p2_ganado_numero);
        txt_p2_impuestos_numero = (TextView) findViewById(R.id.txt_p2_impuestos_numero);
        txt_p2_total_neto_numero = (TextView) findViewById(R.id.txt_p2_total_neto_numero);
        txt_p2_impuestos = (TextView) findViewById(R.id.txt_p2_impuestos);
        txt_p2_total_neto = (TextView) findViewById(R.id.txt_p2_total_neto);
        edt_iva = (EditText) findViewById(R.id.edt_iva);
        //-------------------------------------------------------
        Intent intent = getIntent();
        if(intent != null)
        {
           temporada = intent.getStringExtra(MainActivity.EXTRA_TEMPORADA);
           num_pasajeros = intent.getIntExtra(MainActivity.EXTRA_NUM_PASAJEROS, 0);
           ganancias = intent.getDoubleExtra(MainActivity.EXTRA_INGRESOS, 0);
           perdidas = intent.getDoubleExtra(MainActivity.EXTRA_GASTOS, 0);
           ganado = ganancias - perdidas;
           // actualizo los valores de ganancia y pérdida de la pantalla
            txt_p2_ingresos_numero.setText(String.valueOf(ganancias));
            txt_p2_gastos_numero.setText(String.valueOf(perdidas));
            txt_p2_ganado_numero.setText(String.valueOf(ganado));
        }
    }

    public void aplicar_iva(View view) {
        String texto = String.valueOf(edt_iva.getText());
        if(texto.isEmpty())
        {
            edt_iva.setError("debes poner el iva");
            return;
        }
        iva = Double.valueOf(texto);
        total_neto = (ganancias - perdidas) * (1-(iva /100));
        txt_p2_impuestos_numero.setVisibility(View.VISIBLE);
        txt_p2_impuestos.setVisibility(View.VISIBLE);
        txt_p2_total_neto_numero.setVisibility(View.VISIBLE);
        txt_p2_total_neto.setVisibility(View.VISIBLE);
        txt_p2_impuestos_numero.setText(String.valueOf((ganancias-perdidas)*iva/100));
        txt_p2_total_neto_numero.setText(String.valueOf(total_neto));
    }
}