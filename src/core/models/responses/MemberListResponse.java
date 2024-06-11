/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.responses;

/**
 *
 * @author wi2nu
 */
public class MemberListResponse {
    private int id;
    private String memberNumber;
    private String joinDate;
    private int point;
    private String nik;
    private String name;
    private String birthPlace;
    private String gender;
    private String address;
    
    public MemberListResponse() {
    }
    
    public int getId() {
        return id;
    }
    
    public String getMemberNumber() {
        return memberNumber;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public int getPoint() {
        return point;
    }

    
    public String getNik(){
        return nik;
    }
    
    public String getName(){
        return name;
    }
    
    public String getBirthPlace(){
        return birthPlace;
    }
    
    public String getGender(){
        return gender;
    }
    
    public String getAddress(){
        return address;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public void setPoint(int point) {
        this.point = point;
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
