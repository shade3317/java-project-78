package hexlet.code.schemas;

import java.util.Map;


public final class MapSchema<K, V> extends BaseSchema<Map<K, V>> {
    public MapSchema<K, V> sizeof(int size) {
        restrictions.put("sizeof", m -> m == null || m.size() == size);
        return this;
    }

    public MapSchema<K, V> shape(Map<K, BaseSchema<V>> schemas) {
        for (Map.Entry<K, BaseSchema<V>> entry : schemas.entrySet()) {
            K key = entry.getKey();
            BaseSchema<V> schema = entry.getValue();

            restrictions.put("shape", v -> v == null || schema.isValid(v.get(key)));
        }
        return this;
    }
}
