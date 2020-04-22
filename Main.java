package application;

import java.io.File;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.scene.control.ScrollPane;
import javafx.event.EventHandler;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.stage.Modality;
import javafx.scene.control.TextField;
import java.util.HashMap;

/**
 * 
 * @author Boyuan Zou
 *
 */

public class Main extends Application {
	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;

	private static final int WINDOW_WIDTH = 900;
	private static final int WINDOW_HEIGHT = 450;
	private static final String APP_TITLE = "Milk Weight";

	Label label = new Label();

	/**
	 * @author Boyuan Zou
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();

		// top
		VBox vb_top = new VBox();
		HBox hb_title = new HBox();
		Label title = new Label(APP_TITLE);
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
		title.setTextFill(Color.web("#464646"));
		hb_title.getChildren().add(title);
		hb_title.setPadding(new Insets(5, 100, 5, 335));
		vb_top.getChildren().add(hb_title);

		Button buttonAddFile = new Button("Add Info from Files...");
		buttonAddFile.setPadding(new Insets(0, 20, 0, 20));// up, right, down, left
		buttonAddFile.setFont(Font.font("Times New Roman", 20));
		buttonAddFile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				addFile();
			}
		});

		Button buttonReport = new Button("Report");
		buttonReport.setPadding(new Insets(0, 20, 0, 20));
		buttonReport.setFont(Font.font("Times New Roman", 20));
		buttonReport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				report();
			}
		});

		HBox hb_topbut1 = new HBox();
		hb_topbut1.getChildren().addAll(buttonAddFile);
		hb_topbut1.setPadding(new Insets(0, 260, 0, 30));
		HBox hb_topbut2 = new HBox();
		hb_topbut2.getChildren().addAll(buttonReport);
		hb_topbut2.setPadding(new Insets(0, 30, 0, 260));
		HBox all_hb_topbut = new HBox();
		all_hb_topbut.getChildren().addAll(hb_topbut1, hb_topbut2);
		vb_top.getChildren().addAll(all_hb_topbut);

		root.setTop(vb_top);

		// left
		Button buttonEditInfo = new Button("Edit Info");
		buttonEditInfo.setPadding(new Insets(100, 100, 100, 100));
		buttonEditInfo.setFont(Font.font("Times New Roman", 50));
		HBox hb_left = new HBox();
		hb_left.getChildren().add(buttonEditInfo);
		hb_left.setPadding(new Insets(20, 20, 0, 20));
		root.setLeft(hb_left);

		// right
		Button buttonTotalPercent = new Button("Total Percent");
		buttonTotalPercent.setPadding(new Insets(100, 55, 100, 55));
		buttonTotalPercent.setFont(Font.font("Times New Roman", 50));
		HBox hb_right = new HBox();
		hb_right.getChildren().add(buttonTotalPercent);
		hb_right.setPadding(new Insets(20, 20, 0, 20));
		root.setRight(hb_right);

		// bottom
		Button buttonClose = new Button("Close");
		buttonClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				primaryStage.close();
			}
		});

		HBox hb = new HBox();
		hb.setPadding(new Insets(10, 10, 10, 820));
		hb.setSpacing(10);
		hb.getChildren().addAll(buttonClose);

		root.setBottom(hb);

		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		// Add the stuff and set the primary stage
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	/**
	 * addFile
	 * 
	 * @author Boyuan Zou
	 */
	void addFile() {
		int width = 600;
		int height = 125;
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		BorderPane root = new BorderPane();

		// top
		HBox hb_title = new HBox();
		Label title = new Label("Add info from file");
		title.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
		title.setTextFill(Color.web("#464646"));
		hb_title.getChildren().add(title);
		hb_title.setPadding(new Insets(20, 0, 0, 100));
		root.setTop(hb_title);

		// center
		HBox hb_center = new HBox();
		Label l = new Label("");
		Button buttonChooseFile = new Button("ChooseFile");
		buttonChooseFile.setPadding(new Insets(0, 20, 0, 20));// up, right, down, left
		buttonChooseFile.setFont(Font.font("Times New Roman", 15));
		buttonChooseFile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Resource File");
				File file = fileChooser.showOpenDialog(window);
				l.setText(file.getName());
			}
		});
		hb_center.setPadding(new Insets(0, 0, 0, 400));// up, right, down, left
		hb_center.getChildren().addAll(l, buttonChooseFile);
		root.setCenter(hb_center);

		// bottom
		Button buttonClose = new Button("Close");
		buttonClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				window.close();
			}
		});
		HBox hb_bot = new HBox();
		hb_bot.setPadding(new Insets(10, 10, 10, 540));
		hb_bot.setSpacing(10);
		hb_bot.getChildren().addAll(buttonClose);
		root.setBottom(hb_bot);

		Scene scene = new Scene(root, width, height);
		window.setTitle("Add info from file");
		window.setScene(scene);
		window.show();
	}

	/**
	 * @author Boyuan Zou
	 */
	void report() {
		int width = 610;
		int height = 630;
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		BorderPane root = new BorderPane();

		// top
		VBox vb_top = new VBox();
		HBox hb_title = new HBox();
		Label title = new Label("Report");
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
		title.setTextFill(Color.web("#464646"));
		hb_title.getChildren().add(title);
		hb_title.setPadding(new Insets(5, 220, 5, 220));
		vb_top.getChildren().add(hb_title);
		root.setTop(vb_top);

		// center
		Button buttonFarm = new Button("Farm");
		buttonFarm.setPadding(new Insets(103, 112, 103, 112));
		buttonFarm.setFont(Font.font("Times New Roman", 30));
		HBox hb_Farm = new HBox();
		hb_Farm.getChildren().add(buttonFarm);
		hb_Farm.setPadding(new Insets(20, 10, 0, 10));// up, right, down, left
		buttonFarm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				FarmReport();
			}
		});

		Button buttonAnnual = new Button("Annual");
		buttonAnnual.setPadding(new Insets(100, 100, 100, 100));
		buttonAnnual.setFont(Font.font("Times New Roman", 30));
		HBox hb_Annual = new HBox();
		hb_Annual.getChildren().add(buttonAnnual);
		hb_Annual.setPadding(new Insets(20, 10, 0, 10));// up, right, down, left
		buttonAnnual.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				AnnualReport();
			}
		});

		Button buttonMonth = new Button("Month");
		buttonMonth.setPadding(new Insets(102, 100, 102, 100));
		buttonMonth.setFont(Font.font("Times New Roman", 30));
		HBox hb_Month = new HBox();
		hb_Month.getChildren().add(buttonMonth);
		hb_Month.setPadding(new Insets(20, 10, 0, 10));// up, right, down, left
		buttonMonth.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				MonthReport();
			}
		});

		Button buttonDateRangeReport = new Button("Date\nRange\nReport");
		buttonDateRangeReport.setPadding(new Insets(66, 100, 66, 100));
		buttonDateRangeReport.setFont(Font.font("Times New Roman", 30));
		HBox hb_DateRangeReport = new HBox();
		hb_DateRangeReport.getChildren().add(buttonDateRangeReport);
		hb_DateRangeReport.setPadding(new Insets(20, 10, 0, 10));// up, right, down, left
		buttonDateRangeReport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				DRReport();
			}
		});

		HBox hb_Center = new HBox();
		VBox vb1 = new VBox();
		vb1.getChildren().addAll(hb_Farm, hb_Annual);
		VBox vb2 = new VBox();
		vb2.getChildren().addAll(hb_Month, hb_DateRangeReport);
		hb_Center.getChildren().addAll(vb1, vb2);
		root.setCenter(hb_Center);

		// bottom
		Button buttonClose = new Button("Close");
		buttonClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				window.close();
			}
		});

		HBox hb = new HBox();
		hb.setPadding(new Insets(10, 10, 10, 550));
		hb.setSpacing(10);
		hb.getChildren().addAll(buttonClose);

		root.setBottom(hb);
		Scene scene = new Scene(root, width, height);
		window.setTitle("Report");
		window.setScene(scene);
		window.show();
	}

	void FarmReport() {
		int width = 610;
		int height = 500;
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		ScrollPane root = new ScrollPane();
		VBox vb = new VBox();
		HBox hb_title = new HBox();
		Label title = new Label("Farm Report");
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
		title.setTextFill(Color.web("#464646"));
		hb_title.getChildren().add(title);
		hb_title.setPadding(new Insets(5, 5, 5, 160));
		vb.getChildren().add(hb_title);

		Button buttonAnnual = new Button("Switch to annual report");
		buttonAnnual.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				window.close();
				AnnualReport();
			}
		});

		HBox button1 = new HBox();
		button1.setPadding(new Insets(10, 10, 10, 435));
		button1.setSpacing(10);
		button1.getChildren().addAll(buttonAnnual);
		vb.getChildren().add(button1);

		HBox input = new HBox(35);

		String[] var = { "Farm ID", "Year" };
		TextField[] tf = { new TextField(), new TextField() };

		for (int i = 0; i < var.length; i++) {
			HBox hbox = new HBox(10);
			Label l = new Label(var[i]);
			l.setFont(Font.font("Times New Roman", 30));
			hbox.getChildren().add(l);
			hbox.getChildren().add(tf[i]);
			input.getChildren().add(hbox);
		}
		input.setPadding(new Insets(0, 0, 0, 10));
		vb.getChildren().add(input);

		HBox production = new HBox(35);
		String[] var1 = { "Total Amount", "Percentage of year" };
		double[] result = { 10, 4 };

		for (int i = 0; i < var1.length; i++) {
			HBox hbox = new HBox(10);
			Label l = new Label(var1[i] + ": " + result[i]);
			l.setFont(Font.font("Times New Roman", 30));
			hbox.getChildren().add(l);
			production.getChildren().add(hbox);
		}
		production.setPadding(new Insets(20, 0, 0, 10));
		vb.getChildren().add(production);

		VBox month = new VBox(35);

		String[] monthName = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		HashMap<Integer, double[]> data = new HashMap<Integer, double[]>();
		data.put(1, new double[] { 0.8, 0.8 / result[0] });
		data.put(2, new double[] { 0.8, 0.8 / result[0] });
		data.put(3, new double[] { 0.8, 0.8 / result[0] });
		data.put(4, new double[] { 0.8, 0.8 / result[0] });
		data.put(5, new double[] { 0.8, 0.8 / result[0] });
		data.put(6, new double[] { 0.8, 0.8 / result[0] });
		data.put(7, new double[] { 0.8, 0.8 / result[0] });
		data.put(8, new double[] { 0.8, 0.8 / result[0] });
		data.put(9, new double[] { 0.8, 0.8 / result[0] });
		data.put(10, new double[] { 0.8, 0.8 / result[0] });
		data.put(11, new double[] { 2, 2 / result[0] });
		data.put(12, new double[] { 2, 2 / result[0] });

		for (int j = 0; j < 12; j++) {
			HBox info = new HBox(35);

			HBox hbox = new HBox(10);
			Label l = new Label(monthName[j] + ": " + data.get(j + 1)[0] + " tons        ");
			l.setFont(Font.font("Times New Roman", 30));
			Label l2 = new Label(data.get(j + 1)[1] * 100 + "% of entire year");
			l2.setFont(Font.font("Times New Roman", 30));
			hbox.getChildren().add(l);
			hbox.getChildren().add(l2);
			info.getChildren().add(hbox);

			month.getChildren().add(info);
		}

		month.setPadding(new Insets(20, 0, 0, 10));
		vb.getChildren().add(month);

		// bottom
		Button buttonClose = new Button("Close");
		buttonClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				window.close();
			}
		});

		HBox hb = new HBox();
		hb.setPadding(new Insets(10, 10, 10, 550));
		hb.setSpacing(10);
		hb.getChildren().addAll(buttonClose);

		vb.getChildren().add(hb);
		root.setContent(vb);
		Scene scene = new Scene(root, width, height);
		window.setTitle("Farm Report");
		window.setScene(scene);
		window.show();
	}

	void AnnualReport() {
		int width = 610;
		int height = 500;
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		ScrollPane root = new ScrollPane();
		VBox vb = new VBox();
		HBox hb_title = new HBox();
		Label title = new Label("Annual Report");
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
		title.setTextFill(Color.web("#464646"));
		hb_title.getChildren().add(title);
		hb_title.setPadding(new Insets(5, 5, 5, 160));
		vb.getChildren().add(hb_title);

		Button buttonFarm = new Button("Switch to Farm report");
		buttonFarm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				window.close();
				FarmReport();
			}
		});

		HBox button1 = new HBox();
		button1.setPadding(new Insets(10, 10, 10, 10));
		button1.setSpacing(10);
		button1.getChildren().addAll(buttonFarm);
		vb.getChildren().add(button1);

		HBox input = new HBox(35);

		String var = "Year";
		TextField tf = new TextField();

		HBox hboxA = new HBox(10);
		Label l = new Label(var);
		l.setFont(Font.font("Times New Roman", 30));
		hboxA.getChildren().add(l);
		hboxA.getChildren().add(tf);
		input.getChildren().add(hboxA);

		input.setPadding(new Insets(0, 0, 0, 10));
		vb.getChildren().add(input);

		HBox production = new HBox(35);
		String var1 = "Total Amount";
		double result = 10;

		for (int i = 0; i < 1; i++) {
			HBox hbox = new HBox(10);
			Label lA = new Label(var1 + ": " + result);
			lA.setFont(Font.font("Times New Roman", 30));
			hbox.getChildren().add(lA);
			production.getChildren().add(hbox);
		}
		production.setPadding(new Insets(20, 0, 0, 10));
		vb.getChildren().add(production);

		VBox farms = new VBox(35);

		int farmNum = 5;
		HashMap<Integer, double[]> dataA = new HashMap<Integer, double[]>();
		dataA.put(1, new double[] { 1, 1 / result });
		dataA.put(2, new double[] { 2, 2 / result });
		dataA.put(3, new double[] { 3, 3 / result });
		dataA.put(4, new double[] { 4, 4 / result });
		dataA.put(5, new double[] { 0, 0 / result });

		for (int j = 0; j < farmNum; j++) {
			HBox info = new HBox(35);

			HBox hbox = new HBox(10);
			Label lA2 = new Label("Farm" + (j + 1) + ": " + dataA.get(j + 1)[0] + " tons        ");
			lA2.setFont(Font.font("Times New Roman", 30));
			Label lA3 = new Label(dataA.get(j + 1)[1] * 100 + "% of all farms");
			lA3.setFont(Font.font("Times New Roman", 30));
			hbox.getChildren().add(lA2);
			hbox.getChildren().add(lA3);
			info.getChildren().add(hbox);

			farms.getChildren().add(info);
		}

		farms.setPadding(new Insets(20, 0, 0, 10));
		vb.getChildren().add(farms);

		// bottom
		Button buttonClose = new Button("Close");
		buttonClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				window.close();
			}
		});

		HBox hb = new HBox();
		hb.setPadding(new Insets(10, 10, 10, 550));
		hb.setSpacing(10);
		hb.getChildren().addAll(buttonClose);

		vb.getChildren().add(hb);
		root.setContent(vb);
		Scene scene = new Scene(root, width, height);
		window.setTitle("Farm Report");
		window.setScene(scene);
		window.show();
	}

	void MonthReport() {
		int width = 610;
		int height = 500;
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		ScrollPane root = new ScrollPane();
		VBox vb = new VBox();
		HBox hb_title = new HBox();
		Label title = new Label("Month Report");
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
		title.setTextFill(Color.web("#464646"));
		hb_title.getChildren().add(title);
		hb_title.setPadding(new Insets(5, 5, 5, 160));
		vb.getChildren().add(hb_title);

//		Button buttonFarm = new Button("Switch to Farm report");
//		buttonFarm.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent e) {
//				window.close();
//				FarmReport();
//			}
//		});

		HBox button1 = new HBox();
		button1.setPadding(new Insets(10, 10, 10, 10));
		button1.setSpacing(10);
//		button1.getChildren().addAll(buttonFarm);
		vb.getChildren().add(button1);

		HBox input = new HBox(35);

		String var = "Month";
		ComboBox cb = new ComboBox();
		
		for(int i = 0; i< 12; i++) {
			cb.getItems().add(i+1);
		}

		HBox hboxA = new HBox(10);
		Label l = new Label(var);
		l.setFont(Font.font("Times New Roman", 30));
		hboxA.getChildren().add(l);
		hboxA.getChildren().add(cb);
		input.getChildren().add(hboxA);

		input.setPadding(new Insets(0, 0, 0, 10));
		vb.getChildren().add(input);

		HBox production = new HBox(35);
		String var1 = "Total Amount";
		double result = 10;

		for (int i = 0; i < 1; i++) {
			HBox hbox = new HBox(10);
			Label lA = new Label(var1 + ": " + result);
			lA.setFont(Font.font("Times New Roman", 30));
			hbox.getChildren().add(lA);
			production.getChildren().add(hbox);
		}
		production.setPadding(new Insets(20, 0, 0, 10));
		vb.getChildren().add(production);

		VBox farms = new VBox(35);

		int farmNum = 5;
		HashMap<Integer, double[]> dataA = new HashMap<Integer, double[]>();
		dataA.put(1, new double[] { 1, 1 / result });
		dataA.put(2, new double[] { 2, 2 / result });
		dataA.put(3, new double[] { 3, 3 / result });
		dataA.put(4, new double[] { 4, 4 / result });
		dataA.put(5, new double[] { 0, 0 / result });

		for (int j = 0; j < farmNum; j++) {
			HBox info = new HBox(35);

			HBox hbox = new HBox(10);
			Label lA2 = new Label("Farm" + (j + 1) + ": " + dataA.get(j + 1)[0] + " tons        ");
			lA2.setFont(Font.font("Times New Roman", 30));
			Label lA3 = new Label(dataA.get(j + 1)[1] * 100 + "% of all farms");
			lA3.setFont(Font.font("Times New Roman", 30));
			hbox.getChildren().add(lA2);
			hbox.getChildren().add(lA3);
			info.getChildren().add(hbox);

			farms.getChildren().add(info);
		}

		farms.setPadding(new Insets(20, 0, 0, 10));
		vb.getChildren().add(farms);

		// bottom
		Button buttonClose = new Button("Close");
		buttonClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				window.close();
			}
		});

		HBox hb = new HBox();
		hb.setPadding(new Insets(10, 10, 10, 550));
		hb.setSpacing(10);
		hb.getChildren().addAll(buttonClose);

		vb.getChildren().add(hb);
		root.setContent(vb);
		Scene scene = new Scene(root, width, height);
		window.setTitle("Farm Report");
		window.setScene(scene);
		window.show();

	}

	void DRReport() {
		int width = 610;
		int height = 500;
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		ScrollPane root = new ScrollPane();
		VBox vb = new VBox();
		HBox hb_title = new HBox();
		Label title = new Label("Date Range Report");
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
		title.setTextFill(Color.web("#464646"));
		hb_title.getChildren().add(title);
		hb_title.setPadding(new Insets(5, 5, 5, 160));
		vb.getChildren().add(hb_title);

//		Button buttonFarm = new Button("Switch to Farm report");
//		buttonFarm.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent e) {
//				window.close();
//				FarmReport();
//			}
//		});

		HBox button1 = new HBox();
		button1.setPadding(new Insets(10, 10, 10, 10));
		button1.setSpacing(10);
//		button1.getChildren().addAll(buttonFarm);
		vb.getChildren().add(button1);

		HBox input = new HBox(35);

		String var_start = "Start Date";
		TextField tf_start = new TextField();
		
		String var_end = "End Date";
		TextField tf_end = new TextField();

		HBox hboxA = new HBox(10);
		Label lstart = new Label(var_start);
		lstart.setFont(Font.font("Times New Roman", 30));
		hboxA.getChildren().add(lstart);
		hboxA.getChildren().add(tf_start);
		input.getChildren().add(hboxA);

		HBox hboxB = new HBox(10);
		Label lend = new Label(var_end);
		lend.setFont(Font.font("Times New Roman", 30));
		hboxB.getChildren().add(lend);
		hboxB.getChildren().add(tf_end);
		input.getChildren().add(hboxB);
		
		
		
		input.setPadding(new Insets(0, 0, 0, 10));
		vb.getChildren().add(input);
		
		HBox production = new HBox(35);
		String var1 = "Total Amount";
		double result = 10;
		
		

		for (int i = 0; i < 1; i++) {
			HBox hbox = new HBox(10);
			Label lA = new Label(var1 + ": " + result);
			lA.setFont(Font.font("Times New Roman", 30));
			hbox.getChildren().add(lA);
			production.getChildren().add(hbox);
		}
		
		HBox checkButtonBox = new HBox(10);
		Button checkButton = new Button("Check");
		checkButtonBox.setPadding(new Insets(0,0,0,200));
		checkButtonBox.getChildren().add(checkButton);
		production.getChildren().add(checkButtonBox);
		
		production.setPadding(new Insets(20, 0, 0, 10));
		vb.getChildren().add(production);

		VBox farms = new VBox(35);

		int farmNum = 5;
		HashMap<Integer, double[]> dataA = new HashMap<Integer, double[]>();
		dataA.put(1, new double[] { 1, 1 / result });
		dataA.put(2, new double[] { 2, 2 / result });
		dataA.put(3, new double[] { 3, 3 / result });
		dataA.put(4, new double[] { 4, 4 / result });
		dataA.put(5, new double[] { 0, 0 / result });

		for (int j = 0; j < farmNum; j++) {
			HBox info = new HBox(35);

			HBox hbox = new HBox(10);
			Label lA2 = new Label("Farm" + (j + 1) + ": " + dataA.get(j + 1)[0] + " tons        ");
			lA2.setFont(Font.font("Times New Roman", 30));
			Label lA3 = new Label(dataA.get(j + 1)[1] * 100 + "% of all farms");
			lA3.setFont(Font.font("Times New Roman", 30));
			hbox.getChildren().add(lA2);
			hbox.getChildren().add(lA3);
			info.getChildren().add(hbox);

			farms.getChildren().add(info);
		}

		farms.setPadding(new Insets(20, 0, 0, 10));
		vb.getChildren().add(farms);

		// bottom
		Button buttonClose = new Button("Close");
		buttonClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				window.close();
			}
		});

		HBox hb = new HBox();
		hb.setPadding(new Insets(10, 10, 10, 550));
		hb.setSpacing(10);
		hb.getChildren().addAll(buttonClose);

		vb.getChildren().add(hb);
		root.setContent(vb);
		Scene scene = new Scene(root, width, height);
		window.setTitle("Farm Report");
		window.setScene(scene);
		window.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
