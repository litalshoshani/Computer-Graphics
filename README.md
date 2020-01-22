# Computer-Graphics
A 3D shapes generator, written in Java

In this project we created a program that generates a 3D shape. The user can move the shape, rotate it, change its size and clipp it. 
Working on this project demanded a high level of Linear Algebra understanding, in order to built and write the transformations 
efficiently.

When the program is activated, two files are uploaded:

1). cowExample.scn: a scene file, contains the 3D world. a scn file contains:

n // n is the number of vertices (integer)

 v0x v0y v0z // vertex coordinates (real numbers)
      
 …
 
v(n-1)x v(n-1)y v(n-1)z

m // m is the number of polygons (integer)

e01 e02 // vertex numbers (integers)

…

e(m-1)1 e(m-1)2

2). cowExample.viw: contains the viewing parameters. a viw file contains:

_Position_ Px Py Pz // coordinates of camera (real numbers)

_LookAt_ Lx Ly Lz // coordinates of the point the camera looks at (real numbers)

_Up_ Vx Vy Vz // a vector representing the up direction of the camera (real numbers)

_Window_ l r b t // left, right, bottom, and top boundaries of the window (real numbers)

_Viewport_ vw vh // width and height of viewport in pixels (integers)

The program opens a window in the size of (vw+40, vh+40), and represents a 2D world in an inner window in the size (vw, vh). 
The inner window is essential for clipping: when a 3D shape is inside the outer window, its edges are being cut out (clipping).


The program reacts to the next keyboards keys:

C: Toggle Clip on/off

R - Resets the view and the world to the original position

L - Load a new scene/view file according to the user selection  (the file type)

X - Sets the X axis as the rotation axis

Y - Sets the Y axis as the rotation axis

Z - Sets the Z axis as the rotation axis

Q - Quit

The inner window is divided into 9 smaller cubes (3x3), when the division is not shown in any way to the user, and only used to define 
which transformation is being executed, based on the start point, by clicking on the mouse.


The Transformations:

Translate : when the start point is the center cube, dragging the mouse will cause the world to move in the direction of the dragging.

Scale : when clicking on one of the cubes on the sides, above or below the center cube - dragging the mouse towards the center will 
      cause a “zoom out” (meaning - the world will become smaller). dragging the mouse towards the edges will cause a “zoom in” 
      (meaning - the world will become bigger).

Rotate : when clicking on one of the corners cubes, dragging the mouse will cause a rotation of the world in the direction of the 
        dragging. The rotation axis will be the axis chosen by the user (according to the clicked key. The default axis is Z). 
