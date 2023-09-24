import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class Cart extends DBEntity{
	private String customerId;
	private String productId;
	private Integer quantity;
	
	public Cart(String customerId, String productId, Integer quantity) {
		super("ms_cart");
		this.customerId = customerId;
		this.productId = productId;
		this.quantity = quantity;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public static ArrayList<Cart> getAll(String userId){
		ArrayList<Cart> carts = new ArrayList<>();
		
		Cart c = new Cart(null, null, null);
		String[] conditions = {"cust_id"};
		String[] values = {userId};
		
		ResultSet rs = ConnectDB.getAll(c, conditions, values);
		try {
			while(rs.next()) {
				Cart n = new Cart(rs.getString("cust_id"), rs.getString("product_id"), rs.getInt("quantity"));
				
				carts.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return carts;
	}
	
	public void insert() {
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("INSERT INTO ms_cart (cust_id, product_id, quantity) VALUES (?, ?, ?)");
			ps.setString(1, this.customerId);
			ps.setString(2, this.productId);
			ps.setInt(3, this.quantity);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete() {
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("DELETE FROM ms_cart WHERE product_id = ? AND cust_id = ?");
			ps.setString(1, this.productId);
			ps.setString(2,  this.customerId);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static List<TableColumn<Cart, ?>> getTableColumns(){
		List<TableColumn<Cart, ?>> cols = new ArrayList<>();
		String[] types = {"string", "integer"};
		String[] attributes = {"productId", "quantity"};
		String[] titles = {"ID", "Quantity"};
		
		for(int i = 0; i < attributes.length; i++) {
			TableColumn<Cart, ?> col = null;
			if(types[i].equals("string")) {
				col = new TableColumn<Cart, String>(titles[i]);
			}else{
				col = new TableColumn<Cart, Integer>(titles[i]);
			}
			col.setCellValueFactory(new PropertyValueFactory<>(attributes[i]));
			cols.add(col);
		}
		
		return cols;
	}
	
}
