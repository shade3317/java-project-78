package hexlet.code;

import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public final class SchemaTest {
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

        schema.contains("war");
        assertThat(schema.isValid("I'm ready to settle the score")).isEqualTo(false);
        assertThat(schema.isValid("Get ready cause this is war.")).isEqualTo(true);
    }

    @Test
    public void testNumberSchema() {
        NumberSchema schema = new Validator().number();

        assertThat(schema.isValid(null)).isEqualTo(true);
        assertThat(schema.isValid(4)).isEqualTo(true);
        assertThat(schema.isValid(-6)).isEqualTo(true);

        schema.required();
        assertThat(schema.isValid(null)).isEqualTo(false);

        schema.positive();
        assertThat(schema.isValid(8)).isEqualTo(true);
        assertThat(schema.isValid(-10)).isEqualTo(false);

        schema.range(10, 15);
        assertThat(schema.isValid(12)).isEqualTo(true);
        assertThat(schema.isValid(20)).isEqualTo(false);
    }
}
