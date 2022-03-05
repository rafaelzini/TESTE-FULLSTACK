package com.zini.locadora.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseFilter {

    private LocalDate startDate;
    private LocalDate endDate;
    private String search;
    private Boolean active;
    private Long page;
    private Long size;

}
