package mvvm.sample;

import java.util.Random;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enterlib.data.ICollectionRepository;
import com.enterlib.databinding.BindingResources;
import com.enterlib.exceptions.InvalidOperationException;
import com.enterlib.mvvm.DataViewModel;
import com.enterlib.mvvm.GenericListFragment;

/**
	The GenericListFragment is a View that support a ListField and provides out of the box and
	functionalities for sorting
*/
public class ProductListFragment extends GenericListFragment<ProductListItem> {

	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {	
		return inflater.inflate(R.layout.fragment_products_list, null);
	}
	
	@Override
	protected ICollectionRepository<ProductListItem> createRepository() {
		return new ICollectionRepository<ProductListItem>() {
			
			@Override
			public ProductListItem[] getItems() throws InvalidOperationException {
				ProductListItem[]products = new ProductListItem[20];
				Random ran = new Random();
				
				for (int i = 0; i < products.length; i++) {
					ProductListItem p = new ProductListItem();
					p.id = i +1;
					p.name ="Product "+ String.valueOf(p.id);
					p.price = ran.nextDouble() * 1000;
					p.currency = "USD";
					p.stock = ran.nextInt(50);
					products[i] = p;
				}
				
				//Simulating a time consuming operation
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return products;
			}
		};
	}
	
	@Override
	protected DataViewModel createViewModel(Bundle savedStateInstance) {
		ProductListViewModel vm = new ProductListViewModel(this, createRepository());
		setSelectionCommand(vm.SelectCommand);
		return vm;
	}

	//creates the Resources for the Bindings 
 	//In this case we put the class for the R.layout, this is necessary for the
	//binding infrastructure to locate the product_item_template layout applied 
	//to each ProductListItem
	@Override
	protected BindingResources getBindingResources() {
		return new BindingResources()
		.put("R.string", R.string.class)
		.put("R.layout", R.layout.class);
	}
	
	@Override
	protected String getFilterHint() {	
		return "Name";
	}

}
