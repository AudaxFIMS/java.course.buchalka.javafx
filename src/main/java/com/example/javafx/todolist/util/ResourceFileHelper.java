package com.example.javafx.todolist.util;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class ResourceFileHelper {
	public static URL getResource(Class<?> resourceClass, String resourceName) {
		URL resource = resourceClass.getResource(resourceName);
		
		if (resource != null) {
			return resource;
		}
		
		try {
			Path path = Paths.get(resourceClass.getResource("").toURI());
			Path parent = path.getParent();
			while (resource == null && parent != null) {
				resource = parent.resolve(resourceName).toUri().toURL();
				parent = parent.getParent();
			}
			if (resource != null) {
				return resource;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
}
