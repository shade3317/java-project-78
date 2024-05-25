package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;


public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> restrictions = new LinkedHashMap<>();

    /**
     * Checks that all restrictions were respected
     * @param object to be checked
     * @return true if all restrictions were respected, otherwise false
     */
    public boolean isValid(T object) {
        return restrictions.values().stream().allMatch(r -> r.test(object));
    }

    /**
     * Add nonNull restrictions
     * @return this object type
     */
    public BaseSchema<T> required() {
        restrictions.put("required", Objects::nonNull);
        return this;
    }
}
