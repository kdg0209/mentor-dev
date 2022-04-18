package com.intw.mentorapi.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ApiResponse {

    private HttpStatus status = HttpStatus.OK;
    private Object result;

    public ApiResponse() {}

    public ApiResponse(HttpStatus status, Object result) {
        this.status = status;
        this.result = result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
