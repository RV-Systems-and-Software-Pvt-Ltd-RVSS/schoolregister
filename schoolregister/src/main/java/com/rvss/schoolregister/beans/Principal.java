package com.rvss.schoolregister.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import com.rvss.schoolregister.beans.Phones;

@Data
@ToString
@AllArgsConstructor
public class Principal {
    private String name;
    private String email;
    private String years_exp;
    private String salutation;
    private Phones phones;
}
