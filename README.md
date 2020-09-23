# OpenClassrooms

This repository contains all sources to launch the EntreVoisins application.

The actual version of the application allows user to :
- Display a list of neighbours stored
- Select a neighbour as favorite
- Display a list of favorite neighbours
- Have access to all informaton about a selected neighbour
- Modify a selected neighbour 

Several unit and instrumented tests are implemented in this version :

Unit tests:

File NeighbourServiceTest.java
- Check if the list of Neighbours is initialized with success
- Check if the list of favorite Neighbours is initialized with success
- Check if a Neighbour is correctly deleted by user's action
- Check if a Neighbour is correctly deselected as Favorite by user's action
- Check is a new Neighbour is correctly added to the list of Neighbour


Instrumented tests :

File NeighbourListTest.java
- Check if the initialized list of Neighbour is correctly displayed 
- Check if displayed list is correctly updated when a Neighbour is deleted

File InfoNeighbourTest.java 
- Check if InfoNeighbourActivity activity is correctly launched
- Check if information displayed in InfoNeighbourActivity is correct
- Check if information of a selected Neighbour is correctly displayed in AddNeighbourActivity when user click on "edit" icon of InfoNeighbourActivity

File FavoriteListTest.java :
- Check if a scroll action displays the "Favorite" fragment 
- Check if a unselected Neighbour in Favorite fragment is removed from the list
- Check if all elements displayed in the Favorite list are favorite neighbours

File ModifyNeighbourTest.java :
- Check if modifications in AddNeighbourActivity are applied in InfoNeighbourActivity

Test Coverage :
- Class : 18.5% (5/27)
- Method : 10.6% (14/132)
- Line : 9.8% (43/440)
