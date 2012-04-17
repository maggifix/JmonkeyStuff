package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 * test
 * @author normenhansen
 */
public class TestPlacing extends SimpleApplication {
    
    private int size=21;
    
    public static void main(String[] args) {
        TestPlacing app = new TestPlacing();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(50);
        
        GameObject[] array= new GameObject[size];
        for(int y=0;y<size;y++){
            array[y]= new GameObject();
            array[y].setGameSpatial(assetManager.loadModel("Models/Student/StudentBody.j3o"));
        }

        Spatial room = assetManager.loadModel("Models/Scene/Scene.j3o");
        room.scale(0.2f);
        rootNode.attachChild(room);    /** A white ambient light source. */ 
        placeStudents(array);
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White);
        rootNode.addLight(ambient); 
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }
    
    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    /**
     * places up to 36 students in the scenery
     * @createdby Tobias Koenig
     * @param gobjs array of GameObjects
     */
    public void placeStudents(GameObject[] gobjs){
        //initial vectors for each row
        Vector3f initVec1 = new Vector3f(-8.68f,2.3f,0.38f);
        Vector3f initVec2 = initVec1.add(9.6153f/5-0.6f, 5.1925f/5, -14.64f/5);
        Vector3f initVec3 = initVec1.add(0, 5.1925f/5*2+0.1f, -14.64f/5*2+0.23f);
        Vector3f initVec4 = initVec2.add(-0.1f, 5.1925f/5*2+0.1f, -14.64f/5*2+0.25f);
        float sideOffset = 0;
        
        for(int x=0;x<gobjs.length;x++){
            //first row
            if(x>=0 && x <=8){
                fillRow(gobjs[x].getGameSpatial(),initVec1,sideOffset);
                sideOffset+=2.48f;
            }
            //second row
            if(x>=9 && x<=17){
                if(x==9) sideOffset=0;
                fillRow(gobjs[x].getGameSpatial(),initVec2,sideOffset);
                sideOffset+=2.48f;
            }
            //third row
            if(x>=18 && x<=26){
                if(x==18) sideOffset=0;
                fillRow(gobjs[x].getGameSpatial(),initVec3,sideOffset);
                sideOffset+=2.48f;
            }
            //fourth row
            if(x>=27 && x<=35){
                if(x==27) sideOffset=0;
                fillRow(gobjs[x].getGameSpatial(),initVec4,sideOffset);
                sideOffset+=2.48f;
            }
        }
    }
    
    /**
     * fills a row up with students depending on the iniial vector and the current offset.
     * @createdby Tobias Koenig
     * @param spat Spatial object to set the LocalTranslation
     * @param initVec initial vector to determine position
     * @param sideOffset current offset in row
     */
    private void fillRow(Spatial spat,Vector3f initVec, float sideOffset){
        spat.setLocalTranslation(initVec);
        spat.move(sideOffset, 0, 0);
        spat.scale(0.2f);
        rootNode.attachChild(spat);
    }
}