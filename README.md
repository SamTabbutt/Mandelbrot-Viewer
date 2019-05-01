# Mandelbrot-Viewer
This is an in-progress project I am working on to visualize and interact with complex dynamical systems

Welcome to the Mandelbrot Set Explorer
The mandelbrot set is a fractal set which displays visually the long-term behavior of each point (c)
on the complex plane under iterations of the function f(x)=x^2+c with the seed of the function x0=0
if a point is colored black, then it remains bound for n iterations of the function. All other colors
represent different slopes of unboundedness.

This program has the ability to zoom into any point with magnification on the order of 10^10.
Once you get deep enough, the java capability of distinguishing between points is lost, and you get
distorted images.

To zoom, click and drag the mouse to create the new frame you would like to view. The initial click
will set the upper-left corner of the new zoom frame. Drag down and to the right to make your frame.
The frame will auto-adjust to keep the image display to have a real-to-imaginary aspect ratio of 1:1.
Once you dive deeper, you may see less interesting activity. At this point you can turn the iteration
setting up (which is available through a chooser displayed on the applet)

In its current state, this program is designed to explore the Mandelbrot
set under rather strict operating conditions
The program has initialized to display the set under 100 iterations in the complex
plane from -2 through 1 on the real axis and -1.5 to 1.5 on the imaginary axis
to zoom, create a zoom frame by clicking and dragging down and to the right.
At a certain point, you may find that the detail is lost, at which point 
you can increase the iteration to enhance the definition in a small frame
Enjoy exploring!
