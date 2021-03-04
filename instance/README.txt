--------------------------- Propriétés -----------------------------------

Version de java : 11
Les sources sont sous instance/src.
Le programme a été developpé avec le logiciel Intellij IDEA.


---------------------- Lancement du programme ----------------------------

>cd instance/
>java -jar instance.jar


------------------------ Contenu du programme ----------------------------

Il y a 3 types d'entités : Les clients, les officiers, les nettoyeurs.
Chacun ont une carte avec un id spécifique (1 pour les officiers, 2 pour les nettoyeurs, >=3 pour les clients).

Les salles sont séparées en 2 catégories : 
    salle de repos (chambre) et salle de service (restaurant, salon de massage, etc).
    De plus chaque salle de service propose différentes action appellées extra.

Il y 5 machines. Chacunes héritent de la précédente et y ajoute des fonctionnalités.
PayementBilling <-- CheckInOut <-- AddExtra <-- CleanerMachine <-- AlarmMachine

où les fonctionnalités sont pour : 
    -PaymentBilling : 
        > Payment() : Pour payer une reservation
        > Billing() : Pour payer les extras consommés durant le séjour.
    
    -CheckInOut :
        >checkIn() : Pour qu'un client puisse entrer dans une salle.
        >checkOut() : Pour que n'importe qui puisse sortir d'une salle.
        >checkInOfficer() : Car les officiers ont accès à toutes les salles.
        >Close() : Pour fermer la porte d'une salle.
        
        Rq : CheckInCleaner : Bouger dans CleanerMachine pour éviter une forte redondance.
        
    -AddExtra :
        >addExtra() : Ajouter un extra à un client.
    
    -CleanerMachine :
        >CheckInCleaner() : Pour qu'un nettoyeur aille dans une pièce à nettoyé.
        >RequestCleaning() : Quand un officier demande le nettoyage d'une salle.
        >RequestCleaningClient() : Quand un client demande le nettoyage d'une salle.
        >finishToClean(): Quand une salle a fini d'être nettoyée
    
    -AlarmMachine :
        >SetAlarmOn() : Pour activer l'alarme.
        >SetAlarmOff() : Pour désactiver l'alarme.

Il y a 3 singleton, permettant de faire les tests :
    >CounterIdSingleton : Pour générer les ids des cartes des clients.
    >EntitySingleton : Qui contient la liste des clients, des officiers et des nettoyeurs.
    >RoomSingleton : Qui contient l'ensemble des salles, que ça soit des chambres ou non.

Enfin, il y a la class Main qui se charge d'appeler les différentes actions.
Il y a 13 actions, chacune est appelé au moins une fois toutes les 13 actions.
L'ordre des actions est séléctionné aléatoirement.