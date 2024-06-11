/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.requests;

/**
 *
 * @author wi2nu
 */
public class MemberRequest {
    private String nik;
    private String name;
    private String birthPlace;
    private String gender;
    private String address;

    public MemberRequest(String nik, String name, String birthPlace, String gender, String address) {
        this.nik = nik;
        this.name = name;
        this.birthPlace = birthPlace;
        this.gender = gender;
        this.address = address;
    }

    public String getNik() {
        return nik;
    }

    public String getName() {
        return name;
    }

    public String getBirthPlace() {
        return birthPlace;
    }
    
    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
