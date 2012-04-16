package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.Node;

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
        createGround(10, 1);
        //achChild(geom);
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
