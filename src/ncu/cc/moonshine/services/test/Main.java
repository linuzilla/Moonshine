package ncu.cc.moonshine.services.test;

import com.google.gson.GsonBuilder;

import flexjson.JSONSerializer;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		
		a.setAint(1);
		a.setB(b);
		b.setBint(2);
		b.setA(a);
		
		System.out.println(new JSONSerializer().prettyPrint(true).serialize(a));
		System.out.println(new JSONSerializer().prettyPrint(true).serialize(b));
		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(a));
	}

}
