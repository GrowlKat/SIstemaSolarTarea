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
    public Node mercurioNode = new Node("Mercurio");
    public Node venusNode = new Node("Venus");
    public Node marteNode = new Node("Marte");
    public Node jupiterNode = new Node("Jupiter");
    
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
        
        Sphere s = new Sphere(100, 100, 0.6f);
        Sphere s2 = new Sphere(10, 100, 10f);
        Sphere s3 = new Sphere(100, 100, 0.3f);
        Sphere s4 = new Sphere(100, 100, 0.5f);
        Sphere s5 = new Sphere(100, 100, 0.4f);
        Sphere s6 = new Sphere(100, 100, 4f);
        
        Geometry geom = new Geometry("Tierra", s);
        Geometry geom2 = new Geometry("Sol", s2);
        Geometry geom3 = new Geometry("Mercurio", s3);
        Geometry geom4 = new Geometry("Venus", s4);
        Geometry geom5 = new Geometry("Marte", s5);
        Geometry geom6 = new Geometry("Jupiter", s6);
        
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/planeta_tierra.jpg"));
        
        Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setTexture("ColorMap", assetManager.loadTexture("Textures/sol.jpg"));
        
        Material mat3 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat3.setTexture("ColorMap", assetManager.loadTexture("Textures/mercurio.jpg"));
        
        Material mat4 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat4.setTexture("ColorMap", assetManager.loadTexture("Textures/venus.jpg"));
        
        Material mat5 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat5.setTexture("ColorMap", assetManager.loadTexture("Textures/marte.jpg"));
        
        Material mat6 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat6.setTexture("ColorMap", assetManager.loadTexture("Textures/jupiter.jpg"));
        
        geom.setMaterial(mat);
        geom2.setMaterial(mat2);
        geom3.setMaterial(mat3);
        geom4.setMaterial(mat4);
        geom5.setMaterial(mat5);
        geom6.setMaterial(mat6);
        
        geom3.move(12, 0, 0);
        geom4.move(18, 0, 0);
        geom.move(24, 0, 0);
        geom5.move(30, 0, 0);
        geom6.move(40, 0, 0);
        
        tierraNode.attachChild(geom);
        mercurioNode.attachChild(geom3);
        venusNode.attachChild(geom4);
        marteNode.attachChild(geom5);
        jupiterNode.attachChild(geom6);
        
        solNode.attachChild(geom2);
        solNode.attachChild(tierraNode);
        solNode.attachChild(mercurioNode);
        solNode.attachChild(venusNode);
        solNode.attachChild(marteNode);
        solNode.attachChild(jupiterNode);
        
        geom.rotate(FastMath.PI * 1.5f, 0, 0);
        geom2.rotate(FastMath.PI * 1.5f, 0, 0);
        geom3.rotate(FastMath.PI * 1.5f, 0, 0);
        geom4.rotate(FastMath.PI * 1.5f, 0, 0);
        geom5.rotate(FastMath.PI * 1.5f, 0, 0);
        geom6.rotate(FastMath.PI * 1.5f, 0, 0);
        
        //Se cambia la ubicación de la cámara para dar la perspectiva que requiere
        cam.setLocation(new Vector3f(0, 40, 15));
        cam.setRotation(PITCH090);
        
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
            ob.rotate(0, tpf / 4, 0);
        }
        
        mercurioNode.rotate(0, tpf, 0);
        venusNode.rotate(0, tpf * 0.8f, 0);
        tierraNode.rotate(0, tpf * 0.6f, 0);
        marteNode.rotate(0, tpf / 2, 0);
        jupiterNode.rotate(0, tpf / 3, 0);
        
        mercurioNode.getChild("Mercurio").rotate(0, 0, tpf * 4);
        venusNode.getChild("Venus").rotate(0, 0, tpf);
        tierraNode.getChild("Tierra").rotate(0, 0, tpf * 3);
        marteNode.getChild("Marte").rotate(0, 0, tpf * 2);
        jupiterNode.getChild("Jupiter").rotate(0, 0, tpf * 6);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
