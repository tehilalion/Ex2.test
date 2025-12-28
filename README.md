# Ex2.test
This project is a Java-based framework for handling 2D maps as raster matrices (grids of pixels). It supports everything from basic geometric drawing and image scaling to complex pathfinding algorithms like BFS. It's designed to treat a 2D array not just as data, but as a navigable space or a digital canvas.
Some features include Map manipulation which allows you to rescale the map,draw circles,rectangles and lines. You can also check if two maps are equal.
Other algorithms include fill which aloows you to fill a whole space acting as a paint bucket tool, shortest path which uses the BFS algorithm to find the most efficient way.
There's also a GUI class which lets you manifest the fuctions visualy implementing an StdDraw class.
Interfaces: Map2D and Pixel2D define the behavior for the map and its coordinates.
Implementations: Map and Index2D provide the actual logic for storing data and calculating distances.
🎮 GUI Controls & Keyboard Shortcuts-
s / e: Set the Start and End points for pathfinding.
0: Run the shortest path algorithm based on your set points.
p: "Point" mode to draw obstacles manually.
f: "Fill" mode to trigger the paint-bucket tool.
l / r / c: Draw Lines, Rectangles, or Circles (requires two mouse clicks).
a / d: Save the current map to map.txt or load it back.

The picture bellow shows an example of an StdDraw implemention of these fuctions:
show the path i did manually from start to end-
<img width="515" height="534" alt="צילום מסך 2025-12-28 200011" src="https://github.com/user-attachments/assets/abcd7628-728a-4bee-b810-e6b54daca3f4" />
implements all functions-

<img width="686" height="756" alt="צילום מסך 2025-12-28 200537" src="https://github.com/user-attachments/assets/cea237a3-bb9b-45cd-afee-0c2b549739e8" />

