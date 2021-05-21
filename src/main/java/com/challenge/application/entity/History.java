package com.challenge.application.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "history")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
