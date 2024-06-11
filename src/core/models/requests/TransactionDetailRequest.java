package core.models.requests;


public class TransactionDetailRequest {
    private int productId;
    private int qty;

    public TransactionDetailRequest(int productId, int qty) {
        this.productId = productId;
        this.qty = qty;
    }

}
