package com.example.codingchallenge.config;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.util.UUID;

public class UUIDAdapter extends XmlAdapter<String, UUID> {
    /* These methods are to handle JSON parsing errors */

    @Override
    public UUID unmarshal(String v) throws Exception {
        return UUID.fromString(v);
    }

    @Override
    public String marshal(UUID v) throws Exception {
        return v.toString();
    }
}