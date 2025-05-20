import java.util.*;
import java.io.*;

public class ListPokemon {
    List<Pokemon> pokemonList = new ArrayList<>();

    public ListPokemon() {
        try (BufferedReader rc = new BufferedReader(new FileReader("pokemonchara.txt"))){
            String line = rc.readLine();
            while (line != null){
                String[] pokemon = line.split(",");
                if (pokemon.length == 5){
                    String name = pokemon[0];
                    Type type = Type.valueOf(pokemon[1]);
                    int health = Integer.parseInt(pokemon[2]);
                    int attack = Integer.parseInt(pokemon[3]);
                    int defense = Integer.parseInt(pokemon[4]);

                    Pokemon pokemonchara = new Pokemon(name, type, health, attack, defense);
                    pokemonList.add(pokemonchara);
                }
            }
        } catch (IOException e){
            //ini harusnya nanti bikin page kalo misal filenya tidak ditemukan
            System.out.println(e.getMessage());
        }
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public static void main(String[] args) {
        ListPokemon listPokemon = new ListPokemon();
        for (Pokemon p : listPokemon.getPokemonList()) {
            System.out.println(p); // pastikan class Pokemon punya toString()
        }
    }

    //try {(BufferedReader rc = new BufferedReader(new FileReader("pokemonchara.txt")));}
}
