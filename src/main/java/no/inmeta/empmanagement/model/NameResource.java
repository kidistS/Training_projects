package no.inmeta.empmanagement.model;

public class NameResource {

    private Long id;
    private String firstName;

    public NameResource(){

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){

        return this.id;
    }
}
