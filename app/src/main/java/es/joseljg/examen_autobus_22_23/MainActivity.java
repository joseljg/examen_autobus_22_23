package es.joseljg.examen_autobus_22_23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_INGRESOS = "es.joseljg.examen_autobus_22_23.mainactivity.ingresos" ;
    public static final String EXTRA_GASTOS = "es.joseljg.examen_autobus_22_23.mainactivity.gastos" ;
    public static final String EXTRA_TEMPORADA = "es.joseljg.examen_autobus_22_23.mainactivity.temporada" ;
    public static final String EXTRA_NUM_PASAJEROS = "es.joseljg.examen_autobus_22_23.mainactivity.num_pasajeros" ;

    private EditText edt_num_pasajeros = null;
    private Spinner sp_temporada = null;
    private String temporadaElegida = null;
    private int num_pasajeros;
    private double precio_billete;
    private double ingresos;
    private double gastos;

    private RadioButton rb_tiene_servicios = null;
    private RadioButton rb_no_tiene_servicios = null;

    private TextView txt_ganancia;
    private TextView txt_perdida;

    private Button bt_siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_num_pasajeros = (EditText) findViewById(R.id.edt_num_pasajeros);
        rb_tiene_servicios = (RadioButton) findViewById(R.id.rb_servicios_si);
        rb_no_tiene_servicios = (RadioButton) findViewById(R.id.rb_servicios_no);
        txt_ganancia = (TextView) findViewById(R.id.txt_ganancias);
        txt_perdida = (TextView) findViewById(R.id.txt_perdidas);
        bt_siguiente = (Button) findViewById(R.id.bt_siguiente);
        //-----------------------------------------------------------------------
        sp_temporada = (Spinner) findViewById(R.id.sp_temporada);
        temporadaElegida = "baja";
        String[] temporada ={"baja","media","alta"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, temporada);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_temporada.setAdapter(adapter);
        sp_temporada.setOnItemSelectedListener(this);
        sp_temporada.setSelection(2);
        //-----------------------------------------------------------------------

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        temporadaElegida = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        temporadaElegida = "baja";
    }

    public void mostrar_ingresos_gastos(View view) {
        bt_siguiente.setEnabled(false);
        String texto = String.valueOf(edt_num_pasajeros.getText());
        if(texto.isEmpty())
        {
            edt_num_pasajeros.setError("debes escribir el número de pasajeros");
            return;
        }
        num_pasajeros = Integer.valueOf(texto);
       calcularPrecioBillete();
        //----------------------------------------------------------------------
        ingresos = num_pasajeros * precio_billete;
        //---------------------------------------------------------------------
        calcularGastosAutobus();
        // rellenar los valores de total disponible y total pérdidas
        txt_ganancia.setText(String.valueOf(ingresos));
        txt_perdida.setText(String.valueOf(gastos));
        // habilito el boton siguiente
        bt_siguiente.setEnabled(true);
    }

    private void calcularGastosAutobus() {
        if (rb_tiene_servicios.isChecked()) {
            gastos = 500;
        } else if (rb_no_tiene_servicios.isChecked()) {
            gastos = 400;
        }
    }

    private void calcularPrecioBillete() {
        if(temporadaElegida.equalsIgnoreCase("baja"))
        {
            precio_billete=10;
        }
        else if(temporadaElegida.equalsIgnoreCase("media"))
        {
            precio_billete=11;
        }
        else if(temporadaElegida.equalsIgnoreCase("alta"))
        {
            precio_billete=12;
        }
    }

    public void ir_a_siguiente_pantalla(View view) {
        Intent intent = new Intent(this, Pantalla2Activity.class);
        intent.putExtra(EXTRA_INGRESOS, ingresos);
        intent.putExtra(EXTRA_GASTOS, gastos);
        intent.putExtra(EXTRA_TEMPORADA, temporadaElegida);
        intent.putExtra(EXTRA_NUM_PASAJEROS, num_pasajeros);
        startActivity(intent);
    }
}