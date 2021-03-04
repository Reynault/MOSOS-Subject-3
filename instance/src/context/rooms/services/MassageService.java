package context.rooms.services;

import context.extra.Extra;
import context.rooms.ServiceRoom;

public class MassageService extends ServiceRoom {
    public MassageService() {
        super("Massage");
        this.extras.add(new Extra("head", 10));
        this.extras.add(new Extra("full body", 30));
    }

}
