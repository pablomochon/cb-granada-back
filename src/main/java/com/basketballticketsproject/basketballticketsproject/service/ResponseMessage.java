package com.basketballticketsproject.basketballticketsproject.service;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage extends RuntimeException {

    private String message;
}
