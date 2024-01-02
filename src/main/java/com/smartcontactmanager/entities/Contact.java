package com.smartcontactmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "SCM_CONTACTS")
public class Contact extends BaseEntity{

    @Id  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contacts_seq")
    @SequenceGenerator(name = "contacts_seq", sequenceName = "CONTACTS_SEQ", allocationSize = 1)
    @Column(name = "contact_id")
    private int id;

    @NotBlank
    @Size(min = 3, max = 40, message = "Should be between 3 - 40 characters")
    private String name;

    @NotBlank @Email(message = "Enter valid Email address")
    private String email;

    @NotBlank
    @Pattern(regexp="(^$|[0-9]{10})", message = "Enter valid contact number")
    private String phone;

    @NotBlank(message = "Please select relationship")
    private String relationship;

    private String profileImg="default.png";

    private String description;

    private boolean isFavorite=false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!name.equals("")){
            this.name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = ((description.equals("") || description==null) ? "No description" : description);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
