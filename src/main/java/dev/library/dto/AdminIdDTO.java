package dev.library.dto;

import dev.library.user.domain.Admin;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminIdDTO {
    private String adminId;

    public AdminIdDTO(Admin admin){
        this.adminId = admin.getAdminId();
    }
}
