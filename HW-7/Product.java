import java.util.Calendar;

public class Product {

	private String productName;
	private double price;
	private Calendar saleDate;
	
	public Product (String name, Calendar date, double price) throws Exception {
		setProductName(name);
		saleDate = date;
		this.price = price;
	}
	
	public Product () {
		
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) throws Exception {
		if(productName.length()>=3)
			this.productName = productName;
		else throw new Exception ("The name of product must include at least 3 letters!");
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Calendar getSaleDate() {
		return saleDate;
	}
	
	public String stringCalendar () {
		return this.getSaleDate().getTime().toString();
	}

	public void setSaleDate(Calendar saleDate) {
		this.saleDate = saleDate;
	}
	
	public String toString () {
		return "[productName=" + this.getProductName() + ", transactionDate=" + this.stringCalendar() + ", price=" + this.getPrice() + "]";
	}
	
	
	
}
