package com.example.warsztatsamochodowy.dto;

public enum OrderStatus {
    ACCEPTED,
    CANCELED,
    WAITING_FOR_PARTS,
    IN_PROGRESS,
    DONE;
    public String getStatusName() {
        switch (this) {
            case ACCEPTED -> {
                return "przyjęte";
            }
            case CANCELED -> {
                return "anulowane";
            }
            case WAITING_FOR_PARTS -> {
                return "oczekiwanie na części";
            }
            case IN_PROGRESS -> {
                return "w trakcie";
            }
            case DONE -> {
                return "zakończone";
            }
            default -> {
                return "";
            }
        }
    }
}
