import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.time.Instant;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application{
	public static User auth = null;
	private Stage mainStage = null;
	//Login Page
	private VBox log_parent = new VBox();
	private Scene log_sc = new Scene(log_parent, 250, 180);
	private GridPane log_gpLayout = new GridPane();
	private Label log_lblLogin = new Label("Login");
	private Label log_lblEmail = new Label("Email");
	private Label log_lblPassword = new Label("Password");
	private TextField log_tfEmail = new TextField();
	private TextField log_pfPassword = new PasswordField();
	private Button log_btnSignUp = new Button("Sign Up");
	private Button log_btnSignIn = new Button("Sign In");
	
	//Register Page
	private VBox reg_parent = new VBox();
	private Scene reg_sc = new Scene(reg_parent, 275, 500);
	private GridPane reg_gpLayout = new GridPane();
	private Label reg_lblRegister = new Label("Register");
	private Label reg_lblId = new Label("User ID");
	private Label reg_lblName = new Label("Username");
	private Label reg_lblPhone = new Label("Phone");
	private Label reg_lblAddress = new Label("Address");
	private Label reg_lblEmail = new Label("Email");
	private Label reg_lblPassword = new Label("Password");
	private Label reg_lblDob = new Label("Date of Birth");
	private Label reg_lblGender = new Label("Gender");
	private TextField reg_tfId = new TextField();
	private TextField reg_tfName = new TextField();
	private TextField reg_tfPhone = new TextField();
	private TextField reg_tfAddress = new TextField();
	private TextField reg_tfEmail = new TextField();
	private PasswordField reg_pfPassword = new PasswordField();
	private DatePicker reg_dpDob = new DatePicker();
	private ToggleGroup reg_tgGender = new ToggleGroup();
	private RadioButton reg_rbMale = new RadioButton("Male");
	private RadioButton reg_rbFemale = new RadioButton("Female");
	private Button reg_btnSignIn = new Button("Sign In");
	private Button reg_btnSignUp = new Button("Sign Up");
	
	//Home Page
	private BorderPane home_parent = new BorderPane();
	private Scene home_sc = new Scene(home_parent, 900, 700);
	private MenuBar home_mbMenu = new MenuBar();
	private Menu home_menuMenu = new Menu("Menu");
	private Menu home_menuAccount = new Menu("Account");
	private MenuItem home_miBuy = new MenuItem("Buy");
	private MenuItem home_miTrans = new MenuItem("Transactions");
	private MenuItem home_miMProduct = new MenuItem("Manage Product");
	private MenuItem home_miMUser = new MenuItem("Manage User");
	private MenuItem home_miLogoff = new MenuItem("Logoff");
	
	//(SUB) Home Content: Buy
	private VBox home_buy_vbMain = new VBox();
	private HBox home_buy_hbCart = new HBox();
	private VBox home_buy_vbInputs = new VBox();
	private TableView<Product> home_buy_tvProduct = new TableView<Product>();
	private TableView<Cart> home_buy_tvCart = new TableView<Cart>();
	private Spinner<Integer> home_buy_spQuantity = new Spinner<>(1, 999, 1);
	private Button home_buy_btnAddCart = new Button("Add to Cart");
	private Button home_buy_btnRemCart = new Button("Remove from Cart");
	private Button home_buy_btnOrder = new Button("Order");
	
	//(SUB) Home Content: Transactions
	private HBox home_trans_hbMain = new HBox();
	private VBox home_trans_vbDetails = new VBox();
	private TableView<TransactionHeader> home_trans_tvTrans = new TableView<TransactionHeader>();
	private TableView<TransactionDetail> home_trans_tvDetails = new TableView<TransactionDetail>();
	private HBox home_trans_hbTotal = new HBox();
	private Label home_trans_lblTotal = new Label("Total Price");
	private TextField home_trans_tfTotal = new TextField();
	
	//(SUB) Home Content: Manage Product
	private VBox home_mprod_vbMain = new VBox();
	private TableView<Product> home_mprod_tvProduct = new TableView<Product>();
	private GridPane home_mprod_gpLayout = new GridPane();
	private HBox home_mprod_hbName = new HBox();
	private HBox home_mprod_hbPrice = new HBox();
	private HBox home_mprod_hbStock = new HBox();
	private HBox home_mprod_hbType = new HBox();
	private HBox home_mprod_hbButtons = new HBox();
	private Label home_mprod_lblName = new Label("Product Name");
	private Label home_mprod_lblPrice = new Label("Product Price");
	private Label home_mprod_lblStock = new Label("Product Stock");
	private Label home_mprod_lblType = new Label("Product Type");
	private TextField home_mprod_tfName = new TextField();
	private TextField home_mprod_tfPrice = new TextField();
	private Spinner<Integer> home_mprod_spStock = new Spinner<>(1, 999, 1);
	private ComboBox<ProductType> home_mprod_cbPType = new ComboBox<>();
	private Button home_mprod_btnInsert = new Button("Insert");
	private Button home_mprod_btnUpdate = new Button("Update");
	private Button home_mprod_btnDelete = new Button("Remove");
	
	//(SUB) Home Content: Manage User
	private VBox home_muser_vbMain = new VBox();
	private TableView<User> home_muser_tvUser = new TableView<User>();
	private HBox home_muser_hbLayout = new HBox();
	private GridPane home_muser_gpInputs1 = new GridPane();
	private GridPane home_muser_gpInputs2 = new GridPane();
	private VBox home_muser_vbButtons = new VBox();
	private Label home_muser_lblName = new Label("User Name");
	private Label home_muser_lblPhone = new Label("User Phone");
	private Label home_muser_lblAddress = new Label("User Address");
	private Label home_muser_lblEmail = new Label("User Email");
	private Label home_muser_lblPassword = new Label("User Password");
	private Label home_muser_lblGender = new Label("User Gender");
	private Label home_muser_lblRole = new Label("User Role");
	private Label home_muser_lblDob = new Label("Date of Birth");
	private TextField home_muser_tfName = new TextField();
	private TextField home_muser_tfPhone = new TextField();
	private TextField home_muser_tfAddress = new TextField();
	private TextField home_muser_tfEmail = new TextField();
	private TextField home_muser_tfPassword = new TextField();
	private ToggleGroup home_muser_tgGender = new ToggleGroup();
	private RadioButton home_muser_rbMale = new RadioButton("Male");
	private RadioButton home_muser_rbFemale = new RadioButton("Female");
	private ToggleGroup home_muser_tgRole = new ToggleGroup();
	private RadioButton home_muser_rbUser = new RadioButton("User");
	private RadioButton home_muser_rbAdmin = new RadioButton("Admin");
	private DatePicker home_muser_dpDob = new DatePicker();
	private Button home_muser_btnInsert = new Button("Insert");
	private Button home_muser_btnUpdate = new Button("Update");
	private Button home_muser_btnDelete = new Button("Remove");
	
	
	public void error(String message) {
		Alert a = new Alert(AlertType.ERROR);
		a.setTitle("An Error Occured");
		a.setHeaderText(null);
		a.setContentText(message);
		a.show();
	}
	
	public void info(String message) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("Information");
		a.setHeaderText(null);
		a.setContentText(message);
		a.show();
	}
	
	public void logout() {
		auth = null;
		openLogin();
	}
	
	public void openLogin() {
		mainStage.setScene(log_sc);
	}
	
	public void openRegister() {
		mainStage.setScene(reg_sc);
		reg_tfId.setText(User.nextID());
	}
	
	public void openHomeUser() {
		mainStage.setScene(home_sc);
		home_parent.setCenter(null);
		home_miBuy.setVisible(true);
		home_miTrans.setVisible(true);
		home_miMUser.setVisible(false);
		home_miMProduct.setVisible(false);
	}
	
	public void openHomeAdmin() {
		mainStage.setScene(home_sc);
		home_parent.setCenter(null);
		home_miBuy.setVisible(false);
		home_miTrans.setVisible(false);
		home_miMUser.setVisible(true);
		home_miMProduct.setVisible(true);
	}
	
	public void openBuy() {
		mainStage.setScene(home_sc);
		home_parent.setCenter(home_buy_vbMain);
		home_buy_tvProduct.getItems().clear();
		home_buy_tvCart.getItems().clear();
		home_buy_tvProduct.getItems().addAll(Product.getAll());
		home_buy_tvCart.getItems().addAll(Cart.getAll(auth.getId()));
	}
	
	public void openTrans() {
		mainStage.setScene(home_sc);
		home_parent.setCenter(home_trans_hbMain);
		home_trans_tvTrans.getItems().clear();
		home_trans_tvDetails.getItems().clear();
		home_trans_tvTrans.getItems().addAll(TransactionHeader.getAll(auth.getId()));
	}
	
	public void openMUser() {
		mainStage.setScene(home_sc);
		home_parent.setCenter(home_muser_vbMain);
		home_muser_tvUser.getItems().clear();
		home_muser_tvUser.getItems().addAll(User.getAll());
		
		for(Node n : home_muser_gpInputs1.getChildren()) {
			if(n instanceof TextInputControl) {
				((TextInputControl) n).clear();
			}
		}
		
		for(Node n : home_muser_gpInputs2.getChildren()) {
			if(n instanceof RadioButton) {
				((RadioButton) n).setSelected(false);
			}
			home_muser_dpDob.setValue(null);
		}
	}
	
	public void openMProduct() {
		mainStage.setScene(home_sc);
		home_parent.setCenter(home_mprod_vbMain);
		home_mprod_tvProduct.getItems().clear();
		home_mprod_tvProduct.getItems().addAll(Product.getAll());
		
		home_mprod_tfName.clear();
		home_mprod_tfPrice.clear();
		home_mprod_spStock.getValueFactory().setValue(0);
		home_mprod_cbPType.getSelectionModel().clearSelection();
	}
	
	public boolean isAlphaNum(String test) {
	    for (int i = 0; i < test.length(); i++) {
	        char c = test.charAt(i);
	        if (!Character.isLetterOrDigit(c)) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public boolean isNumeric(String test) {
		for (int i = 0; i < test.length(); i++) {
	        char c = test.charAt(i);
	        if (!Character.isDigit(c)) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public static boolean emailFormat(String test) {
	    if (test == null || test.isEmpty()) {
	        return false;
	    }
	    
	    String[] parts = test.split("@");

	    if (parts.length != 2) {
	        return false;
	    }

	    if (parts[0].isEmpty()) {
	        return false;
	    }

	    if (parts[1].isEmpty() || !parts[1].contains(".")) {
	        return false;
	    }

	    if(parts[1].startsWith(".")){
	        return false;
	    }

	    return true;
	}
	
	public void setUp(){
		//Login Page
		log_lblLogin.setFont(Font.font("", FontWeight.SEMI_BOLD, 24));
		log_parent.setSpacing(7);
		log_gpLayout.setVgap(5);
		log_gpLayout.setHgap(5);
		log_tfEmail.setPromptText("Your Email");
		log_pfPassword.setPromptText("Your Password");
		log_btnSignUp.setOnAction(e -> {
			openRegister();
		});
		log_btnSignIn.setOnAction(e -> {
			String email = log_tfEmail.getText();
			String password = log_pfPassword.getText();
			
			if(!isAlphaNum(password)) {
				error("Password must be alphanumeric!");
				return;
			}
			if(password.length() < 6 || password.length() > 12) {
				error("Password must be between 6 - 12 chars!");
				return;
			}
			if(!emailFormat(email)) {
				error("Email must be in a valid format!");
				return;
			}
			
			User u = User.auth(email, password);
			if(u == null) error("Invalid User Credentials");
			else {
				auth = u;
				info("Login Successful");
				log_tfEmail.clear();
				log_pfPassword.clear();
				if(auth.getRole().equals("admin")) {
					openHomeAdmin();
				}else {
					openHomeUser();
				}
				
			}
		});
		log_gpLayout.add(log_lblLogin, 0, 0, 3, 1);
		log_gpLayout.add(log_lblEmail, 0, 1, 1, 1);
		log_gpLayout.add(log_tfEmail, 1, 1, 2, 1);
		log_gpLayout.add(log_lblPassword, 0, 2, 1, 1);
		log_gpLayout.add(log_pfPassword, 1, 2, 2, 1);
		log_gpLayout.add(log_btnSignUp, 1, 3, 1, 1);
		log_gpLayout.add(log_btnSignIn, 2, 3, 1, 1);
		log_parent.setPadding(new Insets(30));
		log_parent.setAlignment(Pos.CENTER);
		log_parent.getChildren().add(log_gpLayout);
		
		//Register Page
		reg_parent.setSpacing(7);
		reg_lblRegister.setFont(Font.font("", FontWeight.SEMI_BOLD, 24));
		reg_gpLayout.setVgap(10);
		reg_gpLayout.setHgap(10);
		reg_tfEmail.setPromptText("Your Email");
		reg_tfName.setPromptText("Your Username");
		reg_tfPhone.setPromptText("Your Phone Number");
		reg_tfAddress.setPromptText("Your Address");
		reg_tfId.setPromptText("Your User ID");
		reg_pfPassword.setPromptText("Your Password");
		reg_tfId.setEditable(false);
		
		reg_gpLayout.add(reg_lblRegister, 0, 0, 3, 1);
		reg_gpLayout.add(reg_lblId, 0, 1, 1, 1);
		reg_gpLayout.add(reg_lblEmail, 0, 2, 1, 1);
		reg_gpLayout.add(reg_lblName, 0, 3, 1, 1);
		reg_gpLayout.add(reg_lblPhone, 0, 4, 1, 1);
		reg_gpLayout.add(reg_lblAddress, 0, 5, 1, 1);
		reg_gpLayout.add(reg_lblPassword, 0, 6, 1, 1);
		reg_gpLayout.add(reg_lblDob, 0, 7, 1, 1);
		reg_gpLayout.add(reg_lblGender, 0, 8, 1, 2);
		
		reg_gpLayout.add(reg_tfId, 1, 1, 2, 1);
		reg_gpLayout.add(reg_tfEmail, 1, 2, 2, 1);
		reg_gpLayout.add(reg_tfName, 1, 3, 2, 1);
		reg_gpLayout.add(reg_tfPhone, 1, 4, 2, 1);
		reg_gpLayout.add(reg_tfAddress, 1, 5, 2, 1);
		reg_gpLayout.add(reg_pfPassword, 1, 6, 2, 1);
		reg_gpLayout.add(reg_dpDob, 1, 7, 2, 1);
		reg_gpLayout.add(reg_rbMale, 1, 8, 2, 1);
		reg_gpLayout.add(reg_rbFemale, 1, 9, 2, 1);
		
		reg_gpLayout.add(reg_btnSignIn, 1, 10, 1, 1);
		reg_gpLayout.add(reg_btnSignUp, 2, 10, 1, 1);
		
		reg_parent.setPadding(new Insets(30));
		reg_parent.setAlignment(Pos.CENTER);
		reg_parent.getChildren().add(reg_gpLayout);
		
		reg_rbMale.setToggleGroup(reg_tgGender);
		reg_rbFemale.setToggleGroup(reg_tgGender);
		
		reg_btnSignIn.setOnAction(e -> {
			openLogin();
		});
		
		reg_btnSignUp.setOnAction(e -> {
			if(reg_tfEmail.getText().isBlank() || reg_tfName.getText().isBlank() || reg_tfPhone.getText().isBlank() || reg_pfPassword.getText().isBlank() || reg_tfAddress.getText().isBlank() || reg_dpDob.getValue() == null) {
				error("All fields must be filled!");
				return;
			}
			String email = reg_tfEmail.getText();
			String password = reg_pfPassword.getText();
			String name = reg_tfName.getText();
			String phone = reg_tfPhone.getText();
			String address = reg_tfAddress.getText();
			String id = reg_tfId.getText();
			Date dob = Date.valueOf(reg_dpDob.getValue());
			if(reg_tgGender.getSelectedToggle() == null) {
				error("Gender must be selected!");
				return;
			}
			
			
			String gender = ((RadioButton)reg_tgGender.getSelectedToggle()).getText();
			
			
			if(!isAlphaNum(password)) {
				error("Password must be alphanumeric!");
				return;
			}
			if(password.length() < 6 || password.length() > 12) {
				error("Password must be between 6 - 12 chars!");
				return;
			}
			if(!emailFormat(email)) {
				error("Email must be in a valid format!");
				return;
			}
			if(reg_dpDob.getValue() == null || dob == null) {
				error("Date of birth must be filled!");
				return;
			}
			Date now = Date.valueOf(LocalDate.now());
			System.out.println(dob + " | " + now + " | " + dob.before(now));
			if(!dob.before(now)){
				error("Date of birth must be before today!");
				return;
			}
			
			if(!address.endsWith("Street")) {
				error("Address must end with 'Street'!");
				return;
			}
			if(phone.length() < 10 || phone.length() > 13) {
				error("Phone Number must be between 10 - 13 chars!");
				return;
			}
			if(!isNumeric(phone)) {
				error("Phone Number must be numeric!");
				return;
			}

			if(name.length() < 5 || name.length() > 30) {
				error("Username must be between 5 - 30 chars!");
				return;
			}
			
			User u = new User(id, name, phone, address, email, password, gender, dob, "user");
			u.insert();
			
			info("Registration Successful!");
			
			for(Node n : reg_gpLayout.getChildren()) {
				if(n instanceof TextInputControl) {
					((TextInputControl) n).clear();
				}
				reg_dpDob.setValue(null);
				reg_tgGender.selectToggle(null);
			}
			
			openLogin();
		});
		
		//Home Page
		home_menuAccount.getItems().add(home_miLogoff);
		home_menuMenu.getItems().addAll(home_miBuy, home_miTrans, home_miMUser, home_miMProduct);
		
		home_miBuy.setOnAction(e -> {
			openBuy();
		});
		home_miTrans.setOnAction(e -> {
			openTrans();
		});
		home_miMUser.setOnAction(e -> {
			openMUser();
		});
		home_miMProduct.setOnAction(e -> {
			openMProduct();
		});
		home_miLogoff.setOnAction(e -> {
			logout();
		});
		
		home_mbMenu.getMenus().addAll(home_menuMenu, home_menuAccount);
		home_parent.setTop(home_mbMenu);
		
		//Home Content: Buy
		home_buy_tvProduct.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		home_buy_tvProduct.getColumns().addAll(Product.getTableColumns());
		home_buy_tvCart.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		home_buy_tvCart.getColumns().addAll(Cart.getTableColumns());
		
		home_buy_vbMain.getChildren().addAll(home_buy_tvProduct, home_buy_hbCart);
		home_buy_hbCart.getChildren().addAll(home_buy_tvCart, home_buy_vbInputs);
		home_buy_vbInputs.getChildren().addAll(home_buy_spQuantity, home_buy_btnAddCart, home_buy_btnRemCart, home_buy_btnOrder);
		home_buy_vbInputs.setAlignment(Pos.CENTER);
		home_buy_spQuantity.setPrefWidth(200);
		home_buy_btnAddCart.setPrefWidth(200);
		home_buy_btnRemCart.setPrefWidth(200);
		home_buy_btnOrder.setPrefWidth(200);
		home_buy_vbInputs.setPadding(new Insets(120));
		HBox.setHgrow(home_buy_vbInputs, Priority.ALWAYS);
		home_buy_vbInputs.setSpacing(10);
		
		home_buy_btnAddCart.setOnAction(e -> {
			if(home_buy_tvProduct.getSelectionModel().getSelectedItem() != null) {
				Product p = home_buy_tvProduct.getSelectionModel().getSelectedItem();
				for(Cart c : home_buy_tvCart.getItems()) {
					if(c.getProductId().equals(p.getId())) {
						error("Product already exists in cart, remove it first!");
						return;
					}
				}
				Cart n = new Cart(auth.getId(), p.getId(), home_buy_spQuantity.getValue());
				n.insert();
				
				home_buy_tvCart.getItems().add(n);
			}else {
				error("Select a product first from the shop!");
			}
		});
		
		home_buy_btnRemCart.setOnAction(e -> {
			if(home_buy_tvCart.getSelectionModel().getSelectedItem() != null) {
				Cart c = home_buy_tvCart.getSelectionModel().getSelectedItem();
				c.delete();
				
				home_buy_tvCart.getItems().remove(c);
			}else {
				error("Select an item first from the cart!");
			}
		});
		
		home_buy_btnOrder.setOnAction(e -> {
			if(home_buy_tvCart.getItems().size() > 0) {
				TransactionHeader th = new TransactionHeader(TransactionHeader.nextID(), auth.getId(), Date.valueOf(LocalDate.now()));
				List<TransactionDetail> tds = new ArrayList<TransactionDetail>();
				
				for(Cart c : home_buy_tvCart.getItems()) {
					Product p = Product.get(c.getProductId());
					if(c.getQuantity() > p.getStock()) {
						error("The following item is out of stock for your requested quantity: \n\n" + p.getName() + " (x " + c.getQuantity() +")");
						return;
					}
					tds.add(new TransactionDetail(th.getId(), p.getId(), c.getQuantity()));
				}
				
				th.insert();
				
				for(TransactionDetail td : tds) {
					td.insert();
				}
				
				for(Cart c : home_buy_tvCart.getItems()) {
					Product p = Product.get(c.getProductId());
					p.setStock(p.getStock() - c.getQuantity());
					p.update();
					
					c.delete();
				}
				
				home_buy_tvProduct.getItems().clear();
				home_buy_tvProduct.getItems().addAll(Product.getAll());
				
				home_buy_tvCart.getItems().clear();
				home_buy_tvCart.getItems().addAll(Cart.getAll(auth.getId()));
				
				info("Successfully ordered items!");
				
			}else {
				error("Please add at least one item to the cart first!");
			}
		});
		
		//Home Content: Transactions
		home_trans_tvTrans.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		home_trans_tvTrans.getColumns().addAll(TransactionHeader.getTableColumns());
		home_trans_tvDetails.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		home_trans_tvDetails.getColumns().addAll(TransactionDetail.getTableColumns());
		
		home_trans_tfTotal.setEditable(false);
		home_trans_hbTotal.setSpacing(5);
		home_trans_hbTotal.setPadding(new Insets(100));
		HBox.setHgrow(home_trans_vbDetails, Priority.ALWAYS);
		VBox.setVgrow(home_trans_hbTotal, Priority.SOMETIMES);
		VBox.setVgrow(home_trans_tvDetails, Priority.ALWAYS);
		home_trans_lblTotal.setFont(Font.font("", FontWeight.BOLD, 14));
		home_trans_hbTotal.setAlignment(Pos.CENTER);
		home_trans_hbTotal.getChildren().addAll(home_trans_lblTotal, home_trans_tfTotal);
		home_trans_vbDetails.getChildren().addAll(home_trans_tvDetails, home_trans_hbTotal);
		home_trans_hbMain.getChildren().addAll(home_trans_tvTrans, home_trans_vbDetails);
		
		home_trans_tvTrans.getSelectionModel().selectedItemProperty().addListener((item, oldval, newval) -> {
			if(item != null) {
				TransactionHeader th = item.getValue();
				home_trans_tvDetails.getItems().clear();
				home_trans_tvDetails.getItems().addAll(TransactionDetail.getAll(th.getId()));
				
				home_trans_tfTotal.setText("" + th.getTotalPrice());
			}
		});
		
		//Home Content: Manage User
		home_muser_tvUser.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		home_muser_tvUser.getColumns().addAll(User.getTableColumns());
		home_muser_vbMain.getChildren().addAll(home_muser_tvUser, home_muser_hbLayout);
		home_muser_hbLayout.setSpacing(15);
		home_muser_hbLayout.getChildren().addAll(home_muser_gpInputs1, home_muser_gpInputs2, home_muser_vbButtons);
		home_muser_hbLayout.setPadding(new Insets(20));
		home_muser_gpInputs1.setVgap(5);
		home_muser_gpInputs1.setHgap(5);
		home_muser_gpInputs2.setVgap(10);
		home_muser_gpInputs2.setHgap(10);
		home_muser_vbButtons.setSpacing(5);
		home_muser_vbButtons.setPadding(new Insets(30));
		
		home_muser_gpInputs1.add(home_muser_lblName, 0, 0);
		home_muser_gpInputs1.add(home_muser_lblPhone, 0, 1);
		home_muser_gpInputs1.add(home_muser_lblAddress, 0, 2);
		home_muser_gpInputs1.add(home_muser_lblEmail, 0, 3);
		home_muser_gpInputs1.add(home_muser_lblPassword, 0, 4);
		home_muser_gpInputs1.add(home_muser_tfName, 1, 0);
		home_muser_gpInputs1.add(home_muser_tfPhone, 1, 1);
		home_muser_gpInputs1.add(home_muser_tfAddress, 1, 2);
		home_muser_gpInputs1.add(home_muser_tfEmail, 1, 3);
		home_muser_gpInputs1.add(home_muser_tfPassword, 1, 4);
		
		home_muser_gpInputs2.add(home_muser_lblGender, 0, 0, 1, 2);
		home_muser_gpInputs2.add(home_muser_lblRole, 0, 2, 1, 2);
		home_muser_gpInputs2.add(home_muser_lblDob, 0, 4, 1, 1);
		home_muser_gpInputs2.add(home_muser_rbMale, 1, 0, 1, 1);
		home_muser_gpInputs2.add(home_muser_rbFemale, 1, 1, 1, 1);
		home_muser_gpInputs2.add(home_muser_rbUser, 1, 2, 1, 1);
		home_muser_gpInputs2.add(home_muser_rbAdmin, 1, 3, 1, 1);
		home_muser_gpInputs2.add(home_muser_dpDob, 1, 4, 1, 1);
		
		home_muser_rbMale.setToggleGroup(home_muser_tgGender);
		home_muser_rbFemale.setToggleGroup(home_muser_tgGender);
		
		home_muser_rbUser.setToggleGroup(home_muser_tgRole);
		home_muser_rbAdmin.setToggleGroup(home_muser_tgRole);
		
		home_muser_btnInsert.setMinWidth(90);
		home_muser_btnInsert.setMinHeight(50);
		home_muser_btnUpdate.setMinWidth(90);
		home_muser_btnUpdate.setMinHeight(50);
		home_muser_btnDelete.setMinWidth(90);
		home_muser_btnDelete.setMinHeight(50);
		
		home_muser_vbButtons.getChildren().addAll(home_muser_btnInsert, home_muser_btnUpdate, home_muser_btnDelete);
		home_muser_vbButtons.setAlignment(Pos.CENTER);
		
		HBox.setHgrow(home_muser_vbButtons, Priority.ALWAYS);
		HBox.setHgrow(home_muser_gpInputs1, Priority.ALWAYS);
		HBox.setHgrow(home_muser_gpInputs2, Priority.ALWAYS);
		
		home_muser_tvUser.getSelectionModel().selectedItemProperty().addListener((item, oldval, newval) -> {
			if(item != null && item.getValue() != null) {
				User u = item.getValue();
				home_muser_tfName.setText(u.getName());
				home_muser_tfPhone.setText(u.getPhone());
				home_muser_tfAddress.setText(u.getAddress());
				home_muser_tfEmail.setText(u.getEmail());
				home_muser_tfPassword.setText(u.getPassword());
				home_muser_tgGender.selectToggle(u.getGender().equals("Male") ? home_muser_rbMale : home_muser_rbFemale);
				home_muser_tgRole.selectToggle(u.getRole().equals("user") ? home_muser_rbUser : home_muser_rbAdmin);
				home_muser_dpDob.setValue(u.getDob().toLocalDate());
			}
		});
		
		home_muser_btnInsert.setOnAction(e -> {
			if(home_muser_tfEmail.getText().isBlank() || home_muser_tfName.getText().isBlank() || home_muser_tfPhone.getText().isBlank() || home_muser_tfPassword.getText().isBlank() || home_muser_tfAddress.getText().isBlank() || home_muser_dpDob.getValue() == null) {
				error("All fields must be filled!");
				return;
			}
			String email = home_muser_tfEmail.getText();
			String password = home_muser_tfPassword.getText();
			String name = home_muser_tfName.getText();
			String phone = home_muser_tfPhone.getText();
			String address = home_muser_tfAddress.getText();
			String id = User.nextID();
			Date dob = Date.valueOf(home_muser_dpDob.getValue());
			if(home_muser_tgGender.getSelectedToggle() == null) {
				error("Gender must be selected!");
				return;
			}
			if(home_muser_tgRole.getSelectedToggle() == null) {
				error("Role must be selected!");
				return;
			}
			
			String gender = ((RadioButton)home_muser_tgGender.getSelectedToggle()).getText();
			String role = ((RadioButton)home_muser_tgRole.getSelectedToggle()).getText().toLowerCase();
			
			if(!isAlphaNum(password)) {
				error("Password must be alphanumeric!");
				return;
			}
			if(password.length() < 6 || password.length() > 12) {
				error("Password must be between 6 - 12 chars!");
				return;
			}
			if(!emailFormat(email)) {
				error("Email must be in a valid format!");
				return;
			}
			if(home_muser_dpDob.getValue() == null || dob == null) {
				error("Date of birth must be filled!");
				return;
			}
			Date now = Date.valueOf(LocalDate.now());
			System.out.println(dob + " | " + now + " | " + dob.before(now));
			if(!dob.before(now)){
				error("Date of birth must be before today!");
				return;
			}
			
			if(!address.endsWith("Street")) {
				error("Address must end with 'Street'!");
				return;
			}
			if(phone.length() < 10 || phone.length() > 13) {
				error("Phone Number must be between 10 - 13 chars!");
				return;
			}
			if(!isNumeric(phone)) {
				error("Phone Number must be numeric!");
				return;
			}

			if(name.length() < 5 || name.length() > 30) {
				error("Username must be between 5 - 30 chars!");
				return;
			}
			
			User u = new User(id, name, phone, address, email, password, gender, dob, role);
			u.insert();
			
			home_muser_tvUser.getItems().add(u);
			
			for(Node n : home_muser_gpInputs1.getChildren()) {
				if(n instanceof TextInputControl) {
					((TextInputControl) n).clear();
				}
			}
			
			for(Node n : home_muser_gpInputs2.getChildren()) {
				if(n instanceof RadioButton) {
					((RadioButton) n).setSelected(false);
				}
				home_muser_dpDob.setValue(null);
			}
		});
		
		home_muser_btnUpdate.setOnAction(e -> {
			if(home_muser_tvUser.getSelectionModel().getSelectedItem() != null) {
				User u = home_muser_tvUser.getSelectionModel().getSelectedItem();
				if(home_muser_tfEmail.getText().isBlank() || home_muser_tfName.getText().isBlank() || home_muser_tfPhone.getText().isBlank() || home_muser_tfPassword.getText().isBlank() || home_muser_tfAddress.getText().isBlank() || home_muser_dpDob.getValue() == null) {
					error("All fields must be filled!");
					return;
				}
				String email = home_muser_tfEmail.getText();
				String password = home_muser_tfPassword.getText();
				String name = home_muser_tfName.getText();
				String phone = home_muser_tfPhone.getText();
				String address = home_muser_tfAddress.getText();
				String id = u.getId();
				Date dob = Date.valueOf(home_muser_dpDob.getValue());
				if(home_muser_tgGender.getSelectedToggle() == null) {
					error("Gender must be selected!");
					return;
				}
				if(home_muser_tgRole.getSelectedToggle() == null) {
					error("Role must be selected!");
					return;
				}
				
				String gender = ((RadioButton)home_muser_tgGender.getSelectedToggle()).getText();
				String role = ((RadioButton)home_muser_tgRole.getSelectedToggle()).getText().toLowerCase();
				
				if(!isAlphaNum(password)) {
					error("Password must be alphanumeric!");
					return;
				}
				if(password.length() < 6 || password.length() > 12) {
					error("Password must be between 6 - 12 chars!");
					return;
				}
				if(!emailFormat(email)) {
					error("Email must be in a valid format!");
					return;
				}
				if(home_muser_dpDob.getValue() == null || dob == null) {
					error("Date of birth must be filled!");
					return;
				}
				Date now = Date.valueOf(LocalDate.now());
				System.out.println(dob + " | " + now + " | " + dob.before(now));
				if(!dob.before(now)){
					error("Date of birth must be before today!");
					return;
				}
				
				if(!address.endsWith("Street")) {
					error("Address must end with 'Street'!");
					return;
				}
				if(phone.length() < 10 || phone.length() > 13) {
					error("Phone Number must be between 10 - 13 chars!");
					return;
				}
				if(!isNumeric(phone)) {
					error("Phone Number must be numeric!");
					return;
				}

				if(name.length() < 5 || name.length() > 30) {
					error("Username must be between 5 - 30 chars!");
					return;
				}
				
				User u2 = new User(id, name, phone, address, email, password, gender, dob, role);
				u2.update();
				
				home_muser_tvUser.getItems().clear();
				home_muser_tvUser.getItems().addAll(User.getAll());
			}else {
				error("Select a user first to update!");
			}
			
		});
		
		home_muser_btnDelete.setOnAction(e -> {
			if(home_muser_tvUser.getSelectionModel().getSelectedItem() != null) {
				User u = home_muser_tvUser.getSelectionModel().getSelectedItem();
				
				u.delete();
				home_muser_tvUser.getItems().remove(u);
			}else {
				error("Select a user first to delete!");
			}
		});
		
		
		//Home Content: Manage Products
		home_mprod_tvProduct.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		home_mprod_tvProduct.getColumns().addAll(Product.getTableColumns());
		home_mprod_vbMain.getChildren().addAll(home_mprod_tvProduct, home_mprod_gpLayout);
		
		VBox.setVgrow(home_mprod_gpLayout, Priority.ALWAYS);
		home_mprod_gpLayout.setVgap(40);
		home_mprod_gpLayout.setHgap(40);
		home_mprod_gpLayout.setPadding(new Insets(40));
		
		home_mprod_hbName.setSpacing(5);
		home_mprod_hbPrice.setSpacing(5);
		home_mprod_hbStock.setSpacing(5);
		home_mprod_hbType.setSpacing(5);
		home_mprod_hbButtons.setSpacing(15);
		HBox.setHgrow(home_mprod_btnInsert, Priority.ALWAYS);
		HBox.setHgrow(home_mprod_btnUpdate, Priority.ALWAYS);
		HBox.setHgrow(home_mprod_btnDelete, Priority.ALWAYS);
		GridPane.setHgrow(home_mprod_hbButtons, Priority.ALWAYS);
		GridPane.setHgrow(home_mprod_hbName, Priority.ALWAYS);
		GridPane.setHgrow(home_mprod_hbStock, Priority.ALWAYS);
		GridPane.setHgrow(home_mprod_hbType, Priority.ALWAYS);
		GridPane.setHgrow(home_mprod_hbButtons, Priority.ALWAYS);
		
		home_mprod_hbButtons.setAlignment(Pos.CENTER);
		
		home_mprod_btnInsert.setMinWidth(90);
		home_mprod_btnInsert.setMinHeight(50);
		home_mprod_btnUpdate.setMinWidth(90);
		home_mprod_btnUpdate.setMinHeight(50);
		home_mprod_btnDelete.setMinWidth(90);
		home_mprod_btnDelete.setMinHeight(50);
		
		home_mprod_hbName.getChildren().addAll(home_mprod_lblName, home_mprod_tfName);
		home_mprod_hbPrice.getChildren().addAll(home_mprod_lblPrice, home_mprod_tfPrice);
		home_mprod_hbStock.getChildren().addAll(home_mprod_lblStock, home_mprod_spStock);
		home_mprod_hbType.getChildren().addAll(home_mprod_lblType, home_mprod_cbPType);
		
		home_mprod_cbPType.getItems().addAll(ProductType.getAll());
		
		home_mprod_hbButtons.getChildren().addAll(home_mprod_btnInsert, home_mprod_btnUpdate, home_mprod_btnDelete);
		
		home_mprod_gpLayout.add(home_mprod_hbName, 0, 0, 1, 1);
		home_mprod_gpLayout.add(home_mprod_hbPrice, 1, 0, 1, 1);
		home_mprod_gpLayout.add(home_mprod_hbStock, 0, 1, 1, 1);
		home_mprod_gpLayout.add(home_mprod_hbType, 1, 1, 1, 1);
		home_mprod_gpLayout.add(home_mprod_hbButtons, 0, 2, 2, 2);
		
		home_mprod_tvProduct.getSelectionModel().selectedItemProperty().addListener((item, oldval, newval) -> {
			if(item != null && item.getValue() != null) {
				Product p = item.getValue();
				home_mprod_tfName.setText(p.getName());
				home_mprod_tfPrice.setText("" + p.getPrice());
				home_mprod_spStock.getValueFactory().setValue(p.getStock());
				home_mprod_cbPType.getSelectionModel().select(new ProductType(p.getPtypeid(), p.getPtypename()));
			}
		});
		
		home_mprod_btnInsert.setOnAction(e -> {
			if(home_mprod_tfName.getText().isBlank() || home_mprod_tfPrice.getText().isBlank() || home_mprod_spStock.getValue() == null || home_mprod_cbPType.getSelectionModel().getSelectedItem() == null) {
				error("All fields must be filled!");
				return;
			}
			
			if(!isNumeric(home_mprod_tfPrice.getText())) {
				error("Price must be numeric");
				return;
			}
			
			String name = home_mprod_tfName.getText();
			int price = Integer.parseInt(home_mprod_tfPrice.getText());
			int stock = home_mprod_spStock.getValue();
			
			if(home_mprod_cbPType.getValue() == null) {
				error("Product Type must be selected!");
				return;
			}
			
			String ptypeid = home_mprod_cbPType.getValue().getId();
			
			if(price <= 0) {
				error("Price must be greater than 0!");
				return;
			}
			
			if(name.length() < 5 || name.length() > 30) {
				error("Product Name must be between 5 - 30 chars!");
				return;
			}
			
			Product p = new Product(Product.nextID(), ptypeid, name, price, stock);
			p.insert();
			
			home_mprod_tvProduct.getItems().add(p);
			
			home_mprod_tfName.clear();
			home_mprod_tfPrice.clear();
			home_mprod_spStock.getValueFactory().setValue(0);
			home_mprod_cbPType.getSelectionModel().clearSelection();
		});
		
		home_mprod_btnUpdate.setOnAction(e -> {
			if(home_mprod_tvProduct.getSelectionModel().getSelectedItem() != null) {
				Product p = home_mprod_tvProduct.getSelectionModel().getSelectedItem();
				if(home_mprod_tfName.getText().isBlank() || home_mprod_tfPrice.getText().isBlank() || home_mprod_spStock.getValue() == null || home_mprod_cbPType.getSelectionModel().getSelectedItem() == null) {
					error("All fields must be filled!");
					return;
				}
				
				if(!isNumeric(home_mprod_tfPrice.getText())) {
					error("Price must be numeric");
					return;
				}
				
				String name = home_mprod_tfName.getText();
				int price = Integer.parseInt(home_mprod_tfPrice.getText());
				int stock = home_mprod_spStock.getValue();
				
				if(home_mprod_cbPType.getValue() == null) {
					error("Product Type must be selected!");
					return;
				}
				
				String ptypeid = home_mprod_cbPType.getValue().getId();
				
				if(price <= 0) {
					error("Price must be greater than 0!");
					return;
				}
				
				if(name.length() < 5 || name.length() > 30) {
					error("Product Name must be between 5 - 30 chars!");
					return;
				}
				
				Product p2 = new Product(p.getId(), ptypeid, name, price, stock);
				p2.update();
				
				home_mprod_tvProduct.getItems().clear();
				home_mprod_tvProduct.getItems().addAll(Product.getAll());
			}else {
				error("Select a product first to update!");
			}
		});
		
		home_mprod_btnDelete.setOnAction(e -> {
			if(home_mprod_tvProduct.getSelectionModel().getSelectedItem() != null) {
				Product p = home_mprod_tvProduct.getSelectionModel().getSelectedItem();
				p.delete();
			}else {
				error("Select a product first to delete!");
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnectDB.start();
		
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		mainStage = arg0;
		setUp();
		
		openLogin();
		
		mainStage.setTitle("G&M");
		mainStage.show();
	}

}
