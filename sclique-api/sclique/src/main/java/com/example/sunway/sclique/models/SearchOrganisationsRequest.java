package com.example.sunway.sclique.models;

import com.example.sunway.sclique.behaviors.PageableModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchOrganisationsRequest extends PageableModel {
    private String query;
}
