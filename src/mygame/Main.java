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
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        cam.setLocation(new Vector3f(-7,4,5));
        
        Vector3f initVec = new Vector3f(-8.68f,2.3f,0.38f);
        
        createGround(10, 1);
        //achChild(geom);
        Spatial room = assetManager.loadModel("Models/Scene/Scene.j3o");
        room.scale(0.2f);
        rootNode.attachChild(room);    /** A white ambient light source. */ 
        Spatial student = assetManager.loadModel("Models/Student/StudentBody.j3o");
        student.setLocalTranslation(initVec);
        student.scale(0.2f);
        Spatial student2 = student.clone();
        //student.move(-8.68f,2.3f,0.38f);
        student2.move(2.5f,0,0);
        Spatial student3 = student2.clone();
        student3.move(2.5f,0,0);
        rootNode.attachChild(student3); 
        rootNode.attachChild(student2); 
        rootNode.attachChild(student); 
            /** A white ambient light source. */ 
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
