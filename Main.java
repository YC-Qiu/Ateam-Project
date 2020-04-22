/**
 * Main.java created by Toma on personal PC in HelloFX
 *
 * Author: Tianyu(Toma) Chen(tchen346@wisc.edu) Date: 2020/2/6
 *
 * Course: Comp Sci 400 Semester: 2020 Spring Lecture: 001
 * 
 * IDE: Eclipse IDE for Java Developer Version: 2019-12
 *
 * Other Credits:
 */
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
  // store any command-line arguments that were entered.
  // NOTE: this.getParameters().getRaw() will get these also
  private List<String> args;

  private static final int WINDOW_WIDTH = 400;
  private static final int WINDOW_HEIGHT = 300;
  private static final String APP_TITLE = "Hello World!";

  @Override
  public void start(Stage primaryStage) throws Exception {
    // save args example
    args = this.getParameters().getRaw();
    total_percent();
    edit_info();
  }
  
  public void total_percent() {
    int width = 400;
    int height = 300;
    Stage window = new Stage();
    
    String farm_names[] = {"Tomato's","Tom's","Tom's","TaderJoe's"};
    int farm_percents[] = {25,25,25,25};
    VBox vbox = new VBox();
    int i =0;
    for (String arg : farm_names) {
      
      HBox hbox = new HBox();
      Label label = new Label(arg+" : "+farm_percents[i]+"%");
      label.setFont(Font.font("Times New Roman",30));
      hbox.getChildren().add(label);
      i++;
      vbox.getChildren().add(hbox);
    }
    BorderPane root = new BorderPane();
    
    Label totalPercent = new Label("Total Percent:");
    totalPercent.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
    root.setTop(totalPercent);
    root.setLeft(vbox);
    
    Scene mainScene = new Scene(root, width, height);

    // Add the stuff and set the primary stage
    window.setTitle("Milk Weight");
    window.setScene(mainScene);
    window.show();
  }
  public void edit_info() {
    int width = 400;
    int height = 300;
    Stage window = new Stage();
    String info[] = {"Year:", "Month:", "Day:", "Farm:", "Num:"};

    // Create a vertical box for each info

    TextField input[] = {new TextField(),new TextField(),new TextField(),new TextField(),new TextField()};
    VBox vbox = new VBox();
    int i =0;
    for (String arg : info) {
      
      HBox hbox = new HBox();
      Label label = new Label(arg);
      label.setFont(Font.font("Times New Roman",30));
      hbox.getChildren().add(label);
      hbox.getChildren().add(input[i]);
      i++;
      vbox.getChildren().add(hbox);
    }
    // Add a sumbit button and handle event
    Button submit = new Button("Submit");
    submit.setFont(Font.font("Times New Roman", 15));
    submit.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        try {
          PrintWriter outputFile = new PrintWriter("output.txt");
          for(TextField i : input) {
            outputFile.write(i.getText());
            outputFile.write('\n');
          }
          outputFile.close();
        } catch (FileNotFoundException e1) {
          e1.printStackTrace();
        }
        
      }
    });
    vbox.getChildren().add(submit);
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    // Add the vertical box to the center of the root pane
    Label editInfo = new Label("Edit Info:");
    editInfo.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
    root.setTop(editInfo);
    root.setLeft(vbox);
    root.setBottom(submit);
    
    Scene mainScene = new Scene(root, width, height);

    // Add the stuff and set the primary stage
    window.setTitle("Milk Weight");
    window.setScene(mainScene);
    window.show();
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
