package dev.library.admin.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class AdminDTO {

    @NotBlank(message = "공백 혹은 Null")
    private String adminId;

    @NotBlank(message = "공백 혹은 Null")
    private String adminPw;

}
