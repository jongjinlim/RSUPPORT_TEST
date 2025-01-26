package com.example.rsupporttest.error.response;


import com.example.rsupporttest.error.CoreErrorType;
import com.example.rsupporttest.error.ErrorMessage;

/**
 * API 호출을 통한 모든 응답갑의 형식을 정의합니다.
 *
 * @param data result: SUCCESS 시 응답에서 제공하고자 하는 정보
 * @param error result: ERROR 시 에러에 대한 정보
 * @param <D>
 */
public record CoreResponse<D>(D data, ErrorMessage error) {

    public static CoreResponse<?> success() {
        return new CoreResponse<>(null, null);
    }

    public static <S> CoreResponse<S> success(S data) {
        return new CoreResponse<>(data, null);
    }

    public static CoreResponse<?> error(CoreErrorType error) {
        return new CoreResponse<>(null, ErrorMessage.from(error));
    }

    public static CoreResponse<?> error(CoreErrorType error, Object errorData) {
        return new CoreResponse<>(null, ErrorMessage.from(error, errorData));
    }
}
