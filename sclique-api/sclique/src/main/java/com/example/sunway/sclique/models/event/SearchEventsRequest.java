package com.example.sunway.sclique.models.event;

import com.example.sunway.sclique.behaviors.PageableModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchEventsRequest extends PageableModel {
    private String query;
}
