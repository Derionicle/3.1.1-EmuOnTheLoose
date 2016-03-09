package org.pltw.examples.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import org.pltw.examples.util.Constants;

/**
 * Created by wdumas on 10/14/2015.
 */
public class WorldModel {
    int row =0;
    public Sprite[][] sampleMap =
            new Sprite[Constants.SAMPLE_MAP_HEIGHT][Constants.SAMPLE_MAP_WIDTH];
    private float tempSpriteX, tempSpriteY;
    private Sprite tempSprite;

    public WorldModel() {
        initSampleMap();
    }

    private void initSampleMap() {
        for (int i = 0; i < Constants.SAMPLE_MAP_HEIGHT; i++) { //for each row in the map

            for (int j = 0; j < Constants.SAMPLE_MAP_WIDTH; j++){ // for each column in the map
                if (i != j || i != 0) {
                    tempSpriteX = 0-i*Constants.X_PADDING_TO_NEXT_TILE +
                            j*Constants.X_PADDING_TO_NEXT_TILE;
                    // Case: i or j indicate a wall sprite (value of 0)
                    if (i == Constants.WALL_ROW || j == Constants.WALL_COL) {
                        setupWallSprite(i, j);
                        tempSprite.setSize(Constants.TILE_WIDTH, Constants.TILE_HEIGHT);
                        tempSpriteY = -0.0f-i*Constants.Y_PADDING_TO_NEXT_TILE -
                                j*Constants.Y_PADDING_TO_NEXT_TILE;

                    }
                    // Case: i and j are both non-0 indicates a floor tile sprite
                    else {
                        if(j==1){
                            pattern(i);
                        }

                        /* ToDo add conditionals, methods, and method calls like the one below
                        per the instructions in Part IV of Activity 3.1.1 */
                        /*if((i+j)%2 == 0){
                            blackFloorTile();
                        } else{
                            blueFloorTile();
                        }*/


                    }


                }
            }
        }
    }
    private void pattern(int start){
        if(start%3 ==0){
            row = 0;
            blackFloorTile();
            spriteManagement(start);
            blueFloorTile();
            spriteManagement(start);
            woodFloorTile();
            spriteManagement(start);
            blackFloorTile();
            spriteManagement(start);
        }else if(start%3 == 1){
            row = 0;
            blueFloorTile();
            spriteManagement(start);
            woodFloorTile();
            spriteManagement(start);
            blackFloorTile();
            spriteManagement(start);
            blueFloorTile();
            spriteManagement(start);
        }else{
            row = 0;
            woodFloorTile();
            spriteManagement(start);
            blackFloorTile();
            spriteManagement(start);
            blueFloorTile();
            spriteManagement(start);
            woodFloorTile();
            spriteManagement(start);
        }
    }
    private void spriteManagement(int column){
        tempSpriteX = 0-column*Constants.X_PADDING_TO_NEXT_TILE +
                row*Constants.X_PADDING_TO_NEXT_TILE;
        tempSprite.setSize(Constants.TILE_WIDTH, Constants.TILE_HEIGHT);
        tempSpriteY = -0.0f-column*Constants.Y_PADDING_TO_NEXT_TILE - row*Constants.Y_PADDING_TO_NEXT_TILE;
        tempSprite.setPosition(tempSpriteX,tempSpriteY);
        sampleMap[column][row] = tempSprite;
        row++;
    }

    private void woodFloorTile() {
        tempSprite = new Sprite(Assets.instance.roomTiles.tileWood);
    }

    private void blueFloorTile() {
        tempSprite = new Sprite(Assets.instance.roomTiles.tileBlue);
    }

    private void setupWallSprite(int i, int j){
            tempSprite = new Sprite(Assets.instance.roomTiles.wall1Blank);

            // Right side wall sprites must be flipped
            if (i == Constants.WALL_ROW) {
                tempSprite.flip(true, false); // flip along the Y axis
                tempSpriteY = - j*Constants.Y_PADDING_TO_NEXT_TILE;
            }
            else {
                tempSpriteX = tempSpriteX + Constants.WALL_TILE_WIDTH;
                tempSpriteY = -i*Constants.Y_PADDING_TO_NEXT_TILE +
                        j*Constants.Y_PADDING_TO_NEXT_TILE;
            }

            tempSprite.setSize(Constants.WALL_TILE_WIDTH, Constants.WALL_TILE_HEIGHT);
    }
    private void blackFloorTile() {
        tempSprite = new Sprite(Assets.instance.roomTiles.tileBlack);
    }


}
