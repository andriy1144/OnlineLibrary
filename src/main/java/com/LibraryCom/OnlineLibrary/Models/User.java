package com.LibraryCom.OnlineLibrary.Models;

import com.LibraryCom.OnlineLibrary.Models.ResponcesEntities.BookResponce;
import com.LibraryCom.OnlineLibrary.Models.ResponcesEntities.LibraryResponse;
import com.LibraryCom.OnlineLibrary.Models.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "User")
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private Long phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "isActive")
    private boolean isActive;

    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roleSet = new HashSet<>();

    //Taken List
    @Column(name = "takenBooks")
    @OneToMany(targetEntity = Book.class,
            mappedBy = "userTaker",
            cascade = {
            CascadeType.REFRESH
            },
    fetch = FetchType.EAGER)
    private Set<Book> takenBooks = new HashSet<>();

    public void addBookToInventory(Book book){
        takenBooks.add(book);
    }

    public void removeBook(Book book){
        this.takenBooks.remove(book);
    }
    //Library Responses relation
    @OneToOne(mappedBy = "user",cascade = {CascadeType.REMOVE,CascadeType.ALL})
    private LibraryResponse libraryResponse;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER,mappedBy = "user")
    private Set<BookResponce> bookResponces = new HashSet<>();

    @Column(name = "dateOfCreating")
    private LocalDateTime dateOfCreating;

    @PrePersist
    private void init(){
        dateOfCreating = LocalDateTime.now();
    }
    //User Details implementation
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleSet;
    }

    @Override
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
        return isActive;
    }
}
