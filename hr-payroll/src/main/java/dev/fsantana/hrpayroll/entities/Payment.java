package dev.fsantana.hrpayroll.entities;

import java.io.Serializable;


public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private Double dailyIcome;
    private Integer days;

    public Payment() {
    }

    public Payment(String nome, Integer days, Double dailyIcome) {
        this.nome = nome;
        this.days = days;
        this.dailyIcome = dailyIcome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getDailyIcome() {
        return dailyIcome;
    }

    public void setDailyIcome(Double dailyIcome) {
        this.dailyIcome = dailyIcome;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Double getTotal() {
        return dailyIcome * days;
    }


}
