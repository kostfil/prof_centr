package javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
public class Main extends Application {
    @Override
    public void start(Stage outputStageObject) {
        outputStageObject.setTitle("Sphere");
        Sphere sphereObject = new Sphere(50);
        Group groupObject = new Group();
        groupObject.getChildren().add(sphereObject);
        Camera perspectiveCamera = new PerspectiveCamera();
        Scene sceneObject = new Scene(groupObject, 500, 500);
        sceneObject.setFill(Color.GREEN);
        sceneObject.setCamera(perspectiveCamera);
        sphereObject.translateXProperty().set(500 / 2);
        sphereObject.translateYProperty().set(500 / 2);
        outputStageObject.addEventHandler(KeyEvent.KEY_PRESSED, eventHandler -> {
            switch (eventHandler.getCode()) {
                case A:
                    sphereObject.translateZProperty().set(sphereObject.getTranslateZ() + 300);
                    break;
                case B:
                    sphereObject.translateZProperty().set(sphereObject.getTranslateZ() - 300);
                    break;
            }
        });
        outputStageObject.setScene(sceneObject);
        outputStageObject.show();
    }
    public static void main(String[] args) {

        launch(args);
    }
}