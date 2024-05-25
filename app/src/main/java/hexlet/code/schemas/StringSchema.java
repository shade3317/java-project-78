package hexlet.code.schemas;

import org.apache.commons.lang3.StringUtils;


public final class StringSchema extends BaseSchema<String> {
    @Override
    public StringSchema required() {
        restrictions.put("required", s -> !StringUtils.isEmpty(s));
        return this;
    }

    public StringSchema minLength(int minLength) {
        restrictions.put("minLength", s -> s == null || minLength <= s.length());
        return this;
    }

    public StringSchema contains(String text) {
        restrictions.put("contains", s -> s == null || s.contains(text));
        return this;
    }
}
