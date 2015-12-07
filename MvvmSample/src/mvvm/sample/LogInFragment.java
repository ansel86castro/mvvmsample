package mvvm.sample;

import com.enterlib.databinding.BindingResources;
import com.enterlib.mvvm.BindableFragment;
import com.enterlib.mvvm.DataViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LogInFragment extends BindableFragment {

	public LogInFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		return rootView;
	}

	@Override
	protected BindingResources getBindingResources() {
		return new BindingResources()
		.put("R.string", R.string.class);		
	}

	@Override
	protected DataViewModel createViewModel(Bundle savedInstanceState) {
		return new LogInViewModel(this);
	}
	
	@Override
	public void navigateTo(int requestCode, Bundle extras, Object data) {
		switch (requestCode) {
		case LogInViewModel.GO_HOME:
			startActivity(new Intent(getActivity(), HomeActivity.class));
			break;

		default:
			break;
		}
	}
}