import java.util.ArrayList;
import java.util.Calendar;

public class Customer extends Person{

	ArrayList<Product> products;
	
	public Customer (int id, String firstName, String lastName, String gender, Calendar birthDate, String maritalStatus, String hasDriverLicence, ArrayList<Product> products) throws Exception {
		super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence);
		this.products = products;
	}
	
	public Customer (Person p, ArrayList<Product> products) throws Exception {
		super(p.getId(), p.getFirstName(), p.getLastName(), p.getGender(), p.getBirthDate(), p.getMaritalStatus(), p.getHasDriverLicence());
		this.products = products;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	@Override
	public String toString () {
		return "Customer " + "[id=" + this.getId() + ", products=" + productString() + "]" ;
	}
	
	public String productString () {
		String s = "Product Info ";
		for (int i = 0; i < getProducts().size(); i++) {
			s += getProducts().get(i).toString();
			if (i < getProducts().size() - 1)
				s += ", ";
		}
		return s;
	}
}
