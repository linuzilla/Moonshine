package ncu.cc.moonshine.services;

import ncu.cc.moonshine.domain.Role;
import ncu.cc.moonshine.domain.User;

import org.springframework.stereotype.Service;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import flexjson.JSONSerializer;

@Service
public class SerializationServiceImpl implements SerializationService {
	private static final ExclusionStrategy	exclusionStrategy = new ExclusionStrategy() {
		@Override
		public boolean shouldSkipClass(Class<?> clazz) {
			return false;
		}

		@Override
		public boolean shouldSkipField(FieldAttributes field) {			
			if (field.getDeclaringClass() == User.class) {
				if ("roles".equals(field.getName())) {
					return true;
				}
			}

			return false;
		}
	};
	
	@Override
	public String jsonSerialize(Object target) {
		// return new Gson().toJson(target);
		return new JSONSerializer().prettyPrint(true).deepSerialize(target);
		// return new GsonBuilder().setPrettyPrinting().create().toJson(target);
		// return new GsonBuilder().setExclusionStrategies(exclusionStrategy).setPrettyPrinting().create().toJson(target);
		//return new JSONSerializer().prettyPrint(true).serialize(target);
	}
}
