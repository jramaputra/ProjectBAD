import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductType extends DBEntity{
	private String id;
	private String name;
	
	public ProductType(String id, String name) {
		super("ms_product_type");
		this.id = id;
		this.name = name;
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
	
	public static ProductType get(String id) {
		ProductType pt = new ProductType(null, null);
		
		ResultSet rs = ConnectDB.getById(pt, "id", id);
		try {
			if(rs.next()) {
				pt.setId(rs.getString("id"));
				pt.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pt;
	}
	
	public static ArrayList<ProductType> getAll(){
		ArrayList<ProductType> ptypes = new ArrayList<>();
		
		ProductType pt = new ProductType(null, null);
		String[] conditions = {};
		String[] values = {};
		
		ResultSet rs = ConnectDB.getAll(pt, conditions, values);
		
		try {
			while(rs.next()) {
				ProductType n = new ProductType(rs.getString("id"), rs.getString("name"));

				ptypes.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ptypes;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof ProductType) {
			return ((ProductType) obj).getId().equals(this.id) && ((ProductType) obj).getName().equals(this.name);
		}
		return false;
	}
}
