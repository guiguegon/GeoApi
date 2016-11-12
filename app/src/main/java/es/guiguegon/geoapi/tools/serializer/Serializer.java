package es.guiguegon.geoapi.tools.serializer;

import java.util.List;
/**
 * Created by guiguegon on 12/11/2016.
 */

/**
 * Serialized interface that defines the behaviour to encode data to and from JSON
 */
public interface Serializer {

    /**
     * Serialize an object to Json.
     *
     * @param object {@link Object} to serialize.
     */
    String serialize(Object object);

    /**
     * Deserialize a json representation of an object.
     *
     * @param serializedJson A json string to deserialize.
     * @return {@link T}
     */
    <T> T deserialize(String serializedJson, Class<T> clazz);

    /**
     * Serialize a list to Json.
     *
     * @param objects {@link Object} to serialize.
     */
    <T> String serializeList(List<T> objects);

    /**
     * Deserialize a json representation of a list of objects.
     *
     * @param serializedJson A json string to deserialize.
     * @return {@link List<T>}
     */
    <T> List<T> deserializeList(String serializedJson, Class<T> clazz);
}
