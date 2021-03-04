package com.springboot.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String password;

    @Column
    private int age;

    @Column
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
                    joinColumns = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {}

    public User(Long id, String firstname, String lastname, String password, int age, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.age = age;
        this.email = email;
    }

    public User(String firstname, String lastname, String password, int age, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.age = age;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int result = 17;
        long longId = Double.doubleToLongBits(getId());
        long longName = Double.doubleToLongBits(Double.parseDouble(getFirstname()+getLastname()));
        long longPass = Double.doubleToLongBits(Double.parseDouble(getPassword()));
        long longEmail = Double.doubleToLongBits(Double.parseDouble(getEmail()));
        result = 17 * result + (int) longId;
        result = 17 * result + (int) longName;
        result = 17 * result + (int) longEmail;
        result = 17 * result + (int) longPass;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (getClass() != obj.getClass() || obj==null) {return false;}

        User user = (User) obj;
        return (user.getId() == this.getId() && user.getFirstname()==((User) obj).getFirstname() &&
                user.getAge() == this.getAge() && user.getEmail() == this.getEmail()) &&
                user.getLastname() == this.getLastname() && user.getPassword() == this.getPassword();
    }

    @Override
    public String toString() {
        return "User " + id + ", name: " + getFirstname() + " " + getLastname() +
                ", age: " + getAge() + ", Email: " + getEmail()+", password: " + getPassword();
    }
}
