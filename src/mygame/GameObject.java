/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.scene.Spatial;

/**
 *
 * @author martnath
 */
public class GameObject {
    
     private Spatial GameSpatial;
     private int objectstate = 0;   //State for Itemobjects 0=not displayed

    /**
     * @return the GameSpatial
     */
    public Spatial getGameSpatial() {
        return GameSpatial;
    }

    /**
     * @param GameSpatial the GameSpatial to set
     */
    public void setGameSpatial(Spatial GameSpatial) {
        this.GameSpatial = GameSpatial;
    }

    /**
     * @return the objectstate
     */
    public int getObjectstate() {
        return objectstate;
    }

    /**
     * @param objectstate the objectstate to set
     */
    public void setObjectstate(int objectstate) {
        this.objectstate = objectstate;
    }
    

 
   
}
