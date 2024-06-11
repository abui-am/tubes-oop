package core.models.responses;

public class DashboardResponse {
    private int productCount;
    private int memberCount;
    private int transactionCount;
    private int revenue;
    private int employeeCount;

    public String getProductCount() {
        return String.valueOf(productCount);
    }

    public String getMemberCount() {
        return String.valueOf(memberCount);
    }

    public String getTransactionCount() {
        return String.valueOf(transactionCount);
    }

    public String getRevenue() {
        return String.valueOf(revenue);
    }

    public String getEmployeeCount() {
        return String.valueOf(employeeCount);
    }
    
    
}
