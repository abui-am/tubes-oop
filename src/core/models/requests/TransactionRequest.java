package core.models.requests;

import java.util.ArrayList;

public class TransactionRequest {
    private Integer memberId = null;
    private ArrayList<TransactionDetailRequest> details = new ArrayList<>();

    public TransactionRequest(int memberId, ArrayList<TransactionDetailRequest> details) {
        this.memberId = memberId;
        this.details = details;
    }
    
    public TransactionRequest() {
    }

    public Integer getMemberId() {
        if (memberId == null) return null;
        
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public ArrayList<TransactionDetailRequest> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<TransactionDetailRequest> details) {
        this.details = details;
    }
    
    public void addDetails(TransactionDetailRequest request) {
        this.details.add(request);
    }
}

