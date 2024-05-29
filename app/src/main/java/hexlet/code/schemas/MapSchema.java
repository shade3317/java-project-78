package hexlet.code.schemas;

import java.util.Map;


public final class MapSchema<K, V> extends BaseSchema<Map<K, V>> {
    public MapSchema<K, V> sizeof(int size) {
        addRestrictions("sizeof", m -> m == null || m.size() == size);
        return this;
    }

    public MapSchema<K, V> shape(Map<K, BaseSchema<V>> schemas) {
        addRestrictions("shape", value -> value != null && schemas.entrySet().stream()
                .allMatch(entry -> entry.getValue().isValid(value.get(entry.getKey()))));
        return this;
    }
}
