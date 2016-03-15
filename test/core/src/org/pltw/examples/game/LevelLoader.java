package org.pltw.examples.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.IdentityHashMap;

import sun.rmi.runtime.Log;

/**
 * Created by MNM on 3/9/16.
 */
public class LevelLoader {
    public ArrayList<Integer> XArray = new ArrayList<Integer>();
    public ArrayList<Integer> YArray = new ArrayList<Integer>();
    public void read(){
        FileHandle fileHandle = Gdx.files.internal("312floorMap.txt");
        String fileAsString = fileHandle.readString();
        int index = 0;
        boolean complete = false;
        XArray.add(-1);
        while(complete == false){
            //Gdx.app.debug("file lines1", Integer.toString(index));
            char currentChar = fileAsString.charAt(index);
           // Gdx.app.debug("file lines2", Character.toString(currentChar));
            if(currentChar == '\n'){
                Gdx.app.debug("file lines3", Integer.toString(index));
                XArray.add(index);
            } else if(currentChar!=' ' && currentChar != '0' && currentChar !='1'){
                Gdx.app.debug("odd char", Integer.toString(index));
            }
            index++;
            if(index>fileAsString.length()-1){
                XArray.add(index);
                complete = true;
            }
        }
        Gdx.app.debug("file lines4", Integer.toString(XArray.size()));
    }
    public ArrayList<Integer> retrieveY(int x){
        boolean complete = false;
        FileHandle fileHandle = Gdx.files.internal("312floorMap.txt");
        String fileAsString = fileHandle.readString();
        int index = 0;
        //Gdx.app.debug("x array", "checkpoint 1");
        ArrayList<Integer> indexs2 = new ArrayList<Integer>();
        String line = fileAsString.substring(XArray.get(x) + 1, XArray.get(x + 1) - 1);
        if (x==5){
            line = fileAsString.substring(XArray.get(x) + 1, XArray.get(x + 1));
        }
        //Gdx.app.debug("line length", Integer.toString(line.length()));
        if (line.length() == 0) {
            Gdx.app.debug("empty line", "the line was empty");
        }
        //Gdx.app.debug("file lines",line);
        index = 0;
        while (!complete) {

            char currentChar = line.charAt(index);
            if (currentChar == ' ') {

            } else {
                indexs2.add(index);
            }
            if (index > line.length() - 2) {
                complete = true;
            }
            index++;
        }
        //Gdx.app.debug("indexs2 length", Integer.toString(indexs2.size()));
        Gdx.app.debug("line of" + x, line);
        ArrayList<Integer> YArray = new ArrayList<Integer>();
        for (int y = 0; y < indexs2.size(); y++) {
            char type = line.charAt(indexs2.get(y));
            if (type == '0') {
                YArray.add(0);
            } else if (type == '1') {
                YArray.add(1);
            }
        }
        return YArray;
    }
}
