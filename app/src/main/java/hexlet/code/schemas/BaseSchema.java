package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;


public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> restrictions = new LinkedHashMap<>();

    public boolean isValid(T object) {
        return restrictions.values().stream().allMatch(v -> v.test(object));
    }

    public BaseSchema<T> required() {
        restrictions.put("required", Objects::nonNull);
        return this;
    }
}
