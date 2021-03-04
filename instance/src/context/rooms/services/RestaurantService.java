package context.rooms.services;

import context.extra.Extra;
import context.rooms.ServiceRoom;

public class RestaurantService extends ServiceRoom {
    public RestaurantService(){
        super("Restaurant");
        this.extras.add(new Extra("Champagne", 100));
        this.extras.add(new Extra("Menu du chef", 50));
    }
}
