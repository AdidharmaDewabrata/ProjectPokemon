package pokemon;

import java.util.*;
import java.io.*;

public class ListPokemon {
    List<Pokemon> pokemonList = new ArrayList<>();

    public ListPokemon() {
        //ngambil data pokemonnya
        try (BufferedReader rc = new BufferedReader(new FileReader("pokemonchara.txt"))){
            String line = rc.readLine();
            while (line != null){
                String[] pokemon = line.split(",");
                if (pokemon.length == 6){
                    String name = pokemon[0];
                    Type type = Type.valueOf(pokemon[1]);
                    int health = Integer.parseInt(pokemon[2]);
                    int attack = Integer.parseInt(pokemon[3]);
                    int defense = Integer.parseInt(pokemon[4]);
                    String image = pokemon[5];

                    Pokemon pokemonchara = new Pokemon(name, type, health, attack, defense, image);
                    pokemonList.add(pokemonchara);
                }
                line = rc.readLine();
            }
        } catch (IOException e){
            //ini harusnya nanti bikin page kalo misal filenya tidak ditemukan
            System.out.println(e.getMessage());
        }
    }

    //buat namipilin datanya dari listnya
    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

// buat cek data masuk apa ga
//    public static void main(String[] args) {
//        pokemon.ListPokemon listPokemon = new pokemon.ListPokemon();
//        for (pokemon.Pokemon p : listPokemon.getPokemonList()) {
//            System.out.println(p); // pastikan class pokemon.Pokemon punya toString()
//        }
//    }

    //try {(BufferedReader rc = new BufferedReader(new FileReader("pokemonchara.txt")));}
}
