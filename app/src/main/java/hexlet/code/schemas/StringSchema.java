package hexlet.code.schemas;

import org.apache.commons.lang3.StringUtils;


public final class StringSchema extends BaseSchema<String> {
    @Override
    public StringSchema required() {
        addRestrictions("required", s -> !StringUtils.isEmpty(s));
        return this;
    }

    public StringSchema minLength(int minLength) {
        addRestrictions("minLength", s -> s == null || minLength <= s.length());
        return this;
    }

    public StringSchema contains(String text) {
        addRestrictions("contains", s -> s == null || s.contains(text));
        return this;
    }
}
