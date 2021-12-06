#DrawIO
##DrawIO est une petite application java
Elle permet  de dessiner a la souris avec plusieur effet possible.<br>
On peut aussi revenir en arrière avec ctrl+Z.<br>
effacer le tout ou commencer un nouveau tracer.<br>
Et changer l'epaisseur du trait.<br>
En bonus le trait varie de couleur.<br>
##Control
* Dessiner : Move mouse
* Effacer tout : R
* Interompre le tracer : D
* Changer d'effet : E
* Passer en mode ligne : L
* Dessiner une ligne : right click (debut + fin)
* Commencer une nouvelle ligne : left click
* Revenir en arrière : Ctrl+Z
* Changer l'épaisseur du trait : Wheel Mouse (+ Ctrl pour plus de precision ou Shift pour moins)
##Effet
* Line : Dessine une ligne a main levée suivant la souris.
* Laser : Crée des trait qui s'atténue a plus ou moins 45° du trait de base.
* Star : Place N point de brillance alèatoire avec un ecartMax de N par rapport a la souris. (N = epaisseur du trait)
* Oval : Dessine un cercle de taille 10N et d'epaisseur N a coté de la souris. (N = epaisseur du trait)
* Bubble : Dessine N cercle en utilisant l'effet Oval. avec un ecartMax de 100N par rapport a la souris. (N = epaisseur du trait)
* Template : Dessine une image present dans le dossier data en la teintant de la couleur du trait (En cours)
