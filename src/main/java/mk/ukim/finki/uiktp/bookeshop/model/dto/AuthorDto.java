package mk.ukim.finki.uiktp.bookeshop.model.dto;

import lombok.Data;

@Data
public class AuthorDto {
    private String name;
    private String surname;
    private String birthYear;

    public AuthorDto(){

    }

    public AuthorDto(String name, String surname, String birthYear) {
        this.name = name;
        this.surname = surname;
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }
}
