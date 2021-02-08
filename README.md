# MOSOS-Subject-3

## Description

This repository contains an Event-B Project in which we are modeling an access control system for a Hotel.

## Subject

The key card management system of an hotel is managing the access control to rooms as well as facilities as the swimming pool, the leisure room, the pub, the restaurant the . . . The system is providing the following functionalities :
- operations for modelling the interactions with the customer as check in, check out,
billing, payment, reservation, . . .
- actions for controlling the access of rooms, leisure rooms, restaurant room, extra services . . . control of access of a given location
- recording the use of a service as leisure, swimming pool etc for billing to the customer
- managing the reservation of rooms and extra services ;
The properties of the system are for instance the following items :
- Only one card id gives access to a room at any time and is related to a customer. The right is temporary and has a starting date and a ending date.
- However, the cleaners have access to any room when the customer is not in the room ; they use a specific card id with tehir names.
- The security officer has access at any moment to any room and any locations of the hotel.
- When an alert is on for instance fire alarm, the rooms are no more locked. The system is described very simply and you may add features that are not required in
these notes ?

## Work to do

The work to do is to provide an Event-B model modelling the key card managing system.
The project should be conducted in a team of < 4 persons. Each group should write an
electronic report as a ZIP archive namesd group-x-name.zip containing :
- x is the number of the group and name is the name of one person of the group.
- a report of the analysis and explanation of the machines and the cointexts used for modelling the system : explanations of invariants, checking POs, validating each machine,
each context, detailing the safety properties, . . .
- the Rodin archive of the project
- a small program designed from the Event-B models.

## Developpers Team

- Yoann Simon
- Maxime Nicolas
- Alexis Cesaro
- Reynault Sies