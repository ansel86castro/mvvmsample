package mvvm.sample;

import android.view.View;
import android.widget.AdapterView;

import com.enterlib.data.ICollectionRepository;
import com.enterlib.fields.Field;
import com.enterlib.mvvm.CollectionViewModel;
import com.enterlib.mvvm.IMessageReporter;
import com.enterlib.mvvm.IReporterDataView;
import com.enterlib.mvvm.SelectionCommand;

public class ProductListViewModel extends CollectionViewModel<ProductListItem> {

	public ProductListViewModel(IReporterDataView view,
			ICollectionRepository<ProductListItem> repository) {
		super(view, repository);
		
	}
	
	public SelectionCommand SelectCommand = new SelectionCommand() {
		
		@Override
		public void invoke(Field field, AdapterView<?> adapterView, View itemView,
				int position, long id) {
			ProductListItem item = (ProductListItem) adapterView.getItemAtPosition(position);
			((IMessageReporter) getView()).showMessage("Item Selected "+item.name);
			
		}
	};

}
