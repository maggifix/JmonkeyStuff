/*
 * Filename: alternativeGameMain.java
 * Author: Moritz Hader
 * Date of creation: 31.03.2012
 * Description: Main-class of the game that handles the simple initialization.
 */

package mygame;

import GameOfStud.Backbone.ApplicationDefinitionUnit;
import GameOfStud.Backbone.GlobalGameVariables;
import GameOfStud.Backbone.Input.InputManagementUnit;
import GameOfStud.Scene.SceneEnvironment;
import com.jme3.input.FlyByCamera;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.Light;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;

import com.jme3.light.Light.Type;


public class alternativeGameMain extends ApplicationDefinitionUnit{
    
    private GameActionHandler actionListener;
    //protected FreeCamera mFreeCamera;
    protected Camera cam;
    protected InputManagementUnit in;
    private SceneEnvironment sce;
    private boolean mQuit=false;
    public alternativeGameMain()
    {
        super();
    }
    
    @Override
    public void simpleInitialize(){
        
       
        
        this.sce = new SceneEnvironment(this.settings);
        
        //Load the main camera
        //in = this.Input;
        
        cam = new Camera(this.settings.getGameSettings().getWidth(), this.settings.getGameSettings().getHeight());
        cam.setFrustumPerspective(45f, (float)cam.getWidth() / cam.getHeight(), 1f, 1000f);
        cam.setLocation(new Vector3f(0f, 30f, 100f));
        cam.lookAt(new Vector3f(0f, 30f, 0f), Vector3f.UNIT_Y);
        
        CameraController camcontr = new CameraController(cam);
        camcontr.registerWithInput(this.Input.getInputManager());
        camcontr.setMoveSpeed(50);
        
        //Set up the main viewPort
        ViewPort vp = this.engine.getRenderM().createMainView("CUSTOM_VIEW", cam);
        vp.setClearFlags(true, true, true);
        vp.setBackgroundColor(ColorRGBA.White);
        this.scene.setViewport(vp);
        
        
        //Init the Classroom-scenerie
        this.sce.init(scene);
        this.scene.attachChild(this.sce.getScene().getGameSpatial());
        
        //load keys
        setupKeys();
        
        // Add a light Source
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White);
        
        this.sce.setLight(new Light[] {ambient});
        
        
        this.scene.getRoot().addLight(this.sce.getLight()[0]);
        
        //Set debub info on
        this.debugInfo.setToogleDisplay(true);
       // BBDebugInfo.getInstance().setDisplayFps(true);
        //BBDebugInfo.getInstance().setDisplayStatView(true);
        
        
        
    }
    
    
    public static void main(String[] args){
        alternativeGameMain game = new alternativeGameMain();
        game.SystemInitialization();
    }
    
    @Override
    public void simpleUpdate(float tpf){
        if(mQuit){
           this.engine.getContext().destroy(false);
        }
    }
    
    ///public FreeCamera getFreeCamera(){
        //return mFreeCamera;
    //}
    
    private void setupKeys(){
                //Set up keys and listener to read it
        
        
        actionListener = new GameActionHandler(null,null);
        

        // both mouse and button - rotation of cam
//        this.Input.getInputManager().addMapping("FLYCAM_Left", new MouseAxisTrigger(MouseInput.AXIS_X, true),
//                                               new KeyTrigger(KeyInput.KEY_LEFT));
//
//         this.Input.getInputManager().addMapping("FLYCAM_Right", new MouseAxisTrigger(MouseInput.AXIS_X, false),
//                                                new KeyTrigger(KeyInput.KEY_RIGHT));
//
////         this.Input.getInputManager().addMapping("FLYCAM_Up", new MouseAxisTrigger(MouseInput.AXIS_Y, false),
////                                             new KeyTrigger(KeyInput.KEY_UP));
////
////         this.Input.getInputManager().addMapping("FLYCAM_Down", new MouseAxisTrigger(MouseInput.AXIS_Y, true),
////                                               new KeyTrigger(KeyInput.KEY_DOWN));
//
//        // mouse only - zoom in/out with wheel, and rotate drag
//         this.Input.getInputManager().addMapping("FLYCAM_ZoomIn", new MouseAxisTrigger(MouseInput.AXIS_WHEEL, false));
//         this.Input.getInputManager().addMapping("FLYCAM_ZoomOut", new MouseAxisTrigger(MouseInput.AXIS_WHEEL, true));
//         this.Input.getInputManager().addMapping("FLYCAM_RotateDrag", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
//
//        // keyboard only WASD for movement and WZ for rise/lower height
//         this.Input.getInputManager().addMapping("FLYCAM_StrafeLeft", new KeyTrigger(KeyInput.KEY_A));
//         this.Input.getInputManager().addMapping("FLYCAM_StrafeRight", new KeyTrigger(KeyInput.KEY_D));
//        // this.Input.getInputManager().addMapping("FLYCAM_Forward", new KeyTrigger(KeyInput.KEY_Q));
//        // this.Input.getInputManager().addMapping("FLYCAM_Backward", new KeyTrigger(KeyInput.KEY_Z));
//        // this.Input.getInputManager().addMapping("FLYCAM_Rise", new KeyTrigger(KeyInput.KEY_W));
//        // this.Input.getInputManager().addMapping("FLYCAM_Lower", new KeyTrigger(KeyInput.KEY_S));
        
         this.Input.getInputManager().addMapping(GlobalGameVariables.INPUT_MAPPING_EXIT, new KeyTrigger(KeyInput.KEY_ESCAPE));        
         //this.Input.getInputManager().addListener(actionListener, GlobalGameVariables.INPUT_MAPPING_EXIT);
         //this.Input.getInputManager().addListener(actionListener, mappings);
        
        this.Input.getInputManager().addListener(new ActionListener() {

            public void onAction(String name, boolean isPressed, float tpf) { 
                if (name.equals(GlobalGameVariables.INPUT_MAPPING_EXIT))
                mQuit=true;
            }
        }, GlobalGameVariables.INPUT_MAPPING_EXIT);
         //this.Input.setMouseCenter();
         //this.Input.getInputManager().setCursorVisible(true);
    }
    
     
}
