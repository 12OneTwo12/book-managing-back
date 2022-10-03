package dev.library.user.domain;

import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@ToString
public class UserId implements Serializable {

    @Column(name = "user_id")
    private String id;

    protected UserId() {}

    public UserId(String id) { // TODO: Q1. why not protected?
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(id, userId.id);
    }

    public static UserId of(String id) { return new UserId(id); }

    public String createBarcodeAndReturn(){
        if (this.id == null){
            return null;
        } else {
            String newId = this.id;

            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, newId);
            generator.getParameters().setResolution(400);
            String addrs = "C:\\Users\\J\\Desktop\\barcode\\user\\";
            File file = new File(addrs + newId + ".png");
            try {
                generator.save(addrs + newId + ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addrs + newId + ".png";
        }
    }

    public String returnBarcode(){
        if (this.id == null){
            return null;
        } else {

            String newId = this.id;
            String addrs = "C:\\Users\\J\\Desktop\\barcode\\user\\";
            File file = new File(addrs + newId + ".png");

            if (!file.exists()) {

                BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, newId);
                generator.getParameters().setResolution(400);
                try {
                    generator.save(addrs + newId + ".png");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            return addrs + newId + ".png";

        }
    }
}
