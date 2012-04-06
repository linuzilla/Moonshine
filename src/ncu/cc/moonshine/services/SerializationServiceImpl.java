package ncu.cc.moonshine.services;

import org.springframework.stereotype.Service;

import flexjson.JSONSerializer;

@Service
public class SerializationServiceImpl implements SerializationService {
	@Override
	public String jsonSerialize(Object target) {
		return new JSONSerializer().prettyPrint(true).serialize(target);
	}
}
