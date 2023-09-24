import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class User extends DBEntity{
	private String id;
	private String name;
	private String phone;
	private String address;
	private String email;
	private String password;
	private String gender;
	private Date dob;
	private String role;
	
	public User(String id, String name, String phone, String address, String email, String password, String gender,
			Date dob, String role) {
		super("ms_customer");
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.dob = dob;
		this.role = role;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public static String nextID() {
		String maxId = "";
		try {
			ResultSet rs = ConnectDB.getStatement().executeQuery("SELECT id FROM ms_customer ORDER BY id DESC LIMIT 1");
			if(rs.next()) {
				maxId = rs.getString("id");
			}else return "US001";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String nextId = String.format("US%03d", Integer.parseInt(maxId.substring(2)) + 1);
		
		return nextId;
	}
	
	public static User get(String id) {
		User u = new User(null, null, null, null, null, null, null, null, null);
		
		ResultSet rs = ConnectDB.getById(u, "id", id);
		try {
			if(rs.next()) {
				u.setId(rs.getString("id"));
				u.setName(rs.getString("name"));
				u.setAddress(rs.getString("address"));
				u.setDob(rs.getDate("dob"));
				u.setEmail(rs.getString("email"));
				u.setGender(rs.getString("gender"));
				u.setRole(rs.getString("role"));
				u.setPassword(rs.getString("password"));
				u.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u.getId() == null ? null : u;
	}
	
	public static User auth(String email, String password) {
		User u = new User(null, null, null, null, null, null, null, null, null);
		
		String[] conditions = {"email", "password"};
		String[] values = {email, password};
		
		ResultSet rs = ConnectDB.getAll(u, conditions, values);
		try {
			if(rs.next()) {
				u.setId(rs.getString("id"));
				u.setName(rs.getString("name"));
				u.setAddress(rs.getString("address"));
				u.setDob(rs.getDate("dob"));
				u.setEmail(rs.getString("email"));
				u.setGender(rs.getString("gender"));
				u.setRole(rs.getString("role"));
				u.setPassword(rs.getString("password"));
				u.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u.getId() == null ? null : u;
	}
	
	public static ArrayList<User> getAll(){
		ArrayList<User> users = new ArrayList<>();
		
		User u = new User(null, null, null, null, null, null, null, null, null);
		
		String[] conditions = {};
		String[] values = {};
		
		ResultSet rs = ConnectDB.getAll(u, conditions, values);
		try {
			while(rs.next()) {
				User n = new User(rs.getString("id"), rs.getString("name"), rs.getString("phone"), rs.getString("address"), rs.getString("email"), rs.getString("password"), rs.getString("gender"), rs.getDate("dob"), rs.getString("role"));

				users.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	
	public void insert() {
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("INSERT INTO ms_customer (id, name, phone, address, email, password, gender, dob, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, this.id);
			ps.setString(2, this.name);
			ps.setString(3, this.phone);
			ps.setString(4, this.address);
			ps.setString(5, this.email);
			ps.setString(6, this.password);
			ps.setString(7, this.gender);
			ps.setDate(8, this.dob);
			ps.setString(9, this.role);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("UPDATE ms_customer SET name = ?, phone = ?, address = ?, email = ?, password = ?, gender = ?, dob = ?, role = ? WHERE id = ?");
			ps.setString(1, this.name);
			ps.setString(2, this.phone);
			ps.setString(3, this.address);
			ps.setString(4, this.email);
			ps.setString(5, this.password);
			ps.setString(6, this.gender);
			ps.setDate(7, this.dob);
			ps.setString(8, this.role);
			ps.setString(9, this.id);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete() {
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("DELETE FROM ms_customer WHERE id = ?");
			ps.setString(1, this.id);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<TableColumn<User, ?>> getTableColumns(){
		List<TableColumn<User, ?>> cols = new ArrayList<>();
		String[] types = {"string", "string", "string", "string", "string", "string", "string", "date", "string"};
		String[] attributes = {"id", "name", "phone", "address", "email", "password", "gender", "dob", "role"};
		String[] titles = {"ID", "User Name", "User Phone", "User Address", "User Email", "User Password", "User Gender", "User DOB", "Role"};
		
		for(int i = 0; i < attributes.length; i++) {
			TableColumn<User, ?> col = null;
			if(types[i].equals("string")) {
				col = new TableColumn<User, String>(titles[i]);
			}else{
				col = new TableColumn<User, Date>(titles[i]);
			}
			col.setCellValueFactory(new PropertyValueFactory<>(attributes[i]));
			cols.add(col);
		}
		
		return cols;
	}
	
}
