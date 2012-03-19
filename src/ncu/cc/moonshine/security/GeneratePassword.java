package ncu.cc.moonshine.security;

import java.util.Scanner;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

public class GeneratePassword {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PasswordEncoder		encoder = new Md5PasswordEncoder();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Please enter password: ");
		System.out.println(encoder.encodePassword(scan.next(), null));
	}

}
