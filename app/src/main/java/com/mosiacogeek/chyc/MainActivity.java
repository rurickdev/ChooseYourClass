package com.mosiacogeek.chyc;

import android.content.Intent;
import android.os.Handler;
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

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    View viewNav;
    LinearLayout layout;
    Toolbar barra;
    private ImageView imagen;
    private TextView pregunta;
    private Button boton1;
    private Button boton2;
    private Button boton3;
    private Button boton4;
    private List<Button> buttonList = new ArrayList<>();

    private Stack<String> navegacion = new Stack<>();

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
        imagen.setImageResource(R.mipmap.img_question_main);

        pregunta = findViewById(R.id.txt_pregunta);
        pregunta.setText(R.string.text_presentation);

        boton1 = findViewById(R.id.btn1);
        boton2 = findViewById(R.id.btn2);
        boton3 = findViewById(R.id.btn3);
        boton4 = findViewById(R.id.btn4);

        buttonList.add(boton1);
        buttonList.add(boton2);
        buttonList.add(boton3);
        buttonList.add(boton4);
    }

    //Se ejecuta cuando precionas un boton para pasar a la sig pantalla
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

    //Lo manda a llamar el metodo anterior() para poder regresar a la pantalla anterior
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

    //Se usa para decidir a que pantalla ir
    //Cada case ejecuta el metodo que genera/construye la pantalla correspondiente
    public void mySwitch(String tag, View view){
        Log.d("mySwitch",tag);
        switch (tag) {
            case "presentacion":
                this.setTitle("ChYC");
                presentation(view);
                break;
            case "start":
                generarPantalla(tag,
                        R.mipmap.img_question_start,
                        R.string.question_one,
                        2,
                        new Integer[]{R.string.btn_stayBack, R.string.btn_approach},
                        new String[]{"stay", "approach"});
                pregunta.setMovementMethod(new ScrollingMovementMethod());
                break;
            case "stay":
                generarPantalla(tag,
                        R.mipmap.img_question_stayback,
                        R.string.question_two_stay,
                        3,
                        new Integer[]{R.string.btn_withMagic, R.string.btn_cleverTactics, R.string.btn_rallyGuards},
                        new String[]{"magic", "tactics", "rally"});
                break;
            case "magic":
                generarPantalla(tag,
                        R.mipmap.img_question_magic,
                        R.string.question_two_stay_magic,
                        4,
                        new Integer[]{
                                R.string.btn_spellVariety,
                                R.string.btn_spellMastery,
                                R.string.btn_enhancedAbilities,
                                R.string.btn_transformationSpells},
                        new String[]{"wizard", "sorcerer", "warlock", "druid"});
                break;
            case "tactics":
                generarPantalla(tag,
                        R.mipmap.img_question_tactics,
                        R.string.question_two_stay_tactics,
                        3,
                        new Integer[]{
                                R.string.btn_trapsSurprises,
                                R.string.btn_barrageSlay,
                                R.string.btn_terrainMastery},
                        new String[]{"rogue", "fighter", "ranger"});
                break;
            case "rally":
                generarPantalla(tag,
                        R.mipmap.img_question_rally,
                        R.string.question_two_stay_rally,
                        2,
                        new Integer[]{
                                R.string.btn_healEnchant,
                                R.string.btn_debilitateInspire},
                        new String[]{"cleric", "bard"});
                break;
            case "approach":
                generarPantalla(tag,
                        R.mipmap.img_question_approach,
                        R.string.question_two_approach,
                        3,
                        new Integer[]{
                                R.string.btn_chargeAhead,
                                R.string.btn_standFight,
                                R.string.btn_assistGuards},
                        new String[]{"charge", "stand", "assist"});
                break;
            case "charge":
                generarPantalla(tag,
                        R.mipmap.img_question_charge,
                        R.string.question_two_approach_charge,
                        4,
                        new Integer[]{
                                R.string.btn_hitHardNoPain,
                                R.string.btn_explosiveAttacks,
                                R.string.btn_fastDeadly,
                                R.string.btn_giantEagle},
                        new String[]{"barbarian", "paladin", "monk", "druid"});
                break;
            case "stand":
                generarPantalla(tag,
                        R.mipmap.img_question_stand,
                        R.string.question_two_approach_fight,
                        4,
                        new Integer[]{
                                R.string.btn_shootSlash,
                                R.string.btn_steelMagic,
                                R.string.btn_trapsSurprises,
                                R.string.btn_magicVersatility},
                        new String[]{"fighter", "ranger", "rogue", "warlock"});
                break;
            case "assist":
                generarPantalla(tag,
                        R.mipmap.img_question_assist,
                        R.string.question_two_approach_assist,
                        3,
                        new Integer[]{
                                R.string.btn_healEnchant2,
                                R.string.btn_debilitateInspire2,
                                R.string.btn_healLead},
                        new String[]{"cleric", "bard", "paladin"});
                break;
            case "wizard":
                generarPantalla(tag,
                        R.mipmap.img_clase_wizard,
                        R.string.text_empty,
                        0,
                        new Integer[]{},
                        new String[]{});
                break;
            case "sorcerer":
                generarPantalla(tag,
                        R.mipmap.img_clase_sorcerer,
                        R.string.text_empty,
                        0,
                        new Integer[]{},
                        new String[]{});
                break;
            case "warlock":
                generarPantalla(tag,
                        R.mipmap.img_clase_warlock,
                        R.string.text_empty,
                        0,
                        new Integer[]{},
                        new String[]{});
                break;
            case "druid":
                generarPantalla(tag,
                        R.mipmap.img_clase_druid,
                        R.string.text_empty,
                        0,
                        new Integer[]{},
                        new String[]{});
                break;
            case "cleric":
                generarPantalla(tag,
                        R.mipmap.img_clase_cleric,
                        R.string.text_empty,
                        0,
                        new Integer[]{},
                        new String[]{});
                break;
            case "bard":
                generarPantalla(tag,
                        R.mipmap.img_clase_bard,
                        R.string.text_empty,
                        0,
                        new Integer[]{},
                        new String[]{});
                break;
            case "monk":
                generarPantalla(tag,
                        R.mipmap.img_clase_monk,
                        R.string.text_empty,
                        0,
                        new Integer[]{},
                        new String[]{});
                break;
            case "paladin":
                generarPantalla(tag,
                        R.mipmap.img_clase_paladine,
                        R.string.text_empty,
                        0,
                        new Integer[]{},
                        new String[]{});
                break;
            case "barbarian":
                generarPantalla(tag,
                        R.mipmap.img_clase_barbarian,
                        R.string.text_empty,
                        0,
                        new Integer[]{},
                        new String[]{});
                break;
            case "rogue":
                generarPantalla(tag,
                        R.mipmap.img_clase_rogue,
                        R.string.text_empty,
                        0,
                        new Integer[]{},
                        new String[]{});
                break;
            case "fighter":
                generarPantalla(tag,
                        R.mipmap.img_clase_fighter,
                        R.string.text_empty,
                        0,
                        new Integer[]{},
                        new String[]{});
                break;
            case "ranger":
                generarPantalla(tag,
                        R.mipmap.img_clase_ranger,
                        R.string.text_empty,
                        0,
                        new Integer[]{},
                        new String[]{});
                break;
            default:
                generarPantalla(tag,
                        R.mipmap.question_under_construction,
                        R.string.text_auxiliar_work,
                        0,
                        new Integer[]{},
                        new String[]{});
                break;
        }
    }
    //ToDo: pasarlo a usar generarPantalla()
    public void presentation(View view){
        //this.setTitle("ChYC");
        layout.setTag("presentacion");
        barra.setNavigationIcon(R.mipmap.ic_launcher_round);
        imagen.setImageResource(R.mipmap.img_question_main);
        pregunta.setText(R.string.text_presentation);
        boton1.setVisibility(android.view.View.VISIBLE);
        boton2.setVisibility(android.view.View.GONE);
        boton3.setVisibility(android.view.View.GONE);
        boton4.setVisibility(android.view.View.GONE);
        boton1.setText(R.string.btn_start);
        boton1.setTag("start");
    }


    //Se ejecuta cuando precionas el boton de regresar a la pantalla anterior
    public void anterior(){
        try {
            nextQuestion(viewNav, navegacion.pop());
        }catch (EmptyStackException e){
            Log.d("stack", "Se acabó la pila");
        }
    }

    //Metodo que contruye las pantallas segun los paramateros
    //Recibe:
    //  tag = nombre de la pantalla que va acrear
    //  img = imagen que tendra la pantalla
    //  parrafo = texto que tendra la pantalla
    //  numBotones = la cantidad de botones que tendra
    //  [] textos = textos para los botones
    //  [] etiquetas = etiquetas que mandaran (con metodo nextQuestion()) los botones cuando se toque
    public void generarPantalla(String tag, int img, int parrafo, int numBotones, Integer[] textos, String[] etiquetas){
        barra.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        layout.setTag(tag);
        this.setTitle(tag.substring(0, 1).toUpperCase() + tag.substring(1));
        imagen.setImageResource(img);
        pregunta.setText(parrafo);
        pregunta.setScrollY(0);

        for (Button i : buttonList){
            if(buttonList.indexOf(i)<numBotones){
                i.setText(textos[buttonList.indexOf(i)]);
                i.setTag(etiquetas[buttonList.indexOf(i)]);
                i.setVisibility(View.VISIBLE);
            }else{
                i.setText("");
                i.setTag("");
                i.setVisibility(View.GONE);
            }
        }
    }
}
