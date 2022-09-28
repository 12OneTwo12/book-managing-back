package dev.library.user.application;

import dev.library.dto.AdminDTO;
import dev.library.dto.AdminIdDTO;
import dev.library.user.domain.Admin;
import dev.library.user.repository.AdminRepository;
import dev.library.utils.encrypt.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginAdminService {

    @Autowired
    AdminRepository adminRepository;

    public AdminIdDTO loginAdmin(AdminDTO adminDTO){

        Optional<Admin> admin = adminRepository.findByAdminId(adminDTO.getAdminId());

        if (admin.isEmpty()){
            return null;
        } else {

            Admin resultAdmin = admin.get();

            Encrypt encrypt = new Encrypt();
            String salt = resultAdmin.getSalt();

            if (resultAdmin.getAdminPw().equals(encrypt.getEncrypt(adminDTO.getAdminPw(),salt))){ // 만약 로그인 성공에 성공 했다면
                return new AdminIdDTO(resultAdmin);
            } else {
                return null;
            }

        }
    }

}
