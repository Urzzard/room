package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText eTitulo, eDescripcion, eGenero, eDirector;
    PeliculaDB db;
    TextView tvMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTitulo = findViewById(R.id.nomPelicula);
        eDescripcion = findViewById(R.id.etDescripcion);
        eGenero = findViewById(R.id.etGenero);
        eDirector = findViewById(R.id.etDirector);

        db = Room.databaseBuilder(getApplicationContext(), PeliculaDB.class, "peliculas.db").allowMainThreadQueries().build();

        tvMostrar = findViewById(R.id.tvMostrar);
        List<Pelicula> peliculas = new ArrayList<Pelicula>();
        peliculas = db.peliculaDAO().todasPeliculas();
        tvMostrar.setText("");
        for(Pelicula item : peliculas){
            tvMostrar.append("Titulo"+ item.titulo + "\n" +
                            "Descripcion" + item.descripcion + "\n" +
                            "Geneno" + item.genero + "\n" +
                            "Director" + item.director + "\n");
        }
    }

    public void GuardarInfo(View view){

        String nuevotitulo = eTitulo.getText().toString();
        String nuevadescripcion = eDescripcion.getText().toString();
        String nuevogenero = eGenero.getText().toString();
        String nuevodirector = eDirector.getText().toString();

        Pelicula pelicula = new Pelicula();
        pelicula.titulo = nuevotitulo;
        pelicula.descripcion = nuevadescripcion;
        pelicula.genero = nuevogenero;
        pelicula.director = nuevodirector;

        db.peliculaDAO().InsertarPelicula(pelicula);
        Toast.makeText(getApplicationContext(), "Pelicula Guardada", Toast.LENGTH_LONG).show();
    }
}