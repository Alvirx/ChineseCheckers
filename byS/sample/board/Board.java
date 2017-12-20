package sample.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Board {


    HashSet<Field> fields = new HashSet<>();

    int size;


    public HashSet<Field> getFields(){
        return fields;
    }


    public  void getShapes() {

        ArrayList<Field> newLine = new ArrayList<>();
        ArrayList<Field> oldLine = new ArrayList<>();
        Integer id= 0;
        Field firstField = new Field(id++, 0 , 0, Colours.RED);
        fields.add(firstField);
        newLine.add(firstField);

        //gorne ramie gwiazdy
        for (int i = 1; i < size; i++) {
            oldLine = newLine;
            newLine = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                Field newField = new Field(id++,2*j - i,2* i, Colours.GREEN);
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

             Field newField = new Field(id++, 2 * i -(size + 2 * size),  2 * size ); // 4 zejscia w dol oraz trzy odbicia w lewo podczas ktorych przesuwam sie podwojnie
             if(i < size)
                 newField.setColour(Colours.NAVY);
             if(i > 2 * size + 1)
                 newField.setColour(Colours.RED);

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
                Field newField = new Field(id++, 2 * j + i -(size + 1  + 2 * (size - 1)), 2 * (size  + 1 + i));

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
                Field newField = new Field(id++, 2 * j - i - (2 * size), 2 * (2 *  size + i));

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
            Field newField = new Field(id++, 2 * i - (size - 1) ,2 * (3 * size) );

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
                Field newField = new Field(id++, 2 * j - (size - 1 - i), 2 * (3 * size + i));

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

    public Board(int size) {
        this.size = size;
        getShapes();
    }
}
