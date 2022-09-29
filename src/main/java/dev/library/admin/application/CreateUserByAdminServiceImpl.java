package dev.library.admin.application;

import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import dev.library.user.domain.User;
import dev.library.user.domain.UserId;
import dev.library.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

@Service
public class CreateUserByAdminServiceImpl implements CreateUserByAdminService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public String saveUser(User.Request request) {

        // 다음 ID number 생성 ex) 현재 User DB에 userId가 u-g-1까지 있을 경우 새로운 id는 'u-g-2'
        long numberOfRows = userRepository.count();
        UserId id = userRepository.nextUserId(numberOfRows);
        request.setUserId(id);

        User user = User.Request.toEntity(request);
        User result = userRepository.save(user);

        if (result == null){
            return null;
        } else {

            String newId = result.getId().getId();

            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, newId);
            generator.getParameters().setResolution(400);
            String addrs = "C:\\Users\\J\\Desktop\\project\\202209librarySystem\\librarySystem\\src\\main\\webapp\\resources\\barcodes\\";
            File file = new File(addrs + newId + ".png");
            try {
                generator.save(addrs + newId + ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addrs;
        }

    }
}
