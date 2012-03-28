package ncu.cc.webdev.util;

import java.util.HashSet;
import java.util.Set;

public class StackTraceUtil {
	private static Set<String>	skipSet;
	private static Set<String>	stopSet;
	
	static {
		skipSet = new HashSet<String>();
		skipSet.add(StackTraceUtil.class.getName());
		
		stopSet = new HashSet<String>();
	}
	
	public static void print1(String message) {
		System.out.println(message);
		for (StackTraceElement ste: new Throwable().getStackTrace()) {
			if (skipSet.contains(ste.getClassName())) continue;
			
			System.out.println("        at " + 
					ste.getClassName() + "." + 
					ste.getMethodName() + 
					"(" + ste.getFileName() + ":" 
					+ ste.getLineNumber() + ")");
			break;
		}
	}
}
