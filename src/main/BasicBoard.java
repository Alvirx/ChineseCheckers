package Server;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 */
public class BasicBoard implements Server.Board {

    /**
     * Obecnie wszystkie pola sa przechowywane w hashSecie, jeśli bedzie Ci wygodnie mozesz dowolnie zmienic to na jakas
     * inna strukture dancyh, korzystam tylko z dodwania do, oraz z iteracji po elementach.
     */
    HashSet<Server.Field> fields = new HashSet<>();

    /**
     * Dlugosc skrzydla tworzonej planszy, w zadaniu wystepuje plansza z size = 4, ale chcialo mi sie bawic to zrobilem
     * uniwersalne
     */
    int size;


    /**
     * Jedyne co potrzebuje, aby rysowac gui, potem iteruje sobie po elementach i zamieszczam je na rysunku
     * @return
     */
    public HashSet<Server.Field> getFields(){
        return fields;
    }

    /**
     * Jest to funkcja, ktora tworzy mi po koleji wszystkie pola, daje im wspolrzedne oraz namiary do pol jej otaczajacych
     * Dodatkowo ramiona kolorluje wedlug rysunku pod adresem:
     * http://www.fun-free-party-games.com/downloads/Game-Board-Chinese-Checkers-Full.gif
     * Wydaje mi sie, ze to wszystko co musimy nadac tworzac tablice, ale latwo dorzucic nowe elementy
     *
     * Tworzenie podzielilem na 4 etapy, oraz 3 laczenia, chyba nie ma sensu bys wchodzil w strukture algorytmu
     *
     * metoda getShapes nie powinna byc nigdy wywolywana poza konstruktorem, ale nie wiem,
     * czy do jakiegos buildera jej wypchac nie wypadalo by
     */
    private void getShapes() {

        ArrayList<Server.Field> newLine = new ArrayList<>();
        ArrayList<Server.Field> oldLine = new ArrayList<>();
        Integer id= 0;
        Server.Field firstField = new Server.Field(id++, 0 , 0, Server.Colours.RED);
        fields.add(firstField);
        newLine.add(firstField);

        //gorne ramie gwiazdy
        for (int i = 1; i < size; i++) {
            oldLine = newLine;
            newLine = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                Server.Field newField = new Server.Field(id++,2*j - i,2* i, Server.Colours.GREEN);
                if (j > 0) {
                    newField.setLeft(newLine.get(j - 1));
                    newField.setUpLeft(oldLine.get(j - 1));
                }
                if (j < i - 1) {
                    newField.setUpRight(oldLine.get(j));
                }
                newLine.add(newField);
                fields.add(newField);
            }
        }
        //polaczenie gornego ramienia z masywem start y powinno byc 4
        oldLine = newLine;
        newLine = new ArrayList<>();
        for(int i = 0; i < 3* size + 1; i++){

             Server.Field newField = new Server.Field(id++, 2 * i -(size + 2 * size),  2 * size ); // 4 zejscia w dol oraz trzy odbicia w lewo podczas ktorych przesuwam sie podwojnie
             if(i < size)
                 newField.setColour(Server.Colours.NAVY);
             if(i > 2 * size + 1)
                 newField.setColour(Server.Colours.RED);

             if(i > 0)
                 newField.setLeft(newLine.get(i - 1));
             if(i >= size && i < 2 * size)
                 newField.setDownRight(oldLine.get(i - size));
             if(i > size && i <= 2 * size)
                 newField.setUpLeft(oldLine.get(i - size - 1));



             newLine.add(newField);
             fields.add(newField);
        }
        //gorny masyw gwiazdy
        for (int i = 0; i < size; i++) {
            oldLine = newLine;
            newLine = new ArrayList<>();
            for (int j = 0; j < 3 * size - i; j ++){
                Server.Field newField = new Server.Field(id++, 2 * j + i -(size + 1  + 2 * (size - 1)), 2 * (size  + 1 + i));

                newField.setUpLeft(oldLine.get(j));
                newField.setUpRight(oldLine.get(j + 1));
                if(j > 0)
                    newField.setLeft(newLine.get(j - 1));

                newLine.add(newField);
                fields.add(newField);
            }

        }
//        Dolny masyw gwiazdy
        for(int i = 0; i < size; i++){
            oldLine = newLine;
            newLine = new ArrayList<>();
            for(int j = 0; j <= 2 * size + i; j++){
                Server.Field newField = new Server.Field(id++, 2 * j - i - (2 * size), 2 * (2 *  size + i));

                if(j < 2 *  size + i)
                newField.setUpRight(oldLine.get(j));
                if(j > 0){
                    newField.setUpLeft(oldLine.get(j - 1));
                    newField.setLeft(newLine.get(j - 1));
                }

                newLine.add(newField);
                fields.add(newField);
            }

        }
        oldLine = newLine;
        newLine = new ArrayList<>();
        for(int i = 0; i <  size; i++){
            Server.Field newField = new Server.Field(id++, 2 * i - (size - 1) ,2 * (3 * size) );

            newField.setUpLeft(oldLine.get(size + i));
            newField.setUpRight(oldLine.get(size + 1 + i));
            if(i > 0)
                newField.setLeft(newLine.get(i - 1));
            newLine.add(newField);
            fields.add(newField);

        }

        for(int i = 1; i < size; i++){
            oldLine = newLine;
            newLine = new ArrayList<>();
            for(int j = 0; j < size - i; j++){
                Server.Field newField = new Server.Field(id++, 2 * j - (size - 1 - i), 2 * (3 * size + i));

                newField.setUpLeft(oldLine.get(j));
                newField.setUpRight(oldLine.get(j + 1));
                if(j > 0)
                    newField.setLeft(newLine.get(j - 1));

                newLine.add(newField);
                fields.add(newField);
            }
        }
        //dolne ramie gwiazdy


    }

    /**
     * No i konstruktor
     * @param size
     */
    /**
     * Number of players powinien byc sprawdzany na wyzszych instancjach
     * @param size
     * @param numberOfPlayers
     */



    public BasicBoard(int size, int numberOfPlayers) {
        this.size = size;
        getShapes();
        switch (numberOfPlayers){
            case 2:
                for(Server.Field field: fields)
                    if(field.getColour() == Server.Colours.GREEN || field.getColour() == Server.Colours.ORANGE)
                        field.setColourOfCounter(field.getColour());
                break;
            case 3:
                for(Server.Field field: fields)
                    if(field.getColour() == Server.Colours.GREEN || field.getColour() == Server.Colours.YELLOW || field.getColour() == Server.Colours.VIOLET)
                        field.setColourOfCounter(field.getColour());
                break;
            case 6:
                for(Server.Field field: fields)
                    field.setColourOfCounter(field.getColour());
                break;
            default:
                System.out.println("Cos poszlo zle");

        }
    }
}
