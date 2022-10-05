package dev.library.user.presentation;

import dev.library.admin.application.ReturnBarcodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class returnBarcodeTest {

    @Autowired
    ReturnBarcodeService returnBarcodeService;

    @Test
    public void returnBarcodeTest(){
         String resultBook = returnBarcodeService.returnBookBarcode("book-g-1");
         String resultUser = returnBarcodeService.returnUserBarcode("user-g-1");

         System.out.println(resultBook);

         System.out.println(resultUser);

    }

}
