package ncu.cc.webdev.services;

import java.io.IOException;

public interface IClassFinder {
	public Class<?>[]	findClassesByPackage(String packageName) throws IOException;
	public boolean		isa(Class<?> myclazz, Class<?> clazz);
}
