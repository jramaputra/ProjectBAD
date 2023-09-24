import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class TransactionHeader extends DBEntity{
	private String id;
	private String userId;
	private Date date;
	
	public TransactionHeader(String id, String userId, Date date) {
		super("transaction_header");
		this.id = id;
		this.userId = userId;
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public static String nextID() {
		String maxId = "";
		try {
			ResultSet rs = ConnectDB.getStatement().executeQuery("SELECT id FROM transaction_header ORDER BY id DESC LIMIT 1");
			if(rs.next()) {
				maxId = rs.getString("id");
			}else return "TR001";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String nextId = String.format("TR%03d", Integer.parseInt(maxId.substring(2)) + 1);
		
		return nextId;
	}
	
	public static List<TransactionHeader> getAll(String userId){
		ArrayList<TransactionHeader> a = new ArrayList<>();
		
		try {	
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("SELECT * FROM transaction_header WHERE user_id = ?");
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String transactionId = rs.getString("id");
				String user_Id = rs.getString("user_id");
				Date date = rs.getDate("tr_date");
				
				a.add(new TransactionHeader(transactionId, user_Id, date));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return a;
	}
	
	public void insert() {
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("INSERT INTO transaction_header (id, user_id, tr_date) VALUES (?, ?, ?)");
			ps.setString(1, this.id);
			ps.setString(2, this.userId);
			ps.setDate(3, this.date);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getTotalPrice() {
		int total = 0;
		
		TransactionDetail td = new TransactionDetail(null, null, null);
		String[] conditions = {"tr_id"};
		String[] values = {this.id};
		
		ResultSet rs = ConnectDB.getAll(td, conditions, values);
		try {
			while(rs.next()) {
				TransactionDetail n = new TransactionDetail(rs.getString("tr_id"), rs.getString("product_id"), rs.getInt("quantity"));

				total += Product.get(n.getProductId()).getPrice() * n.getQuantity();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return total;
	}
	
	public static List<TableColumn<TransactionHeader, ?>> getTableColumns(){
		List<TableColumn<TransactionHeader, ?>> cols = new ArrayList<>();
		String[] types = {"string", "date"};
		String[] attributes = {"id", "date"};
		String[] titles = {"ID", "Transaction Date"};
		
		for(int i = 0; i < attributes.length; i++) {
			TableColumn<TransactionHeader, ?> col = null;
			if(types[i].equals("string")) {
				col = new TableColumn<TransactionHeader, String>(titles[i]);
			}else{
				col = new TableColumn<TransactionHeader, Date>(titles[i]);
			}
			col.setCellValueFactory(new PropertyValueFactory<>(attributes[i]));
			cols.add(col);
		}
		
		return cols;
	}
	
	
}
