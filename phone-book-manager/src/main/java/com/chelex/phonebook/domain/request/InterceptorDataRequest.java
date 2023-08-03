package com.chelex.phonebook.domain.request;

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
}
