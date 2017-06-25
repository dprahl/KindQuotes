package io.zipcoder.kindquotes.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Quote {

    @Id
    @GeneratedValue
    @Column(name="QUOTE_ID")
    private Long id;

    @Column(name="MESSAGE")
    private String message;

    public Quote(){}

    public Quote(String msg){
        this.message = msg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
