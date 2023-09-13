package com.smartcontactmanager.entities;

import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Table(name = "SCM_USERS")
public class UserRegisterForm {

    @NotBlank
    @Size(min = 3, max = 45, message = "Should be between 3 - 45 characters")
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 20, message = "Should be between 1 - 20 characters")
    private String lastName;

    @NotBlank @Email(message = "Enter valid Email address")
    private String email;

    @NotBlank
    @Pattern(regexp="(^$|[0-9]{10})", message = "Enter valid contact number")
    private String contactNumber;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$",
            message = """
                    contains at least 8 characters and at most 20 characters.
                    contains at least one digit.
                    contains at least one upper case alphabet.
                    contains at least one lower case alphabet.
                    contains at least one special character.
                    doesn’t contain any white space.\"""")
    private String password;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$")
    private String confirmPassword;

    @NotNull
    @Past
    private LocalDate dob;

    @NotBlank
    private String gender;

    @AssertTrue(message = "Please agree to terms and conditions")
    private boolean acceptTerms;

    private MultipartFile profileImg;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isAcceptTerms() {
        return acceptTerms;
    }

    public void setAcceptTerms(boolean acceptTerms) {
        this.acceptTerms = acceptTerms;
    }

    public MultipartFile getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(MultipartFile profileImg) {
        this.profileImg = profileImg;
    }
}
