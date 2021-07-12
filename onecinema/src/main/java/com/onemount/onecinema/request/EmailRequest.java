package com.onemount.onecinema.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class EmailRequest {
    private String filmTitle;

    private String cinema;

    private String date;

    private String mobile;
    
    private String email;

    private String seats;
}
