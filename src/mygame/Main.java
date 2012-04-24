package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import de.lessvoid.nifty.Nifty;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    
    private int size=21;
    
    public static void main(String[] args) {
        Main app = new Main();
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
        
        createGround(10, 1);
        //achChild(geom);
        Spatial room = assetManager.loadModel("Models/Scene/Scene.j3o");
        room.scale(0.2f);
        rootNode.attachChild(room);    /** A white ambient light source. */ 
        placeStudents(array);

        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White);
        rootNode.addLight(ambient); 
        
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(
    assetManager, inputManager, audioRenderer, guiViewPort);
/** Create a new NiftyGUI object */
Nifty nifty = niftyDisplay.getNifty();
/** Read your XML and initialize your custom ScreenController */
nifty.fromXml("gui/gui.xml", "hud");
// nifty.fromXml("Interface/helloworld.xml", "start", new MySettingsScreen(data));
// attach the Nifty display to the gui view port as a processor
guiViewPort.addProcessor(niftyDisplay);
// disable the fly cam
flyCam.setDragToRotate(true);
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
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
     * @created by Tobias Koenig
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

    
    private void createGround(int count,int blocksize){
        Node ground = new Node("ground");
        for(int x=0;x<count*blocksize*2;x+=blocksize*2){
            for(int z=0;z<count*blocksize*2;z+=blocksize*2){
                Box b = new Box(Vector3f.ZERO, blocksize, blocksize, blocksize);
                Geometry geom = new Geometry("Box"+x+z, b);
                Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                mat.setColor("Color", ColorRGBA.randomColor());
                geom.setMaterial(mat);
                geom.setLocalTranslation( new Vector3f(x+(x*0.1f), -10, z+(z*0.1f)));
                ground.attachChild(geom);
            }
        }
        rootNode.attachChild(ground);
    }
}
