package dev.library.user.application;

import dev.library.dto.AdminDTO;
import dev.library.dto.AdminIdDTO;
import dev.library.user.domain.Admin;
import dev.library.user.repository.AdminRepository;
import dev.library.utils.encrypt.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateAdminService {

    @Autowired
    AdminRepository adminRepository;

    public AdminIdDTO createAdmin(AdminDTO adminDTO){

        Encrypt encrypt = new Encrypt();
        String newSalt = encrypt.getSalt();

        String afterEncryptPw = encrypt.getEncrypt(adminDTO.getAdminPw(),newSalt);

        Admin newAdmin = Admin.Request.toEntity(Admin.Request.builder()
                                                             .adminId(adminDTO.getAdminId())
                                                             .adminPw(afterEncryptPw)
                                                             .build());
        newAdmin.setSalt(newSalt);

        Admin result = adminRepository.save(newAdmin);

        if(result != null){
            return new AdminIdDTO(result);
        } else {
            return null;
        }
    }

}
