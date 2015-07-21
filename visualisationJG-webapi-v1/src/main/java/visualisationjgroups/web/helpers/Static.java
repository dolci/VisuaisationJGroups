package visualisationjgroups.web.helpers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 *  méthodes statiques utilitaires  
 * @author Rabaa
 *
 */
public class Static {
 
 /**
  *  
  */
	public Static() {
	}

	// liste des messages d'erreur d'une exception
	public static List<String> getErreursForException(Exception exception) {
		// on récupère la liste des messages d'erreur de l'exception
		Throwable cause = exception;
		List<String> erreurs = new ArrayList<String>();
		while (cause != null) {
			erreurs.add(cause.getMessage());
			cause = cause.getCause();
		}
		return erreurs;
	}
   
	
}
