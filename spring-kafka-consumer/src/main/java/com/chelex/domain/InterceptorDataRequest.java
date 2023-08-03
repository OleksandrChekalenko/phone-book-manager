package com.chelex.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterceptorDataRequest {
    private String requestUrl;
    private Long requestTime;

    @Override
    public String toString() {
        return "Request info: "
                + "requestUrl='" + requestUrl
                + ", requestTime=" + requestTime;
    }
}
