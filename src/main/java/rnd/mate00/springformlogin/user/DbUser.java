package rnd.mate00.springformlogin.user;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "DBUSER")
@Data
public class DbUser {

    @Id
    @GeneratedValue
    private int userId;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private boolean isEnabled;
}
