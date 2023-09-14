package br.com.devstoblu.scheduEase.enums;

public enum EmployeeRole {

    HairStylist("HairStylist"),
    Manicure("Manicure");

    private String role = "";

    EmployeeRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return  this.role;
    }
}
