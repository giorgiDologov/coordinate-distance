# coordinate-distance
streaming millions of 2byte shorts, getting the seeked ones with custom PriorityQueue

We have a couple of millions of 2 byte short-s in a binary file, they are 2 dimension coordinates. We would like to
get the x closest to coordinate (x1,x2), and the 20 farest from (y1,y2) in 2D euclidean space.
1.: stream in the data by 2 byte bytearrays
2.: 1 byte[2] array become a short, 2 of them become a Point object
(Point object have a method to count euclidean method)
3.: create custom PriorityQueue with the given size (how many coordinates we want) and right priorities (by euclidean dist.)
(the priority comparator needed to be overriden in the class where we stream the data in, because we need 2 of them)
4.: put every points in both PriorityQueues, they will fall out by the rules of the current PriorityQueue
