package dev.library.admin.dto;

import dev.library.admin.domain.Admin;
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
