package validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Gender;

public class CheckValidate {
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public boolean CheckNumber(String str) {
		
		for(int i = 0;i<str.length();i++) {
			if(str.charAt(i) <48 || str.charAt(i)>57)
				return false;
		}
		return true;
	}
	
	public Gender CheckGender(String str) {
		
		str=str.toLowerCase().trim();
		
		if(str.equals("nu"))
			return Gender.FEMALE;
		if(str.equals("nam"))
			return Gender.MALE;
		
		return null;
	}
	
	public boolean CheckEmail(String email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
	}

}
