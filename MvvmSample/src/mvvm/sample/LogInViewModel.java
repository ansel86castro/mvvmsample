package mvvm.sample;

import com.enterlib.exceptions.ValidationException;
import com.enterlib.fields.Field;
import com.enterlib.fields.Form;
import com.enterlib.mvvm.Command;
import com.enterlib.mvvm.DataViewModel;
import com.enterlib.mvvm.IDataView;
import com.enterlib.threading.IWorkPost;
import com.enterlib.validations.ErrorInfo;

/***/
public class LogInViewModel extends DataViewModel {
	public static final int GO_HOME = 0;

	/**The Model that constains out login data*/
	public static class LoginModel{
		public String Username;
		public String Password;
	}	
	
	LoginModel login;
	
	/**Define the property Login*/ 
	public LoginModel getLogin() {
		return login;
	}

	public LogInViewModel(IDataView view) {
		super(view);		
	}

	/** here perform the load of data asynchronously
	    you could for example retrieve the last logged user from a repository*/
	@Override
	protected boolean loadAsync() throws Exception {
		login =new LoginModel();
		return true;
	}
	
	/**The comamnd that is binded to the Login Button in the layout*/
	public Command LoginCommand = new Command() {
		
		public void invoke(Object invocator, Object args) {	
			//Gets the field that invoke the command
			Field field = (Field) invocator;
			Form form = field.getForm();
			
			//validates the input data
			if(form.validate()){
				
				//If the data is valid then updates the login's fields
				form.updateSource();
				
				doAsyncWork("Verifing user's credentials...", new IWorkPost() {						
					@Override
					public boolean runWork() throws Exception {
						
						//simulating a time consuming operation such as consulting a sqlite database or invoking 
						//a REST services
						Thread.sleep(2000);
						
						if(!login.Username.equalsIgnoreCase("admin")){
							
							//To indicate a bussines validation failure, raise a validation exception 
							throw new ValidationException(new ErrorInfo()
								.addError("Username", "the User you speficified can not be found in our database."));
						}
						
						//always return true for call onWorkFinish
						return true;
					}						
					@Override
					public void onWorkFinish(Exception workException) {
						if(workException!=null)
							return;
						
						//if no exception was thrown go to the Home Activity
						getNavigator().navigateTo(GO_HOME, null, null);
					}
				});										
				
			}								
		}
	};

}
