package DB2019;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.Date;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.ResultSetMetaData;

import javafx.util.Callback;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import javafx.application.Application;

import javafx.beans.property.SimpleIntegerProperty;

import javafx.beans.property.SimpleStringProperty;

import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.geometry.Insets;

import javafx.geometry.Pos;

import javafx.geometry.VPos;

import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.scene.control.Hyperlink;

import javafx.scene.control.Label;

import javafx.scene.control.ScrollPane;

import javafx.scene.control.SelectionMode;

import javafx.scene.control.TableColumn;

import javafx.scene.control.TableColumn.CellDataFeatures;

import javafx.scene.control.TableColumnBase;

import javafx.scene.control.TablePosition;

import javafx.scene.control.TableView;

import javafx.scene.control.TableView.TableViewSelectionModel;

import javafx.scene.control.TextArea;

import javafx.scene.control.TextField;

import javafx.scene.control.TreeItem;

import javafx.scene.control.TreeView;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.FlowPane;

import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;

import javafx.scene.layout.Pane;

import javafx.scene.layout.Priority;

import javafx.scene.layout.StackPane;

import javafx.scene.layout.TilePane;

import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;

import javafx.scene.paint.CycleMethod;

import javafx.scene.paint.LinearGradient;

import javafx.scene.paint.Stop;

import javafx.scene.shape.Rectangle;

import javafx.scene.text.Font;

import javafx.scene.text.FontWeight;

import javafx.scene.text.Text;

import javafx.stage.Stage;

public class DBGUI extends Application {

// data members

	private Connection con = MyConnection.makeConnection();

	private TableView table;

	TreeView<String> tree;

	Button[] buttons;

	Label[] labels;

	TextField[] txt;

	TextArea txtArea;

	private final String[] btntext = { "clear", "save", "update", "delete", "print", "search" };

	@Override

	public void start(Stage stage) {

		// Add controls and Layouts

		VBox vbox = new VBox();

		vbox.setSpacing(20);

		vbox.setMinSize(900, 500);

		vbox.setMaxSize(1000, 700);

		vbox.setPadding(new Insets(15, 15, 15, 15));

		vbox.setSpacing(10); // Gap between nodes

		vbox.setStyle("-fx-border-color: Black;");

		// Top Box

		HBox tbox = addTopPane();

		tbox.prefHeightProperty().bind(vbox.prefWidthProperty());

		vbox.getChildren().add(tbox);

		// Center box

		HBox cbox = addCenterPane();

		cbox.prefHeightProperty().bind(vbox.prefWidthProperty());

		vbox.getChildren().add(cbox);

		StackPane bbox = addBottomPane();

		bbox.prefHeightProperty().bind(vbox.prefWidthProperty());

		vbox.getChildren().add(bbox);

		// create and show stage

		Scene scene = new Scene(vbox);

		stage.setScene(scene);

		stage.setTitle("Dream Home ");

		stage.show();

	}

	private HBox addTopPane() {

		HBox hbox = new HBox();

		hbox.setPadding(new Insets(15, 15, 15, 15));

		hbox.setSpacing(10); // Gap between nodes

		hbox.setStyle("-fx-border-color: Blue;");

		buttons = new Button[6];

		for (int i = 0; i < buttons.length; i++) {

			buttons[i] = new Button(btntext[i]);

			buttons[i].setPrefSize(80, 20);

			buttons[i].prefHeightProperty().bind(hbox.prefWidthProperty());

		}

		for (int i = 0; i < buttons.length; i++) {

			final int j = i;

			buttons[j].setOnAction((event) -> {

				String str = buttons[j].getText();

				txtArea.appendText("You have clicked + " + str + "\n");

				if ("clear".equals(str)) {
					clearTextFields();
				}

				else if ("save".equals(str)) {

					if (mySelectNode().equals(Nodes.Branch.toString()))
						// insertBrachIS();
						// insertBrachRS();
						insertBrachSP();

					if (mySelectNode().equals(Nodes.Client.toString()))
						insertClientSP();

					if (mySelectNode().equals(Nodes.PrivateOwner.toString()))
						insertPrivateOwnerSP();

					if (mySelectNode().equals(Nodes.PropertyForRent.toString()))
						insertPropertyForRentSP();

					if (mySelectNode().equals(Nodes.Registration.toString()))
						insertRegistrationSP();
					if (mySelectNode().equals(Nodes.Staff.toString()))
						insertStaffSP();
					if (mySelectNode().equals(Nodes.Viewing.toString()))
						insertViewingSP();

				}

				else if ("delete".equals(str)) {
					if (mySelectNode().equals(Nodes.Branch.toString()))
						deleteBanchSP();
					// deleteBranchDS();
					// deleteBanchRS();

					if (mySelectNode().equals(Nodes.Client.toString()))
						deleteClientSP();

					if (mySelectNode().equals(Nodes.PrivateOwner.toString()))
						deletePrivateOwnerSP();

					if (mySelectNode().equals(Nodes.PropertyForRent.toString()))
						deletePropertyForRentSP();
					if (mySelectNode().equals(Nodes.Registration.toString()))
						deleteRegistrationSP();
					if (mySelectNode().equals(Nodes.Staff.toString()))
						deleteStaffSP();
					if (mySelectNode().equals(Nodes.Viewing.toString()))
						deleteViewingSP();

				}

				else if ("update".equals(str)) {
					if (mySelectNode().equals(Nodes.Branch.toString()))
						updateBranchSP();
					// updateBranchUS();
					// updateBranchRS();
					if (mySelectNode().equals(Nodes.Client.toString()))
						updateClientSP();
					if (mySelectNode().equals(Nodes.PrivateOwner.toString()))
						updatePrivateOwnerSP();

					if (mySelectNode().equals(Nodes.PropertyForRent.toString()))
						updatePropertyForRentSP();
					if (mySelectNode().equals(Nodes.Registration.toString()))
						updateRegistrationSP();
					if (mySelectNode().equals(Nodes.Staff.toString()))
						updateStaffSP();
					if (mySelectNode().equals(Nodes.Viewing.toString()))
						updateViewingSP();
				}

				else if ("search".equals(str)) {

				}

				else if ("print".equals(str)) {
					if (mySelectNode().equals(Nodes.Branch.toString()))
						reportBranch();
					if (mySelectNode().equals(Nodes.Client.toString()))
						reportClient();
					if (mySelectNode().equals(Nodes.PrivateOwner.toString()))
						reportPrivateOwner();
					if (mySelectNode().equals(Nodes.PropertyForRent.toString()))
						reportPropertyForRent();
					if (mySelectNode().equals(Nodes.Registration.toString()))
						reportRegistration();
					if (mySelectNode().equals(Nodes.Staff.toString()))
						reportStaff();
					if (mySelectNode().equals(Nodes.Viewing.toString()))
						reportViewing();
				}

			});

		}

		hbox.getChildren().addAll(buttons);

		return hbox;

	}

// function members

	private HBox addCenterPane() {

		HBox hb1 = new HBox();

		// Add TableView

		VBox vb = new VBox();

		table = new TableView<>();

		table.setMinSize(600, 150);

		table.setMaxSize(600, 150);

		table.setStyle("-fx-border-color: Black;");

		table.prefWidthProperty().bind(vb.prefWidthProperty());

		table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		table.getSelectionModel().setCellSelectionEnabled(false);

		table.getSelectionModel().selectedItemProperty().addListener((v, oldV, newV) -> {

			if (newV != null)

				showFields();

		});

		// Add Labels and TextFields

		GridPane gp = new GridPane();

		gp.setPadding(new Insets(15, 15, 15, 125));

		gp.setHgap(5);

		gp.setVgap(5);

		gp.setStyle("-fx-border-color: Blue;");

		gp.prefHeightProperty().bind(table.prefWidthProperty());

		txt = new TextField[10];

		labels = new Label[10];

		for (int i = 0; i < labels.length; i++) {

			labels[i] = new Label("Label..");

			labels[i].setMinSize(150, 20);

			txt[i] = new TextField(" Text.. ");

			txt[i].setMinSize(300, 20);

			gp.addRow(i, labels[i], txt[i]);

			labels[i].prefHeightProperty().bind(gp.widthProperty());

			txt[i].prefHeightProperty().bind(gp.widthProperty());

		}

		vb.getChildren().addAll(table, gp);

		// Add TreeView

		StackPane stack = new StackPane();

		// Create the tree

		tree = addNodestoTree();

		tree.setShowRoot(true);

		tree.getSelectionModel().selectedItemProperty().addListener((v, oldV, newV) -> {

			if (oldV != newV) {

				String str = mySelectNode();

				txtArea.appendText("You have selected " + str + "\n");

				if (str.equals(Nodes.Client.toString()) ||

						str.equals(Nodes.PrivateOwner.toString()) ||

						str.equals(Nodes.Branch.toString()) ||

						str.equals(Nodes.PropertyForRent.toString()) ||

						str.equals(Nodes.Registration.toString()) ||

						str.equals(Nodes.Staff.toString()) ||

						str.equals(Nodes.Viewing.toString()))

				{

					rsToTableView(str);

				}

			}

		});

		tree.setMaxWidth(150);

		tree.prefWidthProperty().bind(stack.prefWidthProperty());

		stack.getChildren().add(tree);

		hb1.getChildren().addAll(stack, vb);

		hb1.setStyle("-fx-border-color:black;");

		hb1.setSpacing(20);

		hb1.prefHeightProperty().bind(vb.prefWidthProperty());

		return hb1;

	}

	private StackPane addBottomPane() {

		StackPane stack = new StackPane();

		stack.setMaxHeight(150);

		stack.setMinHeight(150);

		stack.setPrefHeight(150);

		stack.setStyle("-fx-border-color: #336699;");

		txtArea = new TextArea();

		txtArea.appendText(MyConnection.sb.toString());

		txtArea.prefHeightProperty().bind(stack.prefWidthProperty());

		stack.getChildren().add(txtArea);

		return stack;

	}

	private TreeView<String> addNodestoTree() {

		TreeView<String> tree = new TreeView<String>();

		TreeItem<String> root, tables, reports, exit, about;

		root = new TreeItem<String>("DreamHome");

		tables = new TreeItem<String>("Tables");

		makeChild(Nodes.Branch.toString(), tables);

		makeChild(Nodes.Client.toString(), tables);

		makeChild(Nodes.PrivateOwner.toString(), tables);

		makeChild(Nodes.Staff.toString(), tables);

		makeChild(Nodes.PropertyForRent.toString(), tables);

		makeChild(Nodes.Registration.toString(), tables);

		makeChild(Nodes.Viewing.toString(), tables);

		reports = new TreeItem<String>("Reports");

		makeChild(Nodes.Report01.toString(), reports);

		makeChild(Nodes.Report02.toString(), reports);

		makeChild(Nodes.Report03.toString(), reports);

		exit = new TreeItem<String>(Nodes.Exit.toString());

		about = new TreeItem<String>(Nodes.About.toString());

		root.getChildren().addAll(tables, reports, exit, about);

		tree.setRoot(root);

		return tree;

	}

	// Create child

	private TreeItem<String> makeChild(String title, TreeItem<String> parent) {

		TreeItem<String> item = new TreeItem<>(title);

		item.setExpanded(false);

		parent.getChildren().add(item);

		return item;

	}

	private String mySelectNode() {

		TreeItem ti = tree.getSelectionModel().selectedItemProperty().getValue();

		return ti.getValue().toString();

	}

	private void showFields() {

		clearFields();

		TablePosition pos = (TablePosition) table.getSelectionModel().getSelectedCells().get(0);

		int row = pos.getRow();

		int cols = table.getColumns().size();

		for (int j = 0; j < cols; j++) {

			Object ch = ((TableColumnBase) table.getColumns().get(j)).getText();

			Object cell = ((TableColumnBase) table.getColumns().get(j)).getCellData(row).toString();

			txt[j].setText(cell.toString());

			txt[j].setVisible(true);

			labels[j].setText(ch.toString());

			labels[j].setVisible(true);

		}

	}

	private void clearFields() {

		for (int i = 0; i < txt.length; i++) {

			txt[i].setText("");

			txt[i].setVisible(false);

			labels[i].setText("");

			labels[i].setVisible(false);

		}

	}

	private void clearTextFields() {

		int noc = table.getColumns().size();

		for (int i = 0; i < noc; i++)

			txt[i].setText("");

	}

	private void rsToTableView(String s) {

		table.getColumns().clear();

		for (int i = 0; i < table.getItems().size(); i++) {

			table.getItems().clear();

		}

		ObservableList data = FXCollections.observableArrayList();

		try {

			String query = "select * from " + s + "";

			PreparedStatement pst = con.prepareStatement(query);

			ResultSet rs = pst.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();

			int colCount = rsmd.getColumnCount();

			// get columns

			for (int i = 0; i < colCount; i++) {

				// int dataType = rsmd.getColumnType(i + 1);

				final int j = i;

				TableColumn col = new TableColumn(rsmd.getColumnName(i + 1));

				col.setCellValueFactory(

						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {

							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {

								return new SimpleStringProperty(param.getValue().get(j).toString());

							}

						});

				table.getColumns().add(col);

			}

			// get rows

			while (rs.next()) {

				ObservableList<String> row = FXCollections.observableArrayList();

				for (int k = 1; k <= colCount; k++)

					row.add(rs.getString(k));

				data.add(row);

			}

			table.setItems(data);

			table.getSelectionModel().select(0);

			showFields();

		} catch (Exception e) {

			txtArea.appendText(e.getMessage());

		} finally {

		}

	}

// insert a record in Branch table using Insert Statement

	private void insertBrachIS() {

		int num = 0;

		String sql = "insert into Branch values(?,?,?,?)";

		try {

			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, txt[0].getText());

			pst.setString(2, txt[1].getText());

			pst.setString(3, txt[2].getText());

			pst.setString(4, txt[3].getText());

			num = pst.executeUpdate();

			txtArea.appendText(num + "Record is inserted..");

		}

		catch (Exception e) {

			txtArea.appendText("Record is inserted..");

			txtArea.appendText(e.getMessage());

		}

		finally {
		}

	}

//insert a record in Branch table using ResultSet

	private void insertBrachRS() {
		String sql = "select * from Branch";
		try {
			PreparedStatement pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE); // 요거 틀릴수도
			ResultSet rs = pst.executeQuery();

			rs.moveToInsertRow();
			rs.updateString(1, txt[0].getText());
			rs.updateString(2, txt[1].getText());
			rs.updateString(3, txt[2].getText());
			rs.updateString(4, txt[3].getText());

			rs.insertRow();
			rs.moveToCurrentRow();
			rs.close();
			pst.close();
			txtArea.appendText("Record is inserted by using branchRS" + "\n");

		} catch (Exception e) {
			txtArea.appendText("Record is not inserted..");
			txtArea.appendText(e.getMessage());
		}

		finally {
		}
	}

//insert a record in Branch table using Stored Procedures

	private void insertBrachSP() {

		String sql = "{call usp_insertBranch(?,?,?,?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.setString(2, txt[1].getText());
			cst.setString(3, txt[2].getText());
			cst.setString(4, txt[3].getText());

			cst.execute();
			cst.close();
			txtArea.appendText("Record is inserted by using branchSP" + "\n");
		}

		catch (Exception e) {
			txtArea.appendText("Record is not inserted..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

///////////////////////////// 
	private void deleteBranchDS() {
		String sql = "delete from Branch whee branchNo=?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, txt[0].getText());
			pst.executeUpdate();

			txtArea.appendText("record is deleted...");
			pst.close();
		} catch (Exception e) {
			txtArea.appendText("Record is not deleted..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

//////////////////////////
	private void deleteBanchRS() {
		String sql = "select * from Branch";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			TablePosition pos = (TablePosition) table.getSelectionModel().getSelectedCells().get(0);
			int rowNum = pos.getRow();
			rs.absolute(rowNum + 1);
			rs.deleteRow();
			rs.moveToCurrentRow();
			txtArea.appendText("Record is deleted...\n");
			rs.close();
			pst.close();
		} catch (Exception e) {
			txtArea.appendText("Record is not deleted..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

/////////////////////////////
	private void deleteBanchSP() {
		String sql = "{call usp_deleteBranch(?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.execute();
			txtArea.appendText("record is delete...\n");
			cst.close();
		} catch (Exception e) {
			txtArea.appendText("Record is not deleted..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

/////////////////////////////
	private void updateBranchUS() {
		String sql = "update Branch set Street=?, city=?,postcode=? where branchNo=?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, txt[1].getText());
			pst.setString(2, txt[2].getText());
			pst.setString(3, txt[3].getText());
			pst.setString(4, txt[4].getText());

			pst.executeUpdate();
			txtArea.appendText("Record is updated..." + "\n");
			pst.close();
		} catch (Exception e) {
			txtArea.appendText("Record is not updateBranch..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

///////////////////////////////
	private void updateBranchRS() {

		String sql = "select * from Branch";
		try {
			PreparedStatement pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pst.executeQuery();
			TablePosition pos = (TablePosition) table.getSelectionModel().getSelectedCells().get(0);
			int rowNum = pos.getRow();
			rs.absolute(rowNum + 1);
			rs.updateString(2, txt[1].getText());
			rs.updateString(3, txt[2].getText());
			rs.updateString(4, txt[3].getText());

			rs.updateRow();

			txtArea.appendText("record is update..");
			rs.close();
			pst.close();

		} catch (Exception e) {
			txtArea.appendText("Record is not updateBranch..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void updateBranchSP() {
		String sql = "{call usp_updateBranch(?,?,?,?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.setString(2, txt[1].getText());
			cst.setString(3, txt[2].getText());
			cst.setString(4, txt[3].getText());

			cst.execute();
			txtArea.appendText("Record is updated..." + "\n");
			cst.close();
		} catch (Exception e) {
			txtArea.appendText("Record is not updateBranch..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void reportBranch() {
		String fileName = "C:\\Users\\user\\JaspersoftWorkspace\\MyReports\\DB001.jrxml";
		try {
			JasperReport jr = JasperCompileManager.compileReport(fileName);
			JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
			JasperViewer.viewReport(jp, false);

		} catch (Exception e) {
			txtArea.appendText("There is problem in printing Report..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

//////////////////////////////client

	// insert a record in Branch table using Stored Procedures

	private void insertClientSP() {

		String sql = "{call insertClient(?,?,?,?,?,?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.setString(2, txt[1].getText());
			cst.setString(3, txt[2].getText());
			cst.setString(4, txt[3].getText());
			cst.setString(5, txt[4].getText());
			cst.setString(6, txt[5].getText());

			cst.execute();
			cst.close();
			txtArea.appendText("Record is inserted by using ClientSP" + "\n");
		}

		catch (Exception e) {
			txtArea.appendText("Record is not inserted..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void deleteClientSP() {
		String sql = "{call deleteClient(?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.execute();
			txtArea.appendText("record is delete...\n");
			cst.close();
		} catch (Exception e) {
			txtArea.appendText("Record is not deleted..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void updateClientSP() {
		String sql = "{call updateClient(?,?,?,?,?,?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.setString(2, txt[1].getText());
			cst.setString(3, txt[2].getText());
			cst.setString(4, txt[3].getText());
			cst.setString(5, txt[4].getText());
			cst.setString(6, txt[5].getText());

			cst.execute();
			txtArea.appendText("Record is updated..." + "\n");
			cst.close();
		} catch (Exception e) {
			txtArea.appendText("Record is not updateclient..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void reportClient() {
		String fileName = "C:\\Users\\user\\JaspersoftWorkspace\\MyReports\\DB002.jrxml";
		try {
			JasperReport jr = JasperCompileManager.compileReport(fileName);
			JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
			JasperViewer.viewReport(jp, false);

		} catch (Exception e) {
			txtArea.appendText("There is problem in printing Report..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

//////////////////////////////PrivateOwner

//insert a record in Branch table using Stored Procedures

	private void insertPrivateOwnerSP() {

		String sql = "{call insertPrivateOwner(?,?,?,?,?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.setString(2, txt[1].getText());
			cst.setString(3, txt[2].getText());
			cst.setString(4, txt[3].getText());
			cst.setString(5, txt[4].getText());

			cst.execute();
			cst.close();
			txtArea.appendText("Record is inserted by using PrivateOwnerSP" + "\n");
		}

		catch (Exception e) {
			txtArea.appendText("Record is not inserted..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void deletePrivateOwnerSP() {
		String sql = "{call deletePrivateOwener(?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.execute();
			txtArea.appendText("record is delete...\n");
			cst.close();
		} catch (Exception e) {
			txtArea.appendText("Record is not deleted..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void updatePrivateOwnerSP() {
		String sql = "{call updatePrivateOwner(?,?,?,?,?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.setString(2, txt[1].getText());
			cst.setString(3, txt[2].getText());
			cst.setString(4, txt[3].getText());
			cst.setString(5, txt[4].getText());

			cst.execute();
			txtArea.appendText("Record is updated..." + "\n");
			cst.close();
		} catch (Exception e) {
			txtArea.appendText("Record is not updatePrivateOwner..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void reportPrivateOwner() {
		String fileName = "C:\\Users\\user\\JaspersoftWorkspace\\MyReports\\DB003.jrxml";
		try {
			JasperReport jr = JasperCompileManager.compileReport(fileName);
			JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
			JasperViewer.viewReport(jp, false);

		} catch (Exception e) {
			txtArea.appendText("There is problem in printing Report..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

//////////////////////////////PropertyForRent

//insert a record in Branch table using Stored Procedures

	private void insertPropertyForRentSP() {

		String sql = "{call insertPropertyForRent(?,?,?,?,?,?,?,?,?,?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.setString(2, txt[1].getText());
			cst.setString(3, txt[2].getText());
			cst.setString(4, txt[3].getText());
			cst.setString(5, txt[4].getText());
			cst.setString(6, txt[5].getText());
			cst.setString(7, txt[6].getText());
			cst.setString(8, txt[7].getText());
			cst.setString(9, txt[8].getText());
			cst.setString(10, txt[9].getText());

			cst.execute();
			cst.close();
			txtArea.appendText("Record is inserted by using PropertyForRentSP" + "\n");
		}

		catch (Exception e) {
			txtArea.appendText("Record is not inserted..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void deletePropertyForRentSP() {
		String sql = "{call deletePropertyForRent(?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.execute();
			txtArea.appendText("record is delete...\n");
			cst.close();
		} catch (Exception e) {
			txtArea.appendText("Record is not deleted..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void updatePropertyForRentSP() {
		String sql = "{call updatePropertyForRent(?,?,?,?,?,?,?,?,?,?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.setString(2, txt[1].getText());
			cst.setString(3, txt[2].getText());
			cst.setString(4, txt[3].getText());
			cst.setString(5, txt[4].getText());
			cst.setString(6, txt[5].getText());
			cst.setString(7, txt[6].getText());
			cst.setString(8, txt[7].getText());
			cst.setString(9, txt[8].getText());
			cst.setString(10, txt[9].getText());

			cst.execute();
			txtArea.appendText("Record is updated..." + "\n");
			cst.close();
		} catch (Exception e) {
			txtArea.appendText("Record is not updatePropertyForRent..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void reportPropertyForRent() {
		String fileName = "C:\\Users\\user\\JaspersoftWorkspace\\MyReports\\DB005.jrxml";
		try {
			JasperReport jr = JasperCompileManager.compileReport(fileName);
			JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
			JasperViewer.viewReport(jp, false);

		} catch (Exception e) {
			txtArea.appendText("There is problem in printing Report..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

//////////////////////////////Registration

//insert a record in Branch table using Stored Procedures

	private void insertRegistrationSP() {

		String sql = "{call insertRegistration(?,?,?,?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.setString(2, txt[1].getText());
			cst.setString(3, txt[2].getText());
			cst.setString(4, txt[3].getText());

			cst.execute();
			cst.close();
			txtArea.appendText("Record is inserted by using RegistrationSP" + "\n");
		}

		catch (Exception e) {
			txtArea.appendText("Record is not inserted..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void deleteRegistrationSP() {
		String sql = "{call deleteRegistration(?,?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.setString(2, txt[1].getText());

			cst.execute();
			txtArea.appendText("record is delete...\n");
			cst.close();
		} catch (Exception e) {
			txtArea.appendText("Record is not deleted..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void updateRegistrationSP() {
		String sql = "{call updateRegistration(?,?,?,?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.setString(2, txt[1].getText());
			cst.setString(3, txt[2].getText());
			cst.setString(4, txt[3].getText());

			cst.execute();
			txtArea.appendText("Record is updated..." + "\n");
			cst.close();
		} catch (Exception e) {
			txtArea.appendText("Record is not updateRegistration..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void reportRegistration() {
		String fileName = "C:\\Users\\user\\JaspersoftWorkspace\\MyReports\\DB006.jrxml";
		try {
			JasperReport jr = JasperCompileManager.compileReport(fileName);
			JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
			JasperViewer.viewReport(jp, false);

		} catch (Exception e) {
			txtArea.appendText("There is problem in printing Report..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

/////////////////////////////Staff

//insert a record in Branch table using Stored Procedures

	private void insertStaffSP() {

		String sql = "{call insertStaff(?,?,?,?,?,?,?,?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.setString(2, txt[1].getText());
			cst.setString(3, txt[2].getText());
			cst.setString(4, txt[3].getText());
			cst.setString(5, txt[4].getText());
			cst.setString(6, txt[5].getText());
			cst.setString(7, txt[6].getText());
			cst.setString(8, txt[7].getText());

			cst.execute();
			cst.close();
			txtArea.appendText("Record is inserted by using StaffSP" + "\n");
		}

		catch (Exception e) {
			txtArea.appendText("Record is not inserted..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void deleteStaffSP() {
		String sql = "{call deleteStaff(?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.execute();
			txtArea.appendText("record is delete...\n");
			cst.close();
		} catch (Exception e) {
			txtArea.appendText("Record is not deleted..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void updateStaffSP() {
		String sql = "{call updateStaff(?,?,?,?,?,?,?,?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.setString(2, txt[1].getText());
			cst.setString(3, txt[2].getText());
			cst.setString(4, txt[3].getText());
			cst.setString(5, txt[4].getText());
			cst.setString(6, txt[5].getText());
			cst.setString(7, txt[6].getText());
			cst.setString(8, txt[7].getText());

			cst.execute();
			txtArea.appendText("Record is updated..." + "\n");
			cst.close();
		} catch (Exception e) {
			txtArea.appendText("Record is not updateRegistration..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void reportStaff() {
		String fileName = "C:\\Users\\user\\JaspersoftWorkspace\\MyReports\\DB004.jrxml";
		try {
			JasperReport jr = JasperCompileManager.compileReport(fileName);
			JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
			JasperViewer.viewReport(jp, false);

		} catch (Exception e) {
			txtArea.appendText("There is problem in printing Report..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

//////////////////////////////Viewing

//insert a record in Branch table using Stored Procedures

	private void insertViewingSP() {

		String sql = "{call insertViewing(?,?,?,?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.setString(2, txt[1].getText());
			cst.setString(3, txt[2].getText());
			cst.setString(4, txt[3].getText());

			cst.execute();
			cst.close();
			txtArea.appendText("Record is inserted by using ViewingSP" + "\n");
		}

		catch (Exception e) {
			txtArea.appendText("Record is not inserted..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void deleteViewingSP() {
		String sql = "{call deleteViewing(?,?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.setString(2, txt[1].getText());

			cst.execute();
			txtArea.appendText("record is delete...\n");
			cst.close();
		} catch (Exception e) {
			txtArea.appendText("Record is not deleted..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void updateViewingSP() {
		String sql = "{call updateViewing(?,?,?,?)}";
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1, txt[0].getText());
			cst.setString(2, txt[1].getText());
			cst.setString(3, txt[2].getText());
			cst.setString(4, txt[3].getText());

			cst.execute();
			txtArea.appendText("Record is updated..." + "\n");
			cst.close();
		} catch (Exception e) {
			txtArea.appendText("Record is not updateViewing..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

	private void reportViewing() {
		String fileName = "C:\\Users\\user\\JaspersoftWorkspace\\MyReports\\DB007.jrxml";
		try {
			JasperReport jr = JasperCompileManager.compileReport(fileName);
			JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
			JasperViewer.viewReport(jp, false);

		} catch (Exception e) {
			txtArea.appendText("There is problem in printing Report..");
			txtArea.appendText(e.getMessage());
		} finally {
		}
	}

////////////////////
	public static void main(String[] args) {

		launch(args);

	}

}