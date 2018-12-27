package fr.leroideskiwis.combat.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class SerialManager {

    public Object readFile(String name){

        try {
            InputStreamReader input = new InputStreamReader(new FileInputStream(name));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void writeFile(String name, Object o) {



    }
}
