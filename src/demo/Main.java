/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 */
package demo;
import demo.model.User;
import demo.security.Authenticator;
import eshop.*;
import java.awt.Rectangle;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication1.*;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.paint.Color;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;
import keystrokemotion.*;

/**
 * Main Application. This class handles navigation and user session.
 */
public class Main extends Application {
    //keystrokemotion
    public LettersPane lettersPane;
    private Stage stage;
    private User loggedUser;
    private final double MINIMUM_WINDOW_WIDTH = 1200.0;
    private final double MINIMUM_WINDOW_HEIGHT = 700.0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[])null);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            stage.setTitle("FreakCart");
            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            gotoLogin();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getLoggedUser() {
        return loggedUser;
    }
        
    public boolean userLogging(String userId, String password) throws SQLException, ClassNotFoundException{
        if (!Authenticator.validate(userId, password)) {
            loggedUser = User.of(userId);
            gotoProfile();
            return true;
        } else {
            return false;
        }
    } 
    void userLogout(){
        loggedUser = null;
        gotoLogin();
    }
    
    private void gotoProfile() {
        try {
            ProfileController profile = (ProfileController) replaceSceneContent("profile.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("login.fxml");
            FadeTransition ft = new FadeTransition(Duration.millis(30000), login );
                ft.setFromValue(1.0);
                ft.setToValue(0.1);
                ft.setCycleCount(Timeline.INDEFINITE);
                ft.setAutoReverse(true);
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotoCart() {
        try {
            CartController login = (CartController) replaceSceneContent("FXML.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotoCategories() {
        try {
            CartController login = (CartController) replaceSceneContent2("FXML_1.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
      
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        StackPane root = new StackPane();
         //HBox root = new HBox();
        //NewClass ne = new NewClass();
        //SimpleSlideShowTest.SimpleSlideShow simpleSlideShow = new SimpleSlideShowTest.SimpleSlideShow();
    //Scene scene = new Scene(simpleSlideShow.getRoot());
        //SimpleSlideShowTest sl= new SimpleSlideShowTest();
        //welcome we=new welcome();
        //init();
        lettersPane = new LettersPane();
        //lettersPane.setMaxSize(480,480);
        //init();
        SimpleSlideShow sss = new SimpleSlideShow();
       sss.getRoot().setAlignment(sss.getRoot(),Pos.CENTER_RIGHT);
       //sss.getRoot().relocate(70,70);
        root.getChildren().addAll(page,sss.getRoot());
        Scene scene = new Scene(root, 1200, 700);   
        //Scene scene2 = new Scene(lettersPane, 480, 480);
        stage.setScene(scene);
        stage.sizeToScene();
        sss.start();
        return (Initializable) loader.getController();
    }
     private Initializable replaceSceneContent2(String fxml) throws Exception {
      
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        StackPane root = new StackPane();
         //HBox root = new HBox();
        //NewClass ne = new NewClass();
        //SimpleSlideShowTest.SimpleSlideShow simpleSlideShow = new SimpleSlideShowTest.SimpleSlideShow();
    //Scene scene = new Scene(simpleSlideShow.getRoot());
        //SimpleSlideShowTest sl= new SimpleSlideShowTest();
        //welcome we=new welcome();
        //init();
        lettersPane = new LettersPane();
        //lettersPane.setMaxSize(480,480);
        //init();
        SimpleSlideShow sss = new SimpleSlideShow();
       sss.getRoot().setAlignment(sss.getRoot(),Pos.CENTER_RIGHT);
       //sss.getRoot().relocate(70,70);
        root.getChildren().addAll(page,sss.getRoot());
        Scene scene = new Scene(root, 1355, 698);   
        //Scene scene2 = new Scene(lettersPane, 480, 480);
        stage.setScene(scene);
        stage.sizeToScene();
        sss.start();
        return (Initializable) loader.getController();
    }
    private Pane getOverlay() {
        StackPane p = new StackPane();
        javafx.scene.shape.Rectangle r = RectangleBuilder.create().height(100).width(100).arcHeight(40).arcWidth(40).stroke(Color.RED).fill(Color.web("red", 0.1)).build();

        Text txt=TextBuilder.create().text("Overlay")
                .font(Font.font("Arial", FontWeight.BOLD, 18))
                .fill(Color.BLUE)
                .build();
         p.getChildren().addAll(r);
         return p;
    }

    boolean userSigning(String userId, String password) throws SQLException, ClassNotFoundException {
         //To change body of generated methods, choose Tools | Templates.
        if (Authenticator.validate(userId, password)) {
            loggedUser = User.of(userId);
            //gotoProfile();
             //eshop.home ob = new eshop.home();
             //ob.user(s1);
             //ob.setVisible(true);
           gotoCart();
            return true;
        } else {
            return false;
        }
    }
    
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////
    
       

    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setResizable(false);
        
        //lettersPane = new LettersPane();
        //root.getChildren().add(lettersPane);
        primaryStage.setScene(new Scene(root, 480,480));
    }

    public void play() {
        // request focus so we get key events
        Platform.runLater(new Runnable() {
            @Override public void run() {
                lettersPane.requestFocus();
            }
        });
    }

        public static class LettersPane extends Region {
        private static final Font FONT_DEFAULT = new Font(Font.getDefault().getFamily(), 200);
        private static final Random RANDOM = new Random();
        private static final Interpolator INTERPOLATOR = Interpolator.SPLINE(0.295,0.800,0.305,1.000);
        private Text pressText;

        public LettersPane() {
            setId("LettersPane");
            setPrefSize(480,480);
            setFocusTraversable(true);
            setOnMousePressed(new EventHandler<MouseEvent>() {
                
                @Override public void handle(MouseEvent me) {
                    requestFocus();
                    me.consume();
                }
            });
            setOnKeyPressed(new EventHandler<KeyEvent>() {
                
                @Override public void handle(KeyEvent ke) {
                    createLetter(ke.getText());
                    ke.consume();
                }
            });
            // create press keys text
            pressText = new Text("");
            pressText.setTextOrigin(VPos.TOP);
            pressText.setFont(new Font(Font.getDefault().getFamily(), 40));
            pressText.setLayoutY(5);
            pressText.setFill(Color.rgb(80, 80, 80));
            DropShadow effect = new DropShadow();
            effect.setRadius(0);
            effect.setOffsetY(1);
            effect.setColor(Color.WHITE);
            pressText.setEffect(effect);
            getChildren().add(pressText);
        }

        @Override protected void layoutChildren() {
            // center press keys text
            pressText.setLayoutX((getWidth() - pressText.getLayoutBounds().getWidth()) / 2);
        }

        private void createLetter(String c) {
            final Text letter = new Text(c);
            letter.setFill(Color.BLACK);
            letter.setFont(FONT_DEFAULT);
            letter.setTextOrigin(VPos.TOP);
            letter.setTranslateX((getWidth() - letter.getBoundsInLocal().getWidth()) / 2);
            letter.setTranslateY((getHeight() - letter.getBoundsInLocal().getHeight()) / 2);
            getChildren().add(letter);
            // over 3 seconds move letter to random position and fade it out
            final Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
                        @Override public void handle(ActionEvent event) {
                            // we are done remove us from scene
                            getChildren().remove(letter);
                        }
                    },
                    new KeyValue(letter.translateXProperty(), getRandom(0.0f, getWidth() - letter.getBoundsInLocal().getWidth()),INTERPOLATOR),
                    new KeyValue(letter.translateYProperty(), getRandom(0.0f, getHeight() - letter.getBoundsInLocal().getHeight()),INTERPOLATOR),
                    new KeyValue(letter.opacityProperty(), 0f)
            ));
            timeline.play();
        }

        private static float getRandom(double min, double max) {
            return (float)(RANDOM.nextFloat() * (max - min) + min);
        }
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
      public class SimpleSlideShow {

    StackPane root = new StackPane();
    
    ImageView[] slides;

    public SimpleSlideShow() {
      root.setMaxSize(830,300);
       
      this.slides = new ImageView[4];
      Image image1 = new Image(SimpleSlideShowTest.class.getResource("animal1.jpg").toExternalForm());
      Image image2 = new Image(Main.class.getResource("animal2.jpg").toExternalForm());
      Image image3 = new Image(Main.class.getResource("animal3.jpg").toExternalForm());
      Image image4 = new Image(Main.class.getResource("animal4.jpg").toExternalForm());
      slides[0] = new ImageView(image1);
      slides[1] = new ImageView(image2);
      slides[2] = new ImageView(image3);
      slides[3] = new ImageView(image4);

    }

    public StackPane getRoot() {
      return root;
    }

    // The method I am running in my class

    public void start() {


      //for (ImageView slide : slides) {
   //   while(true){
        SequentialTransition slideshow = new SequentialTransition();
        for(int i=0;i<4;i++) {
        SequentialTransition sequentialTransition = new SequentialTransition();

        FadeTransition fadeIn = getFadeTransition(slides[i], 0.0, 1.0, 1000);
        PauseTransition stayOn = new PauseTransition(Duration.millis(2000));
        FadeTransition fadeOut = getFadeTransition(slides[i], 1.0, 0.0, 500);

        sequentialTransition.getChildren().addAll(fadeIn, stayOn, fadeOut);
        slides[i].setOpacity(0);
        this.root.getChildren().add(slides[i]);
        slideshow.getChildren().add(sequentialTransition);
      }
        slideshow.play();
       // root = new StackPane();
      //}
    }

// the method in the Transition helper class:

    public FadeTransition getFadeTransition(ImageView imageView, double fromValue, double toValue, int durationInMilliseconds) {

      FadeTransition ft = new FadeTransition(Duration.millis(durationInMilliseconds), imageView);
      ft.setFromValue(fromValue);
      ft.setToValue(toValue);

      return ft;

    }
  }

}
