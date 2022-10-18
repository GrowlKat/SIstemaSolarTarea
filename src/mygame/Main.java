package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public Spatial ob;
    public Node tierraNode = new Node("Tierra");
    public Node solNode = new Node("Sol");
    
    public static final Quaternion PITCH090 = new Quaternion().fromAngleAxis(
            FastMath.PI / 2,
            new Vector3f(1, 0, 0));
    
    public static void main(String[] args) {
        AppSettings settings = new AppSettings(true);
        settings.setTitle("Proyecto de Pruebas");
        
        //Se integra la imagen del menú de inicio
        settings.setSettingsDialogImage("UI/BP_Logo_500px_Black.png");
        
        //Modifica la resolución
        settings.setResolution(800, 600);
        
        Main app = new Main();
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(10f);
        
        Sphere s = new Sphere(10, 100, 0.3f);
        Sphere s2 = new Sphere(10, 100, 3f);
        
        Geometry geom = new Geometry("Tierra", s);
        Geometry geom2 = new Geometry("Sol", s2);
        
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        
        Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", ColorRGBA.Yellow);
        
        geom.setMaterial(mat);
        geom2.setMaterial(mat2);
        
        geom.move(4, 0, 0);
        
        tierraNode.attachChild(geom);
        solNode.attachChild(geom2);
        solNode.attachChild(tierraNode);
        
        solNode.rotate(0, 0, 45f);
        //solNode.rotate(new Quaternion(0, 0, FastMath.PI / 4, 1));
        geom.rotate(0, 0, -45f);
        geom2.rotate(0, 0, -45f);
        
        //Se cambia la ubicación de la cámara para dar la perspectiva que requiere
        cam.setLocation(new Vector3f(0, 40, 15));
        cam.setRotation(PITCH090);
        
        Node playerNode = new Node("PlayerNode");
        Node towerNode = new Node("TowerNode");
        Node creepNode = new Node("CreepNode");
        
        rootNode.attachChild(solNode);
    }

    @Override
    public void simpleUpdate(float tpf) {
        if(ob == null)
        {
            System.out.println("No está asignado un objeto");
            ob = rootNode.getChild("Sol");
        }
        else
        {
            ob.rotate(0, tpf, 0);
        }
        
        tierraNode.rotate(0, tpf, 0);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
