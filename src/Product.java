import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class Product extends DBEntity{
	private String id;
	private String ptypeid;
	private String ptypename;
	private String name;
	private Integer price;
	private Integer stock;
	
	public Product(String id, String ptypeid, String name, Integer price, Integer stock) {
		super("ms_product");
		this.id = id;
		this.ptypeid = ptypeid;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.ptypename = ProductType.get(this.ptypeid).getName();
	}
	
	public String getPtypename() {
		return ptypename;
	}

	public void setPtypename(String ptypename) {
		this.ptypename = ptypename;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPtypeid() {
		return ptypeid;
	}
	public void setPtypeid(String ptypeid) {
		this.ptypeid = ptypeid;
	}
	
	public ProductType getType() {
		return ProductType.get(this.ptypeid);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public static Product get(String id) {
		Product p = new Product(null, null, null, 0, 0);
		
		ResultSet rs = ConnectDB.getById(p, "id", id);
		try {
			if(rs.next()) {
				p.setId(rs.getString("id"));
				p.setName(rs.getString("name"));
				p.setPtypeid(rs.getString("type_id"));
				p.setPtypename(ProductType.get(p.getPtypeid()).getName());
				p.setPrice(rs.getInt("price"));
				p.setStock(rs.getInt("stock"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}
	
	public static String nextID() {
		String maxId = "";
		try {
			ResultSet rs = ConnectDB.getStatement().executeQuery("SELECT id FROM ms_product ORDER BY id DESC LIMIT 1");
			if(rs.next()) {
				maxId = rs.getString("id");
			}else return "PR001";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String nextId = String.format("PR%03d", Integer.parseInt(maxId.substring(2)) + 1);
		
		return nextId;
	}
	
	public static ArrayList<Product> getAll(){
		ArrayList<Product> prods = new ArrayList<>();
		
		Product p = new Product(null, null, null, 0, 0);
		
		String[] conditions = {};
		String[] values = {};
		
		ResultSet rs = ConnectDB.getAll(p, conditions, values);
		try {
			while(rs.next()) {
				Product n = new Product(rs.getString("id"), rs.getString("type_id"), rs.getString("name"), rs.getInt("price"), rs.getInt("stock"));

				prods.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prods;
	}
	
	public void insert() {
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("INSERT INTO ms_product (id, type_id, name, price, stock) VALUES (?, ?, ?, ?, ?)");
			ps.setString(1, this.id);
			ps.setString(2, this.ptypeid);
			ps.setString(3, this.name);
			ps.setInt(4, this.price);
			ps.setInt(5, this.stock);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("UPDATE ms_product SET type_id = ?, name = ?, price = ?, stock = ? WHERE id = ?");
			ps.setString(1, this.ptypeid);
			ps.setString(2, this.name);
			ps.setInt(3, this.price);
			ps.setInt(4, this.stock);
			ps.setString(5, this.id);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete() {
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("DELETE FROM ms_product WHERE id = ?");
			ps.setString(1, this.id);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<TableColumn<Product, ?>> getTableColumns(){
		List<TableColumn<Product, ?>> cols = new ArrayList<>();
		String[] types = {"string", "string", "string", "integer", "integer"};
		String[] attributes = {"id", "name", "ptypename", "price", "stock"};
		String[] titles = {"ID", "Product Name", "Product Type", "Price", "Stock"};
		
		for(int i = 0; i < attributes.length; i++) {
			TableColumn<Product, ?> col = null;
			if(types[i].equals("string")) {
				col = new TableColumn<Product, String>(titles[i]);
			}else{
				col = new TableColumn<Product, Integer>(titles[i]);
			}
			col.setCellValueFactory(new PropertyValueFactory<>(attributes[i]));
			cols.add(col);
		}
		
		return cols;
	}
}
