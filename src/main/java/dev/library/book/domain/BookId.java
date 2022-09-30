package dev.library.book.domain;

import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import dev.library.user.domain.UserId;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

@Embeddable
@ToString
@NoArgsConstructor
public class BookId implements Serializable {

    @Column(name = "book_id")
    private String id;

    public BookId(String id) {this.id = id;}

    public String getId() { return id; }

    public static BookId of(String id) { return new BookId(id); }

    public String createBarcodeAndReturn(){
        if (this.id == null){
            return null;
        } else {

            String newId = this.id;

            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, newId);
            generator.getParameters().setResolution(400);
            String addrs = "C:\\Users\\playdata\\Desktop\\barcode\\";
            File file = new File(addrs + newId + ".png");
            try {
                generator.save(addrs + newId + ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addrs + newId + ".png";
        }
    }

}
