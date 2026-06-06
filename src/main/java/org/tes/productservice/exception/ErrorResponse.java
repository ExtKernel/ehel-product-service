package org.tes.productservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
