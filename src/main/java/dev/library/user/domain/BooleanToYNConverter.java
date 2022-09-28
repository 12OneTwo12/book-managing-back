package dev.library.user.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * boolean isRentalable 필드용 설정
 * @Converter를 사용하면 엔티티의 데이터를 변환, DB에 저장 가능
 * Entity에서는 필드의 타입으로 boolean(true or false), DB에는 문자열('Y' or 'N') 값으로 저장하고 싶을 경우 처리해주는 변환기(Converter)
 * ref. https://joont92.github.io/jpa/%EC%97%94%ED%8B%B0%ED%8B%B0-%EA%B0%92%EC%9D%84-%EB%B3%80%ED%99%98%ED%95%B4%EC%84%9C-%EC%A0%80%EC%9E%A5%ED%95%98%EA%B8%B0-Converter/
 */
@Converter(autoApply = true)
public class BooleanToYNConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) { return "Y".equals(dbData); }
}
