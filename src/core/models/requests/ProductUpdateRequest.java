package core.models.requests;

public class ProductUpdateRequest extends ProductRequest {
    private int id;

    public ProductUpdateRequest(int id, String name, int price, int stock) {
        super(name, price, stock);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
