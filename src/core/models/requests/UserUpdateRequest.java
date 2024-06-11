package core.models.requests;

public class UserUpdateRequest extends UserRequest {
    
    private int id;
    
    public UserUpdateRequest(int id, String name, String email, int roleId, String password) {
        super(name, email, roleId, password);
        this.id = id;
    }
    
    public UserUpdateRequest(int id, String name, String email, int roleId) {
        super(name, email, roleId);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 
}
