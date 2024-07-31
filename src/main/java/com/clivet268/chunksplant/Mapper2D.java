package com.clivet268.chunksplant;

import java.util.ArrayList;
import java.util.Hashtable;

public class Mapper2D<T>{
    Hashtable<String, T> xy;

    public Mapper2D(int xradius, int yradius){
        /*
        xymap = new ArrayList<>(xradius);
        for (int i = 0; i < xradius; i++) {
            xymap.add(new ArrayList<>(yradius));
        }

         */
        xy = new Hashtable<>(xradius * yradius);
    }
    public Mapper2D(int radius){
        /*
        xymap = new ArrayList<>(radius);
        for (int i = 0; i < radius; i++) {
            xymap.add(new ArrayList<>(radius));
        }

         */
        xy = new Hashtable<>(radius * radius);
    }
    public Mapper2D(){
        xy = new Hashtable<>();
    }

    //TODO see an optometrist
    public void put(int x, int y, T e){
        //TODO bounds
        xy.remove(strupify(x,y));
        xy.put(strupify(x,y),e);
    }
    public T get(int x, int y){
        return xy.get(strupify(x,y));
    }
    public String strupify(int x, int y){
        return x + "," + y;
    }
}
