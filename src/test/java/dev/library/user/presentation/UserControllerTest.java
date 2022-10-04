package dev.library.user.presentation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.library.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(UserController.class) // 인수로 전달된 대상 클래스만 로드하여 테스트
@DisplayName("UserController 클래스")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc; // mockMvc 생성

    @Autowired
    private ObjectMapper objectmapper;

//    @MockBean
//    CreateUserService createUserService;

    @Nested
    @DisplayName("새로운 User를 등록하는 registerUser(User.Request request)는")
    class Describe_registerUser {

        @Nested
        @DisplayName("회원가입 폼에 작성한 데이터를 전부 전달하면")
        class Context_with_value {

            @Test
            @DisplayName("회원가입이 성공하고, 가입된 userID를 반환한다.")
            void registerUser() throws Exception {
                // Given
                objectmapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                String requestJSONData = objectmapper.writeValueAsString(User.Request.builder().name("라이언").courseName("인공지능 13기"));

                // When
                mockMvc.perform( // perform(): 요청을 전송하는 역할, 반환 타입으로 ResultAction 객체를 받는데, 그 객체가 가진 andExpect() 메서드 사용 가능
                                post("/users") // HTTP 메서드 지정(get, post, put, delete()..), 인수로 요청 경로를 지정
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(requestJSONData))
                        .andExpect(MockMvcResultMatchers.status().isCreated())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                        .andDo(print());

//                verify(createUserService).saveUser();
            }

        }
    }


}