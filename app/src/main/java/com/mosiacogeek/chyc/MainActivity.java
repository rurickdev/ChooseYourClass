package com.mosiacogeek.chyc;

import android.content.Intent;
import android.os.Handler;
/*
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
*/
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.EmptyStackException;
import java.util.Stack;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Button boton1;
    private Button boton2;
    private Button boton3;
    private Button boton4;
    private ImageView imagen;
    private TextView pregunta;
    Toolbar barra;
    private Stack<String> navegacion = new Stack<>();
    View viewNav;
    LinearLayout layout;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reiniciar:
                navegacion.removeAllElements();
                presentation(this.findViewById(android.R.id.content));
                return true;
            case R.id.about:
                Intent intent = new Intent(this, About.class);
                startActivity(intent);
                return true;
            case android.R.id.home:
                anterior();
                return true;
            default:
                return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barra = findViewById(R.id.my_action_bar);
        setSupportActionBar(barra);
        ActionBar ab = getSupportActionBar();
        if(ab!= null)
            ab.setDisplayHomeAsUpEnabled(true);
        barra.setNavigationIcon(R.mipmap.ic_launcher_round);

        layout = findViewById(R.id.main_layout_buttons_row1);

        imagen = findViewById(R.id.img_cabecera);
        imagen.setImageResource(R.mipmap.main);

        pregunta = findViewById(R.id.txt_pregunta);
        pregunta.setText(R.string.text_presentation);

        boton1 = findViewById(R.id.btn1);
        boton2 = findViewById(R.id.btn2);
        boton3 = findViewById(R.id.btn3);
        boton4 = findViewById(R.id.btn4);
    }

    public void nextQuestion(final View view) {

        final String tag = view.getTag().toString();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navegacion.push(layout.getTag().toString());
                mySwitch(tag, view);
            }
        }, 300);
    }

    public void nextQuestion(final View view, String navegacion) {

        final String tag = navegacion;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mySwitch(tag, view);
            }
        }, 300);
    }

    public void mySwitch(String tag, View view){
        Log.d("mySwitch",tag);
        switch (tag) {
            case "start":
                start(tag);
                break;
            case "stay":
                stay(tag);
                break;
            case "approach":
                approach(tag);
                break;
            case "magic":
                magic(tag);
                break;
            case "presentacion":
                presentation(view);
                break;
            //ToDo: los demás pasos
            default:
                underConstruction(tag);
                break;
        }
    }

    public void stay(String tag) {
        metodoGenerico(tag,3,
                R.string.btn_withMagic,
                R.string.btn_cleverTactics,
                R.string.btn_rallyGuards,
                R.string.btn_aux_vacio,
                R.mipmap.stayback,
                "magic",
                "clever",
                "rally",
                "",
                R.string.question_two_stay);
    }

    public void magic(String tag){
        metodoGenerico(tag,4,
                R.string.btn_spellVariety,
                R.string.btn_spellMastery,
                R.string.btn_enhancedAbilities,
                R.string.btn_transformationSpells,
                R.mipmap.magic,
                "sv",
                "sm",
                "enhanced",
                "transformation",
                R.string.question_two_stay_magic);
    }

    public void approach(String tag) {
        metodoGenerico(tag,3,
                R.string.btn_chargeAhead,
                R.string.btn_standFight,
                R.string.btn_assistGuards,
                R.string.btn_aux_vacio,
                R.mipmap.approach,
                "charge",
                "stand",
                "assist",
                "",
                R.string.question_two_approach);
    }

    public void start(String tag) {
        metodoGenerico(tag, 2,
                R.string.btn_stayBack,
                R.string.btn_approach,
                R.string.btn_aux_vacio,
                R.string.btn_aux_vacio,
                R.mipmap.start,
                "stay",
                "approach",
                "",
                "",
                R.string.question_one);
        pregunta.setMovementMethod(new ScrollingMovementMethod());

    }

    public void presentation(View view){
        layout.setTag("presentacion");
        this.setTitle("ChYC");
        barra.setNavigationIcon(R.mipmap.ic_launcher_round);
        imagen.setImageResource(R.mipmap.main);
        pregunta.setText(R.string.text_presentation);
        boton1.setVisibility(android.view.View.VISIBLE);
        boton2.setVisibility(android.view.View.GONE);
        boton3.setVisibility(android.view.View.GONE);
        boton4.setVisibility(android.view.View.GONE);
        boton1.setText(R.string.btn_start);
        boton1.setTag("start");

    }

    public void anterior(){
        try {
            nextQuestion(viewNav, navegacion.pop());
        }catch (EmptyStackException e){
            Log.d("stack", "Se acabó la pila");
        }
    }

    public void metodoGenerico(String tag,
                               int numBotones,
                               int btext1,
                               int btext2,
                               int btext3,
                               int btext4,
                               int img,
                               String bTag1,
                               String bTag2,
                               String bTag3,
                               String bTag4,
                               int ptexto){
        barra.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        layout.setTag(tag);
        this.setTitle(tag.substring(0, 1).toUpperCase() + tag.substring(1));
        imagen.setImageResource(img);
        pregunta.setText(ptexto);
        boton1.setVisibility(android.view.View.VISIBLE);
        boton1.setText(btext1);
        boton1.setTag(bTag1);
        if(numBotones >= 2){
            boton2.setVisibility(android.view.View.VISIBLE);
            boton2.setText(btext2);
            boton2.setTag(bTag2);
            if(numBotones >= 3){
                boton3.setVisibility(android.view.View.VISIBLE);
                boton3.setText(btext3);
                boton3.setTag(bTag3);
                if(numBotones == 4) {
                    boton4.setVisibility(android.view.View.VISIBLE);
                    boton4.setText(btext4);
                    boton4.setTag(bTag4);
                }else {
                    boton4.setVisibility(android.view.View.GONE);
                }
            }else {
                boton3.setVisibility(android.view.View.GONE);
            }
        }else {
            boton2.setVisibility(android.view.View.GONE);
        }

    }

    public void underConstruction(String tag){
        metodoGenerico(tag, 0,
                R.string.btn_aux_vacio,
                R.string.btn_aux_vacio,
                R.string.btn_aux_vacio,
                R.string.btn_aux_vacio,
                R.mipmap.under_construction,
                "",
                "",
                "",
                "",
                R.string.text_auxiliar_work);
        boton1.setVisibility(android.view.View.GONE);
        boton2.setVisibility(android.view.View.GONE);
        boton3.setVisibility(android.view.View.GONE);
        boton4.setVisibility(android.view.View.GONE);
    }


}

