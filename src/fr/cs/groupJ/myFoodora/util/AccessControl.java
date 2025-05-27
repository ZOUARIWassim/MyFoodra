package fr.cs.groupJ.myFoodora.util;
import fr.cs.groupJ.myFoodora.util.Role;
import fr.cs.groupJ.myFoodora.model.SysModel;
import fr.cs.groupJ.myFoodora.model.user.User;

public class AccessControl {
	
	public static boolean roleControl(Role role , SysModel model) {
		User currentUser = model.getCurrentUser();
		if (currentUser==null || !currentUser.getRole().equals(role)) {
			return false;
		}else{
			return true;
		}
	}
}
