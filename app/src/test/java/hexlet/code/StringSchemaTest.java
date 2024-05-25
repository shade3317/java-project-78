package hexlet.code;

import hexlet.code.schemas.StringSchema;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public final class StringSchemaTest {
    @Test
    public void testStringSchema() {
        StringSchema schema = new Validator().string();

        assertThat(schema.isValid(null)).isEqualTo(true);
        assertThat(schema.isValid("")).isEqualTo(true);

        schema.required();
        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid("")).isEqualTo(false);
        assertThat(schema.isValid("War")).isEqualTo(true);
        assertThat(schema.isValid("So what am I fighting for")).isEqualTo(true);

        schema.minLength(25);
        assertThat(schema.isValid("Everything back and more")).isEqualTo(false);
        assertThat(schema.isValid("And I'm not gonna let this go")).isEqualTo(true);

        assertThat(schema.contains("war").isValid("I'm ready to settle the score")).isEqualTo(false);
        assertThat(schema.contains("war").isValid("Get ready cause this is war.")).isEqualTo(true);
    }
}
