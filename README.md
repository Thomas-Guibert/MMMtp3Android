# MMMtp3Android
## But du TP
Le but de ce TP est d'adapter le précédent, le TP2 sur Android Natif a une base de donnée Firebase au lieu d'une base Root local.
Pour ce faire, la correction des TP1 et TP2 ont servi de base, les appelle à la base firebase sont fait dès les fragments "FirstFragement" et "SecondFragement".

## Utilisation


Une fois lancer, l'application prend cette forme:


![image](https://user-images.githubusercontent.com/71399411/150183348-38830f29-ee66-4f9d-847b-cdc5bd1f7079.png)


On peut voir que la liste est vide car il y a un problème du chargement, il faut ajouté un element pour pouvoir la voir.
Dans ce cas la, firebase contient que un seul element:


![image](https://user-images.githubusercontent.com/71399411/150183697-fb9978e0-6928-4e9a-9496-3e8a90ed644e.png)


On cliquant sur "Add à client", un formulaire apparaît avec 3 champs, il faut alors les compléter puis clicker sur "Create" pour créer le client.


![image](https://user-images.githubusercontent.com/71399411/150183837-9eacd284-4bcd-4d52-8901-f4c7372b5e38.png)

Dans la base firestore, on peut voir que l'element a bien été ajouté


![image](https://user-images.githubusercontent.com/71399411/150182590-3dd042bb-e07c-499b-93d6-fcc82349ea2b.png)


Si cette nouvelle page, il faut enlever le clavier du téléphone pour que la liste apparaît enfin:


![image](https://user-images.githubusercontent.com/71399411/150184074-401f4cb6-7ead-4203-8402-d8cf782bdfc9.png)


Les seuls éléments sont bien affichés correctement.

Enfin il est possible de Swiper un element pour le supprimer de la liste

![image](https://user-images.githubusercontent.com/71399411/150184256-0beb75cd-5be6-4085-b199-015b9ac24c6f.png)

L'element swiper est bien supprimé de la base firestore
![image](https://user-images.githubusercontent.com/71399411/150182830-ea19daab-868d-4d11-a0d6-e08042441703.png)

