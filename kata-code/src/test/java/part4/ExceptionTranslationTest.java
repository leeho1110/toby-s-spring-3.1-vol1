package part4;

import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExceptionTranslationTest {

    @Test
    public void test_throwConvertedToProperException() throws DuplicateKeyException, SQLException {
        // given
        ExceptionTranslation exceptionTranslation = new ExceptionTranslation();

        // when & then
        assertThatThrownBy(exceptionTranslation::throwConvertedToProperException).isInstanceOf(DuplicateKeyException.class);
    }
}