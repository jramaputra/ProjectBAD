import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class TransactionDetail extends DBEntity{
	private String transactionId;
	private String productId;
	private Integer quantity;
	private String productName;
	private String productTypeName;
	
	public TransactionDetail(String transactionId, String productId, Integer quantity) {
		super("transaction_detail");
		this.transactionId = transactionId;
		this.productId = productId;
		this.quantity = quantity;
		Product p = getProduct();
		this.productTypeName = p.getType().getName();
		this.productName = p.getName();
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Product getProduct() {
		return Product.get(this.productId);
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public static ArrayList<TransactionDetail> getAll(String transId){
		ArrayList<TransactionDetail> dets = new ArrayList<>();
		
		TransactionDetail td = new TransactionDetail(null, null, null);
		String[] conditions = {"tr_id"};
		String[] values = {transId};
		
		ResultSet rs = ConnectDB.getAll(td, conditions, values);
		try {
			while(rs.next()) {
				TransactionDetail n = new TransactionDetail(rs.getString("tr_id"), rs.getString("product_id"), rs.getInt("quantity"));

				dets.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dets;
	}
	
	public void insert() {
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("INSERT INTO transaction_detail (tr_id, product_id, quantity) VALUES (?, ?, ?)");
			ps.setString(1, this.transactionId);
			ps.setString(2, this.productId);
			ps.setInt(3, this.quantity);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<TableColumn<TransactionDetail, ?>> getTableColumns(){
		List<TableColumn<TransactionDetail, ?>> cols = new ArrayList<>();
		String[] types = {"string", "string", "string", "integer"};
		String[] attributes = {"productId", "productName", "productTypeName", "quantity"};
		String[] titles = {"Product ID", "Product Name", "Product Type", "Quantity"};
		
		for(int i = 0; i < attributes.length; i++) {
			TableColumn<TransactionDetail, ?> col = null;
			if(types[i].equals("string")) {
				col = new TableColumn<TransactionDetail, String>(titles[i]);
			}else{
				col = new TableColumn<TransactionDetail, Date>(titles[i]);
			}
			col.setCellValueFactory(new PropertyValueFactory<>(attributes[i]));
			cols.add(col);
		}
		
		return cols;
	}
}
