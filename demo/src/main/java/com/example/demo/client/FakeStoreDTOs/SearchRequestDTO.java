package com.example.demo.client.FakeStoreDTOs;

import com.example.demo.entity.SortParam;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchRequestDTO {
    private String query;
    private int pageSize;
    private int pageNumber;

    private List<SortParam> sortParamList=new ArrayList<>();

}
