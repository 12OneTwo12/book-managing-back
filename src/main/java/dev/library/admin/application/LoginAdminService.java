package dev.library.admin.application;

import dev.library.admin.dto.AdminDTO;
import dev.library.admin.dto.AdminIdDTO;

public interface LoginAdminService {

    public AdminIdDTO loginAdmin(AdminDTO adminDTO);

}
