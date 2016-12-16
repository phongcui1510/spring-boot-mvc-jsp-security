package home.seminar.proof.domain.enumeration;

public enum Role {

	ADMIN("ADMIN"),
    USER1("Người quy định minh chứng"),
    USER2("Người giao việc nhập quy định minh chứng"), 
    USER3("Người nhập minh chứng"), 
    USER4("Người kiểm tra minh chứng");
    
    private String description;
    
    Role (String description) {
		this.description = description;
	}

}
