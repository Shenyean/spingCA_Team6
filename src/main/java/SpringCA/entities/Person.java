package SpringCA.entities;

import SpringCA.Validation.ContactNumberConstraint;
import SpringCA.Validation.EmailConstraint;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
public class Person {

    @NotEmpty(message = "Firstname cannot be blank")
    private String firstName;
    private String middleName;
    @NotEmpty(message = "Lastname cannot be blank")
    private String lastName;
    private String gender;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthDate;
    private String address;

    @EmailConstraint
    private String email;

    @ContactNumberConstraint
    private String mobile;

    public Person(){}

    public Person(String firstName, String middleName, String lastName, String gender,
                  Date birthDate, String address, String email, String mobile) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

	@Override
	public String toString() {
		return firstName +" "+ middleName + "," + lastName;
	}
    
    
}
