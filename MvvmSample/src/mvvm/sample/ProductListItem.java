package mvvm.sample;

public class ProductListItem {
	public int id;
	public String name;
	public double price;
	public String currency;
	public int stock;
	
	@Override
	public String toString() {	
		return name;
	}
}
