package dev.library.admin.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "admin")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminNumber;

    @Column(name = "admin_id", unique = true)
    private String adminId;

    @Column(name = "admin_pw")
    private String adminPw;

    @Column(name = "salt")
    @Setter
    private String salt;

    @Builder
    public Admin(int adminNumber,String adminId,String adminPw){
        this.adminNumber = adminNumber;
        this.adminId = adminId;
        this.adminPw = adminPw;
    }

    @Setter
    @Getter
    @ToString
    @Builder @AllArgsConstructor
    public static class Request {

        private int adminNumber;
        @NotBlank
        private String adminId;
        private String adminPw;

        public static Admin toEntity(Admin.Request request){
            return Admin.builder()
                    .adminNumber(request.getAdminNumber())
                    .adminId(request.getAdminId())
                    .adminPw(request.getAdminPw())
                    .build();
        }

    }

}
