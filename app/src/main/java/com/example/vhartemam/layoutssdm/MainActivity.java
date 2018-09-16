package com.example.vhartemam.layoutssdm;

import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String ESTADO_NOTIFICACAO_CHECKBOX = "ESTADO_NOTIFICACAO_CHECKBOX";
    private final String NOTIFICACAO_RADIOBUTTON_SELECIONADO = "NOTIFICACAO_RADIOBUTTON_SELECIONADO";
    private final String VIEWS_TELEFONE = "VIEWS_TELEFONE";
    private final String VIEWS_EMAIL = "VIEWS_EMAIL";

    private CheckBox notificacoesCheckBox;
    private RadioGroup notificacoesRadioGroup;
    private TextView nomeEditText;
    //private TextView emailEditText;
    //private TextView telefoneEditText;

    private LinearLayout telefoneLinearLayout;
    private ArrayList<View> telefoneArrayList;
    private LinearLayout emailLinearLayout;
    private ArrayList<View> emailArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //depois que setou o content view é possivel acessar as views
        setContentView(R.layout.scroll_view_activity_main);

        //Referencias para as views
        notificacoesCheckBox = findViewById(R.id.notificacoesCheckBox);
        notificacoesRadioGroup = findViewById(R.id.notificacoesRadioGroup);
        nomeEditText = findViewById(R.id.nomeEditText);
        //emailEditText = findViewById(R.id.emailEditText);
        //telefoneEditText = findViewById(R.id.telefoneEditText);
        telefoneLinearLayout = findViewById(R.id.telefoneLinearLayout);
        emailLinearLayout = findViewById(R.id.emailLinearLayout);

        telefoneArrayList = new ArrayList<>();
        emailArrayList = new ArrayList<>();

        //Tratando evento no checkBox
        /*notificacoesCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox) v).isChecked()){
                    notificacoesRadioGroup.setVisibility(View.VISIBLE);
                }
                else{
                    notificacoesRadioGroup.setVisibility(View.GONE);
                }
            }
        });*/

        notificacoesCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    notificacoesRadioGroup.setVisibility(View.VISIBLE);
                }
                else{
                    notificacoesRadioGroup.setVisibility(View.GONE);
                }
            }
        });


    }

    protected void adicionarTelefone(View view){
        //Toast.makeText( this, "AQUI 1", Toast.LENGTH_SHORT).show();
        if(view.getId() == R.id.adicionarTelefoneButton){
            LayoutInflater layoutInflater = getLayoutInflater();

            View novoTelefoneLayout = layoutInflater.inflate(R.layout.novo_telefone_layout, null);
            telefoneArrayList.add(novoTelefoneLayout);
            telefoneLinearLayout.addView(novoTelefoneLayout);
            Toast.makeText( this, "AQUI " + telefoneArrayList.size(), Toast.LENGTH_SHORT).show();
        }
    }

    protected void adicionarEmail(View view){
        if(view.getId() == R.id.adicionarEmailButton){
            LayoutInflater layoutInflator = getLayoutInflater();

            View novoEmailLayout = layoutInflator.inflate(R.layout.novo_email_layout, null);
            emailArrayList.add(novoEmailLayout);
            emailLinearLayout.addView(novoEmailLayout);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(ESTADO_NOTIFICACAO_CHECKBOX, notificacoesCheckBox.isChecked());
        outState.putInt(NOTIFICACAO_RADIOBUTTON_SELECIONADO, notificacoesRadioGroup.getCheckedRadioButtonId());
        outState.putSerializable(VIEWS_TELEFONE, telefoneArrayList);
        //outState.putParcelableArrayList(VIEWS_TELEFONE, (ArrayList<? extends Parcelable>) telefoneArrayList);
        //outState.putParcelableArrayList(VIEWS_EMAIL, emailArrayList);


    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);

        notificacoesCheckBox.setChecked(savedInstanceState.getBoolean(ESTADO_NOTIFICACAO_CHECKBOX));
        notificacoesRadioGroup.check(savedInstanceState.getInt(NOTIFICACAO_RADIOBUTTON_SELECIONADO));

        telefoneArrayList = (ArrayList<View>) savedInstanceState.getSerializable(VIEWS_TELEFONE);
        //telefoneArrayList = (ArrayList<View>) savedInstanceState.getParcelableArrayList(VIEWS_TELEFONE);
        emailArrayList = (ArrayList<View>) savedInstanceState.getSerializable(VIEWS_EMAIL);

        for(View view : telefoneArrayList){
            telefoneLinearLayout.addView(view);
        }

        for(View view : emailArrayList){
            emailLinearLayout.addView(view);
        }
    }

    public void limparFormulario(View botao){
        nomeEditText.setText(null);
        //emailEditText.setText(null);
        //telefoneEditText.setText(null);
        notificacoesRadioGroup.clearCheck();
        notificacoesCheckBox.setChecked(false);

        nomeEditText.requestFocus();
    }
}
