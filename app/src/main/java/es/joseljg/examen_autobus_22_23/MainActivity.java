package es.joseljg.examen_autobus_22_23;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText edt_num_pasajeros = null;
    private Spinner sp_temporada = null;
    private String temporadaElegida = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_num_pasajeros = (EditText) findViewById(R.id.edt_num_pasajeros);
        //-----------------------------------------------------------------------
        sp_temporada = (Spinner) findViewById(R.id.sp_temporada);
        temporadaElegida = "baja";
        String[] temporada ={"baja","media","alta"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, temporada);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_temporada.setAdapter(adapter);
        sp_temporada.setOnItemSelectedListener(this);
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
}