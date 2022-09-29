package dev.library.admin.application;

import dev.library.admin.dto.AdminDTO;
import dev.library.admin.dto.AdminIdDTO;

public interface CreateAdminService {

    public AdminIdDTO createAdmin(AdminDTO adminDTO);

}
