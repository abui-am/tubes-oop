package core.models.requests;

public class MemberUpdateRequest extends MemberRequest {
    
    private int id;
    
    public MemberUpdateRequest(int id, String nik, String name, String birthPlace, String gender, String address) {
        super(nik, name, birthPlace, gender, address);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 
}
