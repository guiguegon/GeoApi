package es.guiguegon.geoapi.tools.serializer;
/**
 * Created by guiguegon on 06/10/2016.
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used as Serializer/Deserializer for objects using Gson library
 */
@Singleton
public class GsonSerializer implements Serializer {

    private final Gson gson;

    @Inject
    public GsonSerializer(Gson gson) {
        this.gson = gson;
    }

    /**
     * Serialize an object to Json.
     *
     * @param object {@link Object} to serialize.
     */
    public String serialize(Object object) {
        return gson.toJson(object);
    }

    /**
     * Deserialize a json representation of an object.
     *
     * @param serializedJson A json string to deserialize.
     * @return {@link T}
     */
    public <T> T deserialize(String serializedJson, Class<T> clazz) {
        return gson.fromJson(serializedJson, clazz);
    }

    /**
     * Serialize a list to Json.
     *
     * @param objects {@link Object} to serialize.
     */
    public <T> String serializeList(List<T> objects) {
        Type listType = new TypeToken<List<T>>() {
        }.getType();
        return gson.toJson(objects, listType);
    }

    /**
     * Deserialize a json representation of a list.
     *
     * @param serializedJson A json string to deserialize.
     * @return {@link T}
     */
    public <T> List<T> deserializeList(String serializedJson, Class<T> clazz) {
        return gson.fromJson(serializedJson, new ParameterizedListType<>(clazz));
    }

    /**
     * Helper class to deserialize generic list
     *
     * @param <T> the class of objects contained in a list
     */
    private class ParameterizedListType<T> implements ParameterizedType {

        private Class<?> wrapped;

        public ParameterizedListType(Class<T> wrapped) {
            this.wrapped = wrapped;
        }

        public Type[] getActualTypeArguments() {
            return new Type[] { wrapped };
        }

        public Type getRawType() {
            return List.class;
        }

        public Type getOwnerType() {
            return null;
        }
    }
}
